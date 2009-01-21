package tailmaster;

import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 15, 2009
 * Time: 9:02:38 AM
 */
public class TailExecutor extends Thread{
	private Command command;

	public TailExecutor(Command command) {
		this.command = command;
	}

	public void run() {
		try {
			command.executeCommand();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
