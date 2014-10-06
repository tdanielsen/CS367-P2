
public class SimpleLinkedList<E> implements ListADT
{
	private DblListnode<E> simpleList;
	private DblListnode<E> tail;
	private int numItems;
	
	public SimpleLinkedList()
	{
		simpleList = null;
		tail = null;
		numItems = 0;
		
	}
	public void add(Object item)
	{
        DblListnode<E> newnode = new DblListnode(item);
        //Special Case: empty list
        if (simpleList == null) {
          simpleList = newnode;
          tail = newnode;
          numItems++;
        }
        tail.setNext(newnode);
        tail = newnode;
        numItems++;
	}
	public void add(int pos, Object item)
	{
		//check for valid input
		if (pos > numItems || simpleList == null) 
			throw new IllegalArgumentException();
		DblListnode<E> curr = simpleList;
		for (int i = 0; i < pos; i++)
			curr.getNext();
		curr.setNext(new DblListnode(item));
		
	}
	public boolean contains(Object item)
	{
		DblListnode<E> curr = simpleList;
		for (int i = 0; i < numItems; i++)
		{
			if(curr.getData().equals(item))
				return true;
			curr.getNext();
		}
		return false;
	}
	public Object get(int pos)
	{
		//check for valid input
		if (pos > numItems || simpleList == null) 
			throw new IllegalArgumentException();
		DblListnode<E> curr = simpleList;
		for (int i = 0; i < pos; i++)
			curr.getNext();
		return curr.getData();
	}
	public boolean isEmpty() 
	{
		if(simpleList == null)
			return true;
		return false;
	}
	public Object remove(int pos)
	{
		//check for valid input
		if (pos > numItems || simpleList == null) 
			throw new IllegalArgumentException();
		String result = (String) get(pos);
		DblListnode<E> curr = simpleList;
		for (int i = 0; i < pos; i++)
			curr.getNext();
		curr.setNext(curr.getNext().getNext());
		return result;
	}
	public int size()
	{
		return numItems;
	}
}
