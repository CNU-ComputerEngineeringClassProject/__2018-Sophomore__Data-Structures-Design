import java.io.*;
import java.util.StringTokenizer;

public class TestHashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinearProbingHashTable linearh = new LinearProbingHashTable();
		QuadraticProbingHashTable quadh = new QuadraticProbingHashTable();
		DoubleHashingHashTable doubleh = new DoubleHashingHashTable();
		SeparateChainingHashTable Separate = new SeparateChainingHashTable();

		try {

			BufferedReader reader = new BufferedReader ( new FileReader("C:\\Caesar.txt"));

			String line = reader.readLine();

			while(line != null) { 

				StringTokenizer parser = new StringTokenizer (line," ");

				linearh.put(line.toUpperCase(),1);
				quadh.put(line.toUpperCase(),1);
				doubleh.put(line.toUpperCase(),1);
				Separate.put(line.toUpperCase(),1);

				line = reader.readLine();

			}
			System.out.println("***** �� ���̺��� ���� �ܾ���� �� (value) : < I You he Brutus evil the and >");
			String wordi = "I".toUpperCase();
			System.out.println("I	:" + " ��������("+linearh.get(wordi)+"), ��������("+quadh.get(wordi)+"), �����ؽ�("+doubleh.get(wordi)+"), ��� �ּҹ�("+Separate.get(wordi)+")");

			String wordu = "You".toUpperCase();
			System.out.println("You	:" + " ��������("+linearh.get(wordu)+"), ��������("+quadh.get(wordu)+"), �����ؽ�("+doubleh.get(wordu)+"), ��� �ּҹ�("+Separate.get(wordu)+")");

			String wordh = "he".toUpperCase();
			System.out.println("he	:" + " ��������("+linearh.get(wordh)+"), ��������("+quadh.get(wordh)+"), �����ؽ�("+doubleh.get(wordh)+"), ��� �ּҹ�("+Separate.get(wordh)+")");

			String wordbr ="Brutus".toUpperCase();
			System.out.println("Brutus	:" + " ��������("+linearh.get(wordbr)+"), ��������("+quadh.get(wordbr)+"), �����ؽ�("+doubleh.get(wordbr)+"), ��� �ּҹ�("+Separate.get(wordbr)+")");

			String worde = "evil".toUpperCase();
			System.out.println("evil	:" + " ��������("+linearh.get(worde)+"), ��������("+quadh.get(worde)+"), �����ؽ�("+doubleh.get(worde)+"), ��� �ּҹ�("+Separate.get(worde)+")");

			String wordthe = "the".toUpperCase();
			System.out.println("the	:" + " ��������("+linearh.get(wordthe)+"), ��������("+quadh.get(wordthe)+"), �����ؽ�("+doubleh.get(wordthe)+"), ��� �ּҹ�("+Separate.get(wordthe)+")");

			String wordand = "and".toUpperCase();
			System.out.println("and	:" + " ��������("+linearh.get(wordand)+"), ��������("+quadh.get(wordand)+"), �����ؽ�("+doubleh.get(wordand)+"), ��� �ּҹ�("+Separate.get(wordand)+")");

			System.out.println();
			System.out.println("�������� �� �浹 Ƚ�� : "+linearh.get_count());
			System.out.println("�������� �� �浹 Ƚ�� : "+quadh.get_count());
			System.out.println("�����ؽ� �� �浹 Ƚ�� : "+doubleh.get_count());




		}
		catch(IOException e) {System.out.print(e);}


	}

}

