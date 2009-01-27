package tailmaster.gui.action;

import tailmaster.gui.configuration.LogFileConfigurationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Halil KARAKOSE
 * Date: Jan 26, 2009
 * Time: 11:34:12 PM
 */
public class CloseDialogAction extends AbstractAction{
	private JDialog dialog;

	public CloseDialogAction(JDialog jDialog) {
		this.dialog = jDialog;
	}

	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}
}
