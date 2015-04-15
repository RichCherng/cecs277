package Assignment4;

import java.util.ArrayList;
/**
 * Heap class - store generic element in heap structure
 * @author Pongsathorn Cherngchaosil
 *
 * @param <T> Generic element
 */
public class Heap<T extends Comparable<T>> {
	/**
	heap - where every element will be store in
 	*/
	private ArrayList<T> heap;
	/**
	 * Constructor - construct a Heap
	 */
	public Heap() {
		heap = new ArrayList<T>();
	}
	/**
	 * accessor method
	 * @return the size of the heap
	 */
	public int getSize() {
		return heap.size();
	}
	/**
	 * check if the heap is empty
	 * @return true if heap is empty and false otherwise
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	/**
	 * Calculate and return the index of parent of that element
	 * @param i index of element 
	 * @return index of it parent
	 */
	public int getParent(int i) {
		return (i - 1) / 2;
	}
	/**
	 * Calculate and return the index of right child of the element i's
	 * @param i index of element 
	 * @return index of it right child
	 */
	public int getRightChild(int i) {
		return 2 * i + 2;
	}
	/**
	 * Calculate and return the index of left child of the element i's
	 * @param i index of element
	 * @return index of it left child
	 */
	public int getLeftChild(int i) {
		return 2 * i + 1;
	}
	/**
	 * get the element in the specific location in the array
	 * @param i index in the array
	 * @return generic element in that position
	 */
	public T get(int i) {
		if (i < 0 || i > heap.size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds!");
		}
		return heap.get(i);
	}
	/**
	 * remove and re-balance the heap
	 * @return the removed element
	 */
	public T remove() {
		//The root, the minimum element
		T min = heap.get(0);
		//index of the last element
		int index = heap.size() - 1;
		//Remove the last element to replace the root
		T last = heap.remove(index);
		//Fix the heap
		if (index > 0) {
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				if (getLeftChild(index) <= end) {// left exists
					T child = heap.get(getLeftChild(index));
					int childLoc = getLeftChild(index);
					if (getRightChild(index) <= end) {// rt exists
						if (heap.get(getRightChild(index)).compareTo(child) < 0) {
							child = heap.get(getRightChild(index));
							childLoc = getRightChild(index);
						}
					}
					if (child.compareTo(root) < 0) {
						heap.set(index, child);
						index = childLoc;
					} else {
						done = true;
					}
				} else {// no children
					done = true;
				}
			}
			heap.set(index, root);
		}
		return min;
	}
	/**
	 * Sort the heap using heap sort method
	 */
	public void sort() {
		ArrayList<T> t = new ArrayList<T>();
		while (!heap.isEmpty()) {
			t.add(remove());
		}
		heap = t;
	}
	/**
	 * Insert element to the last location and re-balance the heap
	 * @param n
	 */
	public void add(T n) {
		heap.add(null);
		int index = heap.size() - 1;
		while (index > 0 && heap.get(getParent(index)).compareTo(n) > 0) {
			heap.set(index, heap.get(getParent(index)));
			index = getParent(index);
		}
		heap.set(index, n);
	}
	/**
	 * print the arraylist
	 */
	public void printHeap() {
		for (int i = 0; i < heap.size(); i++) {
			System.out.println(heap.get(i).toString());
		}
		System.out.println();
	}
	/**
	 * look for the element in the root
	 * @return the root eleemnt
	 */
	public T peek() {
		// the root
		return heap.get(0);
	}

}
