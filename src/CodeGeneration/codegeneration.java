package CodeGeneration;

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
    public String cpapp_value = "";

    public int mustachesNumber = 1;
    private StringBuilder sb;

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
            Pattern elementPattern = Pattern.compile("<(\\w+)\\s*(?![^>]*\\bid\\s*=)[^>]*(>)");
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
//                if(matcher.group(0).contains("[") && matcher.group(0).contains("]")){
////                    System.out.println("found");
////                    content = content.replaceFirst("\\[","\\\\\\\\[");
////                    content = content.replaceFirst("\\]","\\\\\\\\]");
//
//                    int closebracketIndex = 0  , openbracketIndex = 0 ;
//                    StringBuilder stringBuilder = new StringBuilder(matcher.group(0));
//                    openbracketIndex =  stringBuilder.indexOf("[");
//                    closebracketIndex =  stringBuilder.indexOf("]");
//
//                    stringBuilder.insert(openbracketIndex,"\\\\");
//                    stringBuilder.insert(closebracketIndex + 2,"\\\\");
////                    stringBuilder.insert(stringBuilder.indexOf("[") - 1,"\\\\\\\\");
//                    System.out.println(stringBuilder);
//
//                    content = content.replaceFirst(stringBuilder.toString() , sb.toString());
//                    continue;
//
//                }

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
        write_on_file("     " + cpapp_value + "." + cp_modelvalue + "= event.target.value;" + "\n", generatedfile);
        write_on_file(" });" + "\n", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);

    }

    public void code_generation_static_cpshow() {
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

    public void code_generation_static_cphide() {
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

    public void code_generation_static_render() {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" function render(){", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("     setInterval( function (){", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("         for(var i=mustaches.length - 1 ; i>=0; i--){", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("             mustaches[i]();", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("         }", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("     },1000);", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file(" }", generatedfile);
        write_on_file("render();", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_static_arrays() {
        write_on_file("\n", generatedfile);
        write_on_file("<script>", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("mustaches = [];", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("</script>", generatedfile);
    }

    public void code_generation_cpshow(String id, String cp_showcondition) {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" cpShowStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_cphide(String id, String cp_showcondition) {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" cpHideStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_static() {
        code_generation_static_arrays();
        code_generation_static_cpshow();
        code_generation_static_cphide();
        code_generation_static_render();
    }

    public void code_generation_mustache(String id, String defaultText, List<String> mustaches) {
        String str = "\\\\" + '"';
        defaultText = defaultText.replaceAll("\"", str);
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" var mustacheChange" + mustachesNumber + " = function (event) {", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("     defaultText=\"" + defaultText + "\";", generatedfile);
        write_on_file("\n", generatedfile);
        for (int i = 0; i < mustaches.size(); i++) {
            write_on_file("     defaultText= defaultText.replace(" + '"' + "{{" + mustaches.get(i) + "}}" + '"' + "," + cpapp_value + "." + mustaches.get(i) + ");", generatedfile);
            write_on_file("\n", generatedfile);
        }
        write_on_file("     document.getElementById('" + id + "')" + "." + "innerHTML =defaultText;", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file(" };", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file(" mustaches.push(mustacheChange" + mustachesNumber + ");", generatedfile);
        write_on_file("\n", generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        mustachesNumber++;
    }

    public void switchCodeGeneration(String elementId, String switchValue, int swithcChildCount) {
        String generatedSwitch = "\n<script>\n" +
                "   function jsSwitch(){" +
                "       let originalElement = document.getElementById(\"" + elementId + "\");\n" +
                "       let cases = originalElement.children;\n" +
                "       let switchValue = " +cpapp_value+"."+ switchValue + "\n" +
                "       for ( let i = 0 ; i < cases.length ; i++ ){\n" +
                "       if(!(cases[i].hasAttribute(\"cp-switch-case\") || cases[i].hasAttribute(\"cp-switchDefault\"))){\n" +
                "       console.log(cases[i])" +
                "       }" +
                "       else {\n" +
                "\n" +
                "             cases[i].hidden = true;\n" +
                "\n" +
                "             }\n" +
                "       }" +
                "       switch("+cpapp_value+"."+ switchValue+"){";
        StringBuilder sb = new StringBuilder(generatedSwitch);
        for (int i = 0; i < swithcChildCount; i++) {
            sb = sb.append(" case cases[" + i + "].getAttribute(\"cp-switch-case\") :\n" +
                    "   cases[" + i + "].hidden =false ;\n" +
                    "   break;");
        }
        sb = sb.append(

                "       }\n" +
                        "}" +
                "       var launching = jsSwitch();" +
                "\n</script>");
//        System.out.println(sb);
        generatedSwitch = sb.toString();
        write_on_file(generatedSwitch,generatedfile);
    }
}