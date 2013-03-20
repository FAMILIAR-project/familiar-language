package be.ac.fundp.info.TVLParser.Util;

public class FDElement {
	
	private String name;
	
	public String getName() {
		return name;
	}

	private boolean selected ;
	private boolean unselected ;
	
	public boolean isSelected() {
		return selected;
	}

	public boolean isUnselected() {
		return unselected;
	}

	public FDElement(String n,boolean selected, boolean unselected){
		this.name = n; 
		this.selected = selected ;
		this.unselected = unselected ;
	}

	public boolean equals(Object otherFDElement) {
		try {
			FDElement other = (FDElement) otherFDElement ;
			return (other.getName().equals(this.getName()));
					
		}
		catch(ClassCastException cce) {
			return false;
		}
	}
	
	public int hashCode(){
		return name.hashCode()+(selected ? 2 : 0) + (unselected ? 1 : 0);
	}
	
	public String toString() {
		String result = this.getName()+" [" ;
		result += this.selected ? "I" : this.unselected ? "E" : "U" ;
		result += "]";
		return result ;
		
	}
}
