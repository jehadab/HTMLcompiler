package CodeGeneration;

import models.nodes.MustachNode;

import java.io.*;
import java.util.List;

public class codegeneration {
    public String generatedfile = "";
    public static String cpapp_value = "";

    public int mustachesNumber = 1;
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
        System.out.println("File Copied");
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
    }
    public void code_generation_cphide(String id, String cp_showcondition) {
        write_on_file("\n", generatedfile);
        write_on_file("<script>" + "\n", generatedfile);
        write_on_file(" cpHideStaticFunction('" + id + "'," + cp_showcondition + ");" + "\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
    }

    public void code_generation_static() {
        code_generation_static_arrays();
        code_generation_static_cpshow();
        code_generation_static_cphide();
        code_generation_static_render();
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
            write_on_file("     defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+cpapp_value+"."+mustaches.get(i).getOperand()+".toUpperCase()"+");",generatedfile);
            write_on_file("\n",generatedfile);
        }
        else if(mustaches.get(i).getMethod().equals("lower"))
            {
                write_on_file("     defaultText= defaultText.replace("+'"'+"{{"+mustaches.get(i).getExpressionAsString()+"}}"+'"'+","+cpapp_value+"."+mustaches.get(i).getOperand()+".toLowerCase()"+");",generatedfile);
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
        else if(mustaches.get(i).getMethod().equals("Date"))
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
        write_on_file(" mustaches.push(mustacheChange"+mustachesNumber+");",generatedfile);
        write_on_file("\n",generatedfile);
        write_on_file("</script>" + "\n", generatedfile);
        mustachesNumber++;
    }
}