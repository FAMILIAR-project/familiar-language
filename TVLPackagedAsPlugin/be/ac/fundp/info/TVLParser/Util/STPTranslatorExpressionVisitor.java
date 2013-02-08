package be.ac.fundp.info.TVLParser.Util;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import be.ac.fundp.info.TVLParser.SyntaxTree.*;


public class STPTranslatorExpressionVisitor implements Visitor {

	private StringWriter assertion = new StringWriter();
	private Map<String, Integer> varIndices ;
	private List<String> intAttributes ;
	
	public STPTranslatorExpressionVisitor(Map<String, Integer> varIndices,
			List<String> intAttributes) {
		this.varIndices = varIndices;
		this.intAttributes = intAttributes;
	//	System.out.println("-----") ;
	}
	private boolean empty = false;
	private boolean inBitwiseExpression = false;
	private boolean negatedExpression = false;
	@Override
	public void visit(Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Feature feature) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FeatureBody featureBody) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FeatureGroup featureGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Expression expression) throws Exception {
		String simpleName = expression.getClass().getSimpleName();
		//System.out.println(simpleName+"\n");
		if(!simpleName.equals("LongIDExpression") 
		&& !simpleName.equals("OrExpression") && !simpleName.equals("IfAndOnlyIfExpression")) assertion.append("(");
			
		Method m = getClass().getMethod("translate", expression.getClass());
		m.invoke(this,expression);	
		
		if(!simpleName.equals("LongIDExpression") 
				&& !simpleName.equals("OrExpression") && !simpleName.equals("IfAndOnlyIfExpression")
				) assertion.append(")");
	}
	
	
	public void translate(ImpliesExpression expression) throws Exception {
		expression.getExpression1().accept(this);
		assertion.append( " => ");
		expression.getExpression2().accept(this);
	}
	
	public void translate(ZeroExpression expression) {
		assertion.append("0b00000000000000000000000000000000");
	}
	
	public void translate(ExcludesExpression expression) {
			assertion.append("(NOT(F["+expression.getLongID1()+"]=0b1 AND F["+expression.getLongID2()+"]=0b1))");
	}
	
	public void translate(ParenthesesExpression expression) throws Exception {
		expression.getExpression().accept(this);
	}
	public void translate(LongIDExpression expression) throws Exception {
		if(varIndices.containsKey(expression.getLongID())) {
			
				assertion.append("F["+expression.getLongID()+"]");
				if(!inBitwiseExpression)
						assertion.append("="+getTruthValue());
		}
		else {
		if(intAttributes.contains(expression.getLongID().replace(".", "_"))) {
				assertion.append(expression.getLongID().replace(".", "_")) ;
			} 
			else {
				// throw new Exception("Unknown id : "+expression.getLongID().replace(".", "_"));
				empty = true;
			}
		}
	}
	
	public void translate(EqualsExpression expression) throws Exception {
		expression.getExpression1().accept(this);
		assertion.append(" = ");
		expression.getExpression2().accept(this);
	}
	
	public void translate(InExpression expression) throws Exception {
		if(expression.getSetExpression().hasAnExpressionList()) {
			
			// Not implemented yet.
			throw new UnsupportedOperationException();
		}
		else {
			assertion.append("(BVGE(");
			expression.getExpression().accept(this);
			assertion.append(",");
			assertion.append(STPTranslator.dec2bin(Integer.parseInt(expression.getSetExpression().getMinExpression()),32));
			assertion.append(") AND BVLE(");
			expression.getExpression().accept(this);
			assertion.append(",");
			assertion.append(STPTranslator.dec2bin(Integer.parseInt(expression.getSetExpression().getMaxExpression()),32));
			assertion.append("))");
		}
		
	}
	
	public void translate(GEQExpression expression) throws Exception {
		assertion.append("BVGE(");
		expression.getExpression1().accept(this);
		assertion.append(",");
		expression.getExpression2().accept(this);
		assertion.append(")");
	}
	
	public void translate(LEQExpression expression) throws Exception {
		assertion.append("BVGE(");
		expression.getExpression1().accept(this);
		assertion.append(",");
		expression.getExpression2().accept(this);
		assertion.append(")");
	}
	
	public void translate(GreaterExpression expression) throws Exception {
		assertion.append("BVGT(");
		expression.getExpression1().accept(this);
		assertion.append(",");
		expression.getExpression2().accept(this);
		assertion.append(")");
	}
	
	public void translate(LowerExpression expression) throws Exception {
		assertion.append("BVLT(");
		expression.getExpression1().accept(this);
		assertion.append(",");
		expression.getExpression2().accept(this);
		assertion.append(")");
	}
	
	public void translate(PlusExpression expression) throws Exception {
		assertion.append("BVPLUS(32,");
		expression.getExpression1().accept(this);
		assertion.append(",");
		expression.getExpression2().accept(this);
		assertion.append(")");
	}
	
	public void translate(TimesExpression expression) throws Exception {
		assertion.append("BVMULT(32,");
		expression.getExpression1().accept(this);
		assertion.append(",");
		expression.getExpression2().accept(this);
		assertion.append(")");
	}
	
	public void translate(DivideExpression expression) throws Exception {
		assertion.append("BVDIV(32,");
		expression.getExpression1().accept(this);
		assertion.append(",");
		expression.getExpression2().accept(this);
		assertion.append(")");
	}
	
	public void translate(OrExpression expression) throws Exception {
		assertion.append("(");
		expression.getExpression1().accept(this);
		//System.out.println(assertion.toString());
		assertion.append(" OR ") ;
		expression.getExpression2().accept(this);
		assertion.append(")");
	//	System.out.println(assertion.toString());
	}
	
	public void translate(AndExpression expression) throws Exception {

		assertion.append("(");
		expression.getExpression1().accept(this);
		assertion.append(" AND ") ;
		expression.getExpression2().accept(this);	
		assertion.append(")");

	}
	public void translate(NotExpression expression) throws Exception {
		assertion.append("NOT(");
		expression.getExpression().accept(this);
		assertion.append(")");
	}
	
	public void translate(IntExpression expression) throws Exception {
		assertion.append(STPTranslator.dec2bin(expression.getValue(),32));
	}
	
	public void translate(OrAggExpression expression) throws Exception {
		int n = expression.getExpressionList().getExpressions().size();
		
		assertion.append("(");
		for(Expression subEx : expression.getExpressionList().getExpressions()){
			subEx.accept(this);
			if(0 < --n) {
				assertion.append(" OR ");
			}
		}
		assertion.append(")");
		
		
	}
	
	public void translate(AndAggExpression expression) throws Exception {
		int n = expression.getExpressionList().getExpressions().size();
		
		for(Expression subEx : expression.getExpressionList().getExpressions()){
			subEx.accept(this);
			if(0 < --n) {
				assertion.append(" AND ");
			}
		}
		assertion.append(")");
		
		
	}
	
	private String getTruthValue() {
		return (negatedExpression)?"0bin0":"0bin1";
	}

	public void translate(IfAndOnlyIfExpression expression) throws Exception {
		STPTranslatorExpressionVisitor v1 = new STPTranslatorExpressionVisitor(varIndices,intAttributes);
		new ImpliesExpression(expression.getExpression1(),expression.getExpression2()).accept(v1);
		STPTranslatorExpressionVisitor v2 = new STPTranslatorExpressionVisitor(varIndices,intAttributes);
		new ImpliesExpression(expression.getExpression2(),expression.getExpression1()).accept(v2);
		
		String e2 = v2.toString() ;
		assertion.append(v1.toString()+"));");
		assertion.append("\nASSERT(" + e2);
		
	}
	
	
	public void translate(FalseExpression expression) throws Exception {
		assertion.append("FALSE");
	}
	
	public void translate(TrueExpression expression) throws Exception {
		assertion.append("TRUE");
	}
	@Override
	public void visit(SetExpression setExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Attribute attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttributeBody attributeBody) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttributeConditionnal attributeConditionnal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Record record) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RecordBody recordBody) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RecordField recordField) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Constant constant) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Constraint constraint) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Data data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SimpleType simpleType) {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
		if(empty) return "TRUE";
		return assertion.toString();
	}

}
