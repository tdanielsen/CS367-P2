/**
 * Stores the time, message and user of a Tweet
 */
class Tweet{

    /**
     * Constructs a Tweet for user with message at time. 
     * Throws a TweetTooLongException if message over 140 characters.
     * 
     * @param time time at which tweet occured
     * @param message message of the tweet, <=140 characters
     * @param user the person who tweeted the tweet 
     * @throws TweetTooLongException if message over 140 characters 
     */
    public Tweet(int time, String message, String user) throws TweetTooLongException
    {
    	//makes sure there is valid input
    	if(message.length() > 140)
    		throw new TweetTooLongException("Tweet is over 140 charaters");
    	timeStamp = time;
    	this.message = message;
    	this.user = user;
    }

    /** 
     * Returns the stored message of the Tweet
     * @return the message
     */
    public String getMessage()
    {
    	return message;
    }
    
    /** 
     * Returns the stored time of the Tweet
     * @return the stored time
     */
    public int getTime()
    {
    	return timeStamp;
    }

    /** 
     * Returns the user who tweeted the Tweet
     * @return the user
     */
    public String getUser()
    {
    	return user;
    }

    /** 
     * Print the Tweet with the following format: <TIME> <USER>:<MESSAGE>
     */
    public void print()
    {
    	System.out.println(getTime() + " " + getUser() + ":" + getMessage());
    }
    private int timeStamp;
    private String message;
    private String user;
}

