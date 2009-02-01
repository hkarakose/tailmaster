package tailmaster.command;

import tailmaster.command.Command;
import tailmaster.gui.LogDisplayPanel;
import tailmaster.TabRegistry;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.awt.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 22, 2009
 * Time: 8:25:57 AM
 */
public abstract class TailCommand implements Command {
    protected void appendToTextArea(BufferedInputStream bufferedReader, JEditorPane logTextArea) throws IOException {
		long idle = 0;
        int length;
        byte[] byteBuffer = new byte[2048];
        while ((length = bufferedReader.available()) >= 0) {
            if (isDataAvailable(length) && isPlaying(logTextArea)) {
				idle = 0;
				length = length > byteBuffer.length ? byteBuffer.length : length;
                bufferedReader.read(byteBuffer, 0, length);
				try {
					int offset = logTextArea.getDocument().getLength();
					String str = new String(byteBuffer, 0, length);
					logTextArea.getDocument().insertString(offset, str, null);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
            } else {
                sleep(2500);
				idle+=2500;
            }
        }
    }

    private boolean isPlaying(JEditorPane textArea) {
        Container parent = textArea.getParent().getParent().getParent();
        if (parent instanceof LogDisplayPanel) {
            LogDisplayPanel panel = (LogDisplayPanel) parent;
            return TabRegistry.INSTANCE.getTabData(panel.getPanelId()).isPlaying();
        } else {
            throw new RuntimeException("Illegal parent: " + parent);
        }
    }

    private boolean isDataAvailable(int length) {
        return length > 0;
    }

    private void sleep(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
