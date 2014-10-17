///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Twitter.java
// File:             Timeline.java
// Semester:         CS367 Fall 2014
//
// Author:           Tim Danielsen
// CS Login:         danielsen
// Lecturer's Name:  J. Skrentny
// Lab Section:      N/A
//
// Credits:          Peter Danielsen
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.List;

/**
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields come earlier in the list.
 */
class Timeline{

	private SimpleLinkedList<Tweet> timeLine;

    /**
     * Constructs an empty timeline
     */
    public Timeline()
    {
    	timeLine = new SimpleLinkedList<Tweet>();
    }

    /**
     * Adds a single tweet to the Timeline
     * 
     * @param tweet the tweet to add
     */
    public void add(Tweet tweet)
    {
    	//if the timeline is empty, it just adds it into the list
    	if (timeLine.isEmpty())
    		timeLine.add(tweet);
    	//if the tweet's time being put in is larger than all other tweets,
    	//then it is added at the end
    	else if (tweet.getTime() > timeLine.get(timeLine.size()-1).getTime())
    		timeLine.add(tweet);
    	//if the tweet's time being put in is smaller than all other tweets,
    	//then it is added at the beginning
    	else if (tweet.getTime() < timeLine.get(0).getTime())
    		timeLine.add(0, tweet);
    	//all other cases
    	else
    	{
    		int low = 0;
    		int high = timeLine.size() - 1;
    		int tweetTime = tweet.getTime();
    		//searches binarily for the right spot to put the tweet, divide and
    		//conquer
    		while (high > low)
    			{
    				//the "middle" of the list (reset every loop)
    				int midpoint = (high - low) / 2 + low; 
    				//the middle's time
    				int midpointTime = timeLine.get(midpoint).getTime();
    				//sees if the new tweet should be added here and if it
    				// should, ends the while loop
					if (tweetTime > midpointTime
						&& tweetTime < timeLine.get(midpoint + 1).getTime())
					{
						timeLine.add(midpoint + 1, tweet);
						break;
					}
					//makes the search area half by moving the lower bounds
					//to the midpoint 
    				if (tweetTime > midpointTime)
    					low = midpoint;
    				//makes the search area half by moving the upper bounds
					//to the midpoint 
    				if (tweetTime < midpointTime)
    					high = midpoint;
    				//ends the while loop if the tweet time is already in play
    				if (tweetTime == midpointTime)
    					break;
    			}
    	}
    }

    /**
     * Adds an ordered list of tweets to the Timeline
     * @param tweets the list of tweets to add
     */
    public void add(List<Tweet> tweets)
    {
    	for (int i = 0; i < tweets.size(); i++)
    		add(tweets.get(i));
    }

    /**
     * Removes all tweets by user from the Timeline
     * 
     * @param user the user whose tweets should be removed
     */
    public void remove(String user)
    {
    	for (int i = 0; i < timeLine.size(); i++)
    	{
    		//goes through the timeline to see what user made the tweet and, if
    		//it was made by ther user being searched for, removes it from the
    		//list
    		if (timeLine.get(i).getUser().equals(user))
    		{
    			timeLine.remove(i);
    			i--;
    		}
    	}
    }

    /**
     * Create a new Timeline containing only the tweets containing keyword
     * 
     * @param keyword the keyword to search for
     * @return a Timeline of tweets containing keyword
     */
    public Timeline search(String keyword)
    {
    	// new timeline to store tweets for the return
    	Timeline searchingLine = new Timeline(); 
    	for (int i = 0; i < timeLine.size(); i++)
    	{
    		if (timeLine.get(i).getMessage().contains(keyword))
    			searchingLine.add(timeLine.get(i));
    	}
    	
    	return searchingLine;
    }

    /**
     * Print each tweet in the timeline
     */
    public void print()
    {
    	for (int i = 0; i < timeLine.size(); i++)
    		timeLine.get(i).print();
    }   
    
    /**
     * Print each tweet in the timeline since time
     * 
     * @param time the largest time to print tweets
     */
    public void print(int time)
    {
    	int i = 0;
    	//if time is larger than the last tweet in the timeline, it calls print
    	if (timeLine.get(timeLine.size() - 1).getTime() <= time)
    		print();
    	else
	    	while (timeLine.get(i).getTime() < time)
	    	{
	    		timeLine.get(i).print();
	    		i++;
	    	}
		
    }

}
