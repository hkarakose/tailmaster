package tailmaster.command;

import tailmaster.model.Server;
import tailmaster.model.LogFile;
import tailmaster.command.TailCommand;
import tailmaster.SessionRegistry;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.connection.ChannelInputStream;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;

import javax.swing.*;
import java.io.IOException;
import java.io.BufferedInputStream;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 11:28:51
 */
public class RemoteTailCommand extends TailCommand {
	private Server server;
	private LogFile logFile;
	private JEditorPane editorPane;
	private SshClient sshClient;
    private long connectionId;

	public RemoteTailCommand(long connectionId, Server server, LogFile logFile, JEditorPane editorPane) {
        this.connectionId = connectionId;
		this.server = server;
		this.logFile = logFile;
		this.editorPane = editorPane;
	}

	public Server getServer() {
		return server;
	}

	public LogFile getLogFile() {
		return logFile;
	}

	public void executeCommand() throws IOException {
		sshClient = new SshClient();
		//TODO catch "java.net.ConnectException: Connection timed out: connect"
		//TODO catch "java.net.SocketException: Network is unreachable: connect"
		sshClient.connect(server.getHostname(), new IgnoreHostKeyVerification());

		PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
		auth.setUsername(server.getUsername());
		auth.setPassword(server.getPassword());
		int result = sshClient.authenticate(auth);

		switch (result) {
			case AuthenticationProtocolState.FAILED:
				System.out.println("The authentication failed");
				return;
			case AuthenticationProtocolState.PARTIAL:
				System.out.println("The authentication succeeded but another authentication is required");
				return;
			case AuthenticationProtocolState.COMPLETE:
				System.out.println("The authentication is complete");
				break;
		}

		SessionChannelClient sshChannel = sshClient.openSessionChannel();
//		ChannelEventListener listener = (ChannelEventListener) new SessionOutputReader(ssh);
//		ssh.addEventListener(listener);
		sshChannel.requestPseudoTerminal("vt100", 80, 24, 0, 0, ""); //VT100, VT220, VT320 and ANSI Emulations
		//ssh.startShell();
        SessionRegistry.put(connectionId, sshChannel);
		SessionRegistry.put(connectionId, sshClient);
		ChannelOutputStream out = sshChannel.getOutputStream();
//		String cmd = "tail -f " + logFile.getFileDestination();
//		out.write(cmd.getBytes());

		ChannelInputStream inputStream = sshChannel.getInputStream();

		ChannelOutputStream channelOutputStream = sshChannel.getOutputStream();

		BufferedInputStream bufferedStream = new BufferedInputStream(inputStream);
		sshChannel.executeCommand("tail -123f " + logFile.getFileDestination());
		appendToTextArea(bufferedStream, editorPane);
	}
}
