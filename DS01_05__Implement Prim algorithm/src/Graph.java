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
			int linenum=0; // �ٹ�ȣ

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
			System.out.println("���� ���� ���� : ������ �������� ū ���� ������ �������� �ʽ��ϴ�."); 
			return ;
		}
		else if(v == u) {
			System.out.println("���� ���� ���� : �ڽſ��� �����Ҽ������ϴ�."); 
			return ;
		}
		else if(MAX <= weight) {
			System.out.println("���� ���� ���� : ����ġ���� �ִ밪�� �Ѿ����ϴ�."); 
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
		 * included �迭 ���� : ���� �幮�� �ߴ��� ���ߴ���
		 * near �迭 ���� : �湮�� �������� �����ִ� ������ �� �ּҰ��� ã������ �迭
		 * 
		 * ���� ���� x
		 * x�� �湮�ߴٰ� ǥ��
		 * x�� �� �� (0�� �ƴ�) ���� ���� ����ġ�� ã�� near�� �ִ´�. 
		 * 
		 * near[i]�� ����ġ���� ����� ����y�� Ʈ���� �ְ� �湮�ߴٰ� ǥ��
		 * x�� near���� �����ϰ� y�� near���� ã�´�.
		 * near������ �ּҰ��� ã��
		 * 
		 * */

		//boolean []included = new boolean[size]; //������ �ּҺ�����Ʈ���� ���ԵǾ����� Ȯ���Ѵ�.

		int []near = new int[size]; //������ ������ �ִ´�. ���������� ���°�� : -1 / �湮�����ΰ�� : MAX


		int miniver = x; //�ּҺ�����Ʈ���� ���ԵǾ��ִ� �������� ���Ե��� ���� �����鿡 �����ִ� ����ġ�� �߿� �ּҰ��� ������ ������ ����Ǿ��ִ� ���ԵǾ��������� ����
		int trueedgenum=0;  //Ʈ���� ���� ������ ����
		int cost=0;//���
		int ver=MAX;
		int min=MAX; //near�߿��� ���� ���� ����ġ

		if(size == 0 || edgenum < size-1) { //���� emptyƮ���ų�, ������ ����-1���� �������(����׷����� �ƴҰ��) Ʈ���� ��������ٰ� �޼����� ����ϰ� ����.
			System.out.println("�ּҺ�����Ʈ���� ����� �����ϴ�.");
			return;}

		for(int i=0;i<size;i++) { //�ʱ�ȭ
			near[i] = -1; 
		}

		near[x] = MAX; //near[x]�� MAX���� �ִ´�.(: miniver������ �ּҺ�����Ʈ���� ������ �Ǿ����Ƿ� MAX���� �־��ش�. )



		while(trueedgenum != size-1) { //Ʈ���� �־��� ������ ���� ����-1�϶� �ݺ����� �����Ѵ�.



			for(int i=0;i<size;i++) {

			
				if(near[i] == MAX) {}
				else if(near[i] == -1 ) { //near[i]�� �ּҺ�����Ʈ�� ������� ����Ǿ� �����ʴٰ� ǥ�õǾ��ִٸ� 
					if(a[miniver][i]!=MAX) {near[i] =miniver;} //i������ miniver�� chnearver�� ����Ǿ������ʴٸ� �׳� -1���� �־�д�.
				}
				else { //i������ Ʈ���� ���ԵǾ������ʴٸ�
					if ( a[miniver][i] < a[i][near[i]])   {
						// i������ near[i]�������� ����ġ�� ���� miniver�������� ����ġ���� �� �������
						near[i] =miniver;//near[i]�� miniver������ �ִ´�.
					}
				}

				//
				if(near[i] == -1 || near[i] ==MAX) {} //near�� -1�̳� MAX���ϰ��
				else if(a[i][near[i]] < min) { //a[i][near[i]] ���� (����ġ��) min���� �������
					ver = i;
					min = (int) a[i][near[i]];
				}

			}

			miniver = ver; 
			if(min == MAX) {break;}

			edge[trueedgenum][0] = near[miniver];
			edge[trueedgenum][1] = miniver;
			edge[trueedgenum++][2] = min;	

			near[miniver] = MAX; //near[miniver]�� MAX���� �ִ´�.(: miniver������ �ּҺ�����Ʈ���� ������ �Ǿ����Ƿ� MAX���� �־��ش�. )
			

			cost += min;
			min = MAX;

		}

		if( trueedgenum < size-1 ) {
			System.out.println();
			System.out.println("�ּҺ�����Ʈ���� ����� �����ϴ�.");
			return;}

		System.out.println("���õ� ������");

		for(int i=0;i<size-1;i++) {
			System.out.print("("+(int)edge[i][0]+", "+(int)edge[i][1]+"): "+(int)edge[i][2]+"  ");
		}

		System.out.println();
		System.out.println("�� ��� : "+cost);

	}
}