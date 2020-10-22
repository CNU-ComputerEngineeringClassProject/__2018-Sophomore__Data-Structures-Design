
public class Graph {
	private int size;
	private String[] vertices;
	private Node[] a;

	private static class Node{
		Node next;
		int to;

		Node(int to) {
			this.to =to;
		}
		Node(int to,Node next){
			this.next =next;
			this.to =to;
		}
	}

	public Graph(String[] args) {
		size = args.length;

		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);

		a = new Node[size];

		for(int i=0;i<size;i++) {
			a[i] = new Node(index(args[i]));
		}
	}

	public void add(String v, String w) {
		int i = index(v), j=index(w);

		if(index(v)==a.length||index(w)==a.length) {
			System.out.println("간선 연결 오류 : 입력하신 정점 중에 찾을 수 없는 정점이 있습니다."); 
			return ;} 
		else if(index(v)==index(w)) {
			System.out.println("간선 연결 오류 : 자신에게 연결할수없습니다."); 
			return ;
		}

		a[i].next = new Node(j,a[i].next);
		a[j].next = new Node(i,a[j].next);

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
		Node p;
		
		StringBuffer buf = new StringBuffer(vertices[i]+":");

		if(a[i].next!=null) {
			for(p= a[i].next; p.next != null ; p=p.next){
				buf.append(vertices[p.to]);
			}
			buf.append(vertices[p.to]);
			
		}
		return buf+"";
	}

	//각 정점에 인접한 정점들의 수를 프린트 하는 메소드
/*	public void degree(String v) {
		int vdegree =0;
		//오류출력
		if(index(v)==a.length) {
			System.out.println("오류 : 입력하신 정점을 찾을수 없습니다.");
			return ;
		} 
		for(Node p= a[index(v)]; p.next != null ; p=p.next){
			vdegree++;
		}
		System.out.println("정점 "+v+"에 인접한 정점의 수는 " +vdegree+"개 입니다.");

	}*/

	//서로다른 두 정점에 대해 길이가 2인 경로가 존재하는지 프린트하는 메소드
	public void findPath(String v,String w) {
		int true2path = 0;
		int pathvertex=0;
		int x=index(v),y=index(w);
		Node p;

		if(x==a.length||y==a.length) {System.out.println("오류 : 입력하신 정점 중에 찾을 수 없는 정점이 있습니다."); return ;} 
		else if(x==y) {System.out.println("오류 : 같은 정점끼리는 길이가 2인 경로가 존재하지 않습니다. "); return ;}
		for(int i =0; i < size;i++) {
			pathvertex=0;
			if(a[i].next != null) {
				for(p= a[i].next; p.next != null ; p=p.next){
					if(p.to == x) {pathvertex++;}
					else if(p.to == y) {pathvertex++;}
				}
				if(p.to == x) {pathvertex++;}
				else if(p.to == y) {pathvertex++;} 
				if(pathvertex==2) {
					System.out.println("{"+v + " -> " +vertices[i]+" -> "+w+"}");
					true2path++;
				}

			}
		}

		if(true2path ==0) {System.out.println("두 정점 "+v+","+w+"에 대해 길이가 2인 경로가 존재하지 않습니다.");}
	}
}
