package Assignment4;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

	private ArrayList<T> heap;

	public Heap() {
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

	public T get(int i) {
		if (i < 0 || i > heap.size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds!");
		}
		return heap.get(i);
	}

	public T remove() {
		T min = heap.get(0);
		int index = heap.size() - 1;
		T last = heap.remove(index);
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
						if (heap.get(getRightChild(index)).compareTo(child) == -1){
							child = heap.get(getRightChild(index));
							childLoc = getRightChild(index);
						}
					}
					if (child.compareTo(root) == -1) {
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
	
	public void sort(){
		ArrayList<T> t = new ArrayList<T>();
		while(!heap.isEmpty()){
			t.add(remove());
		}
		heap = t;
	}
	
	public void add(T n) {
		heap.add(null);
		int index = heap.size() - 1;
		while (index > 0 && heap.get(getParent(index)).compareTo(n) == 1) {
			heap.set(index, heap.get(getParent(index)));
			index = getParent(index);
		}
		heap.set(index, n);
	}

	public void printHeap() {
		for (int i = 0; i < heap.size(); i++) {
			System.out.println(heap.get(i).toString());
		}
		System.out.println();
	}

	public T peek() {
		// the root
		return heap.get(0);
	}

}
