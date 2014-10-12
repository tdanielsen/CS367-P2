import java.util.List;

/**
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the list.
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
    	if (timeLine.isEmpty())
    		timeLine.add(tweet);
    	else if (timeLine.size() == 1)
    		{
    		if (tweet.getTime() > timeLine.get(0).getTime())
    			timeLine.add(tweet);
    		else
    			timeLine.add(0, tweet);
    		}
    	else if (tweet.getTime() > timeLine.get(timeLine.size()-1).getTime())
    		timeLine.add(tweet);
    	else
    	{
    		int low = 0;
    		int high = timeLine.size() - 1;
    	
    		while (high >= low)
    			{
					if (tweet.getTime() > timeLine.get(high/2).getTime()
						&& tweet.getTime() < timeLine.get(high/2 + 1).getTime())
					{
					timeLine.add(high/2 + 1, tweet);
					break;
					}
    				if (tweet.getTime() > timeLine.get(high/2).getTime())
    					low = high/2;
    				if (tweet.getTime() < timeLine.get(high/2).getTime())
    					high = high/2;
    			}
    	}
    }

    /**
     * Adds an ordered list of tweets to the Timeline
     * @param tweets the list of tweets to add
     */
    public void add(List<Tweet> tweets)
    {
    	if (tweets.isEmpty())
    		throw new IllegalArgumentException("Empty List");
    	for (int i = 0; i < tweets.size(); i++)
    		if (timeLine.isEmpty())
    			timeLine.add(tweets.get(i));
    		else
    		{
	    		int low = 0;
	    		int high = timeLine.size() - 1;
	    		while (high > low)
	    			{
						if (tweets.get(i).getTime() > timeLine.get(high/2).getTime()
							&& tweets.get(i).getTime() < timeLine.get(high/2 + 1).getTime())
						{
							timeLine.add(high/2 + 1, tweets.get(i));
							break;
						}
	    				if (tweets.get(i).getTime() > timeLine.get(high/2).getTime())
	    					low = high/2;
	    				if (tweets.get(i).getTime() < timeLine.get(high/2).getTime())
	    					high = high/2;
	    			}
	    	}
    	
    }

    /**
     * Removes all tweets by user from the Timeline
     * 
     * @param user the user whose tweets should be removed
     */
    public void remove(String user)
    {
    	for(int i = 0; i < timeLine.size(); i++)
    	{
    		if (timeLine.get(i).getUser().contains(user))
    			timeLine.remove(i);
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
    	Timeline searchingLine = new Timeline();
    	for(int i = 0; i < timeLine.size(); i++)
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
    	for(int i = 0; i < timeLine.size(); i++)
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
    	while (timeLine.get(i).getTime() < time)
    	{
    		timeLine.get(i).print();
    		i++;
    	}
		
    }

}
