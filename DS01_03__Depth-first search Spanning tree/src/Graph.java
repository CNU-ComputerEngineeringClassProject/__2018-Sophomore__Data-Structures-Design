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
			System.out.println("간선 연결 오류 : 입력하신 정점 중에 찾을 수 없는 정점이 있습니다."); 
			return ;} 
		else if(index(v)==index(w)) {
			System.out.println("간선 연결 오류 : 자신에게 연결할수없습니다."); 
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

	//각 정점에 인접한 정점들의 수를 프린트 하는 메소드
	public void degree(String v) {
		int vdegree =0;
		//오류출력
		if(index(v)==a.length) {
			System.out.println("오류 : 입력하신 정점을 찾을수 없습니다.");
			return ;
		} 

		for(int i =0; i<size;i++) {
			if(index(v) == i) {} 
			else if(a[index(v)][i] == true) {vdegree++;}
		}
		System.out.println("정점 "+v+"에 인접한 정점의 수는 " +vdegree+"개 입니다.");
	}

	//서로다른 두 정점에 대해 길이가 2인 경로가 존재하는지 프린트하는 메소드
	public void findPath(String v,String w) {
		int true2path = 0;

		if(index(v)==a.length||index(w)==a.length) {System.out.println("오류 : 입력하신 정점 중에 찾을 수 없는 정점이 있습니다."); return ;} 
		else if(index(v)==index(w)) {System.out.println("오류 : 같은 정점끼리는 길이가 2인 경로가 존재하지 않습니다. "); return ;}

		for(int i =0; i<size;i++) {
			if(a[i][index(v)] == true && a[i][index(w)] == true) {
				System.out.println(v + "-" +vertices[i]+"-"+w);
				true2path++;
			}
		}
		if(true2path ==0) {System.out.println("두 정점 "+v+","+w+"에 대해 길이가 2인 경로가 존재하지 않습니다.");}
	}

	public void recu_dfs(String v) {


		int i=0; //반복문 사용을 위한 변수 선언

		if(num==0) {//시작정점일경우
			for(i = 0 ; i<size ; i++) {//방문배열과 L초기화
				L[i] = null;
				visited[i] = false;
			}

			System.out.println();
			System.out.print("DFS :");
		}

		visited[index(v)] = true; //정점을 방문했다고 표시한다.
		L[num++]=v; //  L[num]에 v를 넣는다. num을 하나 증가시킨다.
		System.out.print(" "+v); //방금 방문한 정점을 출력한다.

		for(i=0 ; i<size ; i++) { //반복문을 사용한다.
			if(a[index(v)][i]) { //만약 v정점에 인접한 정점이 있다면
				if(visited[i]) {} //이미 방문한 정점이라면 그냥 지나간다.
				else { //방문하지 않은 정점이라면
					recu_dfs(vertices[i]); //재귀를 이용하여 인접한 정점의 recu_dfc()를 실행한다.
				}
			}
		}

		if(L[0]==v) { //재귀가 다 끝나고 시작정점이 v인 함수로 돌아왔다면
			num=0; //num을 0으로 초기화 시킨다.(다음에 함수를 호출할때도 실행 될수 있도록)
			return;}

	}

	public void recu_dfs_tree(String v) {

		int i=0; //반복문 사용을 위한 변수 선언

		if(num1==0) { //시작정점일경우
			for(i = 0 ; i<size ; i++) {//방문배열과 리스트L 초기화
				L[i] = null;
				visited[i] = false;
			}
		}
		L[num1++] = v;// L배열 num1자리에 v를 넣는다. 그리고 num1을 하나 증가시킨다.

		visited[index(v)] = true; //v를 방문했다고 표시한다.

		for(i=0 ; i<size ; i++) {//반복문을 사용한다.
			if(a[index(v)][i]) {//만약 v정점에 인접한 정점이 있다면
				if(visited[i]) {}//이미 방문한 정점이라면 그냥 지나간다.
				else {//방문하지 않은 정점이라면
					System.out.println("정점 "+vertices[i]+"는 정점" +v+"의 자식입니다");//인접한정점이 v의 자식임을 나타내는 메세지를 출력한다.
					recu_dfs_tree(vertices[i]);//재귀를 이용하여 인접한 정점의 recu_dfc()를 실행한다.
				}
			}
		}
		if(L[0] == v) { //재귀가 다 끝나고 시작정점이 v인 함수로 돌아왔다면
			System.out.print("DFS :"); //방문순서를 출력한다.
			for(i=0; i<size ; i++) {
				if(L[i]!=null) {System.out.print(" "+L[i]);}
			}
			System.out.println();
			System.out.println();
			num1=0; //num1을 0으로 초기화 시킨다.(다음에 함수를 호출할때도 실행 될수 있도록)
		}

	}


	public void dfs_tree(String v) {

		Stack<String> s = new Stack<String>(); //stack 객체 생성
		String[] parent = new String[size]; //부모를 저장할 배열 생성

		int i,j; //반복문을 위한 변수
		int check =0; //방문한 정점이 리스트에 있는지 확인을 위한 변수
		int num3=0; //리스트에 넣은 정점을 개수를 확인하기 위한 변수

		String x = null; //stack에서 pop했을때 저장할 변수
		String y = null; //x에 인접한 정점중 방문하지 않은 정점을 저장할 변수
 
		if(num3==0) { //시작정점일 경우
			for(i = 0 ; i<size ; i++) {//초기화를 위한 반복문 
				L[i] = null;
				visited[i] = false;
				parent[i]=null;
			}
		}

		visited[index(v)] =true; //시작정점을 방문했다 체크한다.
		s.push(v);//스텍에 v를 넣는다.

		while(!s.empty()) {//스텍이 비워져있지 않을때동안 반복한다.
			x = s.pop(); //x에 s.pop()값을 넣는다.
			
			if( num3!=0 ) { //시작정점이 아닌경우 누구의 자식인지 출력한다.
				System.out.println("정점 "+x+"는 정점" +parent[index(x)]+"의 자식입니다");
			}
			
			L[num3++]=x; //num3번째 L배열에 x값을 넣는다. 그리고 num3값을 하나 증가시킨다.
			
			for(i=size-1;i>=0;i--) { //반복문 실행
				if(a[index(x)][i]) { //정점 x에 인접한 정점이 있다면
					if(!visited[i]) { //그중 방문하지 않은 정점이 있다면
						y=vertices[i]; //y에 정점을 저장한다.
						parent[i] = x; //y의 부모로 x를 저장한다.
						visited[i]= true; //정점을 방문했다고 표시한다.
						s.push(y); //스텍에 y를 넣는다.
					}
					else { //방문한 정점이라면
						for(j=0;j<num3;j++) { //반복문 실행
							if(L[j]==vertices[i]) { //정점이 리스트에 넣어져있다면
								check++; //체크를하고
								break;} //반복문을 끝낸다.
						}
						if(check==0) {parent[i] = x;} 
						//반복문이 끝났는데 check가 0이라면 아직 스텍에 있다는 것이므로 vertices[i]의 부모로 x를 저장한다.
						check=0; //check를 0으로 만든다.
					}
				}
			}
		}

		System.out.print("방문 순서 : "); //방문순서를 출력한다.
		System.out.print(L[0]);
		for(i=1; i<size ; i++) {
			if(L[i]!=null) {System.out.print(" -> "+L[i]);}

		}
		System.out.println();
		System.out.println();

	}

	
	
}



