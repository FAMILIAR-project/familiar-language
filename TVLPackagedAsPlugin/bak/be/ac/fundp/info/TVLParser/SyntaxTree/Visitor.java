package be.ac.fundp.info.TVLParser.SyntaxTree;

/**
 * Abstract Syntax Tree Visitor pattern interface.
 * 
 * @author gsa
 *
 */

public interface Visitor {
	
	public void visit(Model model);

	public void visit(Feature feature);

	public void visit(FeatureBody featureBody);
	
	public void visit(FeatureGroup featureGroup);

	public void visit(Expression expression) throws Exception;

	public void visit(SetExpression setExpression);

	public void visit(Attribute attribute);

	public void visit(AttributeBody attributeBody);

	public void visit(AttributeConditionnal attributeConditionnal);

	public void visit(Record record);

	public void visit(RecordBody recordBody);

	public void visit(RecordField recordField);

	public void visit(Constant constant);

	public void visit(Constraint constraint);

	public void visit(Data data);

	public void visit(SimpleType simpleType);

}
