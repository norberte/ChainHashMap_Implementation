package Mini_algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
	private UnsortedTableMap<K,V>[] table;
	public ChainHashMap() { 
		super();
	}
	public ChainHashMap(int cap) {
		super(cap);
	}
	public ChainHashMap(int cap, int p){
		super(cap, p);
	}
	
	protected void createTable(){
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
	}
	
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for(int h = 0; h < capacity; h++)
			if(table[h] != null)
				for(Entry<K,V> entry: table[h].entrySet())
					buffer.add(entry);
		Set<Entry<K,V>> set = new HashSet<Entry<K,V>>(buffer);
		return set;
	}
	
	protected V bucketGet(int h, K k) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null) return null;
		return bucket.get(k);
	}

	protected V bucketPut(int h, K k, V v) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null){
			bucket = table[h] = new UnsortedTableMap<>();
		}
		int oldSize = bucket.size();
		V answer = bucket.put(k, v);
		n += (bucket.size() - oldSize);
		return answer;
	}

	protected V bucketRemove(int h, K k) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null){
			bucket = table[h] = new UnsortedTableMap<>();
		}
		int oldSize = bucket.size();
		V answer = bucket.remove(k);
		n -= (oldSize - bucket.size());
		return answer;
	}
	
	public void clear() {}
	public boolean containsKey(Object key) {return false;}
	public boolean containsValue(Object value) {return false;}
	public void putAll(Map<? extends K, ? extends V> m) {}
}
