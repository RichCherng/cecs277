package Assignment4;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

	private ArrayList<T> heap;

	public Heap(T t) {
		heap = new ArrayList<T>();
	}

	public int getSize() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public int getParent(int i) {
		return (i - 1) / 2;
	}

	public int getRightChild(int i) {
		return 2 * i + 2;
	}

	public int getLeftChild(int i) {
		return 2 * i + 1;
	}

	@SuppressWarnings({ "hiding", "unchecked" })
	public <T> T get(int i) {
		if (i < 0 || i > heap.size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds!");
		}
		return (T) heap.get(i);
	}

	private void fixHeap()
	{
		T root = heap.get(1);
		
		int lastIndex = heap.size()-1;
		//Promote children of removed root while they are smaller than last
		
		int index = 1;
		boolean more = true;
		while(more)
		{
			int childIndex = getLeftChild(index);
			if(childIndex <= lastIndex)
			{
				//Get smaller child
				
				//Get left child first
				T child = heap.get(getLeftChild(index));
				
				//Use right child instead if it is smaller
				if(getRightChild(index) <= lastIndex
						&& heap.get(getRightChild(index)).compareTo(child)<0)
				{
					childIndex = getRightChild(index);
					child = heap.get(getRightChild(index));
				}
				
				//Check if larger child is smaller than root
				if(child.compareTo(root)<0)
				{
					//Promore child
					heap.set(index, child);
					index = childIndex;
				}
				else
				{
					//Root is smaller than both children
					more = false;
				}
			}
			else
			{
				//No children
				more = false;
			}
		}
		
		//Store root element in vacant slot
		heap.set(index, root);
	}
	
	public T remove()
	{
		T minimum = heap.get(1);
		//remove last element
		int lastIndex = heap.size()-1;
		T last = heap.remove(lastIndex);
		
		if(lastIndex > 1)
		{
			heap.set(1,last);
			fixHeap();
		}
		
		return minimum;
	}
	
	public void add(T newElement)
	{
		//Add new Leaf
		heap.add(null);
		int index = heap.size()-1;
		
		//Demote parents that are larger than the new element
		while(index > 1
				&& heap.get(getParent(index)).compareTo(newElement)>0)
		{
			heap.set(index, heap.get(getParent(index)));
			index = getParent(index);
			
			//Store the new element in the vacant slot
			heap.set(index, newElement);
		}
	}
	
}
