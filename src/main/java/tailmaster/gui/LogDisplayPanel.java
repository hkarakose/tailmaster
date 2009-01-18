package tailmaster.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 12:17:21
 */
public class LogDisplayPanel extends JPanel {
    private JTextArea logTextArea;
	private JScrollPane scroller;

    public LogDisplayPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(1,1,1,1));

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        logTextArea.addMouseListener(new LogTextAreaMouseMotionListener());

        scroller = new JScrollPane(logTextArea);
        scroller.setAutoscrolls(true);
        add(scroller);
    }

    public JTextArea getLogTextArea() {
        return logTextArea;
    }

    public JScrollPane getScroller() {
        return scroller;
    }

    private static class LogTextAreaMouseMotionListener implements MouseListener {

        public void mouseMoved(MouseEvent e) {
            //http://java.sun.com/j2se/1.4.2/docs/api/java/awt/Cursor.html
            //http://java.sun.com/docs/books/tutorial/uiswing/events/mouselistener.html
            //http://java.sun.com/javase/6/docs/api/java/awt/event/MouseListener.html#mouseEntered(java.awt.event.MouseEvent)
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            JTextArea textArea = (JTextArea) e.getComponent();
            textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}
