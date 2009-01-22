package tailmaster;

import tailmaster.model.LogFile;

import javax.swing.*;
import java.io.*;
import java.nio.CharBuffer;
import java.nio.ByteOrder;

/**
 * User: Halil KARAKOSE
 * Date: Jan 21, 2009
 * Time: 2:59:05 PM
 */
public class LocalTailCommand extends TailCommand{
	private LogFile logFile;
	private JTextArea logTextArea;

	public LocalTailCommand(LogFile logFile, JTextArea logTextArea) {
		this.logFile = logFile;
		this.logTextArea = logTextArea;
	}

	public void executeCommand() throws IOException {
		FileInputStream fileReader = new FileInputStream(logFile.getFileDestination());
		BufferedInputStream bufferedReader = new BufferedInputStream(fileReader);
		appendToTextArea(bufferedReader, logTextArea);
	}
}
