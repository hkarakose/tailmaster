package tailmaster;

import tailmaster.model.Server;
import tailmaster.model.LogFile;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.connection.ChannelInputStream;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;

import javax.swing.*;
import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 11:28:51
 */
public class TailCommand {
    private Server server;
    private LogFile logFile;
	private JTextArea textArea;

	public TailCommand(Server server, LogFile logFile, JTextArea textArea) {
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

    public void executeTail() throws IOException {
        SshClient sshClient = new SshClient();
        sshClient.connect(server.getHostname());

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

        SessionChannelClient ssh = sshClient.openSessionChannel();
        ssh.startShell();
        ChannelOutputStream out = ssh.getOutputStream();
        String cmd = "tail -f " + logFile.getFileDestination() + "\n";
        out.write(cmd.getBytes());

        ChannelInputStream in = ssh.getInputStream();
        byte buffer[] = new byte[255];
        int read;
        while (true) {
            read = in.read(buffer);
            if (read > 0) {
                String buff = new String(buffer, 0, read);
                textArea.append(buff);
            }
        }
    }
}
