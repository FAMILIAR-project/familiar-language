package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeaturesGroupAlreadySpecifiedException;

public class Feature implements ModelItem {
	
	private String ID;

	private boolean root, shared, featureGroupDefined;
	
	//cardinalit√© du Feature
	private String minFeatureCardinality, maxFeatureCardinality;
	
	//cardinalit√© de d√©composition
	private String minGroupCardinality, maxGroupCardinality;
	
	private Vector<Data> data = new Vector<Data>();
	private Vector<Constraint> constraints = new Vector<Constraint>();
	private Vector<Attribute> attributes = new Vector<Attribute>();
	private Vector<Feature> childrenFeatures = new Vector<Feature>();

    public Feature(boolean root, String ID, boolean optional, boolean shared) {
		this.ID = ID;
                this.root = root;
                this.shared = shared;
                this.featureGroupDefined = false;
                if (optional){
                	this.minFeatureCardinality = "0";
                    this.maxFeatureCardinality = "1";
                } else {
                	this.minFeatureCardinality = "1";
                    this.maxFeatureCardinality = "1";
                }
                this.minGroupCardinality = "*";
                this.maxGroupCardinality = "*";
	}
    
    public Feature(String ID, Cardinality card) {
		this(false, ID, false, false);
		this.setFeatureCardinality(card);
	}

            
	public Feature(boolean root, String ID, FeatureBody featureBody, boolean optional, boolean shared) throws ChildrenFeaturesGroupAlreadySpecifiedException {
        this(root, ID, optional, shared);
		int i = 0;
		if (featureBody != null) {
			while (i <= featureBody.getItems().size()-1) {
				if (featureBody.getItems().get(i).isAConstraint()) {
					if (this.constraints == null)
						this.constraints = new Vector<Constraint>();
					this.constraints.add((Constraint) featureBody.getItems().get(i));
				}
				if (featureBody.getItems().get(i).isAnAttribute()) {
					if (this.attributes == null)
						this.attributes = new Vector<Attribute>();
					this.attributes.add((Attribute) featureBody.getItems().get(i));
				}
				if (featureBody.getItems().get(i).isAData()) {
					if (this.data == null)
						this.data = new Vector<Data>();
					this.data.add((Data) featureBody.getItems().get(i));
				}
				if (featureBody.getItems().get(i).isAFeatureGroup()) {
					if (this.featureGroupDefined)
						throw new ChildrenFeaturesGroupAlreadySpecifiedException();
					FeatureGroup featureGroup  = (FeatureGroup) featureBody.getItems().get(i);
					this.minGroupCardinality = featureGroup.getCardinality().getMin();
					this.maxGroupCardinality = featureGroup.getCardinality().getMax();
					this.childrenFeatures = featureGroup.getHierarchicalFeatures().getFeatures();
					this.featureGroupDefined = true;
				}
				i++;
			}
		}
	}

    public Feature(String ID, Cardinality card, FeatureBody featureBody) throws ChildrenFeaturesGroupAlreadySpecifiedException {
		this(false, ID, featureBody, false, false);
		this.setFeatureCardinality(card);
	} 

    
	public Feature(boolean root, String ID, FeatureGroup featureGroup, boolean optional, boolean shared) {
		this(root, ID, optional, shared);
        this.minGroupCardinality = featureGroup.cardinality.getMin();
		this.maxGroupCardinality = featureGroup.cardinality.getMax();
		this.childrenFeatures = featureGroup.getHierarchicalFeatures().getFeatures();
	}
 
    public Feature(String ID, Cardinality card, FeatureGroup featureGroup) {
		this(false, ID, featureGroup, false, false);
		this.setFeatureCardinality(card);
	}


    // Aussi utilis√© pour la construction du mod√®le
	public boolean isAFeature() {
		return true;
	}

        /**
         * Set the Feature cardinality to the given value
         * @param card cardinality to set, can't be null
         */
	public void setFeatureCardinality(Cardinality card) {
            assert(card != null);
            this.minFeatureCardinality = card.min;
            this.maxFeatureCardinality = card.max;
	}

        /**
         * Set the Group cardinality to the given value
         * @param card cardinality to set, can't be null
         */
	public void setGroupCardinality(Cardinality card) {
            assert(card != null);
            this.minGroupCardinality = card.min;
            this.maxGroupCardinality = card.max;
	}

        /**
         * Mark the Feature as strict optionnal (0..1 Cardinality)
         */
	public void setOptionalStrict() {
            minFeatureCardinality = "0";
            maxFeatureCardinality = "1";
	}

