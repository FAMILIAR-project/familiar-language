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

package fr.familiar.interpreter;

/**
 * @author mathieuacher
 * 
 */
public class FMLShellConfiguration {

	// 500000, 100000, 100

	// 10000000, 1000000, 1000;

	// 200000, 50000, 1000;

	public final static int DEFAULT_BDDnodes = 10000000;
	public static int BDDnodes = 2000000; // /// 5000000 5000000 ; // ; // 20000000 ; //
													// 1000000 ; // 5000000 //
													// ;5000000 ; // // 5000000
													// //10000000 ; //

	public final static int DEFAULT_BDDcache = 1000000;
	public final static int BDDcache = 100000 ; //100000; // 
	
	// Scalability Experiments of Mendonca
	private final static int BDDcacheSPLOT = 1000000;
	private static final int BDDnodesSPLOT = 5000000;
	

	public static int BDDvar = 1000; // 100; // 1000 ; //2500000

	public static int getBDDnodes() {
		return BDDnodes;
	}

	public static int getBDDcache() {
		return BDDcache;
	}

	public static int getBDDvar() {
		return BDDvar;
	}

	public static int getBDDSPLOTcache() {
		return BDDcacheSPLOT ; 
	}

	public static int getBDDSPLOTnodes() {
		return BDDnodesSPLOT ; 
	}

}
