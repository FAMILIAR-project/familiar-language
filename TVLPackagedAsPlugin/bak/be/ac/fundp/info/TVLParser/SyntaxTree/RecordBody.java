package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class RecordBody {
	
	Vector<RecordField> recordFields;
	
	public RecordBody(RecordField recordField) {
		this.recordFields = new Vector<RecordField>();
		this.recordFields.add(recordField);
	}
	
	public RecordBody(RecordField recordField, RecordBody recordBody) {
		this.recordFields = recordBody.getRecordFields();
		this.recordFields.add(recordField);
	}
	
	public Vector<RecordField> getRecordFields() {
		return this.recordFields;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

}
