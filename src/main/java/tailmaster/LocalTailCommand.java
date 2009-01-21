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
public class LocalTailCommand implements Command {
	private LogFile logFile;
	private JTextArea logTextArea;

	public LocalTailCommand(LogFile logFile, JTextArea logTextArea) {
		this.logFile = logFile;
		this.logTextArea = logTextArea;
	}

	public void executeCommand() throws IOException {
		FileInputStream fileReader = new FileInputStream(logFile.getFileDestination());
		BufferedInputStream bufferedReader = new BufferedInputStream(fileReader);
		byte[] byteBuffer = new byte[255];
		int length = 0;
		while ((length = bufferedReader.available()) >= 0) {
			if (isDataAvailable(length)) continue;

			bufferedReader.read(byteBuffer);
			logTextArea.append(new String(byteBuffer, 0, length));
		}
	}

	private boolean isDataAvailable(int length) {
		if (length == 0) {
			sleep(1000);
			return true;
		}
		return false;
	}

	private void sleep(int sleepMillis) {
		try {
			Thread.sleep(sleepMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
