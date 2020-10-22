
public class BinarySearchTree {
	private int key;
	private BinarySearchTree left, right;
	public static final BinarySearchTree NIL = new BinarySearchTree();

	public BinarySearchTree(int key){
		this.key = key;
		left = right = NIL;
	}
	
	private BinarySearchTree(){
		left = right = this;
	}
	private BinarySearchTree(int key,BinarySearchTree left, BinarySearchTree right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	public int size() {
		if(this == NIL) return 0;
		return 1+left.size()+right.size();
	}

	public boolean search(int key) {
		if (this.key == key) {
			return true;
		}
		if (this.key > key && this.left != NIL) {
			return this.left.search(key);
		}
		if (this.key < key && this.right != NIL) {
			return this.right.search(key);
		}

		return false;}



	public BinarySearchTree insert (int key) {
		if(this == NIL)return new BinarySearchTree(key);
		if(key == this.key)return this;
		if(key < this.key) left = left.insert(key);
		else right = right.insert(key);
		return this;

	}

}
