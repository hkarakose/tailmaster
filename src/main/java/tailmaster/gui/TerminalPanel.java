package tailmaster.gui;

import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.connection.ChannelInputStream;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.*;

import tailmaster.sshterm.EscapeCharacterManager;
import tailmaster.commons.gui.BlockCaret;

/**
 * User: Halil KARAKOSE
 * Date: Feb 6, 2009
 * Time: 12:33:36 AM
 */
public class TerminalPanel extends TailMasterPanel {
	private ChannelOutputStream outputStream;
	private ChannelInputStream inputStream;
    private EscapeCharacterManager escapeCharacterManager;

	public TerminalPanel(SessionChannelClient channel) {
        this.escapeCharacterManager = new EscapeCharacterManager();
		this.outputStream = channel.getOutputStream();

		this.inputStream = channel.getInputStream();

		textArea = new TailMasterTextArea();
		textArea.addKeyListener(new TerminalKeyListener());
		textArea.setCaret(new BlockCaret());
		scroller.setViewportView(textArea);
		new Thread() {
			public void run() {
				char charCode;
				try {
                    //TODO convert to timer task
					while (true) {
						if ((charCode = (char) inputStream.read()) != -1) {
                            if (exitTerminal(charCode)) {
                                //TODO disconnect
                                System.out.println("terminate session");
                                break;
                            }

                            processInput(charCode, textArea);
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

    private boolean exitTerminal(char charCode) {
        return (charCode == Character.MAX_VALUE);
    }

    private void processInput(char charCode, TailMasterTextArea terminalTextArea) {
        if (escapeCharacterManager.canWrite(charCode)) {
            terminalTextArea.append(String.valueOf(charCode));
            terminalTextArea.setCaretPosition(terminalTextArea.getText().length());
        }
    }

    private class TerminalKeyListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			char keyChar = e.getKeyChar();
			try {
				outputStream.write(keyChar);
				outputStream.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
