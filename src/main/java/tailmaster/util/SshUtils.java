package tailmaster.util;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.PseudoTerminal;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;
import tailmaster.model.Server;
import tailmaster.sshterm.PseudoTerminalImpl;

import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: Feb 5, 2009
 * Time: 4:02:34 PM
 */
public class SshUtils {
    public static SshClient connect(Server server) {
        SshClient sshClient = new SshClient();
        //TODO catch "java.net.ConnectException: Connection timed out: connect"
        //TODO catch "java.net.SocketException: Network is unreachable: connect"
        try {
            sshClient.connect(server.getHostname(), new IgnoreHostKeyVerification());
            PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
            auth.setUsername(server.getUsername());
            auth.setPassword(server.getPassword());
            int result = sshClient.authenticate(auth);
            switch (result) {
                case AuthenticationProtocolState.FAILED:
                    System.out.println("The authentication failed");
                    break;
                case AuthenticationProtocolState.PARTIAL:
                    System.out.println("The authentication succeeded but another authentication is required");
                    break;
                case AuthenticationProtocolState.COMPLETE:
                    System.out.println("The authentication is complete");
                    break;
            }

            return sshClient;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SessionChannelClient openSshChannel(SshClient ssh) {
        try {
            return ssh.openSessionChannel();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static boolean requestPseudoTerminal(SessionChannelClient channelClient) {
        try {
            PseudoTerminal pseudoTerminal = new PseudoTerminalImpl();
            return channelClient.requestPseudoTerminal(pseudoTerminal);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    public static boolean startShell(SessionChannelClient channelClient) {
        try {
            return channelClient.startShell();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
