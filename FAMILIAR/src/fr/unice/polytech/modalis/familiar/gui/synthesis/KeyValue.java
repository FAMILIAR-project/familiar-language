package fr.unice.polytech.modalis.familiar.gui.synthesis;

public class KeyValue<K extends Comparable<K>, V> implements Comparable<KeyValue<K, V>>{

	private K key;
	private V value;
	
	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int compareTo(KeyValue<K, V> o) {
		return key.compareTo(o.getKey());
	}

	
}
