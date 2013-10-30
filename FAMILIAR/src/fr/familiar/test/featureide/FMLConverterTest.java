/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.test.featureide;

import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Test;

import de.ovgu.featureide.fm.core.FeatureModel;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.parser.DoubleVariable;
import fr.familiar.test.FMLTest;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.StringVariable;
import gsd.synthesis.Formula;

/**
 * @author mathieuacher
 * 
 */
public class FMLConverterTest extends FMLTest {

	@Test
	public void testXMI1() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )\n"
				+ "fm1xmi = convert fm1 into xmi\n" + "");

		StringVariable fm1xmi = getStringVariable("fm1xmi");
		assertNotNull(fm1xmi);

		fm1xmi.getValue().contains("xmi");
	}
	
	

	@Test
	public void testXMI2() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J)+ ; B -> !J ; (C | D) & !J ; )\n"
				+ "fm1xmi = convert fm1 into xmi\n" + "");

		StringVariable fm1xmi = getStringVariable("fm1xmi");
		assertNotNull(fm1xmi);

		fm1xmi.getValue().contains("xmi");
	}

	@Test
	public void testXMI3() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; D : H I [J] ; B -> !J ; (!C | !D) | !J ; )\n"
				+ "fm1xmi = convert fm1 into xmi\n" + "");

		StringVariable fm1xmi = getStringVariable("fm1xmi");
		assertNotNull(fm1xmi);

		fm1xmi.getValue().contains("xmi");
	}

	@Test
	public void testXMI4() throws Exception {

		_shell.parse("fmFooXmi1 = FM (A: B C [D]; D : H I [J] ; B -> !J ; (!C | !D) | !J ; )\n"
				+ "fm1xmi = convert fmFooXmi1 into xmi\n" + "");

		StringVariable fm1xmi = getStringVariable("fm1xmi");
		assertNotNull(fm1xmi);

		fm1xmi.getValue().contains("xmi");

		String fmFilename = fm1xmi.getIdentifier() + new Random().nextInt()
				+ ".xmi";
		FileSerializer.write("output/" + fmFilename, fm1xmi.getValue());

		String newFmXmi = "fmFooXmi2";
		_shell.parse(newFmXmi + " = " + "FM (" + "\"" + fmFilename + "\"" + ")");

		FeatureModelVariable fmXMI = getFMVariable(newFmXmi);
		System.err.println("fmXMI=" + fmXMI.toString());

	}

	@Test
	public void testSPLOT1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )\n"
				+ "fm1c = convert fm1 into SPLOT\n"
				+ "serialize fm1 into SPLOT");

		StringVariable fm1xml = getStringVariable("fm1c");
		assertNotNull(fm1xml);
		fm1xml.getValue().contains(":r");
		fm1xml.getValue().contains(":o");
		fm1xml.getValue().contains(":m");
		System.err.println("fm1xml=" + fm1xml.getVal());
	}

	@Test
	public void testSPLOT2() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("laptop = " + FMLTest.FM_LAPTOP + "\n"
				+ "claptop = convert laptop into SPLOT\n"
				+ "serialize laptop into SPLOT\n");

		StringVariable fm1xml = getStringVariable("claptop");
		assertNotNull(fm1xml);
		fm1xml.getValue().contains(":r");
		fm1xml.getValue().contains(":o");
		fm1xml.getValue().contains(":m");
		System.err.println("claptop=" + fm1xml.getVal());
		FeatureModelVariable laptop = getFMVariable("laptop");
		assertNotNull(laptop.toSPLOT());
	}

	@Test
	public void testSPLOT3() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("run \"deployment.fml\"" + "\n"
				+ "req = convert requirement into SPLOT\n"
				+ "serialize requirement into SPLOT\n"
				+ "serialize deploymentFM into SPLOT");

		StringVariable splotReq = getStringVariable("req");
		assertNotNull(splotReq);
		splotReq.getValue().contains(":r");
		splotReq.getValue().contains(":o");
		splotReq.getValue().contains(":m");
		System.err.println("req=" + splotReq.getVal());
	}
	
	@Test
	public void testSPLOT4() throws Exception {
		
		String fm = "FM (IOS_EBook_Readers: Book_source_management_features Cost Display_features Edit_tool_features Navigation_features Supported_file_formats ; \n" + 
				"Book_source_management_features: (Bookstores|Tagbooks|Browserdownloading)? [Sortbooks] [Epubdownloading] [Manualdownloading] [Booksearch] [OPDScatalog] [Otherimportvia] ; \n" + 
				"Cost: [InApppurchases] Price ; \n" + 
				"Display_features: (Customizefont|Day_Nightmode)+ (Stylesbold_italic_etc_|BrightnessControl)+ (PageTurnEffects|Customizecolor)+ (Customizetextalignment|Customizefontscale)+ [Enable_Disablehyphenation] [Indentparagraph] [Customizemargins] [Enable_Disablesleepmode] [Enable_DisableCSS] [Themes] [Customizelinespace] [Customizetransparency] [Customizeparagraphspace] ; \n" + 
				"Edit_tool_features: (Annotate|Share)+ (TTSsupport|Editmetadata|Offlinedictionary)? (Lookupwikipedia|Translate)? [Highlight] [Onlinedictionary] [DRMsupport] ExporttoiTunes ; \n" + 
				"Navigation_features: (Fulltextsearch|RotationLock)+ [GoTo] Bookmarks Resume Pagination [Autoscroll] Backward_forward TableofContents Progressindicator ; \n" + 
				"Supported_file_formats: (Djvu|Rtf)? (Epub|Pdf)+ Totalofformats [Mobi] [Cbr__cbz] [Html] [Fb2] [Pdb] [Chm] [Txt] ; \n" + 
				"Bookstores: (AppleiBookstore|Freeebooklibrariesonly)? ; \n" + 
				"Otherimportvia: (CalibreeMail|Calibre|EMail)? ; \n" + 
				"InApppurchases: (X159Extrafeatures|X399uBooksXLadfree|X799Voices)? ; \n" + 
				"Price: (X159|X399|Free) ; \n" + 
				"Indentparagraph: [Configurable_1335028569] ; \n" + 
				"BrightnessControl: [Verticalswipe] ; \n" + 
				"Customizecolor: (Sepiaeffect|Presetchoicesonly)? ; \n" + 
				"Customizemargins: [Presetchoicesonly_1484749378] ; \n" + 
				"Customizefontscale: [Pinchzoomgestures] ; \n" + 
				"Themes: [Configurableincludingbackgroundimage] ; \n" + 
				"Customizefont: [Presetchoicesonly_128375103] ; \n" + 
				"Stylesbold_italic_etc_: [Buggy] ; \n" + 
				"Customizetextalignment: [Justificationonly] ; \n" + 
				"PageTurnEffects: [Configurable] ; \n" + 
				"Day_Nightmode: [Usingstyles] ; \n" + 
				"Editmetadata: [AuthorTitle] ; \n" + 
				"Share: (FacebookTwittereMail|Facebook|TwoChinesewebsitesonly|WholebookonlyeMail|FacebookTwitter|EMail_66113436|Facebookonlybooktitle) ; \n" + 
				"Bookmarks: (Lastplacemarkeronly|Unlimited)? ; \n" + 
				"Pagination: (Mixedscrollingpagination|Configurable_1330677464)? ; \n" + 
				"Backward_forward: [Configurable_2082138782] ; \n" + 
				"RotationLock: [SupportoniPad] ; \n" + 
				"Progressindicator: (Inlibraryandinbook|Inbookonly)? ; \n" + 
				"Totalofformats: (X3|X10|X1|X2|X6|X4|X5) ; \n" + 
				"(Facebook -> Sepiaeffect);\n" + 
				"(Translate -> Html);\n" + 
				"(Justificationonly -> !Sepiaeffect);\n" + 
				"(Facebookonlybooktitle -> Inbookonly);\n" + 
				"(Lastplacemarkeronly -> !Fulltextsearch);\n" + 
				"(Chm -> !Customizeparagraphspace);\n" + 
				"(FacebookTwittereMail <-> Djvu);\n" + 
				"(Sepiaeffect -> Lookupwikipedia);\n" + 
				"(X4 -> Free);\n" + 
				"(Enable_DisableCSS -> !AuthorTitle);\n" + 
				"(Justificationonly -> EMail);\n" + 
				"(Configurable_2082138782 -> Customizetransparency);\n" + 
				"(X5 <-> Configurable_1330677464);\n" + 
				"(Annotate -> !Fb2);\n" + 
				"(CalibreeMail -> Verticalswipe);\n" + 
				"(Inbookonly -> !TTSsupport);\n" + 
				"(Manualdownloading -> !Highlight);\n" + 
				"(Sepiaeffect -> X2);\n" + 
				"(X4 -> !Rtf);\n" + 
				"(Mixedscrollingpagination -> Translate);\n" + 
				"(X799Voices -> Lookupwikipedia);\n" + 
				"(Lastplacemarkeronly <-> Facebookonlybooktitle);\n" + 
				"(Configurable_2082138782 -> Cbr__cbz);\n" + 
				"(Verticalswipe -> !Fb2);\n" + 
				"(X399 -> Enable_Disablesleepmode);\n" + 
				"(X6 <-> Usingstyles);\n" + 
				"(X2 -> !Txt);\n" + 
				"(Customizelinespace -> Share);\n" + 
				"(Djvu -> Tagbooks);\n" + 
				"(Mobi -> !Enable_Disablehyphenation);\n" + 
				"(X399 <-> X6);\n" + 
				"(GoTo -> !X3);\n" + 
				"(FacebookTwitter -> DRMsupport);\n" + 
				"(X1 -> !Txt);\n" + 
				"(Pinchzoomgestures -> Customizeparagraphspace);\n" + 
				"(Pinchzoomgestures -> Inlibraryandinbook);\n" + 
				"(Onlinedictionary -> Annotate);\n" + 
				"(Fb2 -> Txt);\n" + 
				"(Offlinedictionary -> !X4);\n" + 
				"(Configurable -> Customizelinespace);\n" + 
				"(Facebookonlybooktitle -> Customizetransparency);\n" + 
				"(Chm -> Translate);\n" + 
				"(DRMsupport -> GoTo);\n" + 
				"(Calibre <-> X399);\n" + 
				"(X4 -> Enable_Disablehyphenation);\n" + 
				"(Configurableincludingbackgroundimage -> !Highlight);\n" + 
				"(Configurable <-> Verticalswipe);\n" + 
				"(AuthorTitle <-> WholebookonlyeMail);\n" + 
				"(Freeebooklibrariesonly -> Cbr__cbz);\n" + 
				"(Djvu -> Configurableincludingbackgroundimage);\n" + 
				"(Configurable_1330677464 <-> FacebookTwittereMail);\n" + 
				"(DRMsupport -> Unlimited);\n" + 
				"(TwoChinesewebsitesonly -> X4);\n" + 
				"(Epubdownloading -> !DRMsupport);\n" + 
				"(Onlinedictionary -> !Presetchoicesonly_1484749378);\n" + 
				"(Configurable_1335028569 -> Pdb);\n" + 
				"(EMail_66113436 -> !Inlibraryandinbook);\n" + 
				"(Lastplacemarkeronly -> !Customizeparagraphspace);\n" + 
				"(Offlinedictionary -> !Justificationonly);\n" + 
				"(GoTo -> Pdf);\n" + 
				"(Offlinedictionary -> !Enable_DisableCSS);\n" + 
				"(CalibreeMail -> Enable_Disablesleepmode);\n" + 
				"(X2 -> DRMsupport);\n" + 
				"(Inbookonly -> !Freeebooklibrariesonly);\n" + 
				"(Justificationonly -> Fulltextsearch);\n" + 
				"(FacebookTwitter -> Lookupwikipedia);\n" + 
				"(Annotate -> Free);\n" + 
				"(Configurableincludingbackgroundimage -> Enable_DisableCSS);\n" + 
				"(Presetchoicesonly -> !Unlimited);\n" + 
				"(Annotate -> !TwoChinesewebsitesonly);\n" + 
				"(Autoscroll -> Enable_Disablehyphenation);\n" + 
				"(FacebookTwitter -> Inlibraryandinbook);\n" + 
				"(TwoChinesewebsitesonly -> EMail);\n" + 
				"(OPDScatalog -> Booksearch);\n" + 
				"(Customizeparagraphspace -> !DRMsupport);\n" + 
				"(Justificationonly -> !Enable_Disablehyphenation);\n" + 
				"(X4 -> Customizelinespace);\n" + 
				"(X10 -> Rtf);\n" + 
				"(Translate -> EMail);\n" + 
				"(AppleiBookstore -> Sepiaeffect);\n" + 
				"(Bookstores -> Sortbooks);\n" + 
				"(Annotate -> !Buggy);\n" + 
				"(Manualdownloading -> !TwoChinesewebsitesonly);\n" + 
				"(Pinchzoomgestures <-> Configurableincludingbackgroundimage);\n" + 
				"(X2 -> EMail);\n" + 
				"(Pdb -> Manualdownloading);\n" + 
				"(AuthorTitle -> CalibreeMail);\n" + 
				"(Pdf -> !X399uBooksXLadfree);\n" + 
				"(Facebook -> Presetchoicesonly_128375103);\n" + 
				"(Presetchoicesonly_128375103 -> !Enable_Disablehyphenation);\n" + 
				"(Enable_Disablehyphenation -> Customizecolor);\n" + 
				"(FacebookTwitter -> !Customizefontscale);\n" + 
				"(X1 -> Share);\n" + 
				"(Customizelinespace -> !Freeebooklibrariesonly);\n" + 
				"(Customizetransparency -> !Bookstores);\n" + 
				"(Enable_Disablehyphenation -> Fulltextsearch);\n" + 
				"(TwoChinesewebsitesonly -> Pinchzoomgestures);\n" + 
				"(Manualdownloading -> Sortbooks);\n" + 
				"(Sortbooks -> GoTo);\n" + 
				"(Customizetransparency -> CalibreeMail);\n" + 
				"(AppleiBookstore -> Enable_Disablehyphenation);\n" + 
				"(EMail -> !OPDScatalog);\n" + 
				"(WholebookonlyeMail <-> X159Extrafeatures);\n" + 
				"(FacebookTwitter -> Mobi);\n" + 
				"(Configurable_1335028569 -> Bookstores);\n" + 
				"(X399uBooksXLadfree -> Highlight);\n" + 
				"(Presetchoicesonly <-> TTSsupport);\n" + 
				"(X399uBooksXLadfree <-> Mixedscrollingpagination);\n" + 
				"(X799Voices <-> X3);\n" + 
				"(Chm <-> X10);\n" + 
				"(Presetchoicesonly_128375103 -> Enable_DisableCSS);\n" + 
				"(Txt -> Unlimited);\n" + 
				"(Enable_Disablesleepmode <-> OPDScatalog);\n" + 
				"(Enable_DisableCSS -> !EMail_66113436);\n" + 
				"(SupportoniPad -> X159);\n" + 
				"(TwoChinesewebsitesonly -> Bookstores);\n" + 
				"(SupportoniPad <-> Freeebooklibrariesonly);\n" + 
				"(Presetchoicesonly_1484749378 -> Justificationonly);\n" + 
				"(OPDScatalog -> !Lookupwikipedia);\n" + 
				"(TTSsupport -> Epubdownloading);\n" + 
				"(Calibre -> Rtf);\n" + 
				"(X399 -> Autoscroll);\n" + 
				"(X5 -> !Txt);\n" + 
				"(Offlinedictionary -> !Verticalswipe);\n" + 
				"(Facebookonlybooktitle -> X159);\n" + 
				"(Presetchoicesonly_1484749378 -> Presetchoicesonly_128375103);\n" + 
				"(Sortbooks -> Booksearch);\n" + 
				"(X159Extrafeatures <-> Configurable_1335028569);\n" + 
				"(X2 -> Inbookonly);\n" + 
				"(X10 <-> SupportoniPad);\n" + 
				"(SupportoniPad -> !Fulltextsearch);\n" + 
				"(AppleiBookstore -> Offlinedictionary);\n" + 
				"(Pdb -> !Autoscroll);\n" + 
				"(Enable_DisableCSS -> Unlimited);\n" + 
				"(X3 <-> Presetchoicesonly);\n" + 
				"(OPDScatalog -> !Highlight);\n" + 
				"(Configurableincludingbackgroundimage -> Enable_Disablehyphenation);\n" + 
				"(Mixedscrollingpagination -> Autoscroll);\n" + 
				"(Chm -> Mobi);\n" + 
				"(Customizecolor <-> Epub);\n" + 
				"(Configurable_2082138782 <-> X5);\n" + 
				"(Buggy -> Txt);\n" + 
				"(Booksearch -> !Mixedscrollingpagination);\n" + 
				"(Booksearch -> !Justificationonly);\n" + 
				"(Rtf -> Manualdownloading);\n" + 
				"(Tagbooks -> Epubdownloading);\n" + 
				"(Lookupwikipedia -> Onlinedictionary);\n" + 
				"(X6 -> Customizeparagraphspace);\n" + 
				"(Configurableincludingbackgroundimage -> Verticalswipe);\n" + 
				"(Tagbooks -> Editmetadata);\n" + 
				"(OPDScatalog -> Customizelinespace);\n" + 
				"(X799Voices -> Pdf);\n" + 
				"(Usingstyles <-> Browserdownloading);\n" + 
				"(Customizelinespace -> Epubdownloading);\n" + 
				"(DRMsupport -> Highlight);\n" + 
				"(Configurable_1330677464 -> Onlinedictionary);\n" + 
				"(TTSsupport -> Justificationonly);\n" + 
				"(Mobi -> Share);\n" + 
				"(EMail_66113436 <-> Fb2);\n" + 
				"(Buggy -> Inbookonly);\n" + 
				"(EMail -> Epub);\n" + 
				"(Indentparagraph -> Customizeparagraphspace);\n" + 
				"(Inlibraryandinbook -> Fulltextsearch);\n" + 
				"(Justificationonly -> Highlight);\n" + 
				"(Inlibraryandinbook -> GoTo);\n" + 
				"(Epub <-> Customizefontscale);\n" + 
				"(X4 -> !Cbr__cbz);\n" + 
				"(Editmetadata -> Free);\n" + 
				"(Presetchoicesonly_128375103 -> !Booksearch);\n" + 
				"(X6 -> Offlinedictionary);\n" + 
				"(Mobi -> Offlinedictionary);\n" + 
				"(X4 -> Txt);\n" + 
				"(Booksearch -> Epub);\n" + 
				"(Autoscroll -> Buggy);\n" + 
				"(Lastplacemarkeronly -> !Pdf);\n" + 
				"(Mixedscrollingpagination -> Tagbooks);\n" + 
				"(Facebook -> !Fulltextsearch);\n" + 
				"(Rtf -> Epubdownloading);\n" + 
				"(Cbr__cbz -> Pdb);\n" + 
				"(AuthorTitle -> Buggy);\n" + 
				"(Html <-> EMail_66113436);)\n" ;
		
		FeatureModelVariable fm1 = FM ("fm1", fm);
		splar.core.fm.FeatureModel splotFm1 = fm1.toSPLOT() ;
		assertNotNull(splotFm1);
		Formula<String> flaSPLOT = fm1.getSPLOTFormulaAligned(_builder);
		Formula<String> fla3 = fm1.getFormula();
		assertNotNull(fla3);
		fla3.free() ; 
		Formula<String> flaSPLOT2 = fm1.getSPLOTFormula();
		assertNotNull(flaSPLOT2);
		flaSPLOT2.free() ;
		assertNotNull(flaSPLOT);
		flaSPLOT.free() ; 
		 
		
	}

	@Test
	public void testFeatureIDE1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (I|J)+; )\n"
				+ "fm2 = FM (A2 : B2 C2 [D2]; D2 : (E2|F2|G2)+; C2 : (I2|J2); )\n"
				+ "fm3 = FM (A3 : B3 C3 [D3]; D3 : (E3|F3|G3)+; C3 : (I3|J3)+; )\n"
				+ "fm4 = aggregate { fm1 fm2 fm3 }\n"
				+ "s4 = convert fm4 into fmcalc\n"
				+ "s4ide = convert fm4 into featureide");

		FeatureModelVariable fm4 = getFMVariable("fm4");
		System.err.println("fm4=" + fm4.getFm().getDiagram());
		StringVariable s4 = getStringVariable("s4");
		System.err.println("s4=" + s4.getVal());
		StringVariable s4ide = getStringVariable("s4ide");
		System.err.println("s4ide=" + s4ide.getVal());
	}

	@Test
	public void testFeatureIDE2() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (I|J)+; )\n"
				+ "fm2 = FM (A : B C [D]; D : (E|F|G)+; C : (I|J) ; )\n"
				+ "fm3 = FM (A : B C [D]; D : (E|F|G)+; C : (I|J)+ ; )\n"
				+ "fm4 = merge sunion { fm1 fm2 fm3 }\n"
				+ "s4 = convert fm4 into fmcalc\n"
				+ "s4ide = convert fm4 into featureide");

		FeatureModelVariable fm4 = getFMVariable("fm4");
		System.err.println("fm4=" + fm4.getFm().getDiagram());
		StringVariable s4 = getStringVariable("s4");
		System.err.println("s4=" + s4.getVal());
		StringVariable s4ide = getStringVariable("s4ide");
		System.err.println("s4ide=" + s4ide.getVal());
	}

	@Test
	public void testFeatureIDE3() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (OSMobile: Client Marque Truc Developpeur ID ; Client: (Bon_Signal_Client|Cher_Client)+ [Bonne_Batterie_Client] ; Marque: (Apple|Google) ; Truc: [Thing2_Truc] ; Developpeur: (Code_Ouvert_Developpeur|Appli_Rentable_Developpeur)+ [API_Consequente_Developpeur] ; ID: (Android|Iphone) ; API_Consequente_Developpeur: [Open_Source] ; (Iphone -> Apple);(Apple -> Iphone);(true -> ID);(Client -> (Bonne_Batterie_Client | Bon_Signal_Client));(Android -> Open_Source);(Iphone -> Appli_Rentable_Developpeur);(Open_Source -> Android);(Developpeur -> (API_Consequente_Developpeur | Appli_Rentable_Developpeur));(Google -> Android);(Google -> Open_Source);(Open_Source -> Code_Ouvert_Developpeur);(Iphone -> Bonne_Batterie_Client);(Open_Source -> Google);(Android -> Google);(Open_Source -> Bon_Signal_Client);(Iphone -> Cher_Client);(Open_Source -> Thing2_Truc);)\n"
				+ "s1 = convert fm1 into featureide");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		System.err.println("fm1=" + fm1.getFm().getDiagram());
		FeatureModel fm1fide = new FMLtoFeatureIDE(fm1).convert();
		assertNotNull(fm1fide);

		StringVariable s1 = getStringVariable("s1");
		assertNotNull(s1);
		System.err.println("s1=" + s1.getSpecificValue());

	}

	@Test
	public void testInteroperability() throws Exception {

		_shell.parse("" + "requirement = FM (\"requirement.xmi\")\n"
				+ "platform = FM (\"platform.xmi\") \n"
		// it does work but it generates fml files in output folder and
		// therefore
		// ambigous filenames for the rest of the test cases:
		// "serialize requirement into fml \n" +
		// "serialize platform into fml\n"
		);

		FeatureModelVariable requirementFM = getFMVariable("requirement");
		System.err.println("#requirement=" + requirementFM.counting());
		FeatureModelVariable platformFM = getFMVariable("platform");
		System.err.println("#platform=" + platformFM.counting());
	}

	@Test
	public void testLoad() throws Exception {

		_shell.parse("" + "requirement = FM (\"requirement.fml\")\n"
				+ "platform = FM (\"platform.fml\") " + "\n");

		FeatureModelVariable requirementFM = getFMVariable("requirement");
		System.err.println("#requirement=" + requirementFM.counting());
		FeatureModelVariable platformFM = getFMVariable("platform");
		System.err.println("#platform=" + platformFM.counting());

	}
	
	@Test
	public void testTVL1() throws Exception {

		_shell.parse("fm1 = FM (\"inputFMLTests/testTVL1.tvl\")\n"
				+ "n1 = counting fm1\n" + "");

		FeatureModelVariable fmv1 = getFMVariable("fm1");
		DoubleVariable n1 = getDoubleVariable("n1");
		
		System.err.println("n1=" + n1.getDouble());
		System.err.println("fm1.*=" + fmv1.features().names());
		System.err.println("fm1=" + fmv1);
	}

}
