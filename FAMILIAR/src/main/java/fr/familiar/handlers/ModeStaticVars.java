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
package fr.familiar.handlers;

public class ModeStaticVars {

	private static boolean normal = true;
	private static boolean strict = false;
	private static boolean warning = false;
	private static boolean applying = true;

	private static boolean graphicalNormal = true;

	public static boolean isApplying() {
		return applying;
	}

	public static void setApplying(boolean applying) {
		ModeStaticVars.applying = applying;
	}

	public static boolean isNormal() {
		return normal;
	}

	public static void setNormal(boolean normal) {
		ModeStaticVars.normal = normal;
	}

	public static boolean isStrict() {
		return strict;
	}

	public static void setStrict(boolean strict) {
		ModeStaticVars.strict = strict;
	}

	public static boolean isWarning() {
		return warning;
	}

	public static void setWarning(boolean warning) {
		ModeStaticVars.warning = warning;
	}

	public static boolean isGraphicalNormal() {
		return graphicalNormal;
	}

	public static void setGraphicalNormal(boolean normal) {
		ModeStaticVars.graphicalNormal = normal;

	}

}
