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

    public static void main(String[] args) throws TweetTooLongException, IOException {

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
			//System.out.println(userName);
			following.add(userName);
			BufferedReader in
			   = new BufferedReader(new FileReader(fileName));
			String line;

					while((line = in.readLine()) != null)
					{
						int cutPoint = line.indexOf(":");
						int time = Integer.parseInt(line.substring(0, cutPoint));
						String message = line.substring(cutPoint + 1);
						//System.out.println(message);
						try
						{
							Tweet newTweet = new Tweet(time, message, userName);
							//newTweet.print();
							individualTweets.add(newTweet);
							timeLine.add(newTweet);
						}
						catch (TweetTooLongException e)
						{
							//Ignoring bad tweet
						}
					}
				
			in.close();
			//timeLine.print();
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
                    	if(commands.length <= 1)
                    		System.out.println("Invalid Command");
                    	else if(commands[1].equals("users"))
                    		for(int i = 0; i < allUsersTweets.size(); i++)
                    			System.out.println(allUsersTweets.get(i).get(0).getUser());
                    	else if(commands[1].equals("following"))
		                	for(int j = 0; j < following.size(); j++)
		                	{
		                		System.out.println(following.get(j));
		                	}
                        break;
                    case "follow":
                    	boolean check = false;
                    	for (int i = 0; i < allUsersTweets.size(); i++)
                    	{
                    		if (commands[1].equals(allUsersTweets.get(i).get(i).getUser()))
                    				check = true;
                    	}
                    	if (!check)
                    	{
                			System.out.println("Invalid User");
                			break;
                    	}
                    	for(int j = 0; j < following.size(); j++)
                    		if (commands[1].equals(following.get(j)))
                    		{
                    			System.out.println("Warning: User already followed");
                    			break;
                    		}
                    		else
		                    	for (int i = 0; i < allUsersTweets.size(); i++)
		                    	{
		                    		if (commands[1].equals(allUsersTweets.get(i).get(i).getUser()))
		                    				timeLine.add(allUsersTweets.get(i));
		                    	}
                        break;
                    case "unfollow":
                    	check = false;
                    	for (int i = 0; i < allUsersTweets.size(); i++)
                    	{
                    		if (commands[1].equals(allUsersTweets.get(i).get(i).getUser()))
                    			check = true;
                    	}
                    	if (!check)
                    	{
                			System.out.println("Invalid User");
                			break;
                    	}
                    	timeLine.remove(commands[1]);
                    	int initialSize = following.size();
                    	int i = 0;
                    	for(i = 0; i < following.size(); i++)
                    	{
                    		if(following.get(i).contains(commands[1]))
                    		{
                    			following.remove(i);
                    		}
                    	}
                    	if(following.size() == initialSize)
                		{
                			System.out.println("Warning: User not followed");
                			break;
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