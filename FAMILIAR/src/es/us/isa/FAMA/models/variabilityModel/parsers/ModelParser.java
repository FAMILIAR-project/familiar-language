/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
package es.us.isa.FAMA.models.variabilityModel.parsers;

import fr.familiar.attributedfm.AttributedFeatureModel;

import java.util.Collection;


public interface ModelParser {

	public void write(AttributedFeatureModel vm, String path);
	
	public void write(AttributedFeatureModel vm, String path, String writerId);
	
	public AttributedFeatureModel read(String path);
	
	public AttributedFeatureModel read(String path, String readerId);
	
	public Collection<String> getReadersId();
	
	public Collection<String> getWritersId();
	
}
