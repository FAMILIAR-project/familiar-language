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

package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.xtext.example.mydsl.fML.FMFormat;

import fr.familiar.FMLTest;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.parser.ConvertAnalyzer;
import fr.familiar.parser.DoubleVariable;
import fr.familiar.parser.MyExpressionParser;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.StringVariable;

/**
 * @author mathieuacher
 * 
 */
public class FMLMiscTest extends FMLTest {

	@Test
	public void testCTRC1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (Keyboard: [ControlCD] Wireless Wiring [International] ; Wiring: (USB|PS2); )\n"
				+ "d = ctcr fm1\n");

		DoubleVariable d = getDoubleVariable("d");
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(fm1.CTCR_SPLOT(), d.getDouble(), 0);

	}

	@Test
	public void testInsert1() throws Exception {

		_shell.parse("fm1 = FM (Keyboard: [ControlCD] Wireless Wiring [International] ; Wiring: (USB|PS2); )\n"
				+ "fm2 = FM ( Lang : [US] European [Chinese]; )\n"
				+ "insert fm2 into fm1.International with mand\n"
				+ "i = 0\n"
				+ "foreach (fm in fm*) do "
				+ "i = i + 1\n"
				+ "end\n"
				+ "println fm1\n" + "");

		// foreach loop executed twice
		IntegerVariable i = getIntegerVariable("i");
		assertEquals(2, i.getV());

		// side effect
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertTrue(setVariabletoString(fm1.features()).contains("Chinese"));

	}

	
	@Ignore
	@Test
	public void testSATFla() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "FM (OSMobile: Client Marque Truc Developpeur ID ; " +
				"Client: (Bonne_Batterie_Client|Bon_Signal_Client)+ [Cher_Client] ; " +
				"Marque: (Apple|Google) ; Truc: [Thing2_Truc] ; " +
				"Developpeur: (Code_Ouvert_Developpeur|Appli_Rentable_Developpeur) API_Consequente_Developpeur ; " +
				"ID: (Android|Iphone) ; API_Consequente_Developpeur: [Open_Source] ; )");
		new SATFMLFormula(fmv1);
		
		FeatureModelVariable fmv2 = (FeatureModelVariable) fmv1.copy() ;
		fmv2.addConstraint(MyExpressionParser.parseString("OSMobile <-> Client "));
		
		System.err.println("BDD...");
		assertEquals(Comparison.REFACTORING, fmv1.compareBDD(fmv2, _builder));
		
		System.err.println("SAT (FeatureIDE)...");
		assertEquals(Comparison.REFACTORING, fmv1.compareSAT(fmv2, true));
		
		_shell.setVerbose(true);
		System.err.println("SAT (FLA)...");
		assertEquals(Comparison.REFACTORING, fmv1.compareSAT_Formula(fmv2)); // fail :(
		
		
	}
	
	
	@Test
	public void testForeach1() throws Exception {

		_shell.setVerbose(true);
		String script = "Android_Truc = FM (Truc : Thing2_Truc ; )\n"
			+ "Iphone_Client = FM (Client : Bonne_Batterie_Client Cher_Client [Bon_Signal_Client] ; Client -> !Bon_Signal_Client; )\n"
			+ "Iphone_Truc = FM (Truc : [Thing2_Truc] ; Truc -> !Thing2_Truc; )\n"
			+ "Android_Client = FM (Client : [Bonne_Batterie_Client] [Cher_Client] Bon_Signal_Client ; Client -> !Cher_Client; )\n"
			+ "Android_Developpeur = FM (Developpeur : API_Consequente_Developpeur Code_Ouvert_Developpeur [Appli_Rentable_Developpeur] ; API_Consequente_Developpeur : Open_Source ;Developpeur -> !Appli_Rentable_Developpeur; )\n"
			+ "Iphone_Developpeur = FM (Developpeur : [API_Consequente_Developpeur] [Code_Ouvert_Developpeur] Appli_Rentable_Developpeur ; Developpeur -> API_Consequente_Developpeur; Developpeur -> !Code_Ouvert_Developpeur; )\n"
			+ "fm_Iphone = FM(OSMobile : ID Marque ; ID : Iphone ; Marque : Apple ; )\n"
			+ "foreach(f in Iphone_*) do\n"
			+ "insert f into fm_Iphone.OSMobile with mand\n"
			+ "end\n"
			+ "fm_Android = FM(OSMobile : ID Marque ; ID : Android ; Marque : Google ; )\n"
			+ "foreach(f in Android_*) do\n"
			+ "insert f into fm_Android.OSMobile with mand\n" + "end\n"
			+ "finalFM = merge sunion fm_*" ; 
		System.err.println(script);
		_shell.parse(script);

	}

	@Test
	public void testIsRoot() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C ; )");

		FeatureVariable ft = getFeatureVariable("fm1.C");
		assertFalse(ft.isRoot());

	}

	@Test
	public void testMutex() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : [B] C ; B : (D|E); )");

		String splotFM1 = ConvertAnalyzer.convert(getFMVariable("fm1"),
				FMFormat.FSPLOT);
		assertNotNull(splotFM1);

		System.err.println("splotFM1" + splotFM1);

	}

	@Test
	public void testMutex2() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM ( "
				+ "GraphicCard: Bus DirectX Speed [synthMutex1156690526] ; \n"
				+ "Bus: (n128|n64) ; \n" +

				"DirectX: (v10dot1|v9|v10) ; \n" + "Speed: (n1000|n800) ; \n"
				+ "synthMutex1156690526: (Vertex|Multi) ;\n"
				+ "(Multi -> n128);\n" + "(v9 -> n800);\n"
				+ "(Vertex -> n800);\n" + "(n64 -> n800);\n"
				+ "(n1000 -> n128);" + " )");

		String splotFM1 = ConvertAnalyzer.convert(getFMVariable("fm1"),
				FMFormat.FSPLOT);
		assertNotNull(splotFM1);

		System.err.println("splotFM1=" + splotFM1);

	}

	@Test
	public void testDependency1() throws Exception {

		// _shell.setVerbose(true);
		_shell.parse("fmO = FM (A : (B|C|D|E)+; )\n"
				+ "fmI = FM (A : (B|C|F)+; )\n"
				+ "fmc1 = FM (Z1 : [Z2] [Z3] [Z4]; )\n"
				+ "fmc2 = FM (Y1 : [Y2] [Y3]; )"
				+ "fmOI = merge intersection { fmO fmI }\n"
				+ "cst1 = constraints (!(B | C) | !Z2; Z2 <-> C; )\n"
				+ "fmS1 = aggregate { fmOI fmc1 } withMapping cst1\n"
				+ "cleanup fmS1\n" + "cst2 = constraints (B <-> C;)\n");

		FeatureModelVariable fmS1 = getFMVariable("fmS1");
		System.err.println("fmS1=" + fmS1);
		System.err.println("fmS1=" + fmS1.counting());

	}

	@Test
	public void testConvert1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("FinalFM = FM(WikiCompare: GeneralFeatures ID ;\n"
				+ "GeneralFeatures: (LicenseCost_Fee|ProgrammingLanguage|DataStorage|UnicodeSupport)+ License RSSFeeds ;\n"
				+ "ID: (MoinMoin|TWiki|PmWiki|PBwiki|DrupalWiki|Confluence|DokuWiki|MediaWiki) ;\n"
				+ "License: (GPL|GPL2|GPL2_2194447|GPL_70787|Nolimit|CommercialFreepersonal_opensourceproject_communityuse|GPL_732185768|GPL2orcompatible) ; LicenseCost_Fee: [mutexFoo] ; mutexFoo : (Communityrelease0feeforsubscriptionsforsupportorhosting|Differentlicences|StartingatUS10installedunlimitedwikis) ; ProgrammingLanguage: (PHP_1749070102|Java|Python|PHP|PHP_1530417072|PerlJavaScript|PHP_79192) ; UnicodeSupport: [X3_475872822] ; DataStorage: (Files|FilesRCS|Database|Database_1854109083|Files_67881559|Database_370693450|Files_883391020) ;\n"
				+ "RSSFeeds: [mutexFoo2] ;\n"
				+ "mutexFoo2 : (Level1Level2Level3Level4Level5Level6|X3|X3_2779|X3_732464882|X3_1929319512|X3_61816772) ;\n"
				+ "(GPL -> X3);(Confluence -> Java);"
				+ "(X3_732464882 -> FilesRCS);(X3_61816772 -> Database_1854109083);"
				+ "(FilesRCS -> TWiki);(GPL -> MoinMoin);(X3_475872822 -> DokuWiki);"
				+ "(GPL2 -> X3_475872822);(Files_67881559 -> DokuWiki);(PmWiki -> X3_1929319512);"
				+ "(Java -> Confluence);(GPL2 -> DokuWiki);(Database_370693450 -> LicenseCost_Fee);"
				+ "(Communityrelease0feeforsubscriptionsforsupportorhosting -> X3_732464882);"
				+ "(Differentlicences -> GPL2orcompatible);(X3_2779 -> PHP);(PHP_79192 -> PmWiki);"
				+ "(GPL2orcompatible -> Differentlicences);(DokuWiki -> GPL2);"
				+ "(GPL_732185768 -> X3_61816772);(Database_1854109083 -> GPL_732185768);(Nolimit -> Level1Level2Level3Level4Level5Level6);(Differentlicences -> Database);(X3_475872822 -> PHP);(Java -> CommercialFreepersonal_opensourceproject_communityuse);(DokuWiki -> X3_2779);(PHP_79192 -> GPL2_2194447);(GPL2orcompatible -> PHP_1530417072);(PHP_1530417072 -> DrupalWiki);(PerlJavaScript -> FilesRCS);(Database -> LicenseCost_Fee);(Level1Level2Level3Level4Level5Level6 -> LicenseCost_Fee);(MediaWiki -> X3_61816772);(Communityrelease0feeforsubscriptionsforsupportorhosting -> TWiki);(PHP_1749070102 -> GPL_732185768);(X3 -> Python);(MoinMoin -> Python);(Database_370693450 -> Java);(Differentlicences -> DrupalWiki);(TWiki -> Communityrelease0feeforsubscriptionsforsupportorhosting);(TWiki -> FilesRCS);(UnicodeSupport -> DataStorage);"
				+ "((((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_883391020) & !Files_67881559) & !X3_1929319512) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_732464882) & !PerlJavaScript) & !GPL) & !PmWiki) & !PHP) & !X3_475872822) & !Confluence) -> !((((((((PHP_1749070102 & LicenseCost_Fee) & ProgrammingLanguage) & UnicodeSupport) & Database_1854109083) & DataStorage) & MediaWiki) & GPL_732185768) & X3_61816772));(((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !X3_61816772) & !Database_1854109083) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !PHP_1749070102) & !GPL_732185768) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !PHP_79192) & !GPL2_2194447) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_883391020) & !X3_1929319512) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_732464882) & !PerlJavaScript) & !GPL) & !PmWiki) & !Confluence) -> !(((((((((LicenseCost_Fee & ProgrammingLanguage) & X3_475872822) & UnicodeSupport) & X3_2779) & GPL2) & PHP) & Files_67881559) & DokuWiki) & DataStorage));"
				+ "(MediaWiki -> GPL_732185768);(Files_883391020 -> X3_1929319512);(MoinMoin -> Files);(PHP -> X3_475872822);(CommercialFreepersonal_opensourceproject_communityuse -> Database_370693450);(Python -> X3);(Python -> GPL);(PHP_79192 -> Files_883391020);(GPL2 -> Files_67881559);(DrupalWiki -> GPL2orcompatible);(CommercialFreepersonal_opensourceproject_communityuse -> Confluence);(X3_732464882 -> TWiki);(PHP -> Files_67881559);(Confluence -> CommercialFreepersonal_opensourceproject_communityuse);(PHP_1530417072 -> Differentlicences);(GPL2_2194447 -> PmWiki);(DataStorage -> UnicodeSupport);(Java -> StartingatUS10installedunlimitedwikis);"
				+ "(Differentlicences -> PHP_1530417072);(X3_1929319512 -> DataStorage);(PHP -> X3_2779);(FilesRCS -> Communityrelease0feeforsubscriptionsforsupportorhosting);(GPL_732185768 -> PHP_1749070102);(StartingatUS10installedunlimitedwikis -> Confluence);(GPL2 -> PHP);(GPL -> Python);(PHP_1530417072 -> Database);(Database_370693450 -> CommercialFreepersonal_opensourceproject_communityuse);(PerlJavaScript -> Communityrelease0feeforsubscriptionsforsupportorhosting);(X3_475872822 -> X3_2779);(X3_732464882 -> PerlJavaScript);(X3_2779 -> Files_67881559);(FilesRCS -> GPL_70787);(X3_1929319512 -> GPL2_2194447);(Database -> Differentlicences);(ProgrammingLanguage -> UnicodeSupport);(CommercialFreepersonal_opensourceproject_communityuse -> Java);(Files_67881559 -> X3_2779);(Communityrelease0feeforsubscriptionsforsupportorhosting -> PerlJavaScript);(PHP_1749070102 -> X3_61816772);(X3_2779 -> GPL2);(CommercialFreepersonal_opensourceproject_communityuse -> StartingatUS10installedunlimitedwikis);(MediaWiki -> PHP_1749070102);(Level1Level2Level3Level4Level5Level6 -> PBwiki);(PHP_79192 -> X3_1929319512);((((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !X3_61816772) & !Database_1854109083) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !PHP_1749070102) & !GPL_732185768) & !StartingatUS10installedunlimitedwikis) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_883391020) & !Files_67881559) & !X3_1929319512) & !X3_2779) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3_732464882) & !PerlJavaScript) & !PmWiki) & !PHP) & !X3_475872822) & !Confluence) -> !((((((((MoinMoin & GPL) & X3) & LicenseCost_Fee) & Files) & ProgrammingLanguage) & Python) & UnicodeSupport) & DataStorage));(GPL2 -> X3_2779);(TWiki -> X3_732464882);(TWiki -> GPL_70787);(Database_1854109083 -> MediaWiki);(GPL_70787 -> Communityrelease0feeforsubscriptionsforsupportorhosting);(X3 -> Files);(X3_2779 -> DokuWiki);(GPL_70787 -> TWiki);(Database -> GPL2orcompatible);(GPL_70787 -> PerlJavaScript);(X3 -> MoinMoin);(GPL2orcompatible -> Database);(DokuWiki -> X3_475872822);(Java -> Database_370693450);(true -> RSSFeeds);(PerlJavaScript -> X3_732464882);(X3_2779 -> X3_475872822);(X3_732464882 -> GPL_70787);(Files -> GPL);(GPL_70787 -> X3_732464882);(X3_732464882 -> DataStorage);(Files -> X3);(Confluence -> Database_370693450);"
				+ "(GPL -> Files);(FilesRCS -> X3_732464882);(((((((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !X3_61816772) & !Database_1854109083) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !PHP_1749070102) & !GPL_732185768) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_67881559) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_732464882) & !PerlJavaScript) & !GPL) & !PHP) & !X3_475872822) & !Confluence) -> !((((((((LicenseCost_Fee & PmWiki) & ProgrammingLanguage) & UnicodeSupport) & GPL2_2194447) & DataStorage) & X3_1929319512) & PHP_79192) & Files_883391020)) & ((((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_883391020) & !Files_67881559) & !X3_1929319512) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_732464882) & !PerlJavaScript) & !GPL) & !PmWiki) & !PHP) & !X3_475872822) & !Confluence) -> !((((((((PHP_1749070102 & LicenseCost_Fee) & ProgrammingLanguage) & UnicodeSupport) & Database_1854109083) & DataStorage) & MediaWiki) & GPL_732185768) & X3_61816772))) & (((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !X3_61816772) & !Database_1854109083) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !PHP_1749070102) & !GPL_732185768) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !PHP_79192) & !GPL2_2194447) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_883391020) & !X3_1929319512) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_732464882) & !PerlJavaScript) & !GPL) & !PmWiki) & !Confluence) -> !(((((((((LicenseCost_Fee & ProgrammingLanguage) & X3_475872822) & UnicodeSupport) & X3_2779) & GPL2) & PHP) & Files_67881559) & DokuWiki) & DataStorage))) & ((((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !X3_61816772) & !Database_1854109083) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !PHP_1749070102) & !GPL_732185768) & !StartingatUS10installedunlimitedwikis) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_883391020) & !Files_67881559) & !X3_1929319512) & !X3_2779) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3_732464882) & !PerlJavaScript) & !PmWiki) & !PHP) & !X3_475872822) & !Confluence) -> !((((((((MoinMoin & GPL) & X3) & LicenseCost_Fee) & Files) & ProgrammingLanguage) & Python) & UnicodeSupport) & DataStorage)));"
				+ "((((((((((((((((((((((((((((((((((((true & !Database_370693450) & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !PHP_1530417072) & !X3_61816772) & !Database_1854109083) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !PHP_1749070102) & !GPL_732185768) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !Files_67881559) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_732464882) & !PerlJavaScript) & !GPL) & !PHP) & !X3_475872822) & !Confluence) -> !((((((((LicenseCost_Fee & PmWiki) & ProgrammingLanguage) & UnicodeSupport) & GPL2_2194447) & DataStorage) & X3_1929319512) & PHP_79192) & Files_883391020));(PerlJavaScript -> TWiki);(TWiki -> PerlJavaScript);(Confluence -> StartingatUS10installedunlimitedwikis);(GPL2orcompatible -> DrupalWiki);(X3_61816772 -> PHP_1749070102);(Database -> DrupalWiki);(Files -> MoinMoin);(Communityrelease0feeforsubscriptionsforsupportorhosting -> GPL_70787);(Database -> PHP_1530417072);(X3_1929319512 -> PHP_79192);(X3_475872822 -> Files_67881559);(FilesRCS -> PerlJavaScript);(StartingatUS10installedunlimitedwikis -> CommercialFreepersonal_opensourceproject_communityuse);(DrupalWiki -> Database);(PmWiki -> GPL2_2194447);(X3_61816772 -> GPL_732185768);(X3 -> GPL);(MoinMoin -> GPL);(Python -> MoinMoin);(Nolimit -> PBwiki);(GPL_70787 -> FilesRCS);(Database_370693450 -> Confluence);(X3_61816772 -> DataStorage);(DokuWiki -> PHP);(Files_883391020 -> PmWiki);(Files_67881559 -> GPL2);(ProgrammingLanguage -> DataStorage);(GPL_732185768 -> Database_1854109083);(DrupalWiki -> PHP_1530417072);(X3_61816772 -> MediaWiki);(GPL2_2194447 -> PHP_79192);(PHP -> GPL2);(X3_2779 -> DataStorage);(DataStorage -> ProgrammingLanguage);(Database_370693450 -> StartingatUS10installedunlimitedwikis);(PBwiki -> Nolimit);(Level1Level2Level3Level4Level5Level6 -> Nolimit);(Communityrelease0feeforsubscriptionsforsupportorhosting -> FilesRCS);(X3_1929319512 -> Files_883391020);(Files_883391020 -> GPL2_2194447);(PmWiki -> Files_883391020);(MediaWiki -> Database_1854109083);(PHP_1749070102 -> MediaWiki);(DrupalWiki -> Differentlicences);(Files_67881559 -> X3_475872822);(GPL2_2194447 -> Files_883391020);(X3_732464882 -> LicenseCost_Fee);(GPL2_2194447 -> X3_1929319512);(PerlJavaScript -> GPL_70787);(X3_1929319512 -> PmWiki);(Database_1854109083 -> X3_61816772);(UnicodeSupport -> ProgrammingLanguage);(Files -> Python);(X3 -> DataStorage);(GPL_732185768 -> MediaWiki);(X3_732464882 -> Communityrelease0feeforsubscriptionsforsupportorhosting);(Database_1854109083 -> PHP_1749070102);(PHP_1749070102 -> Database_1854109083);"
				+ "(StartingatUS10installedunlimitedwikis -> Database_370693450);(PHP -> DokuWiki);(PHP_1530417072 -> GPL2orcompatible);"
				+ "(X3_475872822 -> GPL2);(PmWiki -> PHP_79192);"
				+ "(StartingatUS10installedunlimitedwikis -> Java);(Python -> Files);(MoinMoin -> X3);(Files_67881559 -> PHP);(PBwiki -> Level1Level2Level3Level4Level5Level6);(Files_883391020 -> PHP_79192);(DokuWiki -> Files_67881559);)");

		FeatureModelVariable finalFM = getFMVariable("FinalFM");
		String splot = ConvertAnalyzer.convert(finalFM, FMFormat.FSPLOT);
		System.err.println("splot=" + splot);
		String featureIDE = ConvertAnalyzer.convert(finalFM, FMFormat.FIDE);
		System.err.println("fide=" + featureIDE);
	}

	// TODO:
	public void testConvert2() throws Exception {

		_shell.setVerbose(true);
		// failed due to feature in several groups!
		// call to group disambiguator needed!
		_shell.parse("FinalFM = FM(WikiCompare: GeneralFeatures ID ; GeneralFeatures: (LicenseCost_Fee|ProgrammingLanguage)+ (LicenseCost_Fee|DataStorage)+ (LicenseCost_Fee|UnicodeSupport)+ License RSSFeeds ; ID: (MoinMoin|TWiki|PmWiki|PBwiki|DrupalWiki|Confluence|DokuWiki|MediaWiki) ; License: (GPL|GPL2|GPL2_2194447|GPL_70787|Nolimit|CommercialFreepersonal_opensourceproject_communityuse|GPL_244633230|GPL2orcompatible) ; LicenseCost_Fee: (Communityrelease0feeforsubscriptionsforsupportorhosting|Differentlicences|StartingatUS10installedunlimitedwikis)? ; ProgrammingLanguage: (Java|Python|PHP_1876541332|PHP|PHP_978928972|PerlJavaScript|PHP_79192) ; UnicodeSupport: [X3_1202686150] ; DataStorage: (Files|FilesRCS|Database|Database_1854109083|Files_67881559|Files_1686175778|Database_1323264074) ; RSSFeeds: (X3_799318634|X3_649471770|Level1Level2Level3Level4Level5Level6|X3|X3_2779|X3_291540672)? ; (GPL -> X3);(Confluence -> Java);(PHP_978928972 -> Database);(FilesRCS -> TWiki);(GPL -> MoinMoin);(PmWiki -> Files_1686175778);(Files_1686175778 -> X3_799318634);(PerlJavaScript -> X3_291540672);(Files_67881559 -> DokuWiki);(PHP_1876541332 -> Database_1854109083);(PHP_1876541332 -> GPL_244633230);((((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !GPL_244633230) & !Database_1854109083) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !Database_1323264074) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !PHP_1876541332) & !Files_67881559) & !PHP_978928972) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_1202686150) & !PerlJavaScript) & !GPL) & !X3_649471770) & !PHP) & !Confluence) -> !((((((((X3_799318634 & LicenseCost_Fee) & PmWiki) & ProgrammingLanguage) & UnicodeSupport) & GPL2_2194447) & Files_1686175778) & DataStorage) & PHP_79192));(GPL2 -> DokuWiki);(Java -> Confluence);(X3_1202686150 -> PHP);(Differentlicences -> GPL2orcompatible);(GPL_244633230 -> X3_649471770);(X3_2779 -> PHP);(PHP_79192 -> PmWiki);(GPL2orcompatible -> Differentlicences);(DrupalWiki -> PHP_978928972);(DokuWiki -> GPL2);(GPL_244633230 -> PHP_1876541332);(Nolimit -> Level1Level2Level3Level4Level5Level6);(Differentlicences -> Database);(PHP_978928972 -> GPL2orcompatible);(GPL2orcompatible -> PHP_978928972);(Java -> CommercialFreepersonal_opensourceproject_communityuse);(X3_1202686150 -> DokuWiki);(DokuWiki -> X3_2779);(PHP_79192 -> GPL2_2194447);(X3_649471770 -> DataStorage);(PerlJavaScript -> FilesRCS);(X3_799318634 -> GPL2_2194447);(Database_1323264074 -> Java);(Database -> PHP_978928972);(Database -> LicenseCost_Fee);((((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !GPL_244633230) & !Database_1854109083) & !DrupalWiki) & !Files_1686175778) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !Database_1323264074) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !PHP_1876541332) & !Files_67881559) & !PHP_978928972) & !X3_2779) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3_1202686150) & !PerlJavaScript) & !PmWiki) & !X3_649471770) & !X3_799318634) & !PHP) & !Confluence) -> !((((((((MoinMoin & GPL) & X3) & LicenseCost_Fee) & Files) & ProgrammingLanguage) & Python) & UnicodeSupport) & DataStorage));(Level1Level2Level3Level4Level5Level6 -> LicenseCost_Fee);(X3_291540672 -> PerlJavaScript);(Communityrelease0feeforsubscriptionsforsupportorhosting -> TWiki);(X3 -> Python);(Files_1686175778 -> PHP_79192);(MoinMoin -> Python);(Communityrelease0feeforsubscriptionsforsupportorhosting -> X3_291540672);(Differentlicences -> DrupalWiki);(TWiki -> Communityrelease0feeforsubscriptionsforsupportorhosting);(FilesRCS -> X3_291540672);(TWiki -> FilesRCS);(UnicodeSupport -> DataStorage);(DokuWiki -> X3_1202686150);(MoinMoin -> Files);(Python -> X3);(Python -> GPL);(GPL2 -> Files_67881559);(Database_1323264074 -> StartingatUS10installedunlimitedwikis);(CommercialFreepersonal_opensourceproject_communityuse -> Confluence);(DrupalWiki -> GPL2orcompatible);(StartingatUS10installedunlimitedwikis -> Database_1323264074);(PHP -> Files_67881559);(Confluence -> CommercialFreepersonal_opensourceproject_communityuse);(X3_291540672 -> DataStorage);(DataStorage -> UnicodeSupport);(GPL2_2194447 -> PmWiki);(X3_649471770 -> MediaWiki);(Java -> StartingatUS10installedunlimitedwikis);(PHP -> X3_2779);(FilesRCS -> Communityrelease0feeforsubscriptionsforsupportorhosting);(StartingatUS10installedunlimitedwikis -> Confluence);(GPL2 -> PHP);(GPL -> Python);(PerlJavaScript -> Communityrelease0feeforsubscriptionsforsupportorhosting);(CommercialFreepersonal_opensourceproject_communityuse -> Database_1323264074);(X3_291540672 -> TWiki);(X3_291540672 -> FilesRCS);(PmWiki -> X3_799318634);(Files_1686175778 -> PmWiki);(PHP_79192 -> Files_1686175778);(X3_291540672 -> Communityrelease0feeforsubscriptionsforsupportorhosting);(Differentlicences -> PHP_978928972);(X3_2779 -> Files_67881559);(FilesRCS -> GPL_70787);(Database -> Differentlicences);(ProgrammingLanguage -> UnicodeSupport);(CommercialFreepersonal_opensourceproject_communityuse -> Java);(GPL2 -> X3_1202686150);(Files_67881559 -> X3_2779);(Communityrelease0feeforsubscriptionsforsupportorhosting -> PerlJavaScript);(Database_1854109083 -> GPL_244633230);(X3_649471770 -> GPL_244633230);(Java -> Database_1323264074);(X3_2779 -> GPL2);(PHP_79192 -> X3_799318634);(Database_1854109083 -> PHP_1876541332);(CommercialFreepersonal_opensourceproject_communityuse -> StartingatUS10installedunlimitedwikis);(Level1Level2Level3Level4Level5Level6 -> PBwiki);(PHP_1876541332 -> MediaWiki);(TWiki -> X3_291540672);(Database_1323264074 -> CommercialFreepersonal_opensourceproject_communityuse);(GPL2 -> X3_2779);(X3_2779 -> X3_1202686150);(X3_1202686150 -> GPL2);(X3_799318634 -> Files_1686175778);(TWiki -> GPL_70787);(X3_649471770 -> Database_1854109083);(Database_1854109083 -> MediaWiki);(GPL_70787 -> Communityrelease0feeforsubscriptionsforsupportorhosting);(PHP_1876541332 -> X3_649471770);(X3_291540672 -> GPL_70787);(X3 -> Files);(GPL_70787 -> TWiki);(X3_2779 -> DokuWiki);(Database -> GPL2orcompatible);(MediaWiki -> PHP_1876541332);(GPL_70787 -> PerlJavaScript);(X3_649471770 -> PHP_1876541332);(X3 -> MoinMoin);(MediaWiki -> GPL_244633230);(GPL2orcompatible -> Database);(Database_1323264074 -> Confluence);(true -> RSSFeeds);(X3_799318634 -> PHP_79192);(X3_1202686150 -> X3_2779);(Files -> GPL);(PHP -> X3_1202686150);(Files -> X3);((((((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !Differentlicences) & !Java) & !PBwiki) & !GPL_244633230) & !Database_1854109083) & !Files) & !DrupalWiki) & !Files_1686175778) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !PHP_79192) & !GPL2_2194447) & !Database_1323264074) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !PHP_1876541332) & !PHP_978928972) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !PerlJavaScript) & !GPL) & !PmWiki) & !X3_649471770) & !X3_799318634) & !Confluence) -> !(((((((((X3_1202686150 & LicenseCost_Fee) & ProgrammingLanguage) & UnicodeSupport) & X3_2779) & GPL2) & PHP) & Files_67881559) & DokuWiki) & DataStorage)) & ((((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !GPL_244633230) & !Database_1854109083) & !Files) & !DrupalWiki) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !Database_1323264074) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !PHP_1876541332) & !Files_67881559) & !PHP_978928972) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_1202686150) & !PerlJavaScript) & !GPL) & !X3_649471770) & !PHP) & !Confluence) -> !((((((((X3_799318634 & LicenseCost_Fee) & PmWiki) & ProgrammingLanguage) & UnicodeSupport) & GPL2_2194447) & Files_1686175778) & DataStorage) & PHP_79192))) & ((((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !GPL_244633230) & !Database_1854109083) & !DrupalWiki) & !Files_1686175778) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !Database_1323264074) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !PHP_1876541332) & !Files_67881559) & !PHP_978928972) & !X3_2779) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3_1202686150) & !PerlJavaScript) & !PmWiki) & !X3_649471770) & !X3_799318634) & !PHP) & !Confluence) -> !((((((((MoinMoin & GPL) & X3) & LicenseCost_Fee) & Files) & ProgrammingLanguage) & Python) & UnicodeSupport) & DataStorage))) & ((((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !Files) & !DrupalWiki) & !Files_1686175778) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !Database_1323264074) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !Files_67881559) & !PHP_978928972) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_1202686150) & !PerlJavaScript) & !GPL) & !PmWiki) & !X3_799318634) & !PHP) & !Confluence) -> !((((((((X3_649471770 & LicenseCost_Fee) & ProgrammingLanguage) & UnicodeSupport) & Database_1854109083) & PHP_1876541332) & DataStorage) & MediaWiki) & GPL_244633230)));(GPL -> Files);(PerlJavaScript -> TWiki);(TWiki -> PerlJavaScript);(Files_67881559 -> X3_1202686150);(X3_799318634 -> PmWiki);(Confluence -> StartingatUS10installedunlimitedwikis);(MediaWiki -> X3_649471770);(GPL2orcompatible -> DrupalWiki);(Database -> DrupalWiki);(Files -> MoinMoin);((((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !GPL2) & !Differentlicences) & !Java) & !PBwiki) & !Files) & !DrupalWiki) & !Files_1686175778) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !DokuWiki) & !PHP_79192) & !GPL2_2194447) & !Database_1323264074) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !Files_67881559) & !PHP_978928972) & !X3_2779) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !X3_1202686150) & !PerlJavaScript) & !GPL) & !PmWiki) & !X3_799318634) & !PHP) & !Confluence) -> !((((((((X3_649471770 & LicenseCost_Fee) & ProgrammingLanguage) & UnicodeSupport) & Database_1854109083) & PHP_1876541332) & DataStorage) & MediaWiki) & GPL_244633230));(Communityrelease0feeforsubscriptionsforsupportorhosting -> GPL_70787);(X3_799318634 -> DataStorage);(FilesRCS -> PerlJavaScript);(GPL_244633230 -> MediaWiki);(DrupalWiki -> Database);(StartingatUS10installedunlimitedwikis -> CommercialFreepersonal_opensourceproject_communityuse);(PHP_978928972 -> Differentlicences);(PmWiki -> GPL2_2194447);(X3 -> GPL);(Confluence -> Database_1323264074);(MoinMoin -> GPL);(Python -> MoinMoin);(Nolimit -> PBwiki);(GPL_70787 -> FilesRCS);(DokuWiki -> PHP);(GPL2_2194447 -> Files_1686175778);(Files_67881559 -> GPL2);(ProgrammingLanguage -> DataStorage);(Database_1323264074 -> LicenseCost_Fee);(GPL2_2194447 -> X3_799318634);(GPL2_2194447 -> PHP_79192);(PHP -> GPL2);(X3_1202686150 -> Files_67881559);(X3_2779 -> DataStorage);(DataStorage -> ProgrammingLanguage);(PBwiki -> Nolimit);(Level1Level2Level3Level4Level5Level6 -> Nolimit);(Communityrelease0feeforsubscriptionsforsupportorhosting -> FilesRCS);(MediaWiki -> Database_1854109083);(DrupalWiki -> Differentlicences);(PHP_978928972 -> DrupalWiki);(PerlJavaScript -> GPL_70787);(X3_291540672 -> LicenseCost_Fee);(UnicodeSupport -> ProgrammingLanguage);(Files -> Python);(X3 -> DataStorage);(Files_1686175778 -> GPL2_2194447);(GPL_70787 -> X3_291540672);(PHP -> DokuWiki);(((((((((((((((((((((((((((((((((((true & !Level1Level2Level3Level4Level5Level6) & !GPL_70787) & !Differentlicences) & !Java) & !PBwiki) & !GPL_244633230) & !Database_1854109083) & !Files) & !DrupalWiki) & !Files_1686175778) & !Communityrelease0feeforsubscriptionsforsupportorhosting) & !TWiki) & !StartingatUS10installedunlimitedwikis) & !MoinMoin) & !PHP_79192) & !GPL2_2194447) & !Database_1323264074) & !MediaWiki) & !GPL2orcompatible) & !FilesRCS) & !Database) & !Nolimit) & !X3_291540672) & !PHP_1876541332) & !PHP_978928972) & !Python) & !CommercialFreepersonal_opensourceproject_communityuse) & !X3) & !PerlJavaScript) & !GPL) & !PmWiki) & !X3_649471770) & !X3_799318634) & !Confluence) -> !(((((((((X3_1202686150 & LicenseCost_Fee) & ProgrammingLanguage) & UnicodeSupport) & X3_2779) & GPL2) & PHP) & Files_67881559) & DokuWiki) & DataStorage));(PmWiki -> PHP_79192);(StartingatUS10installedunlimitedwikis -> Java);(Python -> Files);(GPL_244633230 -> Database_1854109083);(MoinMoin -> X3);(Files_67881559 -> PHP);(PBwiki -> Level1Level2Level3Level4Level5Level6);(DokuWiki -> Files_67881559);(Database_1854109083 -> X3_649471770);)");
		FeatureModelVariable finalFM = getFMVariable("FinalFM");
		String splot = ConvertAnalyzer.convert(finalFM, FMFormat.FSPLOT);
		System.err.println("splot=" + splot);
		String featureIDE = ConvertAnalyzer.convert(finalFM, FMFormat.FIDE);
		System.err.println("fide=" + featureIDE);
	}

	@Test
	public void testCompare1() throws Exception {
		String squeleton = "squeleton = FM ( TESTRF : ID [View1] [View2]  ;  ID : (Id4 | Id3 | Id2 | Id1); View1 : [Test1] [Test3] [Test2] ; Test1 : (Truc12 | Truc1)? ; Test2 : [Truc] ;  View2 : [Car5] [Car4] ; Car5 : (Truc2 | Truc23)? ; Car4 : (Truc12_1781254963 | Truc1_81087493)? ; )";
		String finalFM = "finalFM = FM(TESTRF: (View1|View2)+ ID ; View1: [Test3] [Test1] [Test2] ; View2: [Car5] [Car4] ; ID: (Id3|Id4|Id1|Id2) ; Test1: (Truc12|Truc1) ; Test2: Truc ; Car5: (Truc2|Truc23)? ; Car4: (Truc12_1781254963|Truc1_81087493) ; (Truc -> Id3);(Test3 -> Car4);(Truc12_1781254963 -> Test2);(Test2 -> Id3);(Id2 -> Truc2);(Truc -> Truc12);(Id1 -> View1);(Truc23 -> Truc12);(Truc2 -> Truc1_81087493);(Truc12 -> Test2);((((((((((((((((true & !Truc2) & !Test2) & !Car5) & !Car4) & !Test3) & !Truc1) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) & !Id1) -> !((View1 & View2) & Id4));(Car4 -> Car5);(Truc1 -> Id2);(Truc12 -> Truc23);(Truc23 -> Test2);(Truc1 -> Truc1_81087493);(Car4 -> View1);(Car4 -> Test1);(Truc12 -> Truc12_1781254963);(Truc2 -> Id2);(Test2 -> Truc12_1781254963);(Truc12 -> Truc);(Truc23 -> Id3);(Id3 -> Truc12_1781254963);(Truc2 -> Car4);(Test2 -> Truc23);(Id3 -> Truc);(Truc2 -> Truc1);(Truc23 -> Car4);(Truc1_81087493 -> Truc2);(Truc1 -> Truc2);(Id4 -> View2);(Test2 -> Truc12);(Truc12_1781254963 -> Id3);(Id2 -> Truc1_81087493);(Truc23 -> Truc12_1781254963);((((((((((((((((((true & !Truc2) & !Test2) & !Car4) & !Test3) & !Truc1) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) & !Id1) -> !(((View1 & Car5) & View2) & Id4)) & ((((((((((((((((true & !Truc2) & !Test2) & !Car5) & !Car4) & !Test3) & !Truc1) & !Id4) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) -> !((View1 & View2) & Id1))) & ((((((((((((((((true & !Truc2) & !Test2) & !Car5) & !Car4) & !Test3) & !Truc1) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) & !Id1) -> !((View1 & View2) & Id4))) & (((((((((((((((true & !Truc2) & !Test2) & !Car4) & !Test3) & !Truc1) & !Id4) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) -> !(((View1 & Car5) & View2) & Id1)));(((((((((((((((true & !Truc2) & !Test2) & !Car4) & !Test3) & !Truc1) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) & !Id1) -> !(((View1 & Car5) & View2) & Id4));((((((((((((((((true & !Truc2) & !Test2) & !Car5) & !Car4) & !Test3) & !Truc1) & !Id4) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) -> !((View1 & View2) & Id1));(Truc12 -> Id3);(Truc1_81087493 -> Id2);(Truc12_1781254963 -> Truc12);(Truc1_81087493 -> Truc1);(Truc12_1781254963 -> Truc23);(((((((((((((((true & !Truc2) & !Test2) & !Car4) & !Test3) & !Truc1) & !Id4) & !Id3) & !Test1) & !Truc) & !Truc12_1781254963) & !Truc23) & !Truc1_81087493) & !Id2) & !Truc12) -> !(((View1 & Car5) & View2) & Id1));(Id3 -> Truc12);(Id3 -> Truc23);(Id3 -> Test2);(Id2 -> Truc1);(Test1 -> Car4);)";
		_shell.setVerbose(true);
		_shell.parse(squeleton);
		_shell.parse(finalFM);
		_shell.parse("compareResult = compare finalFM squeleton");
		StringVariable result = getStringVariable("compareResult");
		System.out.println(result.getValue());
	}

	@Test
	public void testSebSeduite() throws Exception {

		String fm1 = "FM (Provider: [Log] Source [Authentification] ; "
				+ "Source: [News] [Timetable] ; "
				+ "News: [Cache] [Profile] [Shuffle] [Truncate] ; "
				+ "Truncate: (UserGiven|SystemGiven) ; "
				+ "Timetable: [CacheTimetable] [ProfileTimetable] ; )";

		_shell.parse("fm1 = " + fm1 + "\n");
		_shell.parse("c1 = counting fm1\n");

		IntegerVariable c1 = getIntegerVariable("c1");
		System.err.println("#fm1=" + c1.getV());

	}

	@Ignore
	@Test
	public void testSebSeduite2() throws Exception {

		String jseduite = "FM (Provider: [Log] Source [Authentification] ;\n"
				+ "         Source: [News] [TimeTable] [APAL] [BreakNews] [Ephemerides]\n"
				+ "[Calendar] [Menu] [Pictogram] [Alarm] [Breaks] [Planning];\n"
				+ "\n"
				+ "//           News: [DietNews] [DryNews] [FilterNews] [NoBreakNews]\n"
				+ "//[ReorderNews] [TruncateNews] [ShuffleNews] [AuthNews] [CacheNews]\n"
				+ "//[LogNews];\n"
				+ "//            TruncateNews:\n"
				+ "// (UserGivenTruncateNews|SystemGivenTruncateNews) ;\n"
				+ "\n"
				+ "           TimeTable: [DietTimeTable] [DryTimeTable]\n"
				+ "[FilterTimeTable] [NoBreakTimeTable] [ReorderTimeTable]\n"
				+ "[TruncateTimeTable] [ShuffleTimeTable] [AuthTimeTable]\n"
				+ "[CacheTimeTable] [LogTimeTable];\n"
				+ "             TruncateTimeTable:\n"
				+ "(UserGivenTruncateTimeTable|SystemGivenTruncateTimeTable) ;\n"
				+ "\n"
				+ "           APAL: [DietAPAL] [DryAPAL] [FilterAPAL] [NoBreakAPAL]\n"
				+ "[ReorderAPAL] [TruncateAPAL] [ShuffleAPAL] [AuthAPAL] [CacheAPAL]\n"
				+ "[LogAPAL];\n"
				+ "             TruncateAPAL:\n"
				+ "(UserGivenTruncateAPAL|SystemGivenTruncateAPAL) ;\n"
				+ "\n"
				+ "           BreakNews: [DietBreakNews] [DryBreakNews]\n"
				+ "[FilterBreakNews] [NoBreakBreakNews] [ReorderBreakNews]\n"
				+ "[TruncateBreakNews] [ShuffleBreakNews] [AuthBreakNews]\n"
				+ "[CacheBreakNews] [LogBreakNews];\n"
				+ "             TruncateBreakNews:\n"
				+ "(UserGivenTruncateBreakNews|SystemGivenTruncateBreakNews) ;\n"
				+ "\n"
				+ "           Ephemerides: [DietEphemerides] [DryEphemerides]\n"
				+ "[FilterEphemerides] [NoBreakEphemerides] [ReorderEphemerides]\n"
				+ "[TruncateEphemerides] [ShuffleEphemerides] [AuthEphemerides]\n"
				+ "[CacheEphemerides] [LogEphemerides];\n"
				+ "             TruncateEphemerides:\n"
				+ "(UserGivenTruncateEphemerides|SystemGivenTruncateEphemerides) ;\n"
				+ "\n"
				+ "           Calendar: [DietCalendar] [DryCalendar] [FilterCalendar]\n"
				+ "[NoBreakCalendar] [ReorderCalendar] [TruncateCalendar]\n"
				+ "[ShuffleCalendar] [AuthCalendar] [CacheCalendar] [LogCalendar];\n"
				+ "             TruncateCalendar:\n"
				+ "(UserGivenTruncateCalendar|SystemGivenTruncateCalendar) ;\n"
				+ "\n"
				+ "           Menu: [DietMenu] [DryMenu] [FilterMenu] [NoBreakMenu]\n"
				+ "[ReorderMenu] [TruncateMenu] [ShuffleMenu] [AuthMenu] [CacheMenu]\n"
				+ "[LogMenu];\n"
				+ "             TruncateMenu:\n"
				+ "(UserGivenTruncateMenu|SystemGivenTruncateMenu) ;\n"
				+ "\n"
				+ "           Pictogram: [DietPictogram] [DryPictogram]\n"
				+ "[FilterPictogram] [NoBreakPictogram] [ReorderPictogram]\n"
				+ "[TruncatePictogram] [ShufflePictogram] [AuthPictogram]\n"
				+ "[CachePictogram] [LogPictogram];\n"
				+ "             TruncatePictogram:\n"
				+ "(UserGivenTruncatePictogram|SystemGivenTruncatePictogram) ;\n"
				+ "\n"
				+ "           Alarm: [DietAlarm] [DryAlarm] [FilterAlarm] [NoBreakAlarm]\n"
				+ "[ReorderAlarm] [TruncateAlarm] [ShuffleAlarm] [AuthAlarm] [CacheAlarm]\n"
				+ "[LogAlarm];\n"
				+ "             TruncateAlarm:\n"
				+ "(UserGivenTruncateAlarm|SystemGivenTruncateAlarm) ;\n"
				+ "\n"
				+ "           Breaks: [DietBreaks] [DryBreaks] [FilterBreaks]\n"
				+ "[NoBreakBreaks] [ReorderBreaks] [TruncateBreaks] [ShuffleBreaks]\n"
				+ "[AuthBreaks] [CacheBreaks] [LogBreaks];\n"
				+ "             TruncateBreaks:\n"
				+ "(UserGivenTruncateBreaks|SystemGivenTruncateBreaks) ;\n"
				+ "\n"
				+ "           Planning: [DietPlanning] [DryPlanning] [FilterPlanning]\n"
				+ "[NoBreakPlanning] [ReorderPlanning] [TruncatePlanning]\n"
				+ "[ShufflePlanning] [AuthPlanning] [CachePlanning] [LogPlanning];\n"
				+ "             TruncatePlanning:\n"
				+ "(UserGivenTruncatePlanning|SystemGivenTruncatePlanning) ;    )\n"
				+ "";

		String jseduite2 = "FM (Provider: [Log] Source [Authentification] ;\n"
				+ "         Source: [News] [TimeTable] [APAL] [BreakNews] [Ephemerides]\n"
				+ "[Calendar] [Menu] [Pictogram] [Alarm] [Breaks] [Planning];\n"
				+ "\n"
				+ "           News: [DietNews] [DryNews] [FilterNews] [NoBreakNews]\n"
				+ "[ReorderNews] [TruncateNews] [ShuffleNews] [AuthNews] [CacheNews]\n"
				+ "[LogNews];\n"
				+ "            TruncateNews:\n"
				+ " (UserGivenTruncateNews|SystemGivenTruncateNews) ;\n"
				+ "\n"
				+ "           TimeTable: [DietTimeTable] [DryTimeTable]\n"
				+ "[FilterTimeTable] [NoBreakTimeTable] [ReorderTimeTable]\n"
				+ "[TruncateTimeTable] [ShuffleTimeTable] [AuthTimeTable]\n"
				+ "[CacheTimeTable] [LogTimeTable];\n"
				+ "             TruncateTimeTable:\n"
				+ "(UserGivenTruncateTimeTable|SystemGivenTruncateTimeTable) ;\n"
				+ "\n"
				+ "           APAL: [DietAPAL] [DryAPAL] [FilterAPAL] [NoBreakAPAL]\n"
				+ "[ReorderAPAL] [TruncateAPAL] [ShuffleAPAL] [AuthAPAL] [CacheAPAL]\n"
				+ "[LogAPAL];\n"
				+ "             TruncateAPAL:\n"
				+ "(UserGivenTruncateAPAL|SystemGivenTruncateAPAL) ;\n"
				+ "\n"
				+ "           BreakNews: [DietBreakNews] [DryBreakNews]\n"
				+ "[FilterBreakNews] [NoBreakBreakNews] [ReorderBreakNews]\n"
				+ "[TruncateBreakNews] [ShuffleBreakNews] [AuthBreakNews]\n"
				+ "[CacheBreakNews] [LogBreakNews];\n"
				+ "             TruncateBreakNews:\n"
				+ "(UserGivenTruncateBreakNews|SystemGivenTruncateBreakNews) ;\n"
				+ "\n"
				+ "           Ephemerides: [DietEphemerides] [DryEphemerides]\n"
				+ "[FilterEphemerides] [NoBreakEphemerides] [ReorderEphemerides]\n"
				+ "[TruncateEphemerides] [ShuffleEphemerides] [AuthEphemerides]\n"
				+ "[CacheEphemerides] [LogEphemerides];\n"
				+ "             TruncateEphemerides:\n"
				+ "(UserGivenTruncateEphemerides|SystemGivenTruncateEphemerides) ;\n"
				+ "\n"
				+ "           Calendar: [DietCalendar] [DryCalendar] [FilterCalendar]\n"
				+ "[NoBreakCalendar] [ReorderCalendar] [TruncateCalendar]\n"
				+ "[ShuffleCalendar] [AuthCalendar] [CacheCalendar] [LogCalendar];\n"
				+ "             TruncateCalendar:\n"
				+ "(UserGivenTruncateCalendar|SystemGivenTruncateCalendar) ;\n"
				+ "\n"
				+ "           Menu: [DietMenu] [DryMenu] [FilterMenu] [NoBreakMenu]\n"
				+ "[ReorderMenu] [TruncateMenu] [ShuffleMenu] [AuthMenu] [CacheMenu]\n"
				+ "[LogMenu];\n"
				+ "             TruncateMenu:\n"
				+ "(UserGivenTruncateMenu|SystemGivenTruncateMenu) ;\n"
				+ "\n"
				+ "           Pictogram: [DietPictogram] [DryPictogram]\n"
				+ "[FilterPictogram] [NoBreakPictogram] [ReorderPictogram]\n"
				+ "[TruncatePictogram] [ShufflePictogram] [AuthPictogram]\n"
				+ "[CachePictogram] [LogPictogram];\n"
				+ "             TruncatePictogram:\n"
				+ "(UserGivenTruncatePictogram|SystemGivenTruncatePictogram) ;\n"
				+ "\n"
				+ "           Alarm: [DietAlarm] [DryAlarm] [FilterAlarm] [NoBreakAlarm]\n"
				+ "[ReorderAlarm] [TruncateAlarm] [ShuffleAlarm] [AuthAlarm] [CacheAlarm]\n"
				+ "[LogAlarm];\n"
				+ "             TruncateAlarm:\n"
				+ "(UserGivenTruncateAlarm|SystemGivenTruncateAlarm) ;\n"
				+ "\n"
				+ "           Breaks: [DietBreaks] [DryBreaks] [FilterBreaks]\n"
				+ "[NoBreakBreaks] [ReorderBreaks] [TruncateBreaks] [ShuffleBreaks]\n"
				+ "[AuthBreaks] [CacheBreaks] [LogBreaks];\n"
				+ "             TruncateBreaks:\n"
				+ "(UserGivenTruncateBreaks|SystemGivenTruncateBreaks) ;\n"
				+ "\n"
				+ "           Planning: [DietPlanning] [DryPlanning] [FilterPlanning]\n"
				+ "[NoBreakPlanning] [ReorderPlanning] [TruncatePlanning]\n"
				+ "[ShufflePlanning] [AuthPlanning] [CachePlanning] [LogPlanning];\n"
				+ "             TruncatePlanning:\n"
				+ "(UserGivenTruncatePlanning|SystemGivenTruncatePlanning) ;    )\n"
				+ "";

		_shell.parse("jseduite = " + jseduite + "\n");
		FeatureModelVariable jseduiteFM = getFMVariable("jseduite");
		_shell.parse("cjseduite = counting jseduite\n");

		IntegerVariable cjseduite = getIntegerVariable("cjseduite");
		System.err.println("#jseduite=" + cjseduite.getV());

		System.err
				.println("(splot) #jseduite=" + jseduiteFM.counting (CountingStrategy.BDD_SPLOT));

		_shell.parse("jseduite2 = " + jseduite2 + "\n");
		FeatureModelVariable jseduite2FM = getFMVariable("jseduite2");
		_shell.parse("cjseduite2 = counting jseduite2\n");

		IntegerVariable cjseduite2 = getIntegerVariable("cjseduite2");
		System.err.println("#jseduite2=" + cjseduite2.getV());

		System.err.println("(splot) #jseduite2="
				+ jseduite2FM.counting (CountingStrategy.BDD_SPLOT));

		String jseduite3 = "FM (Provider: [Log] Source [Authentification] ;\n"
				+ "         Source: [News] [TimeTable] [APAL] [BreakNews] [Ephemerides] [Calendar] [Menu] [Pictogram] [Alarm] [Breaks] [Planning] [Twitter] [Video] [BreakScreen] [FeedReader] [ImageScraper] [PictAlbum] [TvShows] [Weather];\n"
				+ "\n"
				+ "           News: [DietNews] [DryNews] [FilterNews] [NoBreakNews] [ReorderNews] [TruncateNews] [ShuffleNews] [AuthNews] [CacheNews] [LogNews];\n"
				+ "             TruncateNews: (UserGivenTruncateNews|SystemGivenTruncateNews) ;\n"
				+ "\n"
				+ "           TimeTable: [DietTimeTable] [DryTimeTable] [FilterTimeTable] [NoBreakTimeTable] [ReorderTimeTable] [TruncateTimeTable] [ShuffleTimeTable] [AuthTimeTable] [CacheTimeTable] [LogTimeTable];\n"
				+ "             TruncateTimeTable: (UserGivenTruncateTimeTable|SystemGivenTruncateTimeTable) ;\n"
				+ "\n"
				+ "           APAL: [DietAPAL] [DryAPAL] [FilterAPAL] [NoBreakAPAL] [ReorderAPAL] [TruncateAPAL] [ShuffleAPAL] [AuthAPAL] [CacheAPAL] [LogAPAL];\n"
				+ "             TruncateAPAL: (UserGivenTruncateAPAL|SystemGivenTruncateAPAL) ;\n"
				+ "\n"
				+ "           BreakNews: [DietBreakNews] [DryBreakNews] [FilterBreakNews] [NoBreakBreakNews] [ReorderBreakNews] [TruncateBreakNews] [ShuffleBreakNews] [AuthBreakNews] [CacheBreakNews] [LogBreakNews];\n"
				+ "             TruncateBreakNews: (UserGivenTruncateBreakNews|SystemGivenTruncateBreakNews) ;\n"
				+ "\n"
				+ "           Ephemerides: [DietEphemerides] [DryEphemerides] [FilterEphemerides] [NoBreakEphemerides] [ReorderEphemerides] [TruncateEphemerides] [ShuffleEphemerides] [AuthEphemerides] [CacheEphemerides] [LogEphemerides];\n"
				+ "             TruncateEphemerides: (UserGivenTruncateEphemerides|SystemGivenTruncateEphemerides) ;\n"
				+ "\n"
				+ "           Calendar: [DietCalendar] [DryCalendar] [FilterCalendar] [NoBreakCalendar] [ReorderCalendar] [TruncateCalendar] [ShuffleCalendar] [AuthCalendar] [CacheCalendar] [LogCalendar];\n"
				+ "             TruncateCalendar: (UserGivenTruncateCalendar|SystemGivenTruncateCalendar) ;\n"
				+ "\n"
				+ "           Menu: [DietMenu] [DryMenu] [FilterMenu] [NoBreakMenu] [ReorderMenu] [TruncateMenu] [ShuffleMenu] [AuthMenu] [CacheMenu] [LogMenu];\n"
				+ "             TruncateMenu: (UserGivenTruncateMenu|SystemGivenTruncateMenu) ;\n"
				+ "\n"
				+ "           Pictogram: [DietPictogram] [DryPictogram] [FilterPictogram] [NoBreakPictogram] [ReorderPictogram] [TruncatePictogram] [ShufflePictogram] [AuthPictogram] [CachePictogram] [LogPictogram];\n"
				+ "             TruncatePictogram: (UserGivenTruncatePictogram|SystemGivenTruncatePictogram) ;\n"
				+ "\n"
				+ "           Alarm: [DietAlarm] [DryAlarm] [FilterAlarm] [NoBreakAlarm] [ReorderAlarm] [TruncateAlarm] [ShuffleAlarm] [AuthAlarm] [CacheAlarm] [LogAlarm];\n"
				+ "             TruncateAlarm: (UserGivenTruncateAlarm|SystemGivenTruncateAlarm) ;\n"
				+ "\n"
				+ "           Breaks: [DietBreaks] [DryBreaks] [FilterBreaks] [NoBreakBreaks] [ReorderBreaks] [TruncateBreaks] [ShuffleBreaks] [AuthBreaks] [CacheBreaks] [LogBreaks];\n"
				+ "             TruncateBreaks: (UserGivenTruncateBreaks|SystemGivenTruncateBreaks) ;\n"
				+ "\n"
				+ "           Planning: [DietPlanning] [DryPlanning] [FilterPlanning] [NoBreakPlanning] [ReorderPlanning] [TruncatePlanning] [ShufflePlanning] [AuthPlanning] [CachePlanning] [LogPlanning];\n"
				+ "             TruncatePlanning: (UserGivenTruncatePlanning|SystemGivenTruncatePlanning) ;\n"
				+ "\n"
				+ "           Twitter: [DietTwitter] [DryTwitter] [FilterTwitter] [NoBreakTwitter] [ReorderTwitter] [TruncateTwitter] [ShuffleTwitter] [AuthTwitter] [CacheTwitter] [LogTwitter];\n"
				+ "             TruncateTwitter: (UserGivenTruncateTwitter|SystemGivenTruncateTwitter) ;\n"
				+ "\n"
				+ "           Video: [DietVideo] [DryVideo] [FilterVideo] [NoBreakVideo] [ReorderVideo] [TruncateVideo] [ShuffleVideo] [AuthVideo] [CacheVideo] [LogVideo];\n"
				+ "             TruncateVideo: (UserGivenTruncateVideo|SystemGivenTruncateVideo) ;\n"
				+ "\n"
				+ "           BreakScreen: [DietBreakScreen] [DryBreakScreen] [FilterBreakScreen] [NoBreakBreakScreen] [ReorderBreakScreen] [TruncateBreakScreen] [ShuffleBreakScreen] [AuthBreakScreen] [CacheBreakScreen] [LogBreakScreen];\n"
				+ "             TruncateBreakScreen: (UserGivenTruncateBreakScreen|SystemGivenTruncateBreakScreen) ;\n"
				+ "\n"
				+ "           FeedReader: [DietFeedReader] [DryFeedReader] [FilterFeedReader] [NoBreakFeedReader] [ReorderFeedReader] [TruncateFeedReader] [ShuffleFeedReader] [AuthFeedReader] [CacheFeedReader] [LogFeedReader];\n"
				+ "             TruncateFeedReader: (UserGivenTruncateFeedReader|SystemGivenTruncateFeedReader) ;\n"
				+ "\n"
				+ "           ImageScraper: [DietImageScraper] [DryImageScraper] [FilterImageScraper] [NoBreakImageScraper] [ReorderImageScraper] [TruncateImageScraper] [ShuffleImageScraper] [AuthImageScraper] [CacheImageScraper] [LogImageScraper];\n"
				+ "             TruncateImageScraper: (UserGivenTruncateImageScraper|SystemGivenTruncateImageScraper) ;\n"
				+ "\n"
				+ "           PictAlbum: [DietPictAlbum] [DryPictAlbum] [FilterPictAlbum] [NoBreakPictAlbum] [ReorderPictAlbum] [TruncatePictAlbum] [ShufflePictAlbum] [AuthPictAlbum] [CachePictAlbum] [LogPictAlbum];\n"
				+ "             TruncatePictAlbum: (UserGivenTruncatePictAlbum|SystemGivenTruncatePictAlbum) ;\n"
				+ "\n"
				+ "           TvShows: [DietTvShows] [DryTvShows] [FilterTvShows] [NoBreakTvShows] [ReorderTvShows] [TruncateTvShows] [ShuffleTvShows] [AuthTvShows] [CacheTvShows] [LogTvShows];\n"
				+ "             TruncateTvShows: (UserGivenTruncateTvShows|SystemGivenTruncateTvShows) ;\n"
				+ "\n"
				+ "           Weather: [DietWeather] [DryWeather] [FilterWeather] [NoBreakWeather] [ReorderWeather] [TruncateWeather] [ShuffleWeather] [AuthWeather] [CacheWeather] [LogWeather];\n"
				+ "             TruncateWeather: (UserGivenTruncateWeather|SystemGivenTruncateWeather) ;)";

		_shell.parse("jseduite3 = " + jseduite3 + "\n");
		FeatureModelVariable jseduite3FM = getFMVariable("jseduite3");
		_shell.parse("cjseduite3 = counting jseduite3\n");

		IntegerVariable cjseduite3 = getIntegerVariable("cjseduite3");
		System.err.println("#jseduite3=" + cjseduite3.getV());

		System.err.println("(splot) #jseduite3="
				+ jseduite3FM.counting (CountingStrategy.BDD_SPLOT));
		System.err.println("(features) #jseduite3="
				+ jseduite3FM.features().size());

	}

	@Test
	public void testSetAlternative1() throws Exception {

		_shell.setVerbose(true);

		String fm1 = "FM (Provider: [Log] Source [Authentification] ; "
				+ "Source: [News] [Timetable] ; "
				+ "News: [Cache] [Profile] [Shuffle] [Truncate] ; "
				+ "Truncate: (UserGiven|SystemGiven) ; "
				+ "Timetable: [CacheTimetable] [ProfileTimetable] ; )";

		_shell.parse("fm1 = " + fm1 + "\n");
		_shell.parse("setAlternative { fm1.News fm1.Timetable } \n");

		FeatureModelVariable c1 = getFMVariable("fm1");
		System.err.println("fm1=" + c1.getSyntacticalRepresentation());

		_shell.parse("setAlternative { fm1.Log fm1.Source } \n");
		System.err.println("fm1=" + c1.getSyntacticalRepresentation());

	}

}
