package tailmaster;

import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.SshClient;

import java.util.*;
import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 22, 2009
 * Time: 9:06:07 PM
 */
public class SessionRegistry {
	private static HashMap<Long, SessionChannelClient> sshChannelMap = new HashMap<Long, SessionChannelClient>();

	private static HashMap<Long, SshClient> connectionMap = new HashMap<Long, SshClient>();

	public static long put(SshClient client, SessionChannelClient channelClient) {
		long now = System.currentTimeMillis();
		put(now, client);
		put(now, channelClient);
		return now;
	}

	private static void put(long connectionId, SessionChannelClient channelClient) {
        sshChannelMap.put(connectionId, channelClient);
    }

	private static HashMap<Long, SessionChannelClient> getSshChannelMap() {
        return sshChannelMap;
    }

	public static void put(long connectionId, SshClient client) {
        connectionMap.put(connectionId, client);
    }

	public static HashMap<Long, SshClient> getConnectionMap() {
        return connectionMap;
    }

	public static void disconnect(long connectionId) {
        SessionChannelClient sessionChannel = sshChannelMap.get(connectionId);
        SshClient connection = connectionMap.get(connectionId);
        try {
            sessionChannel.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}