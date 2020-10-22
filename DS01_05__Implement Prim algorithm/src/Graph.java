import java.io.*;
import java.util.StringTokenizer;

import javax.sound.midi.Synthesizer;



public class Graph {

	public final int MAX = 128;

	int size;
	int edgenum;
	float[][] a;
	float [][] edge;

	public Graph (String filepath) {

		int u=0,v=0,weight=0;
		try {
			BufferedReader reader = new BufferedReader ( new FileReader(filepath));
			String line = reader.readLine();
			int num=0; 
			int linenum=0; // 줄번호

			String[] wordSplit =new String[3];

			if(line == null) {
				a = null;
			}

			while(line != null) { 

				StringTokenizer parser = new StringTokenizer (line," ");
				linenum++;

				if(linenum==1) { 
					while (parser.hasMoreTokens()) {
						String st = parser.nextToken();
						if(num==0) {
							size = Integer.parseInt(st); 
							num++;

							a = new float[size][size];
							edge = new float[size-1][3];

							for(int i=0 ; i < size ;i++) {
								for(int j =0;j<size;j++) {
									if(i==j) {a[i][j] = 0;}
									else {
										a[i][j] =MAX;
									}
								}

							}
							for(int i=0 ; i < size-1 ;i++) {
								edge[i][0] = -1;
								edge[i][1] = -1;
								edge[i][2] = MAX;
							}
						}
						else {
							edgenum= Integer.parseInt(st); 
							num=0;}

					}

				}
				else {
					int wordnum=0;
					while (parser.hasMoreTokens()) {
						String word = parser.nextToken();
						if(wordnum==0) {
							u= Integer.parseInt(word); 
							wordnum++;
						}
						else if(wordnum==1) {
							v= Integer.parseInt(word);
							wordnum++;}
						else {
							weight= Integer.parseInt(word);
							wordnum=0;
							add(u,v,weight);
						}

					}
				}
				line = reader.readLine();
			}


		}
		catch(IOException e) {System.out.print(e);}


	}


	public void add(int u,int v,int weight) {
		if(size <= v || size <= u) {
			System.out.println("간선 연결 오류 : 정점의 개수보다 큰 수로 정점을 생성하지 않습니다."); 
			return ;
		}
		else if(v == u) {
			System.out.println("간선 연결 오류 : 자신에게 연결할수없습니다."); 
			return ;
		}
		else if(MAX <= weight) {
			System.out.println("간선 연결 오류 : 가중치값이 최대값을 넘었습니다."); 
			return ;
		}

		a[v][u] = a[u][v] = weight;
	}



	public String toString() {
		if(size ==0 )return "{}";
		StringBuffer buf = new StringBuffer("{"+vertex(0));
		for(int i = 1; i< size ; i++)
			buf.append(","+vertex(i));
		return buf + "}";
	}

	private String vertex(int i) {
		StringBuffer buf = new StringBuffer(i+":");
		for(int j=0;j<size;j++)
			if(i!=j && a[i][j] != MAX) buf.append(j);
		return buf+"";
	}

	public void prim(int x) {
		/*
		 * included 배열 생성 : 정점 장문을 했는지 안했는지
		 * near 배열 생성 : 방문된 정점들이 뻗어있는 간선들 중 최소값을 찾기위한 배열
		 * 
		 * 시작 정점 x
		 * x를 방문했다고 표시
		 * x의 열 중 (0이 아닌) 가장 작은 가중치를 찾아 near에 넣는다. 
		 * 
		 * near[i]의 가중치값에 연결된 정점y를 트리에 넣고 방문했다고 표시
		 * x의 near값을 변경하고 y의 near값을 찾는다.
		 * near들중의 최소값을 찾고
		 * 
		 * */

		//boolean []included = new boolean[size]; //정점이 최소비용신장트리에 포함되었는지 확인한다.

		int []near = new int[size]; //인접한 정점을 넣는다. 인접정점이 없는경우 : -1 / 방문정점인경우 : MAX


		int miniver = x; //최소비용신장트리에 포함되어있는 정점들이 포함되지 않은 정점들에 뻗어있는 가중치값 중에 최소값을 가지는 간선에 연결되어있는 포함되어있지않은 정점
		int trueedgenum=0;  //트리에 넣은 간선의 개수
		int cost=0;//비용
		int ver=MAX;
		int min=MAX; //near중에서 제일 작은 가중치

		if(size == 0 || edgenum < size-1) { //만약 empty트리거나, 간선이 정점-1보다 작을경우(연결그래프가 아닐경우) 트리를 만들수없다고 메세지를 출력하고 종료.
			System.out.println("최소비용신장트리를 만들수 없습니다.");
			return;}

		for(int i=0;i<size;i++) { //초기화
			near[i] = -1; 
		}

		near[x] = MAX; //near[x]에 MAX값을 넣는다.(: miniver정점이 최소비용신장트리에 포함이 되었으므로 MAX값을 넣어준다. )



		while(trueedgenum != size-1) { //트리에 넣어진 간선의 수가 정점-1일때 반복문을 종료한다.



			for(int i=0;i<size;i++) {

			
				if(near[i] == MAX) {}
				else if(near[i] == -1 ) { //near[i]가 최소비용신장트리 정점들과 연결되어 있지않다고 표시되어있다면 
					if(a[miniver][i]!=MAX) {near[i] =miniver;} //i정점이 miniver과 chnearver과 연결되어있지않다면 그냥 -1값을 넣어둔다.
				}
				else { //i정점이 트리에 포함되어있지않다면
					if ( a[miniver][i] < a[i][near[i]])   {
						// i정점이 near[i]정점과의 가중치값 보다 miniver정점과의 가중치값이 더 작을경우
						near[i] =miniver;//near[i]에 miniver정점을 넣는다.
					}
				}

				//
				if(near[i] == -1 || near[i] ==MAX) {} //near가 -1이나 MAX값일경우
				else if(a[i][near[i]] < min) { //a[i][near[i]] 값이 (가중치값) min보다 작을경우
					ver = i;
					min = (int) a[i][near[i]];
				}

			}

			miniver = ver; 
			if(min == MAX) {break;}

			edge[trueedgenum][0] = near[miniver];
			edge[trueedgenum][1] = miniver;
			edge[trueedgenum++][2] = min;	

			near[miniver] = MAX; //near[miniver]에 MAX값을 넣는다.(: miniver정점이 최소비용신장트리에 포함이 되었으므로 MAX값을 넣어준다. )
			

			cost += min;
			min = MAX;

		}

		if( trueedgenum < size-1 ) {
			System.out.println();
			System.out.println("최소비용신장트리를 만들수 없습니다.");
			return;}

		System.out.println("선택된 간선들");

		for(int i=0;i<size-1;i++) {
			System.out.print("("+(int)edge[i][0]+", "+(int)edge[i][1]+"): "+(int)edge[i][2]+"  ");
		}

		System.out.println();
		System.out.println("총 비용 : "+cost);

	}
}