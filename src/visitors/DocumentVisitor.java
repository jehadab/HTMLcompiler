package visitors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


import SymboleTable.Scope;
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
import models.expression.value.literal.StringLiteral;
import models.nodes.*;
import models.statements.*;

public class DocumentVisitor extends Visitor<AbstractASTNode> {

    protected static Stack<Boolean> switchExists;
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
        generation_object.generatedfile = Result_File;
        try {
            generation_object.created_generated_file("index.html", "result.html");
            generation_object.code_generation_static();
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
        showSymboleTable();
        print_symbole_linked_list();
        symboletable.getScopes();
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
//            System.out.println(contents.get(index));
        }
        return body;
    }

    @Override
    public AbstractASTNode visitExpDirective(ExpDirectiveContext ctx) {
        Element_Directive_name = ctx.getChild(0).getText();

        Element = "Directive";
        AbstractASTNode value = expressionVisitor.visit(ctx.getChild(3));

        return new Directive(ctx.getChild(0).getText(), value, ctx.getChild(3).getText());
    }

    @Override
    public AbstractASTNode visitStmtDirective(StmtDirectiveContext ctx) {

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

        Scope currentScope = scopesStack.peek();

        AbstractASTNode arrayToLoopOn = expressionVisitor.visit(ctx.getChild(2));
        String loopVariable = ctx.getChild(0).getText();
        line_number = ctx.start.getLine();

        return new ArrayLoopStatement(loopVariable, (ValueExpression) arrayToLoopOn);
    }

    @Override
    public AbstractASTNode visitIndexedArrayLoop(IndexedArrayLoopContext ctx) {
        Element = "Directive";
        Scope currentScope = scopesStack.peek();
        AbstractASTNode arrayToLoopOn = expressionVisitor.visit(ctx.getChild(2));
        String loopVariable = ctx.getChild(0).getText();
        String indexVariable = ctx.getChild(4).getText();
        line_number = ctx.start.getLine();

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
        line_number = ctx.start.getLine();
        return new ObjectLoopStatement(KeyVariable, valueVariable, (ValueExpression) objectToLoopOn);
    }

    @Override
    public AbstractASTNode visitFilterExpression(FilterExpressionContext ctx) {
        pipElement = true;
        Expression oprand = expressionVisitor.visit(ctx.getChild(0));
        FilterStatement filter = (FilterStatement) visit(ctx.getChild(1));
        filter.setOprand(oprand);
        filter.setOperand(ctx.getChild(0).getText());

        return filter;
    }

    @Override
    public AbstractASTNode visitRawFilter(RawFilterContext ctx) {
        AbstractASTNode filter = expressionVisitor.visit(ctx.getChild(1));
        return new FilterStatement((Expression) filter, ctx.getChild(1).getText());
    }

    @Override
    public AbstractASTNode visitParametrizedFilter(ParametrizedFilterContext ctx) {
        AbstractASTNode filter = expressionVisitor.visit(ctx.getChild(1));
        List<Expression> parameters = new ArrayList<Expression>();

        for (int index = 0; index < ctx.getChild(3).getChildCount(); index += 2) {
            parameters.add(expressionVisitor.visit(ctx.getChild(3).getChild(index)));

        }

        return new FilterStatement((Expression) filter, parameters,ctx.getChild(1).getText());
    }

    @Override
    public AbstractASTNode visitNode(NodeContext ctx) {
        return visit(ctx.getChild(0));
    }

    public List<AbstractASTNode> getContent(ElementContentContext ctx) {
        List<AbstractASTNode> contents = new ArrayList<AbstractASTNode>();
        for (int index = 0; index < ctx.getChildCount(); index++) {
            if (ctx.getChild(index) instanceof MustacheContext) {
                Element = "Mustach";
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

        boolean a_tag = false, img_tag = false;
        if (tagName.equals("a")) {
            a_tag = true;
        }
        if (tagName.equals("img")) {
            img_tag = true;
        }

        if (ctx.getChild(2) instanceof ElementAttributesContext)
            attributes = getAttributes((ElementAttributesContext) ctx.getChild(2));

        if (a_tag == true) {
            boolean is_herf = false;
            for (AbstractASTNode attr : attributes) {
                if (attr instanceof AttributeNode) {
                    AttributeNode attributeNode = (AttributeNode) attr;
                    if (attributeNode.getAttribute().equals("href")) {
                        if (!attributeNode.getValue().equals("\"\"")) {
                            is_herf = true;
                        }
                    }
                }
            }
            if (!is_herf) write_error("Anchor tag must has href attribute", ctx.start.getLine());
        }
        if (img_tag == true) {
            boolean is_src = false;
            for (AbstractASTNode attr : attributes) {
                if (attr instanceof AttributeNode) {
                    AttributeNode attributeNode = (AttributeNode) attr;
                    if (attributeNode.getAttribute().equals("src")) {
                        if (!attributeNode.getValue().equals("\"\"")) {
                            is_src = true;
                        }
                    }
                }
            }
            if (!is_src) write_error("Img tag must has src attribute", ctx.start.getLine());
        }

        ElementNode element = new ElementNode(tagName, attributes.toArray(new DocumentNode[attributes.size()]));
        return element;
    }

    @Override
    public AbstractASTNode visitNormalElement(NormalElementContext ctx) {
        boolean isElementDirective = false;
        ElementDirective_number = 0;
        String tagName = ctx.getChild(1).getText();
        String tagClose;

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

        String elementId = getElementId(attributes);

        /*----------------------------- Semantic Check ------------------------*/
        if(tagName.equals("ul") || tagName.equals("ol")) ulolNumber++;

        semanticCheck(tagName,attributes,ctx.start.getLine());
        /*----------------------------- Semantic Check ------------------------*/

        /*------------------------------ Code Generation ---------------------*/
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i) instanceof Directive) {
                Directive directive = (Directive) attributes.get(i);
                String Directive_name = directive.getName();

                if (Directive_name.equals("cp-app")) {
                    generation_object.cpapp_value = Element_Directive_Value;
                }

                if(Directive_name.equals("cp-show")){
                    generation_object.code_generation_cpshow(elementId,directive.getValueAsExpression().getExpressionForJS());
                }

                if(Directive_name.equals("cp-hide")){
                    generation_object.code_generation_cphide(elementId,directive.getValueAsExpression().getExpressionForJS());
                }

                if(Directive_name.equals("cp-if")){
                    generation_object.code_generation_cpif(elementId,directive.getValueAsExpression().getExpressionForJS());
                }

                if(Directive_name.equals("cp-model")){
                    generation_object.code_generation_cpmodel(elementId,directive.getValueAsString());
                }
            }
        }
        /*------------------------------ Code Generation ---------------------*/


        String defaultText = "";
        if (ctx.getChild(3) instanceof ElementContentContext) {
            contents = getContent((ElementContentContext) ctx.getChild(3));
            defaultText = ctx.getChild(3).getText();
        }
        if (ctx.getChild(4) instanceof ElementContentContext) {
            contents = getContent((ElementContentContext) ctx.getChild(4));
            defaultText = ctx.getChild(4).getText();
        }
        List<MustachNode> mustaches = new ArrayList<>();

        for (int i = 0; i < contents.size(); i++) {
            if(contents.get(i) instanceof MustachNode)
            {
                MustachNode mustachNode = (MustachNode) contents.get(i);

                mustaches.add(mustachNode);

            }
        }

        semanticCheckMustaches(tagName,attributes,ctx.start.getLine(),mustaches);
        boolean switchElement = false;
        for (AbstractASTNode node : attributes)
            if (node instanceof Directive && testName(((Directive) node).getName(), "cp-switch")) {
                switchExists.push(true);
                switchElement = true;

                String switchValue = ((Directive) node).getName();
                int switchCasesCount = 0 ;


                for(int i = 0; i < contents.size(); i++){
                    if(contents.get(i) instanceof ElementNode){
                        switchCasesCount++;
                    }
                }
                generation_object.switchCodeGeneration(elementId,switchValue, switchCasesCount);

            }
        for (AbstractASTNode node : attributes) {
            if (node instanceof Directive && (testName(((Directive) node).getName(), "cp-switch-case")
                    || testName(((Directive) node).getName(), "cp-switchDefault")))
            {

            }
//                if (switchExists.isEmpty())
//                    System.err.println("Invalid switch");
        }

        if(!mustaches.isEmpty())generation_object.code_generation_mustache(elementId, defaultText, mustaches);

        if (switchElement){
            switchExists.pop();

        }
        ElementNode element = new ElementNode(tagName, attributes.toArray(new DocumentNode[attributes.size()]), contents.toArray(new DocumentNode[contents.size()]));

        if(tagName.equals("ul") || tagName.equals("ol")) ulolNumber--;
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i) instanceof Directive) {
                Directive directive = (Directive) attributes.get(i);
                String Directive_name = directive.getName();

                if (Directive_name.equals("cp-app")) {
                    cppapp_number--;
                }
                if (Directive_name.equals("cp-for")) {
                    scopesStack.pop();
                }
            }
        }

        return element;
    }

    @Override
    public AbstractASTNode visitSelfClosedElement(SelfClosedElementContext ctx) {
        String Directirv_name = "";
        String tagName = ctx.getChild(1).getText();
        List<AbstractASTNode> attributes = new ArrayList<AbstractASTNode>();

        int ElementDirective_counter = 0;
        boolean a_tag = false, img_tag = false;
        if (tagName.equals("a")) {
            a_tag = true;
        }
        if (tagName.equals("img")) {
            img_tag = true;
        }

        if (ctx.getChild(2) instanceof ElementAttributesContext)
            attributes = getAttributes((ElementAttributesContext) ctx.getChild(2));

        /*----------------------------- Semantic Check ------------------------*/
        if(tagName.equals("ul") || tagName.equals("ol")) ulolNumber++;

        semanticCheck(tagName,attributes,ctx.start.getLine());
        /*----------------------------- Semantic Check ------------------------*/

        /*------------------------------ Code Generation ---------------------*/
        String elementId = getElementId(attributes);

        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i) instanceof Directive) {
                Directive directive = (Directive) attributes.get(i);
                String Directive_name = directive.getName();

                if (Directive_name.equals("cp-app")) {
                    generation_object.cpapp_value = Element_Directive_Value;
                }

                if(Directive_name.equals("cp-show")){
                    generation_object.code_generation_cpshow(elementId,directive.getValueAsString());
                }

                if(Directive_name.equals("cp-hide")){
                    generation_object.code_generation_cphide(elementId,directive.getValueAsString());
                }

                if(Directive_name.equals("cp-model")){
                    generation_object.code_generation_cpmodel(elementId,directive.getValueAsString());
                }
            }
        }
        /*------------------------------ Code Generation ---------------------*/


        ElementNode element = new ElementNode(tagName, attributes.toArray(new DocumentNode[attributes.size()]));
        return element;
    }

    @Override
    public AbstractASTNode visitTextNode(TextNodeContext ctx) {
        return new TextNode(ctx.getText());
    }

    public List<AbstractASTNode> getAttributes(ElementAttributesContext ctx) {

        List<AbstractASTNode> attributes = new ArrayList<AbstractASTNode>();

        for (int index = 0; index < ctx.getChildCount(); index++) {
            attributes.add(visit(ctx.getChild(index)));
        }

        return attributes;
    }

    @Override
    public AbstractASTNode visitAttributeNode(AttributeNodeContext ctx) {

        String name = ctx.getChild(0).getText();

        String value = null;

        if (ctx.getChildCount() > 1)
            value = ctx.getChild(2).getText();

        return new AttributeNode(name, value);
    }

    @Override
    public AbstractASTNode visitMustache(MustacheContext ctx) {
        MustachNode mustache;
        if (ctx.getChildCount() > 2) {
            mustache = new MustachNode(expressionVisitor.visit(ctx.getChild(1)),ctx.getChild(1).getText());
            if(mustache.getExpression() instanceof FilterStatement )
            {
                 mustache.setOperand(((FilterStatement)mustache.getExpression()).getOperand());
                 mustache.setMethod(((FilterStatement)mustache.getExpression()).getFilter_method());

                 if(((FilterStatement) mustache.getExpression()).getParameters() != null)
                 for(int i=0;i<((FilterStatement) mustache.getExpression()).getParameters().size();i++)
                 {
                     mustache.getParameter_value().add(((StringLiteral)((FilterStatement) mustache.getExpression()).getParameters().get(i)).getString());
                 }
            }
        } else
            mustache = new MustachNode();

        return mustache;
    }

    protected boolean testName(String openTag, String closeTag) {
        return openTag.compareToIgnoreCase(closeTag) == 0;
    }

    // fucntion for semantic-check Q1


    public void get_Element_id_value(String id_value) {
        Element_id_Value = id_value;
    }

}


