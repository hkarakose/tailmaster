package tailmaster.commons.gui;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

public class BlockCaret extends DefaultCaret {

    @Override
    protected synchronized void damage(Rectangle r) {
        if (r == null)
            return;

        x = r.x;
        y = r.y;
        height = r.height;
        // A value for width was probably set by paint(), which we leave alone.
        // But the first call to damage() precedes the first call to paint(), so
        // in this case we must be prepared to set a valid width, or else
        // paint()
        // will receive a bogus clip area and caret will not get drawn properly.
        if (width <= 0)
            width = getComponent().getWidth();

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        JTextComponent comp = getComponent();
        if (comp == null)
            return;

        int dot = getDot();
        Rectangle rectangle = null;
        try {
            rectangle = comp.modelToView(dot);
            if (rectangle == null)
                return;
        } catch (BadLocationException e) {
            return;
        }

        if ((x != rectangle.x) || (y != rectangle.y)) {
            // paint() has been called directly, without a previous call to
            // damage(), so do some cleanup. (This happens, for example, when
            // the text component is resized.)
            repaint(); // erase previous location of caret
            x = rectangle.x; // Update dimensions (width gets set later in this method)
            y = rectangle.y;
        }

        g.setColor(comp.getCaretColor());
        g.setXORMode(comp.getBackground());

        width = g.getFontMetrics().getMaxAdvance();
        
        g.fillRect(rectangle.x, rectangle.y, width, height);
    }
}