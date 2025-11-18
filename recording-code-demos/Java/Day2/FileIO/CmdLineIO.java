package Java.Day2.FileIO;

import java.io.PrintWriter;
import java.util.Scanner;

public class CmdLineIO {
    public static void main(String[] args) {
        
        // system default is the command line
        System.out.println("Hello world!");



        /*
         * SCANNER
         *      all data has to be stored into a variable
         *          primitive type or a String ONLY
         */

        Scanner scanIn = new Scanner(System.in);    // tells the scanner to use the default system input

        // use .nextLine() to read in Strings
        System.out.print("Enter a string: ");       // print doesn't append a newline to the end of your text
        String textInput = scanIn.nextLine();
        System.out.println("You entered: " + textInput);

        // use .next<primitive data type>() to read in data of that type
        System.out.print("Enter a number: ");
        double numInput = scanIn.nextDouble();
        System.out.println("You entered: " + numInput);

        // must close your scanners!! otherwise memory leaks
        scanIn.close();



        /*
         * PRINT WRITER
         *      allows for better formatting of print statements
         *      also allows you to queue up several prints and flush them out at once
         */
        String name = "Brendan";
        int age = 22;
        double height = 173.5;

        // this is the normal method without print writer
        System.out.println(name + " is " + age + " years old and is " + height + " cm tall!");

        // the same as above can be done with PrintWriter using printf
        PrintWriter consoleOut = new PrintWriter(System.out);
        consoleOut.printf("%s is %d years old and is %.2f cm tall!\n", name, age, height);  // wildcards must match data type of variables

        consoleOut.print("This prints a normal string!");
        consoleOut.append(" This string will be added to the end of the previous one!\n");

        // need to call flush to clear your print writer queue
        consoleOut.flush();

        // can flush more than once
        consoleOut.print("This will print on the next flush");
        consoleOut.flush();

        // must close your print writers!! otherwise memory leaks and issues
        consoleOut.close();
    }
}
