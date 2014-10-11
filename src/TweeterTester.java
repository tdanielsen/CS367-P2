
public class TweeterTester {

	@SuppressWarnings("unchecked")
	public static <E> void main(String[] args) throws TweetTooLongException 
	{
		SimpleLinkedList<E> test = new SimpleLinkedList<E>();
		test.add((E) "hat");
		test.add((E) "cat");
		test.add((E) "matt");
		//System.out.println(test.get(2));
		//System.out.println(test.remove(2));
		test.add(1, (E) "tweet");
		test.add(2, (E) "t");
		System.out.println(test.get(0));
		System.out.println(test.get(1));
		System.out.println(test.get(2));
		System.out.println(test.get(3));
		System.out.println(test.isEmpty());
		Tweet tweet = new Tweet(1, "Hello World", "Peter");
		Tweet tweet2 = new Tweet(2, "Hello World", "Jon");
		Tweet tweet3 = new Tweet(4, "Hello World", "Zac");
		Tweet tweet4 = new Tweet(5, "Hello World", "Tim");
		Timeline tl = new Timeline();
		tl.add(tweet);
		tl.add(tweet2);
		tl.add(tweet3);
		tl.add(tweet4);
		tl.print();
		tl.add(new Tweet (3, "Hey", "Joe"));
		tl.print();
		tl.print(3);

	}

}
