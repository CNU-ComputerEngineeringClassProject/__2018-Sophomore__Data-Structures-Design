import java.io.*;
import java.util.StringTokenizer;

public class TestGraph {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		try {

			BufferedReader reader = new BufferedReader ( new FileReader("C:\\Users\\LG\\Desktop\\자료구조설계\\graph.txt"));
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
//	
			g2.recu_dfs(g2.vertices[0]);
			System.out.println();
			System.out.println();
			g2.recu_dfs_tree(g2.vertices[0]);
			g2.dfs_tree(g2.vertices[0]);

		
		

		}
		catch(IOException e) {System.out.print(e);}


	}

	public static String[] resize(String[] a) {
		String[] aa = new String[a.length+1];
		System.arraycopy(a, 0, aa, 0, a.length);
		return aa;
	}

}
