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
    private static HashMap<Long, SessionChannelClient> sessionChannelMap = new HashMap<Long, SessionChannelClient>();
    private static HashMap<Long, SshClient> sshClientMap = new HashMap<Long, SshClient>();

    public static void add(long connectionId, SessionChannelClient ssh) {
        sessionChannelMap.put(connectionId, ssh);
    }

    public static HashMap<Long, SessionChannelClient> getSessionChannelMap() {
        return sessionChannelMap;
    }

    public static void add(long connectionId, SshClient sshClient) {
        sshClientMap.put(connectionId, sshClient);
    }

    public static HashMap<Long, SshClient> getSshClientMap() {
        return sshClientMap;
    }

    public static void disconnect() {
        Set<Long> connectionIdSet = sessionChannelMap.keySet();
        for (Long connectionId : connectionIdSet) {
            SessionChannelClient sessionChannel = sessionChannelMap.get(connectionId);
            SshClient client = sshClientMap.get(connectionId);
            try {
                sessionChannel.close();
                client.disconnect();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}