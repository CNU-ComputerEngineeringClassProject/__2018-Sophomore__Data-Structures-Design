import java.io.*;
import java.util.StringTokenizer;

public class TestHashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Map map = new java.util.HashMap();
		
		try {

			BufferedReader reader = new BufferedReader ( new FileReader("C:\\Caesar.txt"));

			String line = reader.readLine();

			while(line != null) { 

				StringTokenizer parser = new StringTokenizer (line," ");
			
				if(map.	containsKey(line.toUpperCase())) {
					map.put(line.toUpperCase(),(int)map.get(line.toUpperCase()) + 1);
				}
				else {
						map.put(line.toUpperCase(),1);
				}
				
		
				line = reader.readLine();
				
			}

			System.out.println("***** HashMap에서 다음 단어들의 값 (value) : < I You he Brutus evil the and >");
			
			String wordi = "I".toUpperCase();
			System.out.println("I	: "+map.get(wordi));

			String wordu = "You".toUpperCase();
			System.out.println("You	: "+map.get(wordu));

			String wordh = "he".toUpperCase();
			System.out.println("he	: " +map.get(wordh));
			
			String wordbr ="Brutus".toUpperCase();
			System.out.println("Brutus	: " +map.get(wordbr));

			String worde = "evil".toUpperCase();
			System.out.println("evil	: " +map.get(worde));
					
			String wordthe = "the".toUpperCase();
			System.out.println("the	: " + map.get(wordthe));
			
			String wordand = "and".toUpperCase();
			System.out.println("and	: " +  map.get(wordand));
			

		}
		catch(IOException e) {System.out.print(e);}


	}

}