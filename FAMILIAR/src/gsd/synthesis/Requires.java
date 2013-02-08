package gsd.synthesis;


public class Requires<T> extends BinaryExpression<T> {

	public Requires(T ante, T cons) {
		super(true);
		assert ante != null;
		assert cons != null;
		_left = new Expression<T>(ante);
		_right = new Expression<T>(cons);
	}


	public T getAntecedent() {
		return _left.getFeature();
	}
	public T getConsequent() {
		return _right.getFeature();
	}


}