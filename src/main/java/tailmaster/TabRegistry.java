package tailmaster;

import tailmaster.model.TabData;

import java.util.HashMap;

/**
 * User: Halil KARAKOSE
 * Date: 28.Oca.2009
 * Time: 20:08:30
 */
public enum TabRegistry {
    INSTANCE;

    HashMap<Long, TabData> tabMap;

    TabRegistry() {
        this.tabMap = new HashMap<Long, TabData>();
    }

    public void addTab(Long key, TabData tabData) {
        tabMap.put(key, tabData);
    }

    public void removeTab(long tabId) {
        tabMap.remove(tabId);
    }

    public TabData getTabData(Long key) {
        return tabMap.get(key);
    }
}
