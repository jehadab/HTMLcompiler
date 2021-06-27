package CodeGeneration;

import java.io.*;
import java.util.ArrayList;

public class codegeneration {
 public String generatedfile ="";
 public  String cpapp_value="";
    public   void  created_generated_file(String htmlfile_path, String generatedfile_path) throws IOException {
        File source = new File(htmlfile_path);
        File  dest = new File(generatedfile_path);

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
        }
        finally {
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
public void write_on_file(String text,  String filename ){

    try{
        FileWriter fstream = new FileWriter(generatedfile,true);
        BufferedWriter out = new BufferedWriter(fstream);
//        out.write(text+"\n");
        out.write(text);
        out.close();
    }catch (Exception e){
        System.err.println("Error while writing to file: " +
                e.getMessage());
    }

}
public void code_generation_cpmodel(String id , String cp_modelvalue){
    System.out.println("print the cpapp value "+cpapp_value);
    System.out.println("print hte cpmodel value "+cp_modelvalue);
    write_on_file("\n",generatedfile);
 write_on_file("<script>"+"\n",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("document.getElementById(",generatedfile);
    write_on_file(id+").value="+cpapp_value+"."+cp_modelvalue+";",generatedfile);
    write_on_file("\n",generatedfile);
 write_on_file("document.getElementById",generatedfile);
 write_on_file("("+id+")"+"."+"addEventListener"+"("+'"'+"input"+'"'+","+"function"+
         "("+"event"+")"+"{"+"\n"+cpapp_value+"."+cp_modelvalue+"="+"document.getElementById("+id+")"
                 +".value;"+"\n"+
         "}"+")"+";"+"</script>"

         ,generatedfile);

}
public void code_generation_single_mustach_in_tag(String id , String default_text, String inside_mustach)
{


    write_on_file("\n",generatedfile);
    write_on_file("<script>"+"\n",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("var myTestChanges =",generatedfile);
    write_on_file("function (event) {",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("defaultText=",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file('"'+default_text +'"'+";",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("document.getElementById("+id+")"+"."+"innerHTML = defaultText.replace("
    +'"'+"{{"+inside_mustach+"}}"+'"'+","+cpapp_value+"."+inside_mustach+");" ,generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("};",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file(" mustaches.push(myTestChanges);",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("function render(){",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("setInterval( function (){",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("for(var i=0 ; i<mustaches.length; i++){",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("mustaches[i]();",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("}",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("},1000);",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("}",generatedfile);
    write_on_file("render();",generatedfile);
    write_on_file("</script>",generatedfile);

}
public void code_generation_multi_mustach_in_tag(String id , String default_text , String inside_mustach){
write_on_file("<script>",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("var myTestChanges =function (event) {",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("defaultText="+'"'+default_text+'"'+";",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("defaultText=defaultText.replace("+'"'+"{{"+inside_mustach+"}}"+'"'+","+cpapp_value+"."+inside_mustach+");",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("};",generatedfile);
write_on_file(" mustaches.push(myTestChanges);",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("function render(){",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("setInterval( function (){",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("for(var i=0 ; i<mustaches.length; i++){",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("mustaches[i]();",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("}",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("},1000);",generatedfile);
write_on_file("\n",generatedfile);
write_on_file("}",generatedfile);
write_on_file("render();",generatedfile);
write_on_file("</script>",generatedfile);
}
public void code_generation_for_last_mustach_in_same_tag(String  id ,String default_text , String  inside_mustach){
    write_on_file("<script>",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("var myTestChanges =function (event) {",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("document.getElementById("+id+")"+"."+"innerHTML =",generatedfile);
    write_on_file("defaultText.replace(",generatedfile);
    write_on_file('"'+"{{"+inside_mustach+"}}"+'"'+","+cpapp_value+"."+inside_mustach+");",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("};",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("mustaches.push(myTestChanges);",generatedfile);
    write_on_file("function render(){",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("setInterval( function (){",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("for(var i=0 ; i<mustaches.length; i++){",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("mustaches[i]();",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("}",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("},1000);",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("}",generatedfile);
    write_on_file("render();",generatedfile);
    write_on_file("</script>",generatedfile);

}

public void  code_generation_arrayof_changes(){
    write_on_file("\n",generatedfile);
    write_on_file("<script>",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("mustaches = [];",generatedfile);
    write_on_file("\n",generatedfile);
    write_on_file("defaultText="+'"'+'"'+";",generatedfile);
    write_on_file("</script>",generatedfile);
}
public void create_default_text(ArrayList<DefaultText> defaultText , String id_value) {
    boolean last_mustach=true;
    int mustach_number = 0;
    String Default_Text = "";
    for (int i = 0; i < defaultText.size(); i++) {

        if (defaultText.get(i).type.equals("mustach")) {

            Default_Text = Default_Text + "{{" + defaultText.get(i).value + "}}";
            mustach_number++;
        } else if (defaultText.get(i).type.equals("text")) {

            Default_Text = Default_Text + defaultText.get(i).value;
        }

    }

    if (mustach_number == 1) {
        for (int i = 0; i < defaultText.size(); i++) {
            if (defaultText.get(i).type.equals("mustach")) {
                code_generation_single_mustach_in_tag(id_value, Default_Text, defaultText.get(i).value);
                break;
            }

        }
    } else if (mustach_number > 1) {
        for (int i = 0; i < defaultText.size(); i++) {
            if (defaultText.get(i).type.equals("mustach")) {
            for(int j=i+1;j<defaultText.size();j++)
            {
                last_mustach=true;
                if(defaultText.get(j).type.equals("mustach"))
                {
                    last_mustach=false;
                    code_generation_multi_mustach_in_tag(id_value,Default_Text,defaultText.get(i).value);
                    break;
                }

            }
           if(last_mustach==true)
           {
               code_generation_for_last_mustach_in_same_tag(id_value,Default_Text,defaultText.get(i).value);
           }
            }
            }
        }

    }
}
