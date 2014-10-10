
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
        	newnode.setPrev(tail);
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
		if (pos - 1 < 0)
		{
			curr.getNext().setPrev(new DblListnode<E>(item));
			curr.setNext(new DblListnode<E>(item));
		}
		else
		{
		for (int i = 0; i < pos-1; i++)
			curr = curr.getNext();
		curr.getNext().setPrev(new DblListnode<E>(item));
		curr.setNext(new DblListnode<E>(item));
		}
		
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
		//If the item is at the beginning of the chain
		if (pos - 1 < 0)
		{
			curr.getNext().setPrev(null);
			curr.setNext(curr.getNext());
		}
		//goes to the position in the chain to remove the item
		for (int i = 0; i < pos - 1; i++)
			curr = curr.getNext();
		//if the item to be removed is at the end of the chain
		if(pos == numItems - 1)
		{
			tail.setPrev(tail.getPrev());
			curr.setNext(null);
		}
		//if the item is 1 before the end of the chain
		else if(pos == numItems - 2)
		{
			tail.setPrev(tail.getPrev().getPrev());
			curr.setNext(curr.getNext().getNext());
		}
		//all other cases
		else
		curr.setNext(curr.getNext().getNext());
		return (E) result;
	}
	public int size()
	{
		return numItems;
	}
}
