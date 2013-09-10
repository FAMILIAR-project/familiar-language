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
/*
 * Created on 26-Dec-2004
 */
package es.us.isa.FAMA.models.variabilityModel.parsers;

/**
 * @author trinidad
 */
public class WrongFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongFormatException() {
		super("Wrong XML format");
	}

	public WrongFormatException(String arg0) {
		super(arg0);
	}

	public WrongFormatException(Throwable arg0) {
		super(arg0);
	}

	public WrongFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
