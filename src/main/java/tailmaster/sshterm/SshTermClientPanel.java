package tailmaster.sshterm;

import com.sshtools.common.configuration.SshToolsConnectionProfile;
import com.sshtools.common.ui.SshToolsApplication;
import com.sshtools.common.ui.SshToolsApplicationClientPanel;
import com.sshtools.common.ui.SshToolsApplicationException;
import com.sshtools.common.ui.SshToolsConnectionTab;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.SshException;
import com.sshtools.j2ssh.SshThread;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.connection.ChannelState;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;
import com.sshtools.j2ssh.transport.TransportProtocolCommon;
import com.sshtools.sshterm.emulation.TerminalEmulation;
import com.sshtools.sshterm.emulation.TerminalListener;
import com.sshtools.sshterm.emulation.TerminalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * User: Halil KARAKOSE
 * Date: 02.Þub.2009
 * Time: 22:30:14
 */
public class SshTermClientPanel extends SshToolsApplicationClientPanel {

	protected TerminalEmulation emulation;

	private TerminalPanel terminal;

	private SessionChannelClient session;

	private final static long VDU_EVENTS = AWTEvent.KEY_EVENT_MASK
			| AWTEvent.FOCUS_EVENT_MASK | AWTEvent.ACTION_EVENT_MASK
			| AWTEvent.WINDOW_EVENT_MASK /*| AWTEvent.WINDOW_FOCUS_EVENT_MASK*/
			| AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK;

	public void init(SshToolsApplication application) throws SshToolsApplicationException {
		super.init(application);

		enableEvents(VDU_EVENTS);

		try {
			emulation = createEmulation();
		}
		catch (IOException ioe) {
			throw new SshToolsApplicationException(ioe);
		}

		emulation.addTerminalListener(new TerminalListener() {
			@Override
			public void screenResized(int w, int h) {
/*        try {
            if (session != null) {
                session.changeTerminalDimensions(emulation);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
			}
		});

		// Set a scrollbar for the terminal - doesn't seem to be as simple as this
		//scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		emulation.setBufferSize(1000);

		// Create our swing terminal and add it to the main frame
		terminal = new TerminalPanel(emulation) {
			public void processEvent(AWTEvent evt) {
				super.processEvent(evt);
				/** We can't add a MouseWheelListener because it was not available in 1.3, so direct processing of events is necessary */
/*
                if (evt instanceof MouseEvent && evt.getID() == 507) {
                    try {
                        Method m = evt.getClass().getMethod("getWheelRotation", new Class[]{});
                        SshTerminalPanel.this.scrollBar.setValue(SshTerminalPanel.this.
                                scrollBar.getValue() +
                                (SshTerminalPanel.this.scrollBar.getUnitIncrement() *
                                        ((Integer) m.invoke(evt, new Object[]{})).intValue() *
                                        PreferencesStore.getInt(PREF_MOUSE_WHEEL_INCREMENT, 1)));

                    }
                    catch (Throwable t) {
                        //	In theory, this should never happen
                    }
                } else {
                    super.processEvent(evt);
                }
*/
			}
		};
		terminal.requestFocus();
		//terminal.setScrollbar(scrollBar);
		//terminal.addMouseMotionListener(this);

		//terminal.addMouseWheelListener(this);
		// Center panel with terminal and scrollbar
		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(Color.red);
		center.add(terminal, BorderLayout.CENTER);
		//center.add(scrollBar, BorderLayout.EAST);

		// Show the context menu on mouse button 3 (right click)
		terminal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if ((evt.getModifiers() & MouseEvent.BUTTON3_MASK) > 0) {
					getContextMenu().setLabel((getCurrentConnectionFile() == null)
							? getApplication().getApplicationName()
							: getCurrentConnectionFile().getName());
					getContextMenu().show(terminal, evt.getX(), evt.getY());
				}
			}
		});

		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);

		terminal.requestFocus();
		connect();
	}

	private TerminalEmulation createEmulation() throws IOException {
		return new TerminalEmulation(TerminalEmulation.VT320);
	}

	@Override
	public SshToolsConnectionTab[] getAdditionalConnectionTabs() {
		return new SshToolsConnectionTab[0];
	}

	@Override
	public boolean postConnection() {
		return false;
	}

	@Override
	public void authenticationComplete(boolean b) throws SshException, IOException {
//        com.sshtools.j2ssh.io.ByteArrayWriter bw = new com.sshtools.j2ssh.io.ByteArrayWriter();
//        bw.writeString("127.0.0.1");
//        bw.writeInt(8085);
//        ssh.sendGlobalRequest("tcpip-forward", true, bw.toByteArray());

		// Make sure the terminal has focus
		terminal.requestFocus();
//        ssh.addEventHandler(new SshEventAdapter() {
//
//            public void onDisconnect(TransportProtocol transport) {
//                closeConnection(false);
//            }
//        });


		emulation.setEOL(TerminalEmulation.EOL_DEFAULT);

		// Start the users shell
		session = ssh.openSessionChannel();

		if (session.startShell()) {
			session.bindInputStream(emulation.getTerminalInputStream());
			session.bindOutputStream(emulation.getTerminalOutputStream());
		}
	}

	@Override
	public boolean canClose() {
		return false;
	}

	@Override
	public void close() {
	}

	@Override
	public void setAvailableActions() {
	}

	public void connect() {
		// We need to connect
		ssh = new SshClient();
		final PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
		auth.setUsername("root");
		auth.setPassword("blekinge");

		// We'll do the threading rather than j2ssh as we want to get errors
		Runnable r = new Runnable() {
			public void run() {
				try {
					ssh.connect("159.107.240.188", new IgnoreHostKeyVerification());
					int result = ssh.authenticate(auth);
					switch (result) {
						case AuthenticationProtocolState.FAILED:
							System.out.println("The authentication failed");
							break;
						case AuthenticationProtocolState.PARTIAL:
							System.out.println("The authentication succeeded but another authentication is required");
							break;
						case AuthenticationProtocolState.COMPLETE:
							System.out.println("The authentication is complete");
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							authenticationComplete(true);
							break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		Thread thread = new SshThread(r, application.getApplicationName() + " connection", true);
		thread.start();
	}

	public void closeConnection(boolean doDisconnect) {
		if (isConnected()) {
			super.closeConnection(doDisconnect);
		}

		if (doDisconnect) {
			// We should disconnect the session
			try {
				if (session != null) {
					session.close();
				}

				if ((ssh != null) && ssh.isConnected()) {
					ssh.disconnect();
				}
			}
			catch (Exception se) {
				se.printStackTrace();
				showExceptionMessage("Disconnect",
						"An unexpected error occured!\n\n" + se.getMessage());
			}
		}

		//  Stop recording
//        if (recordingOutputStream != null) {
//            stopRecording();
//        }

		// Reset the terminal emulation
		emulation.reset();

		// Null the session and current properties
		ssh = null;
		session = null;

//        SessionProviderFrame browserFrame;
//        for (Iterator it = sessionFrames.iterator();
//             it.hasNext();) {
//            try {
//                browserFrame = (SessionProviderFrame) it.next();
//                browserFrame.exit();
//                browserFrame.dispose();
//            }
//            catch (Throwable ex) {
//            }
//        }
//
//        sessionFrames.clear();

		setCurrentConnectionProfile(null);
		setNeedSave(false);
		setCurrentConnectionFile(null);
		setContainerTitle(getCurrentConnectionFile());
	}
}