package org.adaikiss.xun.struts2.fileupload;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.commons.fileupload.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to monitor the upload of a file from web page to the
 * server
 * 
 * @author dave
 */
@SuppressWarnings("serial")
public class ProgressMonitor implements ProgressListener, Serializable {
	public static final String SESSION_PROGRESS_MONITOR = "com.topjet.fmp.mvc.struts2.fileupload.ProgressMonitor";

	private static final Logger logger = LoggerFactory.getLogger(ProgressMonitor.class);
	private static final NumberFormat fmt = NumberFormat.getInstance();

	// Previous Values
	private long previousBytesRead;
	private int bytesReadNotChangedCount;

	// Current Values
	private long bytesRead;
	private long bytesLength;
	private boolean aborted = false;

	private String fileName = "na";

	static {
		fmt.setMaximumFractionDigits(0);
	}

	/** Creates a new instance of ProgressMonitor */
	public ProgressMonitor() {
		if (logger.isDebugEnabled())
			logger.debug("Creating Progress Monitor");
	}

	/**
	 * This is called by the notifier
	 */
	public void update(long pBytesRead, long pContentLength, int pItems) {
		bytesRead = pBytesRead;
		bytesLength = pContentLength;
		// abort if total size is 0 or readed equals total
		if (bytesLength == 0 || pBytesRead == pContentLength) {
			abort();
		}
	}

	/**
	 * This looks at the previousBytesRead value which only will update when the
	 * file is being uploaded and it compares it against the current bytes read
	 * value passed in as a param. If they are the same more the 3 times, we
	 * assume the connection is broken and abort the upload.
	 * 
	 * This is another safety measure to reset the upload form
	 * 
	 * @param currentBytesRead
	 * @return true if the file is still uploading, or false if it has failed
	 */
	public boolean isStillProcessing() {
		if (logger.isDebugEnabled())
			logger.debug("BytesnotReadChangedCount is: " + bytesReadNotChangedCount);
		if (bytesReadNotChangedCount > 3) {
			// Abort processing
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This returns the percentage complete
	 */
	public String percentComplete() {
		double percent = 0;
		if (bytesLength != 0) {
			percent = ((double) bytesRead / (double) bytesLength);
		}
		percent *= 100;
		return fmt.format(percent);
	}

	public long getBytesRead() {
		if (previousBytesRead == bytesRead) {
			bytesReadNotChangedCount++;
		} else {
			bytesReadNotChangedCount = 0;
			previousBytesRead = bytesRead;
		}
		return bytesRead;
	}

	public long getBytesLength() {
		return bytesLength;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void abort() {
		this.aborted = true;
	}

	public boolean isAborted() {
		return this.aborted;
	}

	public void setPreviousBytesRead(long previousBytesRead) {
		this.previousBytesRead = previousBytesRead;
	}

}
