import java.io.*;
import java.util.StringTokenizer;

public class HuffmanTree {


	public Object[][]a;

	public Object key;
	public int num;
	private HuffmanTree left,right;

	public MinHeap q;

	private HuffmanTree(Object key , int num) {
		this.key = key;
		this.num = num;
		left = right = null;
	}

	private HuffmanTree(Object key , int num,HuffmanTree left, HuffmanTree right) {
		this.key = key;
		this.num = num;
		this.left = left;
		this.right = right;
	}

	public HuffmanTree (String filepath) {
		try {
			BufferedReader reader = new BufferedReader ( new FileReader(filepath));
			String line = reader.readLine();

			a = new Object[1][3];
			int linele =0;

			if(line == null) a = null;

			while(line != null) { 
				linele=0;
				while( linele != line.length()) {
					char c = line.charAt(linele); 
					linele++;
					char chan = Character.toUpperCase(c);
					frequencyadd(chan);
				}
				line = reader.readLine();
			}


			if(a != null) {
				HuffmanTree suc;
				PriorityQueuefirst();

				while(q.size() != 1) merge();

				suc = (HuffmanTree)q.remove(); //큐에 완성된 허프만트리 하나만 넣어져 있으므로 마지막으로 큐에서 꺼낸다.
				this.key = suc.key;
				this.num = suc.num;
				this.left = suc.left;
				this.right = suc.right;

				for(int soridx=0 ; soridx < a.length ; soridx++) {
					HuffmanCode(this, a[soridx][0],soridx ,null);
				}
			}
		}
		catch(IOException e) {System.out.print(e);}



	}

	public void linecode(String line) {
		if(a==null) {
			System.out.println("text에 내용이 없습니다.");
			return;
		}
		int linele=0;
		System.out.println("Encoding this String : "+line);
		System.out.print("Huffman Code Stream : ");
		while( linele != line.length()) {
			char c = line.charAt(linele); 
			linele++;
			char chan = Character.toUpperCase(c);
			for(int seaidx = 0 ; seaidx < a.length ; seaidx++ ) {
				if(chan == (char)a[seaidx][0])
					System.out.print(a[seaidx][2]);
			}
		}
		System.out.println();
	}


	public void treeprintcode() {
		if(a==null) {
			System.out.println("text에 내용이 없습니다.");
			return;
		}
		for(int seaidx = 0 ; seaidx < a.length ; seaidx++ ) {
			System.out.println(a[seaidx][0]+"(발생빈도 : "+a[seaidx][1]+"): "+a[seaidx][2]);
		}

	}


	public void HuffmanCode (HuffmanTree coprint, Object keyv, int stoidx  , String code) {
		if(a==null) {
			System.out.println("text에 내용이 없습니다.");
			return;
		}

		if(coprint.key != null) {
			if(coprint.key == keyv) {
				a[stoidx][2] = code;
			}
		}

		else {
			if(code == null) {
				if(coprint.right != null) HuffmanCode (coprint.right,keyv,stoidx,"1");
				if(coprint.left !=  null)HuffmanCode (coprint.left,keyv,stoidx,"0");
			}
			else {
				if(coprint.right != null)HuffmanCode (coprint.right,keyv,stoidx,code+"1");
				if(coprint.left !=  null)HuffmanCode (coprint.left,keyv,stoidx,code+"0");
			}

		}
		return ;
	}



	public void PriorityQueuefirst() {

		q = new MinHeap(1);
		for(int i=0 ; i<a.length ; i++) {
			HuffmanTree tree = new HuffmanTree(a[i][0],(int)a[i][1]);
			q.add(tree);	
		}
	}

	public void merge () {
		HuffmanTree firstmin = (HuffmanTree)q.remove();		
		HuffmanTree secondmin = (HuffmanTree)q.remove();
		HuffmanTree newtree = new HuffmanTree(null,firstmin.num+secondmin.num,firstmin,secondmin);
		q.add(newtree);
	}

	public int frequencyadd (char x) {

		if(a[0][0] == null) {
			a[0][0] = x;
			a[0][1] = 1;
			return 1;
		}

		for(int i=0; i < a.length; i++) {
			if((char)a[i][0] == x) {
				a[i][1] = (int)a[i][1] + 1;
				return (int)a[i][1];
			}
		}

		a = resize(a);
		a[a.length-1][0] = x;
		a[a.length-1][1] = 1;
		return 1;
	}
	public Object[][] resize (Object[][]a){
		Object[][] aa = new Object[a.length+1][3];
		System.arraycopy(a, 0, aa, 0, a.length);
		return aa;
	}

}
