package tailmaster.model;

/**
 * User: Halil KARAKOSE
 * Date: 10.Þub.2009
 * Time: 22:46:24
 */
public enum SecondTokenEnum {
    SEVEN('7'), EIGHT('8'), BRACKET('['), D('D'), UPPER_M('M'), UPPER_H('H'), LEFT_BRACKET('('), RIGHT_BRACKET(')'), LOWER_C('c');

    private char character;

    SecondTokenEnum(char c) {
        this.character = c;
    }

    public char getCharacter() {
        return character;
    }
}
