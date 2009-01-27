package tailmaster.commons.gui;

import javax.swing.*;
import static javax.swing.JComponent.*;
import static javax.swing.WindowConstants.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.*;

public class ClosableJDialog extends JDialog {
	private static KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE);
	private static Action cancelKeyAction = new AbstractAction() {
		public void actionPerformed(ActionEvent ae) {
			Component comp = (Component) ae.getSource();
			Window window = SwingUtilities.windowForComponent(comp);
			if (window instanceof Dialog) {
				window.dispose();
			} else if (comp instanceof JTextComponent && !(comp instanceof JFormattedTextField)) {
				JTextComponent tc = (JTextComponent) comp;
				int end = tc.getSelectionEnd();
				if (tc.getSelectionStart() != end) {
					tc.setCaretPosition(end);
				}
			}
		}
	};

	{
		// Add Escape key binding to the shared JTextComponent key bindings so that if any text component is inside Dialog then dispose that dialog
		Keymap map = JTextComponent.getKeymap(JTextComponent.DEFAULT_KEYMAP);
		map.addActionForKeyStroke(cancelKeyStroke,cancelKeyAction);
	}

	public ClosableJDialog(Frame rootPane, String title, boolean isModal) {
		super(rootPane, title, isModal);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		InputMap inputMap = getRootPane().getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getRootPane().getActionMap();
		if (inputMap != null && actionMap != null) {
			inputMap.put(cancelKeyStroke, "cancel");
			actionMap.put("cancel", cancelKeyAction);
		}
	}
}