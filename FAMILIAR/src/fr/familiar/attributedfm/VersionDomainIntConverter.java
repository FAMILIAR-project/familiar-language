/**
 * 	This file is part of FaMaTS.
 *
 *     FaMaTS is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     FaMaTS is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.attributedfm;

import java.util.StringTokenizer;

public class VersionDomainIntConverter extends AbstractDomainIntConverter {

	//XXX contamos con 22 bits, choco peta con mas dios
	//sabe por que, aunque sean variables enumeradas
	//lo dejaremos como 6.8.8
	
	@Override
	public Integer convertToInteger(Object o) {
		// X.Y.Z, donde X es el numero arquitectonico,
		// Y el numero funcional y X el numero
		// de correccion de bugs
		int res = 0;
		if (o instanceof String) {
			String s = o.toString();
			res = version2Int(s);
		}
		return res;
	}

	private int version2Int(String s) {
		// Version X.Y.Z
		// Usaremos los 22 primeros bits del integer

		int res = 0;
		StringTokenizer st = new StringTokenizer(s, ".", false);
		if (st.countTokens() == 3) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			if (x > 63){
				System.err.println("Warning, on X.Y.Z versions, X can't be" +
						" greater than 63");
			}
			if (y > 255 || z > 255){
				System.err.println("Warning, on X.Y.Z versions, Y and Z can't be" +
						" greater than 255");
			}
			x = (x << 16);
			y = (y << 8);
			res = (res | x);
			res = (res | y);
			res = (res | z);
		}

		return res;
	}

	public boolean canTranslate(Object o) {
		boolean b = true;
		StringTokenizer st = new StringTokenizer(o.toString(),".",false);
		if (b = (st.countTokens() == 3)) {
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			String s3 = st.nextToken();
			b = b && isInteger(s1);
			b = b && isInteger(s2);
			b = b && isInteger(s3);

		}
		return b;

	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		VersionDomainIntConverter v = new VersionDomainIntConverter();
		int i1 = v.version2Int("1.4.125");
		int i2 = v.version2Int("1.5.0");
		System.out.println(i1 < i2);
//		System.out.println(v.version2Int("7.11.114"));
	}

}
