
public class Graph {
	int size;
	String[] vertices;
	boolean[][] a;

	public Graph(String[] args) {
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new boolean[size][size];
	}

	public void add(String v, String w) {
		int i = index(v), j=index(w);
		
		if(index(v)==a.length||index(w)==a.length) {
			System.out.println("���� ���� ���� : �Է��Ͻ� ���� �߿� ã�� �� ���� ������ �ֽ��ϴ�."); 
			return ;} 
		
		a[i][j] = a[j][i] = true;
	}

	private int index(String v) {
		for(int i =0; i<size;i++)
			if(vertices[i].equals(v)) return i;
		return a.length;
	}
	public String toString() {
		if(size ==0 )return "{}";
		StringBuffer buf = new StringBuffer("{"+vertex(0));
		for(int i = 1; i< size ; i++)
			buf.append(","+vertex(i));
		return buf + "}";
	}
	private String vertex(int i) {
		StringBuffer buf = new StringBuffer(vertices[i]+":");
		for(int j=0;j<size;j++)
			if(a[i][j]) buf.append(vertices[j]);
		return buf+"";
	}

	//�� ������ ������ �������� ���� ����Ʈ �ϴ� �޼ҵ�
	public void degree(String v) {
		int vdegree =0;
		//�������
		if(index(v)==a.length) {
			System.out.println("���� : �Է��Ͻ� ������ ã���� �����ϴ�.");
		return ;
		} 

		for(int i =0; i<size;i++) {
			if(a[index(v)][i] == true) vdegree++;
		}
		System.out.println("���� "+v+"�� ������ ������ ���� " +vdegree+"�� �Դϴ�.");

	}

	//���δٸ� �� ������ ���� ���̰� 2�� ��ΰ� �����ϴ��� ����Ʈ�ϴ� �޼ҵ�
	public void findPath(String v,String w) {
		int true2path = 0;

		if(index(v)==a.length||index(w)==a.length) {System.out.println("���� : �Է��Ͻ� ���� �߿� ã�� �� ���� ������ �ֽ��ϴ�."); return ;} 

		for(int i =0; i<size;i++) {
			if(a[i][index(v)] == true && a[i][index(w)] == true) {
				System.out.println(v + "-" +vertices[i]+"-"+w);
				true2path++;
			}
		}
		if(true2path ==0) {System.out.println("�� ���� "+v+","+w+"�� ���� ���̰� 2�� ��ΰ� �������� �ʽ��ϴ�.");}
	}
}
