package CodeGeneration;

import models.nodes.MustachNode;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class codegeneration {
    public String generatedfile = "";
    public static String cpapp_value = "";

    public int mustachesNumber = 1;
    private StringBuilder sb;
    String addToRender = "\n";
    private int switchNumber = 1;

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
        write_on_file("         for(var i=mustaches.length - 1 ; i>=0; i--){",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("             mustaches[i]();",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("         }",generatedfile);
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
        write_on_file("mustaches = [];",generatedfile);
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
        if(mustaches.get(i).getMethod().equals("upper"))
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
                write_on_file("     defaultText= defaultText.replace(" + '"' + "{{" + mustaches.get(i).getExpressionAsString() + "}}" + '"' + "," +'"'+value+'"'+");", generatedfile);
                write_on_file("\n", generatedfile);
                /// i really do not know if the yarget is to store the date formatter in the variable in the cpa-pp
                write_on_file(cpapp_value+"."+mustaches.get(i).getOperand()+"="+'"'+mustaches.get(i).getParameter_value().get(j)+'"'+";",generatedfile);
                write_on_file("\n", generatedfile);
            }
        }
         else {
            write_on_file("     defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+mustaches.get(i).getExpressionAsExpression().getExpressionForJS()+");",generatedfile);
            write_on_file("\n",generatedfile);
         }

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

}