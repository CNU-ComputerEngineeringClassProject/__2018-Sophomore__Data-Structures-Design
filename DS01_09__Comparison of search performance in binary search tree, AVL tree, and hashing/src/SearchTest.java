import java.util.ArrayList;
import java.util.Collections;

public class SearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=200000;
		int searchvalue = 100000;
		System.out.println("총 "+n+"개의 정수를 무작위 생성합니다.");
		ArrayList<Integer>  l = new ArrayList(n);
		for(int i=1 ; i <= n ; i++) {
			l.add(i);
		}
		
		
		Collections.shuffle(l) ;
		System.out.println("정수 생성 완료");
		

		AVLTree Atree = new AVLTree(l.get(0));
		BinarySearchTree Btree = new BinarySearchTree(l.get(0));
		QuadraticHashTable qhash = new QuadraticHashTable(50000);
		qhash.put(l.get(0),0);


		System.out.println("각 자료구조에 "+n+"개의 정수를 추가합니다.");

		for(int b=1 ; b < n ; b++) {
			//Atree.grow(l.get(b));
			Atree.add(l.get(b));
			Btree.insert(l.get(b));
			qhash.put(l.get(b),0);
			
		}
	

		
		System.out.println("각 자료구조에 "+searchvalue+"개의 정수를 검색합니다.");

		int avl_s = 0;
		long avl_start = System.currentTimeMillis();
		for(int c=1 ; c <= searchvalue ; c++) {
			if(Atree.search(c)) {
				avl_s++; 
			}
		}
		long avl_end = System.currentTimeMillis();
		
		System.out.println("avl 검색시간 : " +(float)(avl_end-avl_start)/1000+"초");
		System.out.println("avl 검색 성공 횟수 : "+avl_s+"회");

		int bst_s = 0;
		long bst_start = System.currentTimeMillis();
		for(int c=1 ; c <= searchvalue ; c++) {
			if(Btree.search(c)) {
				bst_s++; 
			}
		}
		long bst_end = System.currentTimeMillis();
		
		System.out.println("bst 검색시간 : " +(float)(bst_end-bst_start)/1000+"초");
		System.out.println("bst 검색 성공 횟수 : "+bst_s+"회");




		int hash_s = 0;
		
		long hash_start = System.currentTimeMillis();
		for(int a=1 ; a <= searchvalue ; a++) {
			if(qhash.search(a)) {
				hash_s++;
			}
		}
		long hash_end = System.currentTimeMillis();
		
		System.out.println("hash 검색시간 : " +(float)(hash_end-hash_start)/1000+"초");
		System.out.println("hash 검색 성공 횟수 : "+hash_s+"회");

	}
}
