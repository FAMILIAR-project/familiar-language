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

package fr.familiar.fm.converter;

import gsd.synthesis.Formula;

import java.io.IOException;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

/**
 * @author mathieuacher
 * 
 */
public class FormulaBDDSerializer {

	public static void save(String fileName, Formula<String> fla)
			throws IOException {
		BDD oBDD = fla.getBDD();
		BDDFactory oBDDFactory = oBDD.getFactory();

		oBDDFactory.save(fileName, oBDD);

	}

}
