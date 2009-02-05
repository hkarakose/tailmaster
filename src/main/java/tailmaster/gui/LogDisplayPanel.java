package tailmaster.gui;

import tailmaster.TabRegistry;
import tailmaster.model.TabData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 12:17:21
 */
public class LogDisplayPanel extends JPanel {
    private JTextArea logTextArea;
    private JScrollPane scroller;
    private long panelId;

    public LogDisplayPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(1, 1, 1, 1));
        setPanelId(System.currentTimeMillis());
        TabRegistry.INSTANCE.addTab(getPanelId(), new TabData(panelId));

        addComponentListener(new PanelComponentListener());

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		logTextArea.setLineWrap(true);
		logTextArea.setWrapStyleWord(true);
		logTextArea.getCaret().setVisible(true);
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

    public long getPanelId() {
        return panelId;
    }

    public void setPanelId(long panelId) {
        this.panelId = panelId;
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

	private static class PanelComponentListener implements ComponentListener {
		@Override
            public void componentResized(ComponentEvent e) {
		}

		@Override
            public void componentMoved(ComponentEvent e) {
		}

		@Override
            public void componentShown(ComponentEvent e) {
			LogDisplayPanel panel = (LogDisplayPanel) e.getComponent();
			boolean playing = TabRegistry.INSTANCE.getTabData(panel.getPanelId()).isPlaying();
			TailMasterToolBar toolBar = (TailMasterToolBar) TailMasterFrame.getInstance().getToolBar();
			toolBar.togglePauseButtonText(playing);
		}

		@Override
            public void componentHidden(ComponentEvent e) {
		}
	}
}
