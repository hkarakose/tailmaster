package tailmaster.sshterm;

import com.sshtools.common.ui.SshToolsApplicationException;

/**
 * User: Halil KARAKOSE
 * Date: 02.Þub.2009
 * Time: 22:31:30
 */
public class Application {
    public static void main(String[] args) throws SshToolsApplicationException {
        SshTerm term = new SshTerm(SshTermClientPanel.class, SshTermFrame.class);
        term.init(args);
        term.newContainer();
    }
}
