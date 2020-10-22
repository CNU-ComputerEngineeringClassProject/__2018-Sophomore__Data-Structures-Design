import java.util.ArrayList;
import java.util.Collections;

public class SearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=200000;
		int searchvalue = 100000;
		System.out.println("�� "+n+"���� ������ ������ �����մϴ�.");
		ArrayList<Integer>  l = new ArrayList(n);
		for(int i=1 ; i <= n ; i++) {
			l.add(i);
		}
		
		
		Collections.shuffle(l) ;
		System.out.println("���� ���� �Ϸ�");
		

		AVLTree Atree = new AVLTree(l.get(0));
		BinarySearchTree Btree = new BinarySearchTree(l.get(0));
		QuadraticHashTable qhash = new QuadraticHashTable(50000);
		qhash.put(l.get(0),0);


		System.out.println("�� �ڷᱸ���� "+n+"���� ������ �߰��մϴ�.");

		for(int b=1 ; b < n ; b++) {
			//Atree.grow(l.get(b));
			Atree.add(l.get(b));
			Btree.insert(l.get(b));
			qhash.put(l.get(b),0);
			
		}
	

		
		System.out.println("�� �ڷᱸ���� "+searchvalue+"���� ������ �˻��մϴ�.");

		int avl_s = 0;
		long avl_start = System.currentTimeMillis();
		for(int c=1 ; c <= searchvalue ; c++) {
			if(Atree.search(c)) {
				avl_s++; 
			}
		}
		long avl_end = System.currentTimeMillis();
		
		System.out.println("avl �˻��ð� : " +(float)(avl_end-avl_start)/1000+"��");
		System.out.println("avl �˻� ���� Ƚ�� : "+avl_s+"ȸ");

		int bst_s = 0;
		long bst_start = System.currentTimeMillis();
		for(int c=1 ; c <= searchvalue ; c++) {
			if(Btree.search(c)) {
				bst_s++; 
			}
		}
		long bst_end = System.currentTimeMillis();
		
		System.out.println("bst �˻��ð� : " +(float)(bst_end-bst_start)/1000+"��");
		System.out.println("bst �˻� ���� Ƚ�� : "+bst_s+"ȸ");




		int hash_s = 0;
		
		long hash_start = System.currentTimeMillis();
		for(int a=1 ; a <= searchvalue ; a++) {
			if(qhash.search(a)) {
				hash_s++;
			}
		}
		long hash_end = System.currentTimeMillis();
		
		System.out.println("hash �˻��ð� : " +(float)(hash_end-hash_start)/1000+"��");
		System.out.println("hash �˻� ���� Ƚ�� : "+hash_s+"ȸ");

	}
}
