import java.io.*;
import java.util.StringTokenizer;

public class Graph {

	Edge a[];
	int vertexnum; 
	int edgenum; 
	int parent[]; 

	private class Edge {
		int v,w,weight; //두 정점과 가중치 필드
		boolean selected; //최소 신장 트리에 적용 판단

		public Edge(int v, int w,int weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;
			this.selected = false;
		}


	}

	public Graph(String filepath) {
		int v=0,w=0,weight=0;
		try {
			BufferedReader reader = new BufferedReader ( new FileReader(filepath));
			String line = reader.readLine();
			int num=0; 
			int linenum=0; // 줄번호

			String[] wordSplit =new String[3];

			if(line == null) {
				a = null;
				parent = null;
			}

			while(line != null) { 

				StringTokenizer parser = new StringTokenizer (line," ");
				linenum++;

				if(linenum==1) { 
					while (parser.hasMoreTokens()) {
						String st = parser.nextToken();
						if(num==0) {
							vertexnum= Integer.parseInt(st); 
							parent = new int[vertexnum];
							num++;}
						else {
							edgenum= Integer.parseInt(st); 
							a = new Edge[edgenum];
							num=0;}

					}

				}
				else {
					int wordnum=0;
					while (parser.hasMoreTokens()) {
						String word = parser.nextToken();
						if(wordnum==0) {
							v= Integer.parseInt(word); 
							wordnum++;
						}
						else if(wordnum==1) {
							w= Integer.parseInt(word);
							wordnum++;}
						else {
							weight= Integer.parseInt(word);
							wordnum=0;
							a[linenum-2]=new Edge(v,w,weight);
						}

					}
				}
				line = reader.readLine();
			}
			if(parent != null) {
				for(int i=0;i<parent.length;i++)
				{parent[i] = -1;}
			}

		}
		catch(IOException e) {System.out.print(e);}


	}

	public void weightedunion (int set1, int set2) {
	
		int a=set1;
		int b=set2;


		if(parent[a] < parent[b]){
			parent[a] += parent[b];
			parent[b] = a;
		
		}

		else {
			parent[b] += parent[a];
			parent[a] = b;
			
		}
		
	}

	public int collapsingfind(int set) {
		
		int a = set;
		int b = set;

		while(parent[a]>=0) {
			a =parent[a];
		}

		while((a != b)&&(parent[b] != a)){
			parent[b] = a;
			parent[a] -= 1;
			b = parent[b];
		}

		return a;
	}

	public void kruskal() {

		Edge b;

		int truenum=0;
		int cost = 0;
		int i=0,j=0;
		int set1,set2;

		if((a==null)||((edgenum < vertexnum-1)) ) {
			System.out.println("최소비용신장트리가 만들어 지지않았습니다.");
			return ;
		}

		for(i=0;i < a.length ;i++) {
			for(j=i;j<a.length;j++) {

				if(a[i].weight > a[j].weight) {
					b = a[i];
					a[i] = a[j];
					a[j] = b;
				}

			}
		}


		for(i=0;i < a.length ;i++) {
			set1 =collapsingfind(a[i].v);
			set2 =collapsingfind(a[i].w);
			if( set1 != set2) {
				weightedunion(set1,set2);
				a[i].selected=true;
				truenum++;
			}
			if(vertexnum-1 == truenum) break;
		}
		if(truenum != vertexnum-1) {System.out.println("최소비용신장트리가 만들어 지지않았습니다.");}
		else {
			System.out.println("- 최소 신장 트리에 포함된 간선");
			for(i=0;i < a.length ;i++) {
				if(a[i].selected==true) {
					System.out.print("( "+a[i].v+" , "+a[i].w+" ) ");
					cost += a[i].weight;
				}
			}
			System.out.println();
			System.out.println("- Min cost : "+cost);
		}

	}

}
