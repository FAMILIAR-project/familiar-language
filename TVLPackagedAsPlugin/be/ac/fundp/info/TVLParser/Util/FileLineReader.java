package be.ac.fundp.info.TVLParser.Util;

import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*public class FileLineReaderException extends Exception {
		
	private static final long serialVersionUID = 0L;

	public FileLineReaderException() {
		super();
	}

	public FileLineReaderException(String message) {
		super(message);
	}

	public FileLineReaderException(Exception source) {
		super(source);
	}

}*/

public final class FileLineReader implements Iterable<String> 
{
	private BufferedReader _reader;
	private final static int BUFFER_SIZE = 64;
 
    public FileLineReader(File file) throws Exception
    {
    	this._reader = new BufferedReader(new FileReader(file), FileLineReader.BUFFER_SIZE);
    }
 
    public void Close() throws Exception
    {
		try
		{
		    _reader.close();
		}
		catch (Exception ex) 
		{
			//throw new Exception("FileLineReader: Can't close file!");
		}
	}
 
    public Iterator<String> iterator()
    {
    	return new FileIterator();
    }
 
    private class FileIterator implements Iterator<String>
    {
    	private String _currentLine;
 
		public boolean hasNext()
		{
		    try
		    {
		    	_currentLine = _reader.readLine();
		    }
		    catch (Exception ex)
		    {
		    	_currentLine = null;
		    	ex.printStackTrace();
		    }
	 
		    return _currentLine != null;
		}
	 
		public String next()
		{
		    return _currentLine;
		}
	 
		public void remove()
		{
		}
    }
}
