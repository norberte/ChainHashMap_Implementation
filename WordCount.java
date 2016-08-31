package Mini_algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) throws FileNotFoundException {
		Map<String, Integer> freq = new ChainHashMap<>();
		File f = new File("C:/Users/Norbert/Desktop/Thank You Letter.docx");
		Scanner doc = new Scanner(f);
		doc.useDelimiter("[^a-zA-Z]+");
		while(doc.hasNext()){
			String word = doc.next().toLowerCase();
			Integer count = freq.get(word);
			if(count == null){
				count = 0;
			}
			freq.put(word, 1 + count);
		}
		
		Iterator<Integer> values = freq.values().iterator();
		Iterator<String> keys = freq.keySet().iterator();
		
		//for(Entry<String,Integer> ent : freq.entrySet()){
		//	System.out.println(ent.getKey() + " : " + ent.getValue());
		//}
		
		while(keys.hasNext()){
			System.out.println(keys.next() + " : " + values.next());
		}
		
		
	}

}
