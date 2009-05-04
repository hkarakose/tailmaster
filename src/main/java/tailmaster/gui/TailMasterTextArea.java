package tailmaster.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: Halil KARAKOSE
 * Date: 03.May.2009
 * Time: 12:19:27
 */
public class TailMasterTextArea extends JTextArea {
	{
		setEditable(false);
		setFont(new Font("Courier New", Font.PLAIN, 12));
		setLineWrap(true);
		setWrapStyleWord(true);
		getCaret().setVisible(true);
		addMouseListener(new TextAreaMouseMotionListener());
	}

	private static class TextAreaMouseMotionListener extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			JTextArea textArea = (JTextArea) e.getComponent();
			textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		}
	}
}
