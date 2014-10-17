///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Twitter.java
// File:             TweetTooLongException.java
// Semester:         CS367 Fall 2014
//
// Author:           Tim Danielsen
// CS Login:         danielsen
// Lecturer's Name:  J. Skrentny
// Lab Section:      N/A
//
// Credits:          Peter Danielsen
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * An exception for when the message in a tweet is more than 140 characters
 *
 * <p>Bugs: none known
 *
 * @author Tim Danielsen
 */
class TweetTooLongException extends Exception
{
	/**
	 * Constructor of the exception without a message to display
	 *
	 */
	public TweetTooLongException()
	{
		
	}
	/**
	 * Constructor of the exception with a message to display
	 *
	 * @return the desired message to be displayed
	 */
	public TweetTooLongException(String message)
	{
		super(message);
	}

}
