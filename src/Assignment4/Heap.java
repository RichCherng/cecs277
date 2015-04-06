package Assignment4;

import java.util.ArrayList;

public class Heap<T extends Comparable>{
	
	private static final int T = 0;
	private ArrayList<T> heap;
	public Heap(T t){
		heap = new ArrayList<T>();
	}
	
	public int getSize(){
		return heap.size();
	}
	
	public boolean isEmpty(){
		return heap.isEmpty();
	}
	
	public int getParentIndex(int i){
		return (i-1)/2;
	}
	
	public int getRightChild(int i){
		return 2*i + 2;
	}
	
	public int getLeftChild(int i){
		return 2*i + 1;
	}
	
	@SuppressWarnings({ "hiding", "unchecked" })
	public <T> T get(int i){
		if( i < 0 || i > heap.size()){
			throw new IndexOutOfBoundsException("Index is out of bounds!");
		}
		return (T) heap.get(i);
	}
	
	private void fixHeap(int rootIndex, int lastIndex)
	{
		T parent = heap.get(rootIndex);
		
		int leftIndex = getLeftChild(rootIndex);
		int rightIndex = getRightChild(rootIndex);
	
		
		//If left child exist  else the heap is fixed
		if(leftIndex <= lastIndex)
		{
			T child = heap.get(leftIndex);
			int curIndex = leftIndex;
			//check if there's a right child
			if(rightIndex <= rightIndex)
			{
				//check which one is bigger
				if(heap.get(rightIndex).compareTo(child) > 1)
				{
					child = heap.get(rightIndex);
					curIndex = rightIndex;
				}
			}
			
			//check if it is greater than parent node
			if(child.compareTo(parent) > 1)
			{
				ar[rootIndex] = child;
				ar[curIndex] = parent;
				fixHeap(curIndex,lastIndex);
			}
		}
		
	}
}
