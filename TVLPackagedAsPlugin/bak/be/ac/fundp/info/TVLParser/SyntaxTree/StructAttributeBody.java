package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class StructAttributeBody {
	Vector<SubAttribute> subAttributes;
	
	public StructAttributeBody(SubAttribute subAttribute) {
		this.subAttributes = new Vector<SubAttribute>();
		this.subAttributes.add(subAttribute);
	}
	
	public StructAttributeBody(SubAttribute subAttribute, StructAttributeBody structAttributeBody) {
		this.subAttributes = structAttributeBody.getSubAttributes();
		this.subAttributes.add(subAttribute);
	}

	public Vector<SubAttribute> getSubAttributes() {
		return subAttributes;
	}
	
}
