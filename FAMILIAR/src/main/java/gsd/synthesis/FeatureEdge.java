/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

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