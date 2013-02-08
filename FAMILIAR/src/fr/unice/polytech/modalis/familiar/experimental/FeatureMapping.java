package fr.unice.polytech.modalis.familiar.experimental;

import java.util.Arrays;
import java.util.List;

public class FeatureMapping {
	
	
	private List<String> _left ; 
	private List<String> _right ; 

	public FeatureMapping(List<String> left, String right) {
		this(left, Arrays.asList(new String[] { right })) ;
	}
	
	public FeatureMapping(List<String> left, List<String> right) {
		setLeft(left) ; 
		setRight(right) ; 
	}
	
	public FeatureMapping(String left, String right) {
		this(Arrays.asList(new String[] { left }), Arrays.asList(new String[] { right }));
	}
	
	public FeatureMapping(String left, List<String> right) {
		this(Arrays.asList(new String[] { left }), right );
	}

	public void setLeft(List<String> _left) {
		this._left = _left;
	}

	public List<String> getLeft() {
		return _left;
	}

	public void setRight(List<String> _right) {
		this._right = _right;
	}

	public List<String> getRight() {
		return _right;
	}

}
