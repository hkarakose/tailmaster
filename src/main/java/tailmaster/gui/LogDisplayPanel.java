package tailmaster.gui;

import tailmaster.TabRegistry;
import tailmaster.model.TabData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 12:17:21
 */
public class LogDisplayPanel extends TailMasterPanel {
	//TODO tailmasterpanel yaz ve bu class'i tailmasterpanel'den extend et.

    public LogDisplayPanel() {
		textArea = new TailMasterTextArea();
		scroller.setViewportView(textArea);
    }
}
