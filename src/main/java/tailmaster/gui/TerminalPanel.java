package tailmaster.gui;

import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.connection.ChannelInputStream;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.*;

import tailmaster.sshterm.EscapeCharacterManager;

/**
 * User: Halil KARAKOSE
 * Date: Feb 6, 2009
 * Time: 12:33:36 AM
 */
public class TerminalPanel extends LogDisplayPanel {
	private ChannelOutputStream outputStream;
	private ChannelInputStream inputStream;
    private EscapeCharacterManager escapeCharacterManager;

	public TerminalPanel(SessionChannelClient channel) {
		super();
        this.escapeCharacterManager = new EscapeCharacterManager();
		this.outputStream = channel.getOutputStream();

		this.inputStream = channel.getInputStream();

		final JTextArea textArea = getLogTextArea();
		getLogTextArea().addKeyListener(new TerminalKeyListener());

		new Thread() {
			public void run() {
				char charCode;
				try {
					while (true) {
						if ((charCode = (char) inputStream.read()) != -1) {
                            System.out.println((short) charCode + "-" + charCode);
                            if (escapeCharacterManager.canWrite(charCode)) {
                                textArea.append(String.valueOf(charCode));
                                textArea.setCaretPosition(textArea.getText().length());
                            }
						} else {
							Thread.sleep(2000);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}.start();

		textArea.requestFocus();
	}
	
	private class TerminalKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
			char keyChar = e.getKeyChar();
			try {
				outputStream.write(keyChar);
				outputStream.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		public void keyPressed(KeyEvent e) {
			//TODO check for UP DOWN
		}

		public void keyReleased(KeyEvent e) {
		}
	}


}
