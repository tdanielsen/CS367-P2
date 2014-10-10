
public class TweeterTester {

	@SuppressWarnings("unchecked")
	public static <E> void main(String[] args) throws TweetTooLongException 
	{
		SimpleLinkedList<E> test = new SimpleLinkedList<E>();
		test.add((E) "hat");
		test.add((E) "cat");
		test.add((E) "matt");
		System.out.println(test.get(2));
		System.out.println(test.remove(1));
		test.add(1, (E) "tweet");
		System.out.println(test.get(1));
		System.out.println(test.isEmpty());
		Tweet tweet = new Tweet(1, "Hello World", "Peter");
		tweet.print();

	}

}
