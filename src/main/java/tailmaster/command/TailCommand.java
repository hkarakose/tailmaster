package tailmaster.command;

import tailmaster.command.Command;
import tailmaster.gui.LogDisplayPanel;
import tailmaster.TabRegistry;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.awt.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 22, 2009
 * Time: 8:25:57 AM
 */
public abstract class TailCommand implements Command {
    protected void appendToTextArea(BufferedInputStream bufferedReader, JTextArea logTextArea) throws IOException {
        int length;
        byte[] byteBuffer = new byte[2048];
        while ((length = bufferedReader.available()) >= 0) {
            if (isDataAvailable(length) && isPlaying(logTextArea)) {
                length = length > byteBuffer.length ? byteBuffer.length : length;
                bufferedReader.read(byteBuffer, 0, length);
                logTextArea.append(new String(byteBuffer, 0, length));
                logTextArea.setCaretPosition(logTextArea.getText().length());
            } else {
                sleep(5000);
            }
        }
    }

    private boolean isPlaying(JTextArea textArea) {
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
