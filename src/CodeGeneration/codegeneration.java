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
//    public void write(String text ){
//        try {
//            FileWriter myWriter = new FileWriter(generatedfile_path,true);
//            myWriter.write(text);
////                                    myWriter.printf("%s\r\n", "NEWLINE");
////                                    myWriter.write( s);   //new line
//
//
//
//
//            System.out.println(Main.BLUE_UNDERLINED+workingDirectory+"\\"+"gc"+"\\"+name+".java"+"  "+ Main.ANSI_RESET);
//
//
//
//            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//
//        }
}