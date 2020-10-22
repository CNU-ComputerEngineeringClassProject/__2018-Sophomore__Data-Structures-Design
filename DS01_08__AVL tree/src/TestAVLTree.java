
public class TestAVLTree {
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	
		
		AVLTree tree = new AVLTree(14);
		tree.add(17);
		tree.add(11);
		tree.add(7);
		tree.add(53);
		tree.add(4);
		tree.add(13);
		tree.add(12);
		tree.add(8);
		
		System.out.println("* 삽입 결과 :"+tree);
		tree.Delete(53);
		System.out.println("* 53"+" 삭제 결과 :"+tree);
		tree.Delete(11);
		System.out.println("* 11"+" 삭제 결과 :"+tree);
		tree.Delete(7);
		System.out.println("*  7"+" 삭제 결과 :"+tree);
		tree.Delete(12);
		System.out.println("* 12"+" 삭제 결과 :"+tree);
		tree.Delete(14);
		System.out.println("* 14"+" 삭제 결과 :"+tree);
		tree.Delete(13);
		System.out.println("* 13"+" 삭제 결과 :"+tree);
		
		
}
}
