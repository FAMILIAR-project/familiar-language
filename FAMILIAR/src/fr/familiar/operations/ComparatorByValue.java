/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations;

import java.util.Comparator;
import java.util.Map;

public class ComparatorByValue implements Comparator<Map.Entry<Integer, String>> {
	
	
    public int compare(Map.Entry<Integer, String> e1, Map.Entry<Integer, String> e2) {
        if (e1.getKey() < e2.getKey()){
            return 1;
        } else if (e1.getValue() == e2.getValue()) {
            return 0;
        } else {
            return -1;
        }
    }
}
