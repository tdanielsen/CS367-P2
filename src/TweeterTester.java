import java.util.ArrayList;
import java.util.List;


public class TweeterTester {

	@SuppressWarnings("unchecked")
	public static <E> void main(String[] args) throws TweetTooLongException 
	{
		SimpleLinkedList<E> test = new SimpleLinkedList<E>();
		test.add((E) "hat");
		test.add((E) "cat");
		test.add((E) "matt");
		//System.out.println(test.get(2));
		System.out.println(test.remove(0));
		test.add(1, (E) "tweet");
		test.add(2, (E) "t");
		System.out.println(test.get(0));
		System.out.println(test.get(1));
		System.out.println(test.get(2));
		System.out.println(test.get(3));
		System.out.println(test.isEmpty());
		Tweet tweet = new Tweet(1, "Hello World", "Peter");
		Tweet tweet2 = new Tweet(8, "Helo World", "Peter");
		Tweet tweet3 = new Tweet(23, "Hell World", "Zac");
		Tweet tweet4 = new Tweet(11, "Hello World", "Tim");
		Tweet tweet5 = new Tweet(90, "Hello World", "Jon");
		Tweet tweet6 = new Tweet(24, "Helo World", "Jon");
		Tweet tweet7 = new Tweet(25, "Hell World", "Tim");
		Tweet tweet8 = new Tweet(12, "Hello World", "Tim");
		Timeline tl = new Timeline();
		List<Tweet> newList = new ArrayList<Tweet>();
		newList.add(tweet);
		newList.add(tweet2);
		newList.add(tweet8);
		newList.add(tweet6);
		newList.add(tweet7);
		newList.add(tweet3);
		newList.add(tweet5);
		
//		tl.add(tweet3);
//		tl.add(tweet);
//		tl.add(tweet2);
//		tl.add(tweet5);
//		tl.add(tweet7);
//		tl.add(tweet6);
//		tl.add(tweet8);
		tl.add(new Tweet (3, "Hey", "Joe"));

		tl.add(tweet4);
		tl.add(newList);
		tl.remove("Jap");
		tl.remove("Peter");
		tl.print();
		tl.print(91);
		//tl.search("Hello").print();
		

	}

}
