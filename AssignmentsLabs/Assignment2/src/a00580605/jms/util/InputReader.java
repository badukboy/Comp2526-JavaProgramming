package a00580605.jms.util;
import java.util.Scanner;

/**
 * Class InputReader reads user input from the keyboard. This version uses
 * the Scanner class from JDK 1.5.
 * 
 * 
 * @version    2009.10.25
 * @author     Colleen Penrowley 
 */
public class InputReader
{
    private Scanner scanner;

    /**
     * Create a new InputReader to read user input.
     */
    public InputReader()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets the user's input 
     * @return the user's input as a String
     */
    public String getInput()
    {
        return scanner.nextLine();
    }
}