	/**
	 * @return the optionnal (min cardinality = 0, no matter on max cardinality)
	 */
	public boolean isOptionnal() {
		return ("0".equals(this.minFeatureCardinality));
	}

        /**
         * Mark the Feature as shared
         */
        public void setShared(){
            shared = true;
        }

	/**
	 * @return the shared
	 */
	public boolean isShared() {
		return shared;
	}

	/**
	 * @return the root
	 */
	public boolean isRoot() {
		return root;
	}

    public void setRoot(boolean root) {
        this.root = root;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

        

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

        /**
	 * @return the max Feature Cardinality
	 */
        public String getMaxFeatureCardinality() {
            return maxFeatureCardinality;
        }

         /**
	 * @return the min Feature Cardinality
	 */
        public String getMinFeatureCardinality() {
            return minFeatureCardinality;
        }

        /**
	 * @return the max Group Cardinality
	 */
        public String getMaxGroupCardinality() {
            return maxGroupCardinality;
        }

        /**
	 * @return the min Group Cardinality
	 */
        public String getMinGroupCardinality() {
            return minGroupCardinality;
        }

	/**
	 * @return the data
	 */
	public Vector<Data> getData() {
		return data;
	}

	/**
	 * @return the constraints
	 */
	public Vector<Constraint> getConstraints() {
		return constraints;
	}

	/**
	 * @return the attributes
	 */
	public Vector<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * @return the childrenFeatures
	 */
	public Vector<Feature> getChildrenFeatures() {
		return childrenFeatures;
	}
		
	// Utilis√© pour la construction du mod√®le
	@Override
	public boolean isAType(){
		return false;
	}

	@Override
	public boolean isAconstant() {
		return false;
	}
	
	
	public boolean hasConstraints() {
		if (this.constraints != null && this.constraints.size() > 0)
			return true;
		else
			return false;
	}
	
	public boolean hasAttributes() {
		if (this.attributes != null && this.attributes.size() > 0)
			return true;
		else
			return false;
	}
	
	public boolean hasChildrenFeatures() {
		if (this.childrenFeatures!= null && this.childrenFeatures.size() > 0)
			return true;
		else
			return false;
	}
	
	public boolean hasData() {
		if (this.data != null && this.data.size() > 0)
			return true;
		else
			return false;		
	}

        public String toString(String whiteSpace) {
		StringBuilder text = new StringBuilder();
		if (this.isShared()) {
			text.append(whiteSpace);
			text.append("shared ");
			text.append(this.ID);
			return text.toString();
		}
		else {
			if (this.isRoot()) {
				text.append(whiteSpace);
				text.append("root ");
				text.append(this.ID);
				text.append(" {\n");
			}
			else {
				if (this.isOptionnal()) {
					text.append(whiteSpace);
					text.append("opt ");
					text.append(this.ID);
					text.append(" {\n");
				}
				else {
					text.append(whiteSpace);
					text.append(this.ID);
					text.append(" {\n");
				}

			}

                        //feature cardinality
                      //  text = text.concat(whiteSpace+" [ "+this.minFeatureCardinality+".."+this.maxFeatureCardinality+" ] {\n");

			if (this.hasAttributes()) {
				int i = 0;
				while (i <= this.attributes.size()-1) {
					text.append(whiteSpace);
					text.append("    ");
					text.append(this.attributes.get(i).toString());
					text.append("\n");
					i++;
				}
			}
			if (this.hasConstraints()) {
				int i = 0;
				while (i <= this.constraints.size()-1) {
					text.append(whiteSpace);
					text.append("    ");
					text.append(this.constraints.get(i).toString());
					text.append("\n");
					i++;
				}
			}
			if (this.hasChildrenFeatures()) {
				int i = 0;
				text.append(whiteSpace);
				text.append("    group [ ");
				text.append(this.minGroupCardinality);
				text.append("..");
				text.append(this.maxGroupCardinality);
				text.append(" ] {\n");
				while (i <= this.childrenFeatures.size()-2) {
					text.append(this.childrenFeatures.get(i).toString(whiteSpace+"        "));
					text.append(",\n");
					i++;
				}
				text.append(this.childrenFeatures.get(this.childrenFeatures.size()-1).toString(whiteSpace+"        "));
				text.append("\n");
				text.append(whiteSpace);
				text.append("    }\n");
			}
			if (this.hasData()) {
				// TODO
			}
		}
		text.append(whiteSpace);
		text.append("}");
		return text.toString();
	}

		@Override
		public void accept(Visitor v) {
			v.visit(this);
		}
}
