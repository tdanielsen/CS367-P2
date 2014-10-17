///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            CS-367 P2
// Files:            Twitter.java, Tweet.java, SimpleLinkedList.java, 
//					 ListADT.java, DBlListnode.java, Timeline.java,
//					 TweetTooLongException.java
// Semester:         CS367 Fall 2014
//
// Author:           Tim Danielsen
// Email:            tdanielsen@wisc.edu
// CS Login:         danielsen
// Lecturer's Name:  J. Skrentny
// Lab Section:      N/A
//
// Credits:          Peter Danielsen
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads in files and puts them into a Twitter time line and then
 * allows the user to access the time line and manipulate it by removing and
 * adding other "users" tweets.
 *
 * <p>Bugs: none known
 *
 * @author Tim Danielsen
 */
public class  Twitter{

	/**
	 * Makes a time line from the files the user put in and provides a way to 
	 * access the time line
	 *
	 * @param args Command line arguments
	 * @throws TweetTooLongException
	 * @throws IOException  
	 */
    public static void main(String[] args) throws TweetTooLongException, IOException
    {
    	//stores all tweets from followed users
    	Timeline timeLine = new Timeline(); 
    	//used to keep track of who the user is following
		List<String> following = new ArrayList<String>(); 
		//stores all the tweets put in when the program starts
		List<List<Tweet>> allUsersTweets = new ArrayList<List<Tweet>>();

		for (int i = 0; i < args.length; i++)
		{
			//used to have a list of all the tweets from a user that will be
			//put into allUsersTweets
			List<Tweet> individualTweets = new ArrayList<Tweet>();
			//used to extract the user name from the file being read in
			String fileName = args[i]; 
			String userName = fileName.substring(0, fileName.indexOf('.'));
			//the try block is used to catch any files that don't exist that
			//are trying to be put in
			try
			{
				//used to read the contents of the files
				BufferedReader in
				   = new BufferedReader(new FileReader(fileName));
				String line;
				following.add(userName);
						//reads each line one at a time
						while ((line = in.readLine()) != null)
						{
							int cutPoint = line.indexOf(":");
							int time = Integer.parseInt
									(line.substring(0, cutPoint));
							String message = line.substring(cutPoint + 1);
							//used to catch TweetTooLongExceptions
							try
							{
								Tweet newTweet = new Tweet
										(time, message, userName);
								individualTweets.add(newTweet);
								timeLine.add(newTweet);
							}
							catch (TweetTooLongException e)
							{
								//Ignoring bad tweet
							}
						}
					
				in.close();
				allUsersTweets.add(individualTweets);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File: " + fileName + " Not Found.");
			}
		}
    	
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
                    	//tests for valid input
                    	if (commands.length <= 1)
                    		System.out.println("Invalid Command");
                    	//prints out what users are out there
                    	else if (commands[1].equals("users"))
                    		for (int i = 0; i < allUsersTweets.size(); i++)
                    			System.out.println(allUsersTweets.get(i).get(0).getUser());
                    	//prints out what users the user is following
                    	else if (commands[1].equals("following"))
		                	for (int j = 0; j < following.size(); j++)
		                	{
		                		System.out.println(following.get(j));
		                	}
                        break;
                    case "follow":
                    	//Sees if the user exists in all the users in 
                    	//allUsersTweets
                    	boolean check = false;
                    	for (int i = 0; i < allUsersTweets.size(); i++)
                    	{
                    		if (commands[1].equals(allUsersTweets.get(i).get(i)
                    				.getUser()))
                    				check = true;
                    	}
                    	if (!check)
                    	{
                			System.out.println("Invalid User");
                			break;
                    	}
                    	//checks if the users is already followed
                    	for (int j = 0; j < following.size(); j++)
                    		if (commands[1].equals(following.get(j)))
                    		{
                    			System.out.
                    				println("Warning: User already followed");
                    			break;
                    		}
                    	//gets the users tweets and adds them to the timeline
                    	for (int i = 0; i < allUsersTweets.size(); i++)
                    	{
                    		
                    		if (commands[1].equals(allUsersTweets.get(i).get(i)
                    				.getUser()))
                    		{
                    			timeLine.add(allUsersTweets.get(i));
                    			following.add(i, allUsersTweets.get(i).get(i)
                    					.getUser());
                    			break;
                    		}
                    	}
                        break;
                    case "unfollow":
                    	//checks for a user that exists
                    	check = false;
                    	for (int i = 0; i < allUsersTweets.size(); i++)
                    	{
                    		if (commands[1].equals(allUsersTweets.get(i).get(i)
                    				.getUser()))
                    			check = true;
                    	}
                    	if (!check)
                    	{
                			System.out.println("Invalid User");
                			break;
                    	}
                    	//removes the specified user from the timeline
                    	timeLine.remove(commands[1]);
                    	int initialSize = following.size();
                    	int i = 0;
                    	//removes the specified user from the following list
                    	for (i = 0; i < following.size(); i++)
                    	{
                    		if (following.get(i).contains(commands[1]))
                    		{
                    			following.remove(i);
                    		}
                    	}
                    	//detects if the user was never followed in the first
                    	//place
                    	if (following.size() == initialSize)
                		{
                			System.out.println("Warning: User not followed");
                			break;
                		}
                        break;
                    case "search":
                    	timeLine.search(commands[1]).print();
                        break;
                    case "print":
                    	//prints out the entire timeline
                    	if (commands.length <= 1)
                    		timeLine.print();
                    	//prints out the timeline up to but not including the
                    	//specified time
                    	else if (Integer.parseInt(commands[1]) >= 0)
                    		timeLine.print(Integer.parseInt(commands[1]));
                    	else
                    		throw new IllegalArgumentException
                    			("Not a valid integer input");
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