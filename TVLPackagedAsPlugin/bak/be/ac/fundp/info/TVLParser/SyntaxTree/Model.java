package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class Model {
	
	Vector<Feature> features;
	Vector<Constant> constants;
	Vector<Type> types;
	
	public String toString() {
		String text = new String("");
		if (this.types != null) {
			int i = this.types.size()-1;
			while (i >= 0) {
				text = text.concat(this.types.get(i).toString());
				i--;
			}
		}
		if (this.constants != null) {
			int i = this.constants.size()-1;
			while (i >= 0) {
				text = text.concat(this.constants.get(i).toString());
				i--;
			}
		}
		if (this.features != null) {
			int i = this.features.size()-1;
			while (i >= 0) {
				text = text.concat(this.features.get(i).toString(""));
				i--;
			}
		}
		return text;
	}
	
	public Model(ModelItem modelItem) {
		if (modelItem.isAFeature()) {
			if (this.features == null)
				this.features = new Vector<Feature>();
			this.features.add((Feature) modelItem);
		}
		else {
			if (modelItem.isAconstant()) {
				if (this.constants == null)
					this.constants = new Vector<Constant>();
				this.constants.add((Constant) modelItem);
			}
			else {
				if (this.types == null)
					this.types = new Vector<Type>();
				this.types.add((Type) modelItem);
			}
		}
	}
	
	public Model(ModelItem modelItem, Model model) {
		this.constants = model.getConstants();
		this.features = model.getFeatures();
		this.types = model.getTypes();
		if (modelItem.isAFeature()) {
			if (this.features == null)
				this.features = new Vector<Feature>();
			this.features.add((Feature) modelItem);
		}
		else {
			if (modelItem.isAconstant()) {
				if (this.constants == null)
					this.constants = new Vector<Constant>();
				this.constants.add((Constant) modelItem);
			}
			else {
				if (this.types == null)
					this.types = new Vector<Type>();
				this.types.add((Type) modelItem);
			}
		}
	}

	/**
	 * @return the features
	 */
	public Vector<Feature> getFeatures() {
		return features;
	}

	/**
	 * @return the constants
	 */
	public Vector<Constant> getConstants() {
		return constants;
	}

	/**
	 * @return the types
	 */
	public Vector<Type> getTypes() {
		return types;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

}
