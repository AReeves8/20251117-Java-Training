package Java.Day2.FileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileIO {
    public static void main(String[] args) {
        
        // relative path is fine if you are within the same project - otherwise use full path
        String inputFile = "C:\\2023Jun19-Java-BSA\\Java\\Day2\\FileIO\\fileInput.txt";     // make sure to escape your backslashes on windows
        String outFile = "Java\\Day2\\FileIO\\fileOutput.txt";


        /*
         * 
         * TRY-CATCH
         *      put some "risky" code in the try block
         *      handle those errors in the catch block
         *          can only catch one at a time, but you can do multiple catch blocks
         */
        try {
            
            /*
             * FILE INPUT AND OUTPUT STREAMS
             *      have to work with streams to have your data flow between files
             */

            InputStream inputStream = new FileInputStream(inputFile);   // creates a new input stream that can read from inputFile
            OutputStream outputStream = new FileOutputStream(outFile);  // creates a new output stream that can write to outFile


            // reading in data from a file
            int byteData;           // data is read in bytes

            // loops until end of input file
            while ((byteData = inputStream.read()) != -1) {     
                System.out.println(byteData);

                // converting each int into a character
                char c = (char) byteData;
                System.out.println(c);

                // write to output file
                outputStream.write(byteData);       // will overwrite file
            }

            // always close your files!!!!
            inputStream.close();
            outputStream.close();


        } catch (IOException ioException) {
            System.out.println("Error with opening or closing files.");
            ioException.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something else went wrong!");
            e.printStackTrace();
        }   



        /*
        * 
        * CLASS LOADER and RESOURCE STREAM
        * 
        *      works similarly to FileInputStream
        *      the difference: 
        *          getResourceAsStream() grabs a static resource from your classpath - ie. within your project
        * 
        * 
        * 
        *      very useful for apps not running on your local machine
        * 
        */
        String resourcePath = "Java\\Day2\\FileIO\\fileInput.txt";  // needs to be relative path for getResourceAsStream()

        try {

            // grabbing a static resource from our project and converting it into a stream
            InputStream inputStream = FileIO.class.getClassLoader().getResourceAsStream(resourcePath);

            // checking if we found the file
            if(inputStream != null) {

                int byteData;

                // loops until end of input file
                while ((byteData = inputStream.read()) != -1) {     
                    // converting each int into a character
                    System.out.println((char) byteData);
                }
            }
            else {
                System.out.println("Reource was not found!");
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




        /*
         * 
         * TRY WITH RESOURCES
         *      still a try-catch block
         * 
         *      we pass in something that needs to be opened (like a file stream)
         *      and it will auto close it for us
         *          
         *         must implement the AutoClosable interface
         * 
         */
        try (FileInputStream fileInput = new FileInputStream(inputFile)) {
            

            int byteData;

            // loops until end of input file
            while ((byteData = fileInput.read()) != -1) {     
                // converting each int into a character
                System.out.println((char) byteData);
            }


            // no longer any need to close the input stream

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    

    

}
