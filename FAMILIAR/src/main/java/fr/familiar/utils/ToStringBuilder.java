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
package fr.familiar.utils;

/**
 * @author mathieuacher
 *
 */

import java.util.List;

public class ToStringBuilder {

	private String separator;
	private String start;
	private String end;

	public ToStringBuilder(String separator, String start, String end) {
		this.separator = separator;
		this.start = start;
		this.end = end;
	}

	public ToStringBuilder(String separator) {
		this(separator, "(", ")");
	}

	public String toString(List<?> l) {
		StringBuilder sb = new StringBuilder(start);
		String sep = "";
		for (Object object : l) {
			sb.append(sep).append(object.toString());
			sep = separator;
		}
		return sb.append(end).toString();
	}

}
