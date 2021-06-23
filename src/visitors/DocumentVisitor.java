package visitors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


import CodeGeneration.codegeneration;
import SymboleTable.Id;
import SymboleTable.Scope;
import SymboleTable.Symbole;
import SymboleTable.Tag;
import misc.HTMLParser.ArrayLoopRawContext;
import misc.HTMLParser.AttributeNodeContext;
import misc.HTMLParser.BodyContext;
import misc.HTMLParser.CommentContext;
import misc.HTMLParser.CommentNodeContext;
import misc.HTMLParser.ConditionalCommentContext;
import misc.HTMLParser.DefaultDirectiveContext;
import misc.HTMLParser.DocumentContext;
import misc.HTMLParser.ElementAttributesContext;
import misc.HTMLParser.ElementContentContext;
import misc.HTMLParser.ExpDirectiveContext;
import misc.HTMLParser.FilterExpressionContext;
import misc.HTMLParser.HalfElementContext;
import misc.HTMLParser.HeaderContext;
import misc.HTMLParser.IndexedArrayLoopContext;
import misc.HTMLParser.LoopStatementContext;
import misc.HTMLParser.MustacheContext;
import misc.HTMLParser.NodeContext;
import misc.HTMLParser.NormalElementContext;
import misc.HTMLParser.ParametrizedFilterContext;
import misc.HTMLParser.RawFilterContext;
import misc.HTMLParser.RawObjectLoopContext;
import misc.HTMLParser.ScriptContext;
import misc.HTMLParser.ScriptletOrSeaWsContext;
import misc.HTMLParser.SelfClosedElementContext;
import misc.HTMLParser.StmtDirectiveContext;
import misc.HTMLParser.StyleContext;
import misc.HTMLParser.TextNodeContext;
import models.*;
import models.directive.*;
import models.documents.*;
import models.enums.CommentType;
import models.expression.Expression;
import models.expression.ValueExpression;
import models.nodes.*;
import models.statements.*;

public class DocumentVisitor extends Visitor<AbstractASTNode> {

    protected static Stack<Boolean> switchExists;
     public static Stack<Scope> scopesStack = new Stack<>();
    public static Stack<Tag> tagsStack = new Stack<>();

    public DocumentVisitor() {
        if (switchExists == null)
            switchExists = new Stack<Boolean>();
    }

    @Override
    public AbstractASTNode visitScript(ScriptContext ctx) {
        return new ScriptNode(ctx.getText());
    }

    @Override
    public AbstractASTNode visitStyle(StyleContext ctx) {
        return new StyleNode(ctx.getText());
    }

