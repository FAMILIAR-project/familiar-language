/**
 * 
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
