package tailmaster.command;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.connection.ChannelInputStream;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.session.SessionChannelClient;
import tailmaster.SessionRegistry;
import tailmaster.model.LogFile;
import tailmaster.model.Server;
import tailmaster.util.SshUtils;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 11:28:51
 */
public class RemoteTailCommand extends TailCommand {
	private Server server;
	private LogFile logFile;
	private JTextArea textArea;
	private SshClient sshClient;

    public RemoteTailCommand(Server server, LogFile logFile, JTextArea textArea) {
		this.server = server;
		this.logFile = logFile;
		this.textArea = textArea;
	}

	public Server getServer() {
		return server;
	}

	public LogFile getLogFile() {
		return logFile;
	}

	public void executeCommand() throws IOException {
		sshClient = SshUtils.connect(server);

		SessionChannelClient sshChannel = sshClient.openSessionChannel();
//		ChannelEventListener listener = (ChannelEventListener) new SessionOutputReader(ssh);
//		ssh.addEventListener(listener);
		sshChannel.requestPseudoTerminal("vt100", 80, 24, 0, 0, ""); //VT100, VT220, VT320 and ANSI Emulations
		//ssh.startShell();
		SessionRegistry.put(connectionId, sshClient, sshChannel);
		ChannelOutputStream out = sshChannel.getOutputStream();
//		String cmd = "tail -f " + logFile.getFileDestination();
//		out.write(cmd.getBytes());

		ChannelInputStream inputStream = sshChannel.getInputStream();
		BufferedInputStream bufferedStream = new BufferedInputStream(inputStream);
		sshChannel.executeCommand("tail -123f " + logFile.getFileDestination());
		appendToTextArea(bufferedStream, textArea);
	}
}
