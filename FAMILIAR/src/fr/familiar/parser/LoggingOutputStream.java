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
/**
 * @see http://blogs.sun.com/nickstephen/entry/java_redirecting_system_out_and
 */
package fr.familiar.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An OutputStream that writes contents to a Logger upon each call to flush()
 */
public class LoggingOutputStream extends ByteArrayOutputStream {

	private String lineSeparator;

	private Logger logger;
	private Level level;

	/**
	 * Constructor
	 * 
	 * @param logger
	 *            Logger to write to
	 * @param level
	 *            Level at which to write the log message
	 */
	public LoggingOutputStream(Logger logger, Level level) {
		super();
		this.logger = logger;
		this.level = level;
		lineSeparator = System.getProperty("line.separator");
	}

	/**
	 * upon flush() write the existing contents of the OutputStream to the
	 * logger as a log record.
	 * 
	 * @throws java.io.IOException
	 *             in case of error
	 */
	public void flush() throws IOException {

		String record;
		synchronized (this) {
			super.flush();
			record = this.toString();
			super.reset();

			if (record.length() == 0 || record.equals(lineSeparator)) {
				// avoid empty records
				return;
			}

			logger.logp(level, "", "", record);
		}
	}
}