    @Override
    public AbstractASTNode visitDocument(DocumentContext ctx) {
generation_object.generatedfile=Result_File;
        try {
            generation_object.created_generated_file("index.html", "result.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scope s = new Scope(null);
        s.setId("global");
        scopesStack.push(s);
        Tag tag = new Tag("firsttag");
        tagsStack.push(tag);
        DocumentHeader header = (DocumentHeader) visit(ctx.getChild(0));
        DocumentBody body = (DocumentBody) visit(ctx.getChild(1));
        if(cppapp_number>1)
        {
            try {
//                FileWriter fstream = new FileWriter("log.txt",true);
//                BufferedWriter out = new BufferedWriter(fstream);
//                out.write("Line Added on: " + new java.util.Date()+"\n");
//                out.close();
                FileWriter fw = new FileWriter(ErrorFile , true);
                BufferedWriter error = new BufferedWriter(fw);

                error.write("erro in line:"+""+line_number);
                error.write("  Nested cp-app is forbidden." );
                error.newLine();
                error.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
        showSymboleTable();
        print_symbole_linked_list();
        symboletable.getScopes();
//        print_linkedlist();
        return new Document(header, body);
    }

    @Override
    public AbstractASTNode visitHeader(HeaderContext ctx) {
        List<DocumentNode> headers = new ArrayList<DocumentNode>();
        DocumentHeader header = new DocumentHeader(headers);
        for (int index = 0; index < ctx.getChildCount(); index++) {
            if (ctx.getChild(index) instanceof CommentNodeContext)
                headers.add((DocumentNode) visit(ctx.getChild(index)));
            else
                headers.add(new TextNode(ctx.getChild(index).getText()));
        }

        return header;
    }

    @Override
    public AbstractASTNode visitComment(CommentContext ctx) {
        return new CommentNode(ctx.HTML_COMMENT().getText(), CommentType.Normal);
    }

    @Override
    public AbstractASTNode visitConditionalComment(ConditionalCommentContext ctx) {
        return new CommentNode(ctx.HTML_CONDITIONAL_COMMENT().getText(), CommentType.Conditional);
    }

    @Override
    public AbstractASTNode visitBody(BodyContext ctx) {
        List<DocumentNode> contents = new ArrayList<DocumentNode>();
        DocumentBody body = new DocumentBody(contents);

        for (int index = 0; index < ctx.getChildCount(); index++) {
            contents.add((DocumentNode) visit(ctx.getChild(index)));
            System.out.println(contents.get(index));
        }
        return body;
    }

    @Override
    public AbstractASTNode visitExpDirective(ExpDirectiveContext ctx) {
        Element_Directive_name=ctx.getChild(0).getText();
       if(!ctx.getChild(0).getText().equals("cp-model")) {
           ElementDirective_number++;
           if(ctx.getChild(0).getText().equals("cp-app"))
               cppapp_number++;
           line_number=ctx.start.getLine();
//           if(cppapp_number>1)
//           {
//               System.err.println("Nested cp-app is forbidden.");
//           }
           if (ElementDirective_number == 1) {
               Scope scope = new Scope(scopesStack.peek());
               scope.setId(ctx.getChild(0).getText() + "_" + scope.hashCode());
               symboletable.addScope(scope);
               scopesStack.push(scope);
           }
       }

        Element="Directive";
        AbstractASTNode value = expressionVisitor.visit(ctx.getChild(3));

//        store_symbole_scope(ctx.getChild(3).getText()  , scopesStack.peek().getParent());

        return new Directive(ctx.getChild(0).getText(), value);
    }

    @Override
    public AbstractASTNode visitStmtDirective(StmtDirectiveContext ctx) {
        if (ctx.getChild(0).getText().equals("cp-for")) {
            ElementDirective_number++;
            if(ElementDirective_number==1)
            {Scope scope = new Scope(scopesStack.peek());
            scope.setId(ctx.getChild(0).getText() + "_" + scope.hashCode());
            symboletable.addScope(scope);
            scopesStack.push(scope);}


        }
        AbstractASTNode value = visit(ctx.getChild(3));

//        store_symbole_scope(ctx.getChild(3).getText().substring(3,4) , scopesStack.peek().getParent());
        return new Directive(ctx.getChild(0).getText(), value);
    }

    @Override
    public AbstractASTNode visitLoopStatement(LoopStatementContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public AbstractASTNode visitArrayLoopRaw(ArrayLoopRawContext ctx) {

        Scope currentScope = scopesStack.peek() ;

        AbstractASTNode arrayToLoopOn = expressionVisitor.visit(ctx.getChild(2));
        String loopVariable = ctx.getChild(0).getText();
        line_number=ctx.start.getLine();
        semanticCheck.isVariableExist(loopVariable , currentScope);

        return new ArrayLoopStatement(loopVariable, (ValueExpression) arrayToLoopOn);
    }

    @Override
    public AbstractASTNode visitIndexedArrayLoop(IndexedArrayLoopContext ctx) {
        Element="Directive";
        Scope currentScope = scopesStack.peek();
        AbstractASTNode arrayToLoopOn = expressionVisitor.visit(ctx.getChild(2));
        String loopVariable = ctx.getChild(0).getText();
        String indexVariable = ctx.getChild(4).getText();
        line_number=ctx.start.getLine();
        semanticCheck.isVariableExist(loopVariable , currentScope);//check loopvar
        semanticCheck.isVariableExist(indexVariable , currentScope);// check loopindex


        return new ArrayLoopStatement(loopVariable, indexVariable, (ValueExpression) arrayToLoopOn);
    }

    @Override
    public AbstractASTNode visitDefaultDirective(DefaultDirectiveContext ctx) {

        return new Directive(ctx.getChild(0).getText());
    }

    @Override
    public AbstractASTNode visitScriptletOrSeaWs(ScriptletOrSeaWsContext ctx) {
        return new TextNode(ctx.getText());
    }

    @Override
    public AbstractASTNode visitRawObjectLoop(RawObjectLoopContext ctx) {
        Scope currentScope = scopesStack.peek();
        AbstractASTNode objectToLoopOn = expressionVisitor.visit(ctx.getChild(4));
        String KeyVariable = ctx.getChild(0).getText();
        String valueVariable = ctx.getChild(2).getText();
        line_number=ctx.start.getLine();
        semanticCheck.isVariableExist(KeyVariable, currentScope);
        semanticCheck.isVariableExist(valueVariable, currentScope);
        return new ObjectLoopStatement(KeyVariable, valueVariable, (ValueExpression) objectToLoopOn);
    }

    @Override
    public AbstractASTNode visitFilterExpression(FilterExpressionContext ctx) {
        pipElement=true;
        Expression oprand = expressionVisitor.visit(ctx.getChild(0));
        FilterStatement filter = (FilterStatement) visit(ctx.getChild(1));
        filter.setOprand(oprand);

        return filter;
    }

    @Override
    public AbstractASTNode visitRawFilter(RawFilterContext ctx) {
        AbstractASTNode filter = expressionVisitor.visit(ctx.getChild(1));
        return new FilterStatement((Expression) filter);
    }

    @Override
    public AbstractASTNode visitParametrizedFilter(ParametrizedFilterContext ctx) {
        AbstractASTNode filter = expressionVisitor.visit(ctx.getChild(1));
        List<Expression> parameters = new ArrayList<Expression>();

        for (int index = 0; index < ctx.getChild(3).getChildCount(); index += 2) {
            parameters.add(expressionVisitor.visit(ctx.getChild(3).getChild(index)));
        }
        if(filter_method.equals("upper") || filter_method.equals("lower"))
        {

            if(parameters.size()!=0)
            {
                try {
//                FileWriter fstream = new FileWriter("log.txt",true);
//                BufferedWriter out = new BufferedWriter(fstream);
//                out.write("Line Added on: " + new java.util.Date()+"\n");
//                out.close();
                    FileWriter fw = new FileWriter(ErrorFile , true);
                    BufferedWriter error = new BufferedWriter(fw);
                    line_number=ctx.start.getLine();
                    error.write("erro in line:"+""+line_number);
                    error.write("  (upper/lower) pipes should not recieve any parameter." );
                    error.newLine();
                    error.close();

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }

        return new FilterStatement((Expression) filter, parameters);
    }

    @Override
    public AbstractASTNode visitNode(NodeContext ctx) {
        return visit(ctx.getChild(0));
    }

    public List<AbstractASTNode> getContent(ElementContentContext ctx) {
        List<AbstractASTNode> contents = new ArrayList<AbstractASTNode>();
        for (int index = 0; index < ctx.getChildCount(); index++) {
            if(ctx.getChild(index) instanceof  MustacheContext)
            {
                Element="Mustach";
            }
            if (ctx.getChild(index) instanceof NodeContext || ctx.getChild(index) instanceof MustacheContext)
                contents.add(visit(ctx.getChild(index)));
            else
                contents.add(new TextNode(ctx.getChild(index).getText()));

        }

        return contents;
    }

    @Override
    public AbstractASTNode visitHalfElement(HalfElementContext ctx) {
        String tagName = ctx.getChild(1).getText();
        List<AbstractASTNode> attributes = new ArrayList<AbstractASTNode>();

        if (ctx.getChild(2) instanceof ElementAttributesContext)
            attributes = getAttributes((ElementAttributesContext) ctx.getChild(2));
        if(tagName.equals("img"))
        {
            if(attributes.size()==0)
            {
                is_src=false;

            }
            if(is_src==false)
                {
                    try {
//                FileWriter fstream = new FileWriter("log.txt",true);
//                BufferedWriter out = new BufferedWriter(fstream);
//                out.write("Line Added on: " + new java.util.Date()+"\n");
//                out.close();
                        FileWriter fw = new FileWriter(ErrorFile , true);
                        BufferedWriter error = new BufferedWriter(fw);
                        line_number=ctx.start.getLine();
                        error.write("erro in line:"+""+line_number);
                        error.write("  img tag must has src attribute." );
                        error.newLine();
                        error.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
//                    System.err.println("img tag must has src attribute.");
                }
        }
        ElementNode element = new ElementNode(tagName, attributes.toArray(new DocumentNode[attributes.size()]));
        return element;
    }

    @Override
    public AbstractASTNode visitNormalElement(NormalElementContext ctx) {
        int ElementDirective_counter = 0;
        boolean isElementDirective = false;
        ElementDirective_number = 0;
        String Directive_name = "";
        String tagName = ctx.getChild(1).getText();
        String tagClose;
        if (tagName.equals("a")) {
            a_tag = true;
        }



        if((tagName.equals("ol") || tagName.equals("ul"))&& tagsStack.peek().getTagname().equals("li"))
        {
            try {
//                FileWriter fstream = new FileWriter("log.txt",true);
//                BufferedWriter out = new BufferedWriter(fstream);
//                out.write("Line Added on: " + new java.util.Date()+"\n");
//                out.close();
                FileWriter fw = new FileWriter(ErrorFile , true);
                BufferedWriter error = new BufferedWriter(fw);
                line_number=ctx.start.getLine();
                error.write("erro in line:"+""+line_number);
                error.write("   li tag should not be outside ul/ol");
                error.newLine();
                error.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
//            System.err.println("li tag should not be outside ul/ol");
        }
        if(tagName.equals("ol")|| tagName.equals("li")|| tagName.equals("ul"))
        {

            Tag tag = new Tag(tagName);
            tagsStack.push(tag);
        }

        if (ctx.getChild(2) instanceof ElementAttributesContext) {
            tagClose = ctx.getChild(6).getText();
        } else
            tagClose = ctx.getChild(5).getText();
        if (!testName(tagName, tagClose))
            System.err.println("name does not match!");
        List<AbstractASTNode> attributes = new ArrayList<AbstractASTNode>();
        List<AbstractASTNode> contents = new ArrayList<AbstractASTNode>();

        if (ctx.getChild(2) instanceof ElementAttributesContext)
            attributes = getAttributes((ElementAttributesContext) ctx.getChild(2));

            if(a_tag==true)
            {
                if(attributes.size()==0)
                {
                    is_herf=false;
                    try {
                        FileWriter fw = new FileWriter(ErrorFile , true);
                        BufferedWriter error = new BufferedWriter(fw);
                        line_number=ctx.start.getLine();
                        error.write("erro in line:"+""+line_number);
                        error.write("   Anchor tag must has href attribute");
                        error.newLine();
                        error.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
//                    System.err.println("Anchor tag must has href attribute");
                }

            }

        if (tagName.equals("img"))
        {
            if(attributes.size()==0)
            {
                is_src=false;

            }
            if (is_src == false) {
                try {
//                FileWriter fstream = new FileWriter("log.txt",true);
//                BufferedWriter out = new BufferedWriter(fstream);
//                out.write("Line Added on: " + new java.util.Date()+"\n");
//                out.close();
                    FileWriter fw = new FileWriter(ErrorFile , true);
                    BufferedWriter error = new BufferedWriter(fw);
                    line_number=ctx.start.getLine();
                    error.write("erro in line:"+""+line_number);
                    error.write(" img tag must has src attribute." );
                    error.newLine();
                    error.close();

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
//                System.err.println(" img tag must has src attribute.");
            }

        }

        for (int i = 0; i < attributes.size(); i++) {

            if (attributes.get(i) instanceof Directive) {
                Directive_name = ((Directive) attributes.get(i)).getName();
                if (!Directive_name.equals("cp-model")) {
                    isElementDirective = true;
                    ElementDirective_counter++;
                    if(ElementDirective_counter>1)
                    {
                        try {
                            FileWriter fw = new FileWriter(ErrorFile , true);
                            BufferedWriter error = new BufferedWriter(fw);
                            line_number=ctx.start.getLine();
                            error.write("erro in line:"+""+line_number);
                            error.write("   Each html element has at most one structural attribute");
                            error.newLine();
                            error.close();

                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
//                        System.err.println("Each html element has at most one structural attribute");
                    }


                    }
                if(Directive_name.equals("cp-app"))
                {

//                        System.out.println("print the value of the cpapp"+Element_Directive_Value);
                        generation_object.cpapp_value = Element_Directive_Value;

                }

                }
            }


                boolean switchElement = false;
                for (AbstractASTNode node : attributes)
                    if (node instanceof Directive && testName(((Directive) node).getName(), "cp-switch")) {
                        switchExists.push(true);
                        switchElement = true;

                    }
                for (AbstractASTNode node : attributes)
                    if (node instanceof Directive && (testName(((Directive) node).getName(), "cp-switch-case") || testName(((Directive) node).getName(), "cp-switchDefault")))
                        if (switchExists.isEmpty())
                            System.err.println("Invalid switch");
                if (ctx.getChild(3) instanceof ElementContentContext)
                    contents = getContent((ElementContentContext) ctx.getChild(3));
                if (ctx.getChild(4) instanceof ElementContentContext)
                    contents = getContent((ElementContentContext) ctx.getChild(4));
                if (switchElement)
                    switchExists.pop();
                ElementNode element = new ElementNode(tagName, attributes.toArray(new DocumentNode[attributes.size()]), contents.toArray(new DocumentNode[contents.size()]));


                if (!scopesStack.peek().getId().equals("global") && isElementDirective) {
                    scopesStack.pop();

                }

                if (tagName.equals("ul") || tagName.equals("li") || tagName.equals("ol")) {
                    if (!tagsStack.peek().getTagname().equals("firsttag")) {
                        tagsStack.pop();


                    }
                }

                a_tag = false;
                return element;
            }

            @Override
            public AbstractASTNode visitSelfClosedElement (SelfClosedElementContext ctx){

                 String Directirv_name ="";
                String tagName = ctx.getChild(1).getText();
                List<AbstractASTNode> attributes = new ArrayList<AbstractASTNode>();
                if (ctx.getChild(2) instanceof ElementAttributesContext)
                    attributes = getAttributes((ElementAttributesContext) ctx.getChild(2));
                    for(int i=0;i<attributes.size();i++) {
                        if (attributes.get(i) instanceof Directive) {
                            Directirv_name = ((Directive) attributes.get(i)).getName();

                            if (Directirv_name.equals("cp-model")) {
                                System.out.println("number of time we get in here");
                                if(Element_id_Value.equals("noid"))
                                {
                                    Element_id_Value='"'+Element_Directive_Value+hashCode()+'"';
                                }
                                generation_object.code_generation_cpmodel(Element_id_Value, Element_Directive_Value);

                            }
                        }

                    }


                ElementNode element = new ElementNode(tagName, attributes.toArray(new DocumentNode[attributes.size()]));
                return element;
            }

            @Override
            public AbstractASTNode visitTextNode (TextNodeContext ctx){
                return new TextNode(ctx.getText());
            }

            public List<AbstractASTNode> getAttributes (ElementAttributesContext ctx){

                List<AbstractASTNode> attributes = new ArrayList<AbstractASTNode>();

                for (int index = 0; index < ctx.getChildCount(); index++) {
                    attributes.add(visit(ctx.getChild(index)));

                    if (ctx.getChild(index).getChild(0).toString().equals("id")) {

                Id id = new Id(ctx.getChild(index).getChild(2).toString());
                if(CheckidValue(id)==false)
                {symboletable.addId(id);}
                else {
                    try {
                        FileWriter fw = new FileWriter(ErrorFile , true);
                        BufferedWriter error = new BufferedWriter(fw);
                        line_number=ctx.start.getLine();
                        error.write("erro in line:"+""+line_number);
                        error.write("   id attribute must be uniqe on document level");
                        error.newLine();
                        error.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
//                    System.err.println("id attribute must be uniqe on document level");
                }
            }
        }

        if(is_herf==false && a_tag==true)
        {
            try {
                FileWriter fw = new FileWriter(ErrorFile , true);
                BufferedWriter error = new BufferedWriter(fw);
                line_number=ctx.start.getLine();
                error.write("erro in line:"+""+line_number);
                error.write("   Anchor tag must has href attribute");
                error.newLine();
                error.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
//            System.err.println("Anchor tag must has href attribute");
        }
        is_herf=false;


                return attributes;
            }

            @Override
            public AbstractASTNode visitAttributeNode (AttributeNodeContext ctx){

                String name = ctx.getChild(0).getText();

                String value = null;

                if (ctx.getChildCount() > 1)
                    value = ctx.getChild(2).getText();

                if (name.equals("src")) {
                    is_src = true;
                }
//                } else if(name.equals("src")  ) {
//                    is_src = false;
//                }
                  if(name.equals("id"))
                  {
                      get_Element_id_value(value);
                  }

                if (name.equals("href") && a_tag == true) {
                    is_herf = true;
                }

                return new AttributeNode(name, value);
            }

            @Override
            public AbstractASTNode visitMustache (MustacheContext ctx){
                MustachNode mustache;
                if (ctx.getChildCount() > 2) {
                    mustache = new MustachNode(expressionVisitor.visit(ctx.getChild(1)));


                } else
                    mustache = new MustachNode();
                return mustache;
            }

            protected boolean testName (String openTag, String closeTag){
                return openTag.compareToIgnoreCase(closeTag) == 0;
            }
            // fucntion for semantic-check Q1
            public boolean CheckidValue (Id id ){
                boolean exits = false;
                for (int i = 0; i < symboletable.getids().size(); i++) {
                    if (id.getValue().equals(symboletable.getids().get(i).getValue())) {
                        exits = true;
                    }
                }
                return exits;
            }
            public void get_Element_id_value(String id_value){
                Element_id_Value=id_value;
    }

        }


