
public class TweeterTester {

	public static <E> void main(String[] args) 
	{
		SimpleLinkedList<E> test = new SimpleLinkedList<E>();
		test.add("hat");
		test.add("cat");
		test.add("matt");
		System.out.println(test.get(2));
		test.remove(1);
		test.add(1, "tweet");
		System.out.println(test.get(1));
		System.out.println(test.isEmpty());

	}

}
