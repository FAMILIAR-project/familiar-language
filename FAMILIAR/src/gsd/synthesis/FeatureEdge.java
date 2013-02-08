package gsd.synthesis;


public class FeatureEdge {

  /** These numbers are used as bit-masks
   *  TODO Change this to use {@link java.util.EnumSet}
   **/
  public static final int HIERARCHY = 0x0001;
  public static final int MANDATORY = 0x0002;
  public static final int MUTEX = 0x0004;
  public static final int OR = 0x0008;
  public static final int XOR = 0x0010;
  public static final int DEAD = 0x0020;
  public static final int FROZEN = 0x0040;
  public static final int MARKED = 0x0080;

  /**
   * Convenience constant for groups
   */
  public static final int GROUPS = MUTEX | OR | XOR;

  private final int _type;

  protected FeatureEdge(int type) {
    switch (type) {
    case HIERARCHY:
    case MANDATORY:
    case MUTEX:
    case OR:
    case XOR:
    case DEAD:
    case FROZEN:
      break;
    default:
      throw new IllegalArgumentException("unrecognized type!");
    }
    _type = type;

  }

  public int getType() {
    return _type;
  }

  public boolean isBinary() {
    return _type == HIERARCHY || _type == MANDATORY;
  }
}