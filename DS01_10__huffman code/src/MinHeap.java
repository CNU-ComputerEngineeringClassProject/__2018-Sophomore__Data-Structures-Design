public class MinHeap {

	private Object[] q;
	private int size;
		
	public MinHeap(int capacity) {
		q = new Object[capacity];
		this.size=0;
	}
	
	public void add(Object tree) {
		
		if(size == 0) {
			q[size++] = (HuffmanTree)tree ;
			return ;
		}
		
		int x = ((HuffmanTree)tree).num;
		if(size == q.length) resize();
		
		int i = size++;
		
		while(i>0) {
			int j = i;
			i = (i-1)/2;
			HuffmanTree a = (HuffmanTree)q[i];
			if(a.num <= x) {
				q[j] = tree;
				return;
			}
			q[j] = a;
		}
		q[i] = tree;
		
	}
	public Object best() {
		if (size == 0) 
			throw new java.util.NoSuchElementException();
		return q[0];     }
	
	public Object remove() {
		Object best = best();
		q[0] = (HuffmanTree)q[--size];
		heapify(0, size); 
		return best;
	}
	public int size() {
		return size;
	}
	
	private void heapify(int i, int n) {
		HuffmanTree ai = (HuffmanTree)q[i];
		while(i < n/2) {
			int j = 2*i+1;
			
			if(j+1 < n && ((HuffmanTree)q[j+1]).num < ((HuffmanTree)q[j]).num) {
				++j;
			}
			if(((HuffmanTree)q[j]).num >= ai.num) break;
			q[i] = q[j];
			i=j;
		}
		q[i] = ai;
	}
	
	private void resize() {
		Object[] aa = new Object[2*q.length]; 
		System.arraycopy(q, 0, aa, 0, q.length); 
		q = aa; 
	}
}

