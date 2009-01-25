package tailmaster;

import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.SshClient;

import java.util.ArrayList;
import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 22, 2009
 * Time: 9:06:07 PM
 */
public class SessionRegistry {
	private static ArrayList<SessionChannelClient> sessionChannelClients = new ArrayList<SessionChannelClient>();
	private static ArrayList<SshClient> sshClients = new ArrayList<SshClient>();

	public static void add(SessionChannelClient ssh) {
		sessionChannelClients.add(ssh);
	}

	public static ArrayList<SessionChannelClient> getSessionChannelClients() {
		return sessionChannelClients;
	}

	public static void add(SshClient sshClient) {
		sshClients.add(sshClient);
	}

	public static ArrayList<SshClient> getSshClients() {
		return sshClients;
	}

	public static void disconnect() {
		for (SessionChannelClient sessionChannelClient : sessionChannelClients) {
			try {
				sessionChannelClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		for (SshClient sshClient : sshClients) {
			sshClient.disconnect();
		}
	}
}