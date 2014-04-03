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

package fr.familiar.fm;

import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureGraphFactory;

import java.util.Map;

public class BDDBuilderFactory {

	public static BDDBuilder<String> mkBuilder(Map<String, Integer> map) {
		BDDBuilder<String> builder = new BDDBuilder<String>(
				FeatureGraphFactory.mkStringFactory());
		builder.getFeatureMap().putAll(map);
		return builder;
	}

}
