package be.ac.fundp.info.TVLParser.SyntaxTree;


public class SyntaxTreePrinter implements Visitor {
	
	private String result;
	
	public SyntaxTreePrinter() {
		result = "";
	}
	
	public SyntaxTreePrinter(Model model) {
		result = "";
		model.accept(this);
	}
	
	public String getResult() {
		return result;
	}
	
	public void reset() {
		result = "";
	}

	@Override
	public void visit(Model model) {
		// model.toString() already prints the whole model recursively => no need to visit children
		result += model.toString();
	}

	@Override
	public void visit(Feature feature) {
		// feature.toString() prints all there is to know about this feature
		result += feature.toString();
	}

	@Override
	public void visit(FeatureBody featureBody) {
		// a FeatureBody is just a bunch of FeatureBodyItem
		// a FeatureBodyItem is either a FeatureGroup, Data, Constraint or Attribute
		for(FeatureBodyItem i: featureBody.getItems()) {
			i.accept(this);
		}
	}

	@Override
	public void visit(FeatureGroup featureGroup) {
		result += featureGroup.toString();
	}

	@Override
	public void visit(Expression expression) {
		result += expression.toString();
	}

	@Override
	public void visit(SetExpression setExpression) {
		result += setExpression.toString();
	}

	@Override
	public void visit(Attribute attribute) {
		result += attribute.toString();
	}

	@Override
	public void visit(AttributeBody attributeBody) {
		result += attributeBody.toString();
	}

	@Override
	public void visit(AttributeConditionnal attributeConditionnal) {
		result += attributeConditionnal.toString();
	}

	@Override
	public void visit(Record record) {
		result += record.toString();
	}

	@Override
	public void visit(RecordBody recordBody) {
		// a RecordBody is a model fragment containing a list of RecordFields

		for (RecordField r : recordBody.getRecordFields()) {
			this.visit(r);
		}
	}

	@Override
	public void visit(RecordField recordField) {
		result += recordField.toString();
	}

	@Override
	public void visit(Constant constant) {
		result += constant.toString();

	}

	@Override
	public void visit(Constraint constraint) {
		result += constraint.toString();
	}

	@Override
	public void visit(Data data) {
		result += data.toString();
	}

	@Override
	public void visit(SimpleType simpleType) {
		result += simpleType.toString();
	}
}
