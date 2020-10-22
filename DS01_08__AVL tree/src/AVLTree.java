
public class AVLTree {

	private int key, height;
	private AVLTree left,right;
	public static final AVLTree NIL = new AVLTree();


	private AVLTree() {
		left = right = this;
		height = -1;
	}
	public AVLTree(int key) {
		this.key = key;
		left = right = NIL;
	}
	private AVLTree(int key,AVLTree left, AVLTree right) {
		this.key = key;
		this.left = left;
		this.right = right;
		height = 1+Math.max(left.height, right.height);
	}


	public int size() {
		if(this == NIL) return 0;
		return 1+left.size()+right.size();
	}

	public String toString() {
		if(this == NIL || height==-1) return "";
		return left + " " + key+"("+(left.height - right.height)+")" + " " + right;
	}
	public boolean add(int key) {

		int oldSize = size();
		grow(key);
		return size() > oldSize;
	}
	public AVLTree grow(int key) {
		if(this == NIL)return new AVLTree(key);
		if(key == this.key)return this;
		if(key < this.key) {
			left = left.grow(key);
		}
		else{
			right = right.grow(key);
		}
		rebalance();
		height = 1+Math.max(left.height, right.height);
		return this;}

	private void rebalance() {
		if(right.height > left.height+1) {
			if(right.left.height > right.right.height)
				right.rotateRight();
			rotateLeft();
		}
		else if(left.height > right.height+1) {
			if(left.right.height > left.left.height)
				left.rotateLeft();
			rotateRight();
		}
	}
	private void rotateLeft() {
		left = new AVLTree(this.key,left,right.left);
		key = right.key;
		right = right.right;
	}
	private void rotateRight() {
		right = new AVLTree(this.key,left.right,right);
		key = left.key;
		left = left.left;
	}

	public boolean Delete(int key) {
		int oldSize = size();
		remove(key);
		return size() < oldSize;

	}

	public AVLTree remove(int keyValue) {

		if(this == NIL) return NIL;

		if(keyValue == this.key) {
			if(this.right == NIL && this.left == NIL){return NIL;}//리프노드일경우
			else if(right == NIL){ //자식이 하나일경우(왼쪽자식)
				this.key = left.key;
				left = NIL;
			}
			else if(left == NIL ) { //자식이 하나일경우(오른쪽자식)
				this.key = right.key;
				right = NIL;
			}
			else { //자식이 둘일경우
				AVLTree successor = this.right;
				while(successor.left!=NIL) {
					successor = successor.left;
				}
				this.key = successor.key;
				right = right.remove(successor.key);
			}
		}

		else if(keyValue < this.key) {
			left = left.remove(keyValue);
		}
		else if(keyValue > this.key){
			right = right.remove(keyValue);
		}
		rebalance();
		height = 1+Math.max(left.height, right.height);
		return this;
	}


}
