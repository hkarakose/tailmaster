package tailmaster;

import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.SshClient;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;

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

	private static ArrayList<Connection> connections = new ArrayList<Connection>();
	private static ArrayList<Session> sessions = new ArrayList<Session>();

	public static void add(SessionChannelClient ssh) {
		sessionChannelClients.add(ssh);
	}

	public static ArrayList<SessionChannelClient> getSessionChannelClients() {
		return sessionChannelClients;
	}

	public static void add(SshClient sshClient) {
		sshClients.add(sshClient);
	}

	public static ArrayList<SshClient> getSessionClients() {
		return sshClients;
	}

	public static void add(Connection connection) {
		connections.add(connection);
	}

	public static ArrayList<Connection> getConnections() {
		return connections;
	}

	public static void add(Session session) {
		sessions.add(session);
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

		for (Session session : sessions) {
			session.close();
		}

		for (Connection connection : connections) {
			connection.close();
		}

	}
}
