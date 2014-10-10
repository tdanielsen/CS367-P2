
public class SimpleLinkedList<E> implements ListADT<E>
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
	public void add(E item)
	{
        DblListnode<E> newnode = new DblListnode<E>(item);
        //Special Case: empty list
        if (simpleList == null) 
        {
          simpleList = newnode;
          tail = newnode;
          numItems++;
        }
        else
        {
        	//set new node prev to old end
        tail.setNext(newnode);
        tail = newnode;
        numItems++;
        }
	}
	public void add(int pos, E item)
	{
		//check for valid input
		if (pos > numItems || simpleList == null) 
			throw new IllegalArgumentException();
		DblListnode<E> curr = simpleList;
		for (int i = 0; i < pos - 1; i++)
			curr.getNext();
		curr.setNext(new DblListnode<E>(item));
		
	}
	public boolean contains(E item)
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
	public E get(int pos)
	{
		//check for valid input
		if (pos > numItems || simpleList == null) 
			throw new IllegalArgumentException();
		DblListnode<E> curr = simpleList;
		for (int i = 0; i < pos; i++)
			curr = curr.getNext();
		return curr.getData();
	}
	public boolean isEmpty() 
	{
		if(simpleList == null)
			return true;
		return false;
	}
	public E remove(int pos)
	{
		//check for valid input
		if (pos > numItems || simpleList == null) 
			throw new IllegalArgumentException();
		String result = (String) get(pos);
		DblListnode<E> curr = simpleList;
		for (int i = 0; i < pos; i++)
			curr = curr.getNext();
		curr.setNext(curr.getNext().getNext());
		return (E) result;
	}
	public int size()
	{
		return numItems;
	}
}
