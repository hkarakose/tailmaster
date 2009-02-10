package tailmaster.model;

/**
 * User: Halil KARAKOSE
 * Date: 10.Þub.2009
 * Time: 22:42:57
 */
public enum FirstTokenEnum {
    ESC((char)27);

    private char character;

    FirstTokenEnum(char c) {
        this.character = c;
    }

    public char getCharacter() {
        return character;
    }
}
