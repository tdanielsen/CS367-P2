///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Twitter.java
// File:             SimpleLinkedList.java
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
 * Makes a doubly linked list of nodes that can be added to, have nodes removed
 * and see if it is empty or it's size. 
 *
 * <p>Bugs: none known
 *
 * @author Tim Danielsen
 */
public class SimpleLinkedList<E> implements ListADT<E>
{
	private DblListnode<E> simpleList; //points to the first node
	private DblListnode<E> tail; //points to the last node
	private int numItems; //holds the total number of items in the list
	
	/**
	 * Constructs an empty simple linked list with the the first and tail
	 * references set to null and the # of items in the list as 0
	 */
	public SimpleLinkedList()
	{
		simpleList = null;
		tail = null;
		numItems = 0;
		
	}
	/**
	 * Adds an object into the list at the end of the list.
	 *
	 * @param item an object
	 */
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
        //adding to the end of the list
        else
        {
        	newnode.setPrev(tail);
        	tail.setNext(newnode);
        	tail = newnode;
        	numItems++;
        }
	}
	/**
	 * Adds an object at a specified point in the list.
	 *
	 * @param pos an integer that should be between the size of the list to 0
	 * @param item an object
	 */
	public void add(int pos, E item)
	{
		//check for valid input
		if (pos > numItems || simpleList == null || pos < 0) 
			throw new IllegalArgumentException();

		DblListnode<E> curr = simpleList; // used to traverse the list
		DblListnode<E> newItem = new DblListnode<E>(item);
		//position is a beginning of the list
		if (pos == 0)
		{
			simpleList.setPrev(newItem);
			newItem.setNext(simpleList);
			simpleList = newItem;
			numItems++;
		}
		else
		{
		for (int i = 0; i < pos - 1; i++)
			curr = curr.getNext();
		newItem.setPrev(curr);
		newItem.setNext(curr.getNext());
		curr.setNext(newItem);
		//if the position is the second to last in the list, tail's prev. ref.
		//needs to be updated
		if (pos - 1 == numItems)
			tail.setPrev(newItem);
		numItems++;
		}
		
	}
	/**
	 * Sees if the list contains an object.
	 *
	 * @param item an object
	 * @return true if the list has the same object and false otherwise
	 */
	public boolean contains(E item)
	{
		DblListnode<E> curr = simpleList; //used to traverse the list
		for (int i = 0; i < numItems; i++)
		{
			if (curr.getData().equals(item))
				return true;
			curr.getNext();
		}
		return false;
	}
	/**
	 * Retrieves an object from a given position in the list.
	 *
	 * @param pos an integer that should be between the size of the list and 0
	 * @return an object from the given position
	 */
	public E get(int pos)
	{
		//check for valid input
		if (pos > numItems || simpleList == null || pos < 0) 
			throw new IllegalArgumentException();
		DblListnode<E> curr = simpleList; //used to traverse the list
		for (int i = 0; i < pos; i++)
			curr = curr.getNext();
		return curr.getData();
	}
	/**
	 * Sees if the list has any objects in it.
	 *
	 * @return true if the list is empty and false otherwise
	 */
	public boolean isEmpty() 
	{
		if (simpleList == null)
			return true;
		return false;
	}
	/**
	 * Removes the object at a given position and returns what it removed.
	 *
	 * @param pos an integer that should be between the list size and 0
	 * @return the object at the given position that was removed
	 */
	public E remove(int pos)
	{
		//check for valid input
		if (pos > numItems || simpleList == null || pos < 0) 
			throw new IllegalArgumentException();
		E result =  get(pos); //saves the object being removed to be returned
		DblListnode<E> curr = simpleList;
		//if the item is at the beginning of the chain
		if (pos - 1 < 0)
		{
			simpleList = curr.getNext();
			curr.getNext().setPrev(null);
			numItems--;
			return result;
		}
		else
		{
			//goes to the position in the chain to remove the item
			for (int i = 0; i < pos - 1; i++)
				curr = curr.getNext();
			//if the item to be removed is at the end of the chain
			if (pos == numItems - 1)
			{
				tail.setPrev(tail.getPrev());
				curr.setNext(null);
				numItems--;
			}
			//if the item is 1 before the end of the chain
			else if (pos == numItems - 2)
			{
				tail.setPrev(tail.getPrev().getPrev());
				curr.setNext(curr.getNext().getNext());
				numItems--;
			}
			//all other cases
			else
			curr.setNext(curr.getNext().getNext());
			numItems--;
			return result;
		}
	}
	/**
	 * Gives the size of the list.
	 *
	 * @return the size of the list
	 */
	public int size()
	{
		return numItems;
	}
}
