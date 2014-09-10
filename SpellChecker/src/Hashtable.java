

import java.util.Iterator;
import java.util.function.Consumer;


public class Hashtable<K, V> {
	private Entry<K,V>[] values;
	private int size;
	
	public Hashtable(int initialCapacity) {
		values = (Entry<K,V>[])new Entry[initialCapacity];
	}
	
	/**
	 * #3b. Implement this (1 point)
	 * 
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		int index=key.hashCode()%values.length;
		boolean notAdded=true;
		
		if(values[index]==null)
		{
			values[index]=new Entry<K,V>(key,value);
			notAdded=false;
			size++;
		}
		Entry<K,V> val=null;
		if(notAdded)
			val=values[index];
		
		while(notAdded && val.key!=null)
		{
			if(val.key==key)
			{
				val.data=value;
				notAdded=false;
			}
			
			if(notAdded)
				val=val.next;
		}
		if(notAdded)
			val.next=new Entry<K,V>(key,value);
	}
	
	/**
	 * #3b. Implement this (1 point)
	 * @param key
	 * @return
	 */
	public V get(K key) {
		int hash=key.hashCode();
		int index=hash%values.length;
		
		if(values[index]==null)
			return null;
		
		Entry<K,V> val=values[index];
		
		while(val.key!=null)
		{
			if(val.key==key)
			{
				return val.data;
			}
			
			val=val.next;
		}
		
		return null;
	}

	/**
	 * #3c.  Implement this. (1 point)
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key) {
		int index=key.hashCode()%values.length;
		
		if(values[index]==null)
			return null;
		
		Entry<K,V> previous=null;
		Entry<K,V> current=values[index];
				
		while(current.key!=null)
		{
			if(current.key==key)
			{
				if(previous==null)
				{
					V ret=current.data;
					values[index]=current.next;
					if(values[index]==null)
						size--;
					return ret;
				}
				else
				{
					V ret=current.data;
					previous.next=current.next;
					return ret;
				}
			}
			else
			{
				previous=current;
				current=current.next;
			}
		}
		
		return null;
	}
	
	public int size() {
		return size;
	}
	
	public boolean containsKey(K key) {
		return this.get(key) != null; 
	}

	public Iterator<V> values() {
		return new Iterator<V>() {
			private int count = 0;
			private Entry<K, V> currentEntry;
			
			{
				while ( ( currentEntry = values[count] ) == null && count < values.length ) {
					count++;
				}
			}
			
			@Override
			public void forEachRemaining(Consumer<? super V> arg0) {
			}

			@Override
			public boolean hasNext() {
				return count < values.length;
			}

			@Override
			public V next() {
				V toReturn = currentEntry.data;
				currentEntry = currentEntry.next;
				while ( currentEntry == null && ++count < values.length && (currentEntry = values[count]) == null );
				return toReturn;
			}

			@Override
			public void remove() {
			}
			
		};
	}
	
	private static class Entry<K, V> {
		public K key;
		public V data;
		public Entry<K,V> next;
		
		public Entry(K key, V data) {
			this.key = key;
			this.data = data;
		}
		
		public String toString() {
			return "{" + key + "=" + data + "}";
		}
	}
}