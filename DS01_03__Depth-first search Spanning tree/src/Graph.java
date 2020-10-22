import java.util.Stack;

public class Graph {
	int size;
	String[] vertices;
	boolean[][] a;

	public String L[];
	boolean visited[];

	int num = 0;
	int num1 = 0;

	public Graph(String[] args) {

		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new boolean[size][size];

		L = new String[size];
		visited = new boolean[size];


		for(int i = 0 ; i<size ; i++) {
			L[i] = null;
			visited[i] = false;
		}

	}

	public void add(String v, String w) {
		int i = index(v), j=index(w);

		if(index(v)==a.length||index(w)==a.length) {
			System.out.println("���� ���� ���� : �Է��Ͻ� ���� �߿� ã�� �� ���� ������ �ֽ��ϴ�."); 
			return ;} 
		else if(index(v)==index(w)) {
			System.out.println("���� ���� ���� : �ڽſ��� �����Ҽ������ϴ�."); 
			return ;
		}
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
			if(index(v) == i) {} 
			else if(a[index(v)][i] == true) {vdegree++;}
		}
		System.out.println("���� "+v+"�� ������ ������ ���� " +vdegree+"�� �Դϴ�.");
	}

	//���δٸ� �� ������ ���� ���̰� 2�� ��ΰ� �����ϴ��� ����Ʈ�ϴ� �޼ҵ�
	public void findPath(String v,String w) {
		int true2path = 0;

		if(index(v)==a.length||index(w)==a.length) {System.out.println("���� : �Է��Ͻ� ���� �߿� ã�� �� ���� ������ �ֽ��ϴ�."); return ;} 
		else if(index(v)==index(w)) {System.out.println("���� : ���� ���������� ���̰� 2�� ��ΰ� �������� �ʽ��ϴ�. "); return ;}

		for(int i =0; i<size;i++) {
			if(a[i][index(v)] == true && a[i][index(w)] == true) {
				System.out.println(v + "-" +vertices[i]+"-"+w);
				true2path++;
			}
		}
		if(true2path ==0) {System.out.println("�� ���� "+v+","+w+"�� ���� ���̰� 2�� ��ΰ� �������� �ʽ��ϴ�.");}
	}

	public void recu_dfs(String v) {


		int i=0; //�ݺ��� ����� ���� ���� ����

		if(num==0) {//���������ϰ��
			for(i = 0 ; i<size ; i++) {//�湮�迭�� L�ʱ�ȭ
				L[i] = null;
				visited[i] = false;
			}

			System.out.println();
			System.out.print("DFS :");
		}

		visited[index(v)] = true; //������ �湮�ߴٰ� ǥ���Ѵ�.
		L[num++]=v; //  L[num]�� v�� �ִ´�. num�� �ϳ� ������Ų��.
		System.out.print(" "+v); //��� �湮�� ������ ����Ѵ�.

		for(i=0 ; i<size ; i++) { //�ݺ����� ����Ѵ�.
			if(a[index(v)][i]) { //���� v������ ������ ������ �ִٸ�
				if(visited[i]) {} //�̹� �湮�� �����̶�� �׳� ��������.
				else { //�湮���� ���� �����̶��
					recu_dfs(vertices[i]); //��͸� �̿��Ͽ� ������ ������ recu_dfc()�� �����Ѵ�.
				}
			}
		}

		if(L[0]==v) { //��Ͱ� �� ������ ���������� v�� �Լ��� ���ƿԴٸ�
			num=0; //num�� 0���� �ʱ�ȭ ��Ų��.(������ �Լ��� ȣ���Ҷ��� ���� �ɼ� �ֵ���)
			return;}

	}

	public void recu_dfs_tree(String v) {

		int i=0; //�ݺ��� ����� ���� ���� ����

		if(num1==0) { //���������ϰ��
			for(i = 0 ; i<size ; i++) {//�湮�迭�� ����ƮL �ʱ�ȭ
				L[i] = null;
				visited[i] = false;
			}
		}
		L[num1++] = v;// L�迭 num1�ڸ��� v�� �ִ´�. �׸��� num1�� �ϳ� ������Ų��.

		visited[index(v)] = true; //v�� �湮�ߴٰ� ǥ���Ѵ�.

		for(i=0 ; i<size ; i++) {//�ݺ����� ����Ѵ�.
			if(a[index(v)][i]) {//���� v������ ������ ������ �ִٸ�
				if(visited[i]) {}//�̹� �湮�� �����̶�� �׳� ��������.
				else {//�湮���� ���� �����̶��
					System.out.println("���� "+vertices[i]+"�� ����" +v+"�� �ڽ��Դϴ�");//������������ v�� �ڽ����� ��Ÿ���� �޼����� ����Ѵ�.
					recu_dfs_tree(vertices[i]);//��͸� �̿��Ͽ� ������ ������ recu_dfc()�� �����Ѵ�.
				}
			}
		}
		if(L[0] == v) { //��Ͱ� �� ������ ���������� v�� �Լ��� ���ƿԴٸ�
			System.out.print("DFS :"); //�湮������ ����Ѵ�.
			for(i=0; i<size ; i++) {
				if(L[i]!=null) {System.out.print(" "+L[i]);}
			}
			System.out.println();
			System.out.println();
			num1=0; //num1�� 0���� �ʱ�ȭ ��Ų��.(������ �Լ��� ȣ���Ҷ��� ���� �ɼ� �ֵ���)
		}

	}


	public void dfs_tree(String v) {

		Stack<String> s = new Stack<String>(); //stack ��ü ����
		String[] parent = new String[size]; //�θ� ������ �迭 ����

		int i,j; //�ݺ����� ���� ����
		int check =0; //�湮�� ������ ����Ʈ�� �ִ��� Ȯ���� ���� ����
		int num3=0; //����Ʈ�� ���� ������ ������ Ȯ���ϱ� ���� ����

		String x = null; //stack���� pop������ ������ ����
		String y = null; //x�� ������ ������ �湮���� ���� ������ ������ ����
 
		if(num3==0) { //���������� ���
			for(i = 0 ; i<size ; i++) {//�ʱ�ȭ�� ���� �ݺ��� 
				L[i] = null;
				visited[i] = false;
				parent[i]=null;
			}
		}

		visited[index(v)] =true; //���������� �湮�ߴ� üũ�Ѵ�.
		s.push(v);//���ؿ� v�� �ִ´�.

		while(!s.empty()) {//������ ��������� ���������� �ݺ��Ѵ�.
			x = s.pop(); //x�� s.pop()���� �ִ´�.
			
			if( num3!=0 ) { //���������� �ƴѰ�� ������ �ڽ����� ����Ѵ�.
				System.out.println("���� "+x+"�� ����" +parent[index(x)]+"�� �ڽ��Դϴ�");
			}
			
			L[num3++]=x; //num3��° L�迭�� x���� �ִ´�. �׸��� num3���� �ϳ� ������Ų��.
			
			for(i=size-1;i>=0;i--) { //�ݺ��� ����
				if(a[index(x)][i]) { //���� x�� ������ ������ �ִٸ�
					if(!visited[i]) { //���� �湮���� ���� ������ �ִٸ�
						y=vertices[i]; //y�� ������ �����Ѵ�.
						parent[i] = x; //y�� �θ�� x�� �����Ѵ�.
						visited[i]= true; //������ �湮�ߴٰ� ǥ���Ѵ�.
						s.push(y); //���ؿ� y�� �ִ´�.
					}
					else { //�湮�� �����̶��
						for(j=0;j<num3;j++) { //�ݺ��� ����
							if(L[j]==vertices[i]) { //������ ����Ʈ�� �־����ִٸ�
								check++; //üũ���ϰ�
								break;} //�ݺ����� ������.
						}
						if(check==0) {parent[i] = x;} 
						//�ݺ����� �����µ� check�� 0�̶�� ���� ���ؿ� �ִٴ� ���̹Ƿ� vertices[i]�� �θ�� x�� �����Ѵ�.
						check=0; //check�� 0���� �����.
					}
				}
			}
		}

		System.out.print("�湮 ���� : "); //�湮������ ����Ѵ�.
		System.out.print(L[0]);
		for(i=1; i<size ; i++) {
			if(L[i]!=null) {System.out.print(" -> "+L[i]);}

		}
		System.out.println();
		System.out.println();

	}

	
	
}



