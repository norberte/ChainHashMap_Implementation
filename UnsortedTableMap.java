package Mini_algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class UnsortedTableMap<K,V> extends AbstractMap<K,V>{
	private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
	
	public UnsortedTableMap(){	}
	
	public void clear() {
		table.clear();
	}
	
	private int findIndex(K key){
		int n = table.size();
		for(int j = 0; j < n; j++){
			if(table.get(j).getKey().equals(key)){
				return j;
			}
		}
		return -1;
	}
	
	private int findValue(V value){
		int n = table.size();
		for(int j = 0; j < n; j++){
			if(table.get(j).getValue() == value){
				return j;
			}
		}
		return -1;
	}

	public boolean containsKey(Object key) {
		int j = findIndex((K)key);
		if(j == -1){
			return false;
		} else{
			return true;
		}
	}

	public boolean containsValue(Object value) {
		int j = findValue((V)value);
		if(j == -1){
			return false;
		} else{
			return true;
		}
	}

	private class EntryIterator implements Iterator<Entry<K,V>> {
		private int j = 0;
		public boolean hasNext(){
			return j < table.size();
		}
		public Entry<K,V> next(){
			if(j == table.size()){
				throw new NoSuchElementException();
			}
			return table.get(j++);
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	
	private class EntryIterable implements Iterable<Entry<K,V>> {
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	}
	
	public Set<Entry<K, V>> entrySet() {
		return (Set<Entry<K, V>>) new EntryIterable();
	}

	public V get(Object key) {
		int j = findIndex((K)key);
		if(j == -1){
			return null;
		}
		return table.get(j).getValue();
	}

	public V put(K key, V value) {
		int j = findIndex(key);
		if(j == -1){
			table.add(new MapEntry<>(key,value));
			return null;
		} else {
			return table.get(j).setValue(value);
		}
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		table.addAll((Collection<? extends MapEntry<K, V>>) m);
	}

	public V remove(Object key) {
		int j = findIndex((K)key);
		int n = size();
		
		if(j == -1){
			return null;
		} 
		V answer = table.get(j).getValue();
		if(j != n - 1){
			table.set(j, table.get(n-1));
		}
		table.remove(n-1);
		return answer;
	}

	public int size() {
		return table.size();
	}

}
