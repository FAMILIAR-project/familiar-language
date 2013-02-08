package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class Record implements Type {
	int type;
	String ID;
	Vector<RecordField> recordFields;
	
	public String toString() {
		String text = "struct "+this.ID+" {\n";
		int i = 0;
		while (i <= this.recordFields.size()-1) {
			text =  text.concat(this.recordFields.get(i).toString());
			i++;
		}
		return text.concat("}\n");
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the recordFields
	 */
	public Vector<RecordField> getRecordFields() {
		return recordFields;
	}

	public Record(int type, String ID, RecordBody recordBody) {
		this.ID = ID;
		this.type= type;
		this.recordFields = recordBody.getRecordFields();
	}

	@Override
	public boolean isARecord() {
		return true;
	}
	
	// Utilisé pour la construction du modèle
	@Override
	public boolean isAFeature() {
		return false;
	}

	@Override
	public boolean isAType() {
		return true;
	}

	@Override
	public boolean isAconstant() {
		return false;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
