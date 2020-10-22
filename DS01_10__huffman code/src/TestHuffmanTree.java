import java.io.*;
import java.util.StringTokenizer;

public class TestHuffmanTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filepath = "C:\\\\Users\\\\LG\\\\Desktop\\\\자료구조설계\\\\text.txt";
		HuffmanTree tree = new HuffmanTree(filepath);
		tree.treeprintcode();

		try {
			BufferedReader printread = new BufferedReader ( new FileReader(filepath));
			String firstline = printread.readLine();
			tree.linecode(firstline);

		}
		catch(IOException e) {System.out.print(e);}

	}

}