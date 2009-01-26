package tailmaster.command;

import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 21, 2009
 * Time: 2:59:39 PM
 */
public interface Command {
	public void executeCommand() throws IOException;
}
