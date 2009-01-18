package tailmaster;

import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 15, 2009
 * Time: 9:02:38 AM
 */
public class TailExecutor extends Thread{
	private TailCommand command;

	public TailExecutor(TailCommand command) {
		this.command = command;
	}

	public void run() {
		try {
			command.executeTail();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
