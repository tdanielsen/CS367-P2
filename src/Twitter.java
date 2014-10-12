// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
public class  Twitter{

    public static void main(String[] args) throws TweetTooLongException, NumberFormatException, IOException {

        // TODO *** steps 1 - 3 of the main method ***
    	Timeline timeLine = new Timeline();
//    	Tweet tweet = new Tweet(1, "Hello World", "Peter");
//		Tweet tweet2 = new Tweet(2, "Helo World", "Jon");
//		Tweet tweet3 = new Tweet(4, "Hell World", "Zac");
//		Tweet tweet4 = new Tweet(5, "Hello World", "Tim");
//		timeLine.add(tweet2);
//		timeLine.add(tweet);
//		timeLine.add(tweet3);
//		timeLine.add(tweet4);
		List<String> following = new ArrayList<String>();
		List<List<Tweet>> allUsersTweets = new ArrayList<List<Tweet>>();
		//for loop args
		for (int i = 0; i < args.length; i++)
		{
			List<Tweet> individualTweets = new ArrayList<Tweet>();
			String fileName = args[i];
			String userName = fileName.substring(0, fileName.indexOf('.'));
			following.add(userName);
			BufferedReader in
			   = new BufferedReader(new FileReader(args[i]));
			String line = "";
				
					while((line = in.readLine()) != null)
					{
						int time = 0;
						String message = "";
						int cutPoint = in.readLine().indexOf(':');
						time = Integer.parseInt(in.readLine().substring(0, cutPoint));
						message = in.readLine().substring(cutPoint - 1);
						Tweet newTweet = new Tweet(time, message, userName);
						individualTweets.add(newTweet);
						timeLine.add(newTweet);
					}

			allUsersTweets.add(individualTweets);
		}
		//fileReader
		//bufferedreader
		//while loop (while null)
		//indexof(':')
    	
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
                    	if (following.isEmpty())
                    		System.out.println("Invaild way to input files, Tim");
                    	for(int i = 0; i < following.size(); i++)
                    	{
                    		System.out.println(following.get(i));
                    	}
                        break;
                    case "follow":
                        break;
                    case "unfollow":
                    	timeLine.remove(commands[1]);
                    	for(int i = 0; i < following.size(); i++)
                    	{
                    		if(following.get(i).contains(commands[1]))
                    			following.remove(i);
                    		else
                    			System.out.println("Warning: User not followed");
                    	}
                        break;
                    case "search":
                    	timeLine.search(commands[1]).print();
                        break;
                    case "print":
                    	if (commands.length <= 1)
                    		timeLine.print();
                    	else if (Integer.parseInt(commands[1]) >= 0)
                    		timeLine.print(Integer.parseInt(commands[1]));
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