package fr.unice.polytech.modalis.familiar.operations;

import java.util.Comparator;
import java.util.Map;

public class ComparatorByValue implements Comparator<Map.Entry<Integer, String>> {
	
	
    public int compare(Map.Entry<Integer, String> e1, Map.Entry<Integer, String> e2) {
        if (e1.getKey() < e2.getKey()){
            return 1;
        } else if (e1.getValue() == e2.getValue()) {
            return 0;
        } else {
            return -1;
        }
    }
}
