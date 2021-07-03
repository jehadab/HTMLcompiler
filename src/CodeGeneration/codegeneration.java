package CodeGeneration;

import SymboleTable.Scope;
import models.nodes.MustachNode;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class codegeneration {
    public String generatedfile = "";
    public static String cpapp_value = "";

    public int mustachesNumber = 1;
    private StringBuilder sb;
    String addToRender = "\n";
    public static Stack<String> addToFor = new Stack<String>();
    private int switchNumber = 1;
    public int for_number = 0;

    public void created_generated_file(String htmlfile_path, String generatedfile_path) throws IOException {
        File source = new File(htmlfile_path);
        File dest = new File(generatedfile_path);

        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(dest);

        try {

            int n;

            // read() function to read the
            // byte of data
            while ((n = in.read()) != -1) {
                // write() function to write
                // the byte of data
                out.write(n);
            }
        } finally {
            if (in != null) {

                // close() function to close the
                // stream
                in.close();
            }
            // close() function to close
            // the stream
            if (out != null) {
                out.close();
            }
        }
//        generateIdToElements();
//        System.out.println("File Copied");
    }

    public void generateIdToElements() {
        Path sourcePath = Paths.get(".\\index.html");
        Path destPath = Paths.get(".\\index.html");
        Charset charset = StandardCharsets.UTF_8;


        try {

            String content = new String(Files.readAllBytes(sourcePath), charset);
            Pattern elementPattern = Pattern.compile("<(\\w+)\\s*(?:cp-\\w*\\s*=\"\\b[^\"]*\\s*)*(?![^>]*\\bid\\s*=)[^>]*(>)");
            Matcher matcher = elementPattern.matcher(content);

            while (matcher.find()) {
                StringBuilder sb = new StringBuilder(matcher.group(0));
                String elementID = matcher.group(1) + sb.hashCode();
                if (matcher.group(1).equals("script")) {
                } else if (sb.charAt(sb.length() - 2) != '/') {
//                    System.out.println(sb.charAt(sb.length()));

                    sb = sb.insert(sb.length() - 1, " id = \"" + elementID + "\" ");
                } else if (sb.charAt(sb.length() - 2) == '/') {
//                    System.out.println(sb.charAt(sb.length() - 1));
                    sb = sb.insert(sb.length() - 2, " id = \"" + elementID + "\" ");

                }

                content = content.replaceFirst("\\Q"+matcher.group(0)+"\\E", sb.toString());
            }
            Files.write(destPath, content.getBytes(charset));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write_on_file(String text, String filename) {

        try {
            FileWriter fstream = new FileWriter(generatedfile, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.err.println("Error while writing to file: " +
                    e.getMessage());
        }

    }

    public void code_generation_cpmodel(String id, String cp_modelvalue) {

        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" document.getElementById('" + id + "').addEventListener(\"input\", function(event){" + "\n", generatedfile);
        write_on_file("     " + cpapp_value + "." + cp_modelvalue + "= event.target.value;" + "\n",generatedfile);
        write_on_file(" });" + "\n",generatedfile);
        write_on_file("     " + cpapp_value + "." + cp_modelvalue + "= document.getElementById('" + id + "').value;" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);

    }

    public void code_generation_click(String id, String exp) {

        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" document.getElementById('" + id + "').addEventListener(\"click\", function(event){" + "\n", generatedfile);
        write_on_file("     " + exp + ";\n",generatedfile);
        write_on_file(" });" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);

    }

    public void code_generation_doubleclick(String id, String exp) {

        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" document.getElementById('" + id + "').addEventListener(\"dblclick\", function(event){" + "\n", generatedfile);
        write_on_file("     " + exp + ";\n",generatedfile);
        write_on_file(" });" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);

    }

    public void code_generation_static_cpshow()
    {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" function cpShowStaticFunction(id, conditionResult){" + "\n", generatedfile);
        write_on_file("     if(conditionResult)" + "\n", generatedfile);
        write_on_file("         document.getElementById(id).hidden = false;" + "\n", generatedfile);
        write_on_file("     else" + "\n", generatedfile);
        write_on_file("         document.getElementById(id).hidden = true;" + "\n", generatedfile);
        write_on_file(" }" + "\n", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_static_cpif()
    {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" function cpIfStaticFunction(id, conditionResult){" + "\n", generatedfile);
        write_on_file("     if(conditionResult)" + "\n", generatedfile);
        write_on_file("     {" + "\n", generatedfile);
        write_on_file("         for (var i = 0; i < document.getElementById(id).children.length; i++) {" + "\n", generatedfile);
        write_on_file("             var element =  document.getElementById(id).children[i];" + "\n", generatedfile);
        write_on_file("             element.hidden = false;" + "\n", generatedfile);
        write_on_file("         }" + "\n", generatedfile);
        write_on_file("     }" + "\n", generatedfile);
        write_on_file("     else" + "\n", generatedfile);
        write_on_file("     {" + "\n", generatedfile);
        write_on_file("         for (var i = 0; i < document.getElementById(id).children.length; i++) {" + "\n", generatedfile);
        write_on_file("             var element =  document.getElementById(id).children[i];" + "\n", generatedfile);
        write_on_file("             element.hidden = true;" + "\n", generatedfile);
        write_on_file("         }" + "\n", generatedfile);
        write_on_file("     }" + "\n", generatedfile);
        write_on_file(" }" + "\n", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_static_cphide()
    {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" function cpHideStaticFunction(id, conditionResult){" + "\n", generatedfile);
        write_on_file("     if(conditionResult)" + "\n", generatedfile);
        write_on_file("         document.getElementById(id).hidden = true;" + "\n", generatedfile);
        write_on_file("     else" + "\n", generatedfile);
        write_on_file("         document.getElementById(id).hidden = false;" + "\n", generatedfile);
        write_on_file(" }" + "\n", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_static_render()
    {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" function render(){",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("     setInterval( function (){",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("         for(var i=new_elements.length - 1 ; i>=0; i--){\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("             new_elements[i].remove();\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("         }\n",generatedfile);
        write_on_file("         for(var i=mustaches.length - 1 ; i>=0; i--){\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("             mustaches[i]();\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("         }\n",generatedfile);
        write_on_file("         for(var i=renders.length - 1 ; i>=0; i--){\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("             renders[i]();\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("         }\n",generatedfile);
        write_on_file(addToRender,generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("     },1000);",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file(" }",generatedfile);
        write_on_file("render();",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_static_arrays()
    {
        write_on_file("\n",generatedfile);
        write_on_file("<script>",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("mustaches = [];\n",generatedfile);
        write_on_file("renders = [];\n",generatedfile);
        write_on_file("new_elements = [];\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("</script>",generatedfile);
    }

    public void code_generation_static_date_parse()
    {
        write_on_file("\n",generatedfile);
        write_on_file("<script>",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file(" var date_parse = function(date,format){\n",generatedfile);
        write_on_file("     if(!(date instanceof Date))return format;\n",generatedfile);
        write_on_file("     format = format.replace('yyyy',date.getFullYear());\n",generatedfile);
        write_on_file("     format = format.replace('mm',date.getMonth() + 1);\n",generatedfile);
        write_on_file("     format = format.replace('dd',date.getDate());\n",generatedfile);
        write_on_file("     format = format.replace('h',date.getHours());\n",generatedfile);
        write_on_file("     format = format.replace('m',date.getMinutes());\n",generatedfile);
        write_on_file("     format = format.replace('s',date.getSeconds());\n",generatedfile);
        write_on_file("     return format;\n",generatedfile);
        write_on_file(" }\n",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("</script>",generatedfile);
    }

    public void code_generation_cpshow(String id, String cp_showcondition) {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" cpShowStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        addToRender += "        cpShowStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n";
    }

    public void code_generation_cphide(String id, String cp_showcondition) {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" cpHideStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        addToRender += "        cpHideStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n";
    }

    public void code_generation_cpif(String id, String cp_showcondition) {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" cpIfStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        addToRender += "        cpIfStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n";
    }

    public void code_generation_static() {
        code_generation_static_arrays();
        code_generation_static_cpshow();
        code_generation_static_cphide();
        code_generation_static_cpif();
        code_generation_static_date_parse();
    }

    public void code_generation_mustache(String id,String defaultText, List<MustachNode> mustaches) {

        String str = "\\\\" + '"';
        defaultText = defaultText.replaceAll("\"",str);
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" var mustacheChange"+mustachesNumber+" = function (event) {",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("     defaultText=\""+defaultText+"\";",generatedfile);
        write_on_file("\n",generatedfile);
        for(int i=0;i<mustaches.size();i++){
       /* if(mustaches.get(i).getMethod().equals("upper"))
        {
            write_on_file("     defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+cpapp_value+"."+mustaches.get(i).getOperand()+"?.toUpperCase()"+");",generatedfile);
            write_on_file("\n",generatedfile);
        }
        else if(mustaches.get(i).getMethod().equals("lower"))
            {
                write_on_file("     defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+cpapp_value+"."+mustaches.get(i).getOperand()+"?.toLowerCase()"+");",generatedfile);
                write_on_file("\n",generatedfile);
            }
        else if(mustaches.get(i).getMethod().equals("currency"))
        {
            for (int j=0;j<mustaches.get(i).getParameter_value().size();j++) {
                String value = mustaches.get(i).getParameter_value().get(j);

                write_on_file("     defaultText= defaultText.replace(" + '"' + "{{" + mustaches.get(i).getExpressionAsString() + "}}" + '"' + "," +"'"+value+"'"+'+'+cpapp_value + "." +  mustaches.get(i).getOperand()+");", generatedfile);
                write_on_file("\n", generatedfile);


            }

        }
        else if(mustaches.get(i).getMethod().equals("date"))
        {
            mustaches.get(i).setExpressionAsString(mustaches.get(i).getExpressionAsString().replaceAll("\"",str));
            for (int j=0;j<mustaches.get(i).getParameter_value().size();j++) {
                String value = mustaches.get(i).getParameter_value().get(j);
                write_on_file("     defaultText= defaultText.replace(" + '"' + "{{" + mustaches.get(i).getExpressionAsString() + "}}" + '"' + "," +'"'+mustaches.get(i).getExpressionAsExpression().getExpressionForJS()+'"'+");", generatedfile);
                write_on_file("\n", generatedfile);
                /// i really do not know if the yarget is to store the date formatter in the variable in the cpa-pp
                write_on_file(cpapp_value+"."+mustaches.get(i).getOperand()+"="+'"'+mustaches.get(i).getParameter_value().get(j)+'"'+";",generatedfile);
                write_on_file("\n", generatedfile);
            }
        }
         else {
            write_on_file("     defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+mustaches.get(i).getExpressionAsExpression().getExpressionForJS()+");",generatedfile);
            write_on_file("\n",generatedfile);
         }*/
            write_on_file("     defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+mustaches.get(i).getExpressionAsExpression().getExpressionForJS()+");",generatedfile);
            write_on_file("\n",generatedfile);

        }
        write_on_file("     document.getElementById('"+id+"')"+"."+"innerHTML =defaultText;",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file(" };",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file(" mustacheChange"+mustachesNumber+"();\n",generatedfile);
        write_on_file(" mustaches.push(mustacheChange"+mustachesNumber+");",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        mustachesNumber++;
    }

    public void switchCodeGeneration(String elementId, String switchValue, int swithcChildCount) {
        String generatedSwitch = "\n<script>\n" +
                "   function jsSwitch"+switchNumber+"(){" +
                "       let originalElement = document.getElementById(\"" + elementId + "\");\n" +
                "       let cases = originalElement.children;\n" +
                "       let switchValue = " +cpapp_value+"."+ switchValue + ";\n" +
                "       var defaultCase;\n" +
                "       for ( let i = 0 ; i < cases.length ; i++ ){\n" +
                "           if(!(cases[i].hasAttribute(\"cp-switch-case\") || cases[i].hasAttribute(\"cp-switchDefault\"))){\n" +
                "               console.log(cases[i])" +
                "           }else{\n" +
                "               cases[i].hidden = true;\n" +
                "           }\n" +
                "           if(cases[i].hasAttribute(\"cp-switchDefault\")){\n" +
                "               defaultCase = cases[i];\n" +
                "           }\n" +
                "       }" +
                "       switch("+cpapp_value+"."+ switchValue+"){";
        StringBuilder sb = new StringBuilder(generatedSwitch);
        for (int i = 0; i < swithcChildCount; i++) {
            sb = sb.append(" case cases[" + i + "].getAttribute(\"cp-switch-case\") :\n" +
                    "   cases[" + i + "].hidden =false ;\n" +
                    "   break;");
        }
        sb = sb.append(" default :\n" +
                "  defaultCase.hidden =false ;\n" +
                "   break;");
        sb = sb.append(

                "       }\n" +
                        "}" +
                "\njsSwitch"+switchNumber+"();</script>"+"\n");
//        System.out.println(sb);
        generatedSwitch = sb.toString();
        write_on_file(generatedSwitch,generatedfile);
        addToRender += "        jsSwitch"+switchNumber+"();" + "\n";
        switchNumber++;
    }

    public void code_generation_mousemove(String elementId, String expressionForJS) {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" document.getElementById('" + elementId + "').addEventListener(\"mousemove\", function(event){" + "\n", generatedfile);
        write_on_file("     " + expressionForJS + ";\n",generatedfile);
        write_on_file(" });" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_cpforarrayloop(String id,String objectToLoopIn,String loopVariable)
    {
        String fixedid = id.replaceAll("-","_");
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" var cpfor" + fixedid +"= function(){\n",generatedfile);
        write_on_file("     let originalElement"+fixedid+" = document.getElementById('"+id+"');"+ "\n",generatedfile);
        write_on_file("     let container"+fixedid+" = originalElement"+fixedid+".parentElement;"+ "\n",generatedfile);
        write_on_file("     let tmparrayboolean" + fixedid + " = 0;"+ "\n",generatedfile);
        write_on_file("     if(Array.isArray("+objectToLoopIn+")){"+ "\n",generatedfile);
        write_on_file("         "+objectToLoopIn+".forEach (("+loopVariable+") => {"+ "\n",generatedfile);
        write_on_file("             let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n",generatedfile);
        write_on_file("             elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n",generatedfile);
        write_on_file(addToFor.peek()+ "\n",generatedfile);
        write_on_file("             elem.hidden= false;"+ "\n",generatedfile);
        write_on_file("             container"+fixedid+".appendChild(elem);"+ "\n",generatedfile);
        write_on_file("             new_elements.push(elem);"+ "\n",generatedfile);
        write_on_file("         });"+ "\n",generatedfile);
        write_on_file("      }else if(Number.isInteger("+objectToLoopIn+")){"+ "\n",generatedfile);
        write_on_file("         for (var "+loopVariable+" = 0;"+loopVariable+"<" + objectToLoopIn + ";"+loopVariable+"++) {"+ "\n",generatedfile);
        write_on_file("             let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n",generatedfile);
        write_on_file("             elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n",generatedfile);
        write_on_file(addToFor.peek()+ "\n",generatedfile);
        write_on_file("             elem.hidden= false;"+ "\n",generatedfile);
        write_on_file("             container"+fixedid+".appendChild(elem);"+ "\n",generatedfile);
        write_on_file("             new_elements.push(elem);"+ "\n",generatedfile);
        write_on_file("         }"+ "\n",generatedfile);
        write_on_file("      }else{"+ "\n",generatedfile);
        write_on_file("         for (var "+loopVariable+" in "+ objectToLoopIn +") {"+ "\n",generatedfile);
        write_on_file("             let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n",generatedfile);
        write_on_file("             elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n",generatedfile);
        write_on_file(addToFor.peek()+ "\n",generatedfile);
        write_on_file("             elem.hidden= false;"+ "\n",generatedfile);
        write_on_file("             container"+fixedid+".appendChild(elem);"+ "\n",generatedfile);
        write_on_file("             new_elements.push(elem);"+ "\n",generatedfile);
        write_on_file("         }"+ "\n",generatedfile);
        write_on_file("      }"+ "\n",generatedfile);
        write_on_file("      originalElement"+fixedid+".hidden = true;"+ "\n",generatedfile);
        write_on_file(" }"+ "\n",generatedfile);
        write_on_file(" renders.push(cpfor" + id.replaceAll("-","_") + ");"+ "\n",generatedfile);
        //write_on_file(" cpfor" + id.replaceAll("-","_") + "();"+ "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        addToFor.pop();
    }

    public void code_generation_cpforarrayloop_insidefor(String id,String objectToLoopIn,String loopVariable)
    {
        StringBuilder stringBuilder = new StringBuilder();
        String fixedid = id.replaceAll("-","_");
        stringBuilder.append("     let originalElement"+fixedid+" = document.getElementById('"+id+"');"+ "\n");
        stringBuilder.append("     let container"+fixedid+" = elem;"+ "\n");
        stringBuilder.append("     let tmparrayboolean" + fixedid + " = 0;"+ "\n");
        stringBuilder.append("     if(Array.isArray("+objectToLoopIn+")){"+ "\n");
        stringBuilder.append("         "+objectToLoopIn+".forEach (("+loopVariable+") => {"+ "\n");
        stringBuilder.append("             let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n");
        stringBuilder.append("             elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n");
        stringBuilder.append(addToFor.peek()+ "\n");
        stringBuilder.append("             elem.hidden= false;"+ "\n");
        stringBuilder.append("             container"+fixedid+".appendChild(elem);"+ "\n");
        stringBuilder.append("             new_elements.push(elem);"+ "\n");
        stringBuilder.append("         });"+ "\n");
        stringBuilder.append("      }else if(Number.isInteger("+objectToLoopIn+")){"+ "\n");
        stringBuilder.append("         for (var "+loopVariable+" = 0;"+loopVariable+"<" + objectToLoopIn + ";"+loopVariable+"++) {"+ "\n");
        stringBuilder.append("             let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n");
        stringBuilder.append("             elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n");
        stringBuilder.append(addToFor.peek()+ "\n");
        stringBuilder.append("             elem.hidden= false;"+ "\n");
        stringBuilder.append("             container"+fixedid+".appendChild(elem);"+ "\n");
        stringBuilder.append("             new_elements.push(elem);"+ "\n");
        stringBuilder.append("         }"+ "\n");
        stringBuilder.append("      }else{"+ "\n");
        stringBuilder.append("         for (var "+loopVariable+" in "+ objectToLoopIn +") {"+ "\n");
        stringBuilder.append("             let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n");
        stringBuilder.append("             elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n");
        stringBuilder.append(addToFor.peek()+ "\n");
        stringBuilder.append("             elem.hidden= false;"+ "\n");
        stringBuilder.append("             container"+fixedid+".appendChild(elem);"+ "\n");
        stringBuilder.append("             new_elements.push(elem);"+ "\n");
        stringBuilder.append("         }"+ "\n");
        stringBuilder.append("      }"+ "\n");
        stringBuilder.append("      originalElement"+fixedid+".hidden = true;"+ "\n");
        addToFor.pop();
        String add = addToFor.peek();
        add += stringBuilder.toString();
        addToFor.pop();
        addToFor.add(add);
    }

    public void code_generation_mustache_for(String elementId, String defaultText, List<MustachNode> mustaches) {
        String add = addToFor.peek();
        add += "         defaultText=\""+defaultText+"\";\n";
        for(int i=0;i<mustaches.size();i++){
            add += "         defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+mustaches.get(i).getExpressionAsExpression().getExpressionForJS()+");\n";
        }
        add += "         elem"+"."+"innerHTML =defaultText;";
        addToFor.pop();
        addToFor.add(add);
    }


    public void code_generation_cpforarrayobject(String id, String objectToLoopIn, String keyVar, String valVar) {
        String fixedid = id.replaceAll("-","_");
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" var cpfor" + fixedid +"= function(){\n",generatedfile);
        write_on_file("     let originalElement"+fixedid+" = document.getElementById('"+id+"');"+ "\n",generatedfile);
        write_on_file("     let container"+fixedid+" = originalElement"+fixedid+".parentElement;"+ "\n",generatedfile);
        write_on_file("     let tmparrayboolean" + fixedid + " = 0;"+ "\n",generatedfile);
        write_on_file("     for (var "+keyVar+" in "+ objectToLoopIn +") {"+ "\n",generatedfile);
        write_on_file("         let "+valVar+" = "+objectToLoopIn+"["+keyVar+"];"+ "\n",generatedfile);
        write_on_file("         let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n",generatedfile);
        write_on_file("         elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n",generatedfile);
        write_on_file(addToFor.peek()+ "\n",generatedfile);
        write_on_file("         elem.hidden= false;"+ "\n",generatedfile);
        write_on_file("         container"+fixedid+".appendChild(elem);"+ "\n",generatedfile);
        write_on_file("         new_elements.push(elem);"+ "\n",generatedfile);
        write_on_file("     }"+ "\n",generatedfile);
        write_on_file("     originalElement"+fixedid+".hidden = true;"+ "\n",generatedfile);
        write_on_file(" }"+ "\n",generatedfile);
        write_on_file(" renders.push(cpfor" + id.replaceAll("-","_") + ");"+ "\n",generatedfile);
        //write_on_file(" cpfor" + id.replaceAll("-","_") + "();"+ "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        addToFor.pop();
    }

    public void code_generation_cpforarrayobject_insidefor(String id, String objectToLoopIn, String keyVar, String valVar) {
        StringBuilder stringBuilder = new StringBuilder();
        String fixedid = id.replaceAll("-","_");
        stringBuilder.append("     let originalElement"+fixedid+" = document.getElementById('"+id+"');"+ "\n");
        stringBuilder.append("     let container"+fixedid+" = elem;"+ "\n");
        stringBuilder.append("     let tmparrayboolean" + fixedid + " = 0;"+ "\n");
        stringBuilder.append("         for (var "+keyVar+" in "+ objectToLoopIn +") {"+ "\n");
        stringBuilder.append("             let "+valVar+" = "+objectToLoopIn+"["+keyVar+"]"+ "\n");
        stringBuilder.append("             let elem = originalElement"+fixedid+".cloneNode(false);"+ "\n");
        stringBuilder.append("             elem.id = '"+ id +"' + (++tmparrayboolean" + fixedid + ");"+ "\n");
        stringBuilder.append(addToFor.peek()+ "\n");
        stringBuilder.append("             elem.hidden= false;"+ "\n");
        stringBuilder.append("             container"+fixedid+".appendChild(elem);"+ "\n");
        stringBuilder.append("             new_elements.push(elem);"+ "\n");
        stringBuilder.append("         }"+ "\n");
        stringBuilder.append("      originalElement"+fixedid+".hidden = true;"+ "\n");
        addToFor.pop();
        String add = addToFor.peek();
        add += stringBuilder.toString();
        addToFor.pop();
        addToFor.add(add);
    }
}