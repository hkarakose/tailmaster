package tailmaster.model;

/**
 * User: Halil KARAKOSE
 * Date: 28.Oca.2009
 * Time: 20:09:17
 */
public class TabData {
    long tabId;
    boolean playing;

    public TabData(long tabId) {
        this.tabId = tabId;
        playing = true;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
