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

package fr.familiar.variable;

/**
 * @author macher1
 *
 */
public class FeatureName {

	/**
	 * 
	 */
	private FeatureName() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isQuotable(String ftName) {
		
		return
				ftName.contains("-")
		|| ftName.contains(" ")
		|| ftName.contains(".")
		|| ftName.contains("/")
		|| ftName.contains("?")
		|| ftName.contains("?")
		|| ftName.contains("(")
		|| ftName.contains(")")
		|| ftName.contains("[")
		|| ftName.contains("]")
		|| ftName.contains("!")
		|| ftName.contains("$")
		|| ftName.contains("#")
		|| ftName.contains("0")
		|| ftName.contains("1")
		|| ftName.contains("2")
		|| ftName.contains("3")
		|| ftName.contains("4")
		|| ftName.contains("5")
		|| ftName.contains("6")
		|| ftName.contains("7")
		|| ftName.contains("8")
		|| ftName.contains("9")
		|| ftName.contains("+") ; 
		
	}
	
	public static String quote(String ftName) {
		return "\"" + ftName.replaceAll("\"", "") + "\"";
	}

	/**
	 * We remove exotic characters as well as reserved keywords
	 * TODO one of the thing we can consider is to develop a separate parser (eg Xtext project) dedicated to only parsing
	 * feature models ---- it will avoid this unelegant, limited and non comprehensive pre-processing step 
	 * @param ftName
	 * @return
	 */
	public static String normalize(String ftName) {
		
		return ftName.replaceAll("ç", "c")
				.replaceAll("ã", "a")
				.replaceAll("é", "e")
				.replaceAll(",", "")
				.replaceAll("ó", "o")
				.replaceAll("í", "i")
				.replaceAll("á", "a")
				.replaceAll("ê", "e")
				.replaceAll("Configuration", "Configuration_")
				.replaceAll("name", "naMe")
				.replaceAll("parameter", "paraMeter")
				.replaceAll("export", "eXport")
				.replaceAll("Set", "SeT")
				.replaceAll("opt", "oPt")
				.replaceAll("features", "feaTures")
				.replaceAll("true", "trUe")
				.replaceAll("false", "fAlse")
				.replaceAll("run", "rUn")
				.replaceAll("select", "sElect")
				.replaceAll("size", "siZe")
				.replaceAll("Integer", "InteGer")
				.replaceAll("print", "prinT")
				.replaceAll("String", "StrinG")
				; 
				
		
	}

		/**
		 * http://www.java2s.com/Book/Java/Examples/Unquote_string.htm
		 * @param str
		 * @return
		 */
		public static String unquote (String str)  {
		    int length = str == null ? -1 : str.length();
		    if( str == null || length == 0 )
		      return str;

		    if( length > 1 && str.charAt( 0 ) == '\"' && str.charAt( length - 1 ) == '\"' )
		    {
		      str = str.substring( 1, length - 1 );
		    }

		    return str;
	  }

		public static String quoteNeedsBe(String ftName) {
			if (isQuotable(ftName))
				return quote(ftName);
			return ftName ; 
		}

		public static boolean isQuoted(String actualFeatureName) {
			return actualFeatureName.startsWith("\"") && actualFeatureName.endsWith("\"") ; 
		}
	

}
