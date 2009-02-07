package tailmaster.sshterm;

import com.sshtools.j2ssh.session.PseudoTerminal;

/**
 * User: Halil KARAKOSE
 * Date: Feb 5, 2009
 * Time: 6:59:07 PM
 */
public class PseudoTerminalImpl implements PseudoTerminal {
	private int columns;
	private int rows;
	private int width;
	private int height;

	public PseudoTerminalImpl(int columns, int rows, int width, int height) {
		this.columns = columns;
		this.rows = rows;
		this.width = width;
		this.height = height;
	}

	public PseudoTerminalImpl() {
		this(80, 24, 80, 24);
	}

	public int getColumns() {
		return columns;
	}

	public String getEncodedTerminalModes() {
		return null;
	}

	public int getHeight() {
		return height;
	}

	public int getRows() {
		return rows;
	}

	public String getTerm() {
		return "vt100";
	}

	public int getWidth() {
		return width;
	}
}
