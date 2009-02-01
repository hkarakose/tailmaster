package tailmaster.command;

import tailmaster.model.LogFile;
import tailmaster.command.TailCommand;

import javax.swing.*;
import java.io.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 21, 2009
 * Time: 2:59:05 PM
 */
public class LocalTailCommand extends TailCommand {
	private LogFile logFile;
	private JEditorPane editorPane;

	public LocalTailCommand(LogFile logFile, JEditorPane editorPane) {
		this.logFile = logFile;
		this.editorPane = editorPane;
	}

	public void executeCommand() throws IOException {
		FileInputStream fileReader = new FileInputStream(logFile.getFileDestination());
		BufferedInputStream bufferedReader = new BufferedInputStream(fileReader);
		appendToTextArea(bufferedReader, editorPane);
	}
}
