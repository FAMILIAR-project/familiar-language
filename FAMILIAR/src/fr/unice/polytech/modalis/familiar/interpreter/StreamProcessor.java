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
package fr.unice.polytech.modalis.familiar.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamProcessor extends Thread {

	public static final String FAMILIAR = "FAMILIAR";

	private InputStream inputStream;

	public StreamProcessor(InputStream is) {
		this.inputStream = is;
	}

	public void run() {
		

		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(inputStream);
			br = new BufferedReader(isr);

			while (true) {
				//if (br.ready()) {
					String s = br.readLine();
					if (s == null) {
						break;
					}

					FMLShell.getInstance().printDebugMessage(
							FAMILIAR + ": " + s);
					FMLShell.getInstance().parse(s);
					FMLShell.getInstance().printPrompt();

				//}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
