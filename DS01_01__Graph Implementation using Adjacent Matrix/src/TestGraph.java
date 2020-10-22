import java.io.*;
import java.util.StringTokenizer;

public class TestGraph {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		/*
		Graph g = new Graph(new String[] {"A","B","C","D","E"});
		System.out.println(g);
		g.add("A","B");
		g.add("A","C");
		g.add("B","C");
		g.add("B","D");
		g.add("C","D");
		g.add("D","E");
		System.out.println(g);
		g.degree("A");
		g.degree("B");
		g.degree("E");
		g.findPath("B", "D");
		System.out.println();
		 */
		try {

			BufferedReader reader = new BufferedReader ( new FileReader("C:\\Users\\LG\\Desktop\\자료구조설계\\1주차\\graph.txt"));
			String line = reader.readLine();
			Graph g2 = null;

			int linenum=0;
			int i=0;

			String[] lineSplit =new String[0];
			String[] wordSplit =new String[2];
			if(line==null) {g2 = new Graph(lineSplit);}
			else {
				while(line != null) { 

					StringTokenizer parser = new StringTokenizer (line," ");
					linenum++;

					if(linenum==1) { 
						while (parser.hasMoreTokens()) {
							String vertex = parser.nextToken();

							if(i == lineSplit.length ) {
								lineSplit = resize(lineSplit);
							}
							lineSplit[i] = vertex;
							i++;

						}
						g2 = new Graph(lineSplit);
						//정점이 잘 입력되었나 프린트
						System.out.println(g2);
					}
					else {
						int wordnum=0;
						while (parser.hasMoreTokens()) {
							String word = parser.nextToken();
							if(wordnum==0) {
								wordSplit[wordnum++] = word;}
							else {wordSplit[wordnum--] = word;}
						}
						g2.add(wordSplit[0],wordSplit[1]);
					}
					line = reader.readLine();
				}
			}
			//정점이 간선연결까지 잘 되었나 프린트
			System.out.println(g2);
			//각 정점의 입접한 정점 개수 프린트
			g2.degree("A");
			g2.degree("B");
			g2.degree("D");
			g2.degree("K");
			g2.degree("G");
			//서로 다른 두정점에 대해 길이가 2인 경로가 있는 지 확인하고 있으면 프린트
			g2.findPath("A", "B");
			g2.findPath("C", "F");
			g2.findPath("G", "A");
			g2.findPath("D", "C");
			g2.findPath("A", "F");


		}
		catch(IOException e) {System.out.print(e);}


	}

	public static String[] resize(String[] a) {
		String[] aa = new String[a.length+1];
		System.arraycopy(a, 0, aa, 0, a.length);
		return aa;
	}

}
