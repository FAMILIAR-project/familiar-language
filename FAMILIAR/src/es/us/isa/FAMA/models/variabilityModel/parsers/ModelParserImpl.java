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
package es.us.isa.FAMA.models.variabilityModel.parsers;

import fr.familiar.attributedfm.AttributedFeatureModel;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class ModelParserImpl implements ModelParser {

	// readerId -> IReader
	private Map<String, IReader> readers;

	// writerId -> IWriter
	private Map<String, IWriter> writers;

	private Map<String, Collection<String>> typeToReader;

	private Map<String, Collection<String>> typeToWriter;

	public ModelParserImpl() {
		readers = new HashMap<String, IReader>();
		writers = new HashMap<String, IWriter>();
		typeToReader = new HashMap<String, Collection<String>>();
		typeToWriter = new HashMap<String, Collection<String>>();
	}

	public Collection<String> getReadersId() {
		return readers.keySet();
	}

	public Collection<String> getWritersId() {
		return writers.keySet();
	}

	public AttributedFeatureModel read(String path) {
		AttributedFeatureModel res = null;
		File f = new File(path);
		if (!f.exists()){
			throw new IllegalStateException(path+" file does not exist");
		}
		int index = path.lastIndexOf(".");
		String fileType = path.substring(index + 1);
		Collection<String> imps = typeToReader.get(fileType);
		if (imps != null) {
			Iterator<String> it = imps.iterator();
			boolean canParse = false;
			while (it.hasNext() && !canParse){
				String id = it.next();
				IReader r = readers.get(id);
				if (r != null && r.canParse(path)){
					canParse = true;
					try {
						res = r.parseFile(path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (!canParse){
				res = tryAllReaders(path);
			}
		} else {
			res = tryAllReaders(path);
				
		}
		if (res == null){
			System.out.println("Parser not found for " + path
					+ " file type");
			throw new IllegalStateException("Parser not found for " + path
					+ " file type");
		}
		return res;
	}

	
	private AttributedFeatureModel tryAllReaders(String path){
		AttributedFeatureModel res = null;
		Iterator<IReader> it = readers.values().iterator();
		while (it.hasNext() && res == null){
			IReader r = it.next();
			if (r.canParse(path)){
				try {
					res = r.parseFile(path);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	
	public AttributedFeatureModel read(String path, String readerId) {
		AttributedFeatureModel res = null;
		File f = new File(path);
		if (!f.exists()){
			throw new IllegalStateException(path+" file does not exist");
		}
		IReader r = readers.get(readerId);
		if (r != null) {
			if (r.canParse(path)) {
				try {
					res = r.parseFile(path);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				throw new RuntimeException(readerId + " cannot parse " + path
						+ " file");
			}
		} else {
			throw new IllegalArgumentException(readerId
					+ " is not a valid id for a reader");
		}
		return res;
	}

	public void write(AttributedFeatureModel vm, String path) {
		// pillar el primero que coincida
		// con la extension del fichero
		// si no tiene extension, pillar el que queramos
		int index = path.lastIndexOf(".");
		if (index >= 0) {
			String ext = path.substring(index + 1);
			Collection<String> imps = typeToWriter.get(ext);
			if (imps != null) {
				Iterator<String> it = imps.iterator();
				if (it.hasNext()) {
					String id = it.next();
					write(vm, path, id);
				} else {
					throw new IllegalArgumentException("Parser not found for "
							+ ext + " file type");
				}
			} else {
				throw new IllegalArgumentException("Parser not found for "
						+ ext + " file type");
			}
		} else {
			throw new IllegalArgumentException(
					"Please, specify a file extension for " + path);
		}

	}

	public void write(AttributedFeatureModel vm, String path, String writerId) {
		IWriter w = writers.get(writerId);
		try {
			if (w != null) {
				w.writeFile(path, vm);
			} else {
				throw new IllegalArgumentException(writerId
						+ " is not a valid id for a writer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addReader(IReader r, String id) {
		readers.put(id, r);
	}

	public void addWriter(IWriter r, String id) {
		writers.put(id, r);
	}

//	private String getFileType(String path) {
//		DataInputStream dataInput = null;
//		try {
//			dataInput = new DataInputStream(new FileInputStream(path));
//		} catch (FileNotFoundException e) {
//			System.err.println("File " + path + " not found");
//			e.printStackTrace();
//		}
//		return new String(readBytes(4, dataInput));
//	}
//
//	private byte[] readBytes(int n, DataInputStream dataInput) {
//		byte[] b = new byte[n];
//
//		if (dataInput != null) {
//			for (int i = 0; i < n; i++) {
//				try {
//					b[i] = dataInput.readByte();
//				} catch (IOException e) {
//
//					e.printStackTrace();
//				}
//			}
//		}
//		return b;
//	}
	
	public void addReaderType(String type, String readerId){
		Collection<String> aux = typeToReader.get(type);
		if (aux != null){
			aux.add(readerId);
		}
		else{
			aux = new LinkedList<String>();
			aux.add(readerId);
			typeToReader.put(type, aux);
		}
	}
	
	public void addWriterType(String type, String writerId){
		Collection<String> aux = typeToWriter.get(type);
		if (aux != null){
			aux.add(writerId);
		}
		else{
			aux = new LinkedList<String>();
			aux.add(writerId);
			typeToWriter.put(type, aux);
		}
	}

}
