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
			System.out.println("***** °¢ Å×ÀÌºí¿¡¼­ ´ÙÀ½ ´Ü¾îµéÀÇ °ª (value) : < I You he Brutus evil the and >");
			String wordi = "I".toUpperCase();
			System.out.println("I	:" + " ¼±ÇüÁ¶»ç("+linearh.get(wordi)+"), Á¦°öÁ¶»ç("+quadh.get(wordi)+"), ÀÌÁßÇØ½Ì("+doubleh.get(wordi)+"), Æó¼â ÁÖ¼Ò¹ı("+Separate.get(wordi)+")");

			String wordu = "You".toUpperCase();
			System.out.println("You	:" + " ¼±ÇüÁ¶»ç("+linearh.get(wordu)+"), Á¦°öÁ¶»ç("+quadh.get(wordu)+"), ÀÌÁßÇØ½Ì("+doubleh.get(wordu)+"), Æó¼â ÁÖ¼Ò¹ı("+Separate.get(wordu)+")");

			String wordh = "he".toUpperCase();
			System.out.println("he	:" + " ¼±ÇüÁ¶»ç("+linearh.get(wordh)+"), Á¦°öÁ¶»ç("+quadh.get(wordh)+"), ÀÌÁßÇØ½Ì("+doubleh.get(wordh)+"), Æó¼â ÁÖ¼Ò¹ı("+Separate.get(wordh)+")");

			String wordbr ="Brutus".toUpperCase();
			System.out.println("Brutus	:" + " ¼±ÇüÁ¶»ç("+linearh.get(wordbr)+"), Á¦°öÁ¶»ç("+quadh.get(wordbr)+"), ÀÌÁßÇØ½Ì("+doubleh.get(wordbr)+"), Æó¼â ÁÖ¼Ò¹ı("+Separate.get(wordbr)+")");

			String worde = "evil".toUpperCase();
			System.out.println("evil	:" + " ¼±ÇüÁ¶»ç("+linearh.get(worde)+"), Á¦°öÁ¶»ç("+quadh.get(worde)+"), ÀÌÁßÇØ½Ì("+doubleh.get(worde)+"), Æó¼â ÁÖ¼Ò¹ı("+Separate.get(worde)+")");

			String wordthe = "the".toUpperCase();
			System.out.println("the	:" + " ¼±ÇüÁ¶»ç("+linearh.get(wordthe)+"), Á¦°öÁ¶»ç("+quadh.get(wordthe)+"), ÀÌÁßÇØ½Ì("+doubleh.get(wordthe)+"), Æó¼â ÁÖ¼Ò¹ı("+Separate.get(wordthe)+")");

			String wordand = "and".toUpperCase();
			System.out.println("and	:" + " ¼±ÇüÁ¶»ç("+linearh.get(wordand)+"), Á¦°öÁ¶»ç("+quadh.get(wordand)+"), ÀÌÁßÇØ½Ì("+doubleh.get(wordand)+"), Æó¼â ÁÖ¼Ò¹ı("+Separate.get(wordand)+")");

			System.out.println();
			System.out.println("¼±ÇüÁ¶»ç ÃÑ Ãæµ¹ È½¼ö : "+linearh.get_count());
			System.out.println("Á¦°öÁ¶»ç ÃÑ Ãæµ¹ È½¼ö : "+quadh.get_count());
			System.out.println("ÀÌÁßÇØ½Ì ÃÑ Ãæµ¹ È½¼ö : "+doubleh.get_count());




		}
		catch(IOException e) {System.out.print(e);}


	}

}

