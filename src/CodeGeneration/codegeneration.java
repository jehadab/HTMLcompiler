package CodeGeneration;

import java.io.*;

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

    write_on_file("\n",generatedfile);
 write_on_file("<script>"+"\n",generatedfile);
 write_on_file("document.getElementById",generatedfile);
 write_on_file("("+id+")"+"."+"addEventListener"+"("+'"'+"input"+'"'+","+"function"+
         "("+"event"+")"+"{"+"\n"+cpapp_value+"."+cp_modelvalue+"="+"event"+"."+"value"+"\n"+
         "}"+")"+";"+"</script>"

         ,generatedfile);

}

}