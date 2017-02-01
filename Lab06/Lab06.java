/**
 * This is a LAB assignment by Jason Ivey for Java II, a class by Prof. C. Servin.
 * The Author of this program would like to extend thanks to{
 * Valentine Bacerra, for his help in suggesting the use of the Java Stack<character>. 
 * Terry Speciher for his suggestions in processing the text.
 * Professor Servin for his detailed explanations of stacks. 
 * }
 * @author Jason Ivey
 */

import java.util.Scanner; //Imports for Scanner, Stack, and file operations. 
import java.util.Stack;
import java.io.*;

/**
 * Main Class, makes use of FileNotFoundException, InvalidFormatException, Scanner, Stack and util.IO.
 */
public class Lab06{ 
  
  private static String error = ""; // String to hold error message 
  private static final boolean DEBUG = false;
  /**
   * Main Method, accepting string arguments
   * @param args standard header String[]
   */
  public static void main(String args[]){
    if (DEBUG)
      System.out.println("Before input error = " + error);
    Scanner s = new Scanner(System.in); // interface with keyboard
    
    String fileName = "First Iteration"; // filename, initialized to "First Iteration" during first iteration.
    System.out.println(" _____                     _   _               _       _____\n" +                  
                       "|  __ \\                   | | | |             (_)     |  __ \\                   \n"+
                       "| |__) |_ _ _ __ ___ _ __ | |_| |__   ___  ___ _ ___  | |__) |_ _ _ __ ___  ___ \n"+
                       "|  ___/ _` | '__/ _ \\ '_ \\| __| '_ \\ / _ \\/ __| / __| |  ___/ _` | '__/ __|/ _ \\ \n"+
                       "| |  | (_| | | |  __/ | | | |_| | | |  __/\\__ \\ \\__ \\ | |  | (_| | |  \\__ \\  __/\n"+
                       "|_|   \\__,_|_|  \\___|_| |_|\\__|_| |_|\\___||___/_|___/ |_|   \\__,_|_|  |___/\\___|\n"+
                       
                       ".                          A Lab Assignment by Jason Ivey                      .\n" +
                       ".               With help from: Mr. Bacerra, Prof. Servin, Mr. Speicher,       .\n"+
                       ".                            Prof. Blando, and Oracle\u00AE!                        .\n"+
                       ".          This LAB is optimized for Java, other source types may not work.    .\n" ); //Welcome message with ASCII art
    do{ // main loop that will execute until sentinel value of an empty string is called. 
      
      try{ //try method for possible FileNotFoundException or InvalidFormatException. 
        
        System.out.print("\nPress \"[Enter]\"  to exit, type \"help\"  for help, or ENTER FILENAME: "); //Prompt for user input
        fileName = s.nextLine();//Recieving user input through keyboard
        if (DEBUG)
          System.out.println("After input error = " + error + "\nfileName = " + fileName);
        if (helpOrExit(fileName))
        {
          if (DEBUG)
            System.out.println("HelpOrExit = true");
//Checking for sentinel value or help request, if help or exit it will recheck condition.
          continue;
        }
        
        else if(fileName.contains(".")){//Checking input for file extension 
          if (DEBUG)
            System.out.println("File name does contain \".\"");
          Stack<Character> stack = read(fileName); //A new stack of the type character, utilizing java's built in Stack
          if (DEBUG)
            System.out.println("Stack  after read = " + stack +"\nError = " + error);
          parse(stack);// Parsing remnants of stack into error. 
          if (DEBUG)
            System.out.println("Stack  after parse = " + stack +"\nError = " + error);
          if(error.equals(""))//If no error then everything is okay! 
            System.out.println("Everything is Okay!");
          
          else{ //else diagnos the file
            
            System.out.println("----------------------Read Succesful----------------------\n"
                                 +"Diagnosis: Missing these paranthesis [" +  error.substring(0, error.length()-1) + "]"); //length is minus one to remove extra comma
          }
        }
        
        else{ //If extension is missing then file name is invalid 
          if(fileName.trim().length() >= 0)
            throw new InvalidFormatException("\nOh no..."
                                               +"\nFile Name is Invalid");
        }
      }
      catch(InvalidFormatException e) //Bad format 
      {
        System.out.println("\n"+e);
        continue;
      }
      catch (FileNotFoundException e){ //Explains file is missing 
        System.out.println("\nOh no..."+"\nFile does not exist in the directory: " + System.getProperty("user.dir") +"");
        continue;
      }
      error = "";
    } while( fileName.trim().length() > 0); //Condition of main loop
    s.close();
  }
  /**
   * HelpOrExit Will check for help or exit request
   * @param input String of input to be checked for KEY_WORD or exit request.
   * @return Boolean Return true if an exit or a help was requested.
   */
  public static boolean helpOrExit(String input){
    final String KEY_WORD = "help";//Keyword help to be checked for
    if (input.trim().length() <= 0 ) //Exit request is empty String
      return true;
    
    else if (input.length() != 0 && input.substring( 0 , Math.min(input.length(), KEY_WORD.length() )
                                                   ).equalsIgnoreCase(KEY_WORD.substring( 0 , Math.min(input.length(), KEY_WORD.length() ) ) ) )
      /*Extremely complicated if statements explained by Frank Blando in Java I,
       * Basically if someone were to only "hE" it would only compare the first
       * two characters of the key word.
       */ 
    {
      System.out.println("\n____This program allows you to check Java progams for missing parenthesis____"
                           +"\n - Aplicaple parethesis include: \" (, ), <, >, {, }, [,\" and \"].\""
                           +"\n - To exit pres \"[Enter]\""
                           +"\n - Reading: \".ppt, .mov\" and other non text files may cause errors"
                           +"\n - You may check any textual filetype, make sure and include the extension. "
                           +"\n - \".java\" and \".txt\" are the best extensions to use."); //Helpful text
      return true;
    } 
    else return false; //If no request for help ro exit then false!
  }
  /**
   * Read will read the specified file for characters and will remove characters with spouses
   * @param fileName String of the name of the file to be read.
   * @return Stack<charcater> Returns a stack of characters that did not get paired up with their spouses.
   */
  public static Stack<Character> read(String fileName) throws FileNotFoundException {
    Stack<Character> temp = new Stack<Character>(); //temp Stack! 
    String buffer;//String to hold line value of file
    
    File myFile = new File(fileName);//New file to be read! 
    
    if(!myFile.exists())//Wait... does it exist?
      throw new FileNotFoundException();//NO! Then throw this!
    
    Scanner fileReader = new Scanner(myFile);//Lets scan the contents of the file with this. 
    
    while(fileReader.hasNext()){//Loop to go through all input from file
      
      buffer = fileReader.nextLine();//update buffer
      
      for(int x = 0; x < buffer.length(); x++){ //For the length of the line
        /*
         * The problem of Boolean statements and loops was brought up in class and 
         * after trying to implement some pretty wacky answers I decided to call
         * over Valentine and ask him how the heck I was supposed to fix this.
         * He suggested I check the buffer then check the char at x for a comparitive 
         * after this just skip the loop and continue. 
         */ 
        if(buffer.contains("if")          && (buffer.charAt(x) == '<' || buffer.charAt(x) == '>'))
          continue;
        else if(buffer.contains("return") && (buffer.charAt(x) == '<' || buffer.charAt(x) == '>'))
          continue;
        else if(buffer.contains("while")  && (buffer.charAt(x) == '<' || buffer.charAt(x) == '>'))
          continue;
        else if(buffer.contains("for")    && (buffer.charAt(x) == '<' || buffer.charAt(x) == '>'))
          continue;
        
        switch (buffer.charAt(x)){
          /*This switch checks for parenthesis and pushes open parenthesis and pops
           * open parenthesis when it encounters their spouses. 
           * If no spouse is found then error will have the opposite of the closing added to it
           * (Because the spouse is the one who is missing). 
           */ 
          
          case '(' : temp.push('(');
          break;
          
          case ')' : 
            if(!temp.empty() && temp.peek() == '(')
            temp.pop();
            else if(temp.empty())
              error = " (," + error;
            break;
            
          case '{' : temp.push('{');
          break;
          
          case '}' :
            if(!temp.empty() &&temp.peek() == '{')
            temp.pop();
            else if(temp.empty())
              error = " {," + error;
            break;
            
          case '[' : temp.push('[');
          break;
          
          case ']' :
            if(!temp.empty() && temp.peek() == '[')
            temp.pop();
            else if(temp.empty()|| temp.peek() != '<')
              error = " [," + error;
            break;
            
          case '<' : temp.push('<');
          break;
          
          case '>' : 
            if(!temp.empty() && temp.peek() == '<')
            temp.pop();
            else if(temp.empty() || temp.peek() != '<')


              error = " <," + error;
            break;
            //No default needed!!! 
        }
      }
    }
    fileReader.close();
    return temp;//lets give the caller back the temp they deserve! 
  }
  /**
   * parse Will read the stack for characters and assign the spouse(opposite) of the characters into error.
   * @param s Stack<character> to be read.
   * 
   */
  public static void parse(Stack<Character> s){//This does the rest fo the parsing needed 
    
    while(!s.empty())//Do this till our stack is empty
    {
      /*
       * Will basically set the opposite to error. 
       */ 
      switch (s.pop()){//Switching on the character we just pooped
        case '(' : error = " )," + error;
        break;
        case ')' : error = " (," + error;
        break;
        case '{' : error = " }," + error;
        break;
        case '}' : error = " {," + error;//Does the operation in reverse order because of the FILO nature of stacks
        break;
        case '[' : error = " ]," + error;
        break;
        case ']' : error = " [," + error;
        break; 
        case '<' : error = " >," + error;
        break;
        case '>' : error = " <," + error;
        break;
        //No default needed
      }
    }
  }
}
class InvalidFormatException extends Exception{//Nested class for exceptions 
  /**
   * Constructor of Exception.
   * @param s String to be printed as a message.
   */
  public  InvalidFormatException(String s){
    super(s); 
  }
}