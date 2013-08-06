/**
 * 
 */
package org.adaikiss.xun.test;

import java.io.InputStream;
import java.io.Reader;

import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.DataSetException;
import org.xml.sax.InputSource;

/**
 * @author hlw
 * 
 */
public class CustomXmlDataSet extends CachedDataSet {

	/**
	 * Creates an XmlDataSet with the specified xml reader.
	 */
	public CustomXmlDataSet(Reader reader) throws DataSetException {
		super(new CustomXmlProducer(new InputSource(reader)));
	}

	/**
	 * Creates an XmlDataSet with the specified xml input stream.
	 */
	public CustomXmlDataSet(InputStream in) throws DataSetException {
		super(new CustomXmlProducer(new InputSource(in)));
	}

}
