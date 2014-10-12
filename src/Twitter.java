// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
public class  Twitter{

    public static void main(String[] args) throws TweetTooLongException {

        // TODO *** steps 1 - 3 of the main method ***
    	Timeline timeLine = new Timeline();
//    	Tweet tweet = new Tweet(1, "Hello World", "Peter");
//		Tweet tweet2 = new Tweet(2, "Helo World", "Jon");
//		Tweet tweet3 = new Tweet(4, "Hell World", "Zac");
//		Tweet tweet4 = new Tweet(5, "Hello World", "Tim");
//		timeLine.add(tweet);
//		timeLine.add(tweet2);
//		timeLine.add(tweet3);
//		timeLine.add(new Tweet(6, "Yup", "Zork"));
//		timeLine.add(tweet4);
    	Scanner stdin = new Scanner(System.in);  //for console input

        boolean done = false;
        while (!done) {
            System.out.print("Enter option : ");
            String input = stdin.nextLine();

            //only do something if the user enters at least one character
            if (input.length() > 0) {
                String[] commands = input.split(" ");//split on space
                switch(commands[0]){
                    case "list":
                        break;
                    case "follow":
                        break;
                    case "unfollow":
                    	timeLine.remove(input);
                        break;
                    case "search":
                    	timeLine.search(input);
                        break;
                    case "print":
                    	if (input == null)
                    		timeLine.print();
                    	else if (Integer.parseInt(input) >= 0)
                    		timeLine.print(Integer.parseInt(input));
                    	else
                    		throw new IllegalArgumentException("Not a valid integer input");
                        break;
                    case "quit":
                        done = true;
                        System.out.println("exit");
                        break;
                    default:  //a command with no argument
                        System.out.println("Invalid Command");
                        break;
                }
            } //end if
        } //end while
    } //end main
}