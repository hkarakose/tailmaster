package tailmaster.model;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 11:30:38
 */
public class Server {
    private int id;
    private String serverAlias;
    private String hostname;
    private String username;
    private String password;

    public Server(int id, String serverAlias, String hostname, String username, String password) {
        this(serverAlias, hostname, username, password);
        this.id = id;
    }

    public Server(String serverAlias, String hostname, String username, String password) {
        this.serverAlias = serverAlias;
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getServerAlias() {
        return serverAlias;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Server)) {
            return false;
        }
        Server serverObj = (Server) obj;
        if ( !(getServerAlias().equals(serverObj.getServerAlias())) ) return false;
        if ( !(getHostname().equals(serverObj.getHostname())) ) return false;
        if ( !(getUsername().equals(serverObj.getUsername())) ) return false;

        return getPassword().equals(serverObj.getPassword());
    }
}
