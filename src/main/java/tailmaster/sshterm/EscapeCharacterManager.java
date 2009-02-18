package tailmaster.sshterm;

/**
 * User: Halil KARAKOSE
 * Date: 10.Þub.2009
 * Time: 22:26:38
 */
public class EscapeCharacterManager {
    public static final char FIRST_TOKEN = 27;
    public static final char BRACKET = '[';
    public static final String SECOND_TOKEN = "78[DMH()c";
    public static final String TERMINATING_TOKENS = "mpiKJHgrhlnRABCDfsu";

    private StringBuilder buffer;
    private boolean escapeSequnce;
    private boolean bracketReceived;

    public EscapeCharacterManager() {
        this.buffer = new StringBuilder();
    }

    public boolean canWrite(char charCode) {
        if (charCode == FIRST_TOKEN) {
            escapeSequnce = true;
            buffer.append(charCode);
            return false;
        }

        if (charCode == BRACKET) {
            bracketReceived = true;
            buffer.append(charCode);
            return false;
        }

        if (escapeSequnce) {
            if (bracketReceived) {
                if (TERMINATING_TOKENS.contains(String.valueOf(charCode))) {
                    reset();
                    return false;
                }
            } else {
                if (SECOND_TOKEN.contains(String.valueOf(charCode))) {
                    reset();
                    return false;
                }
            }

            return false;
        }

        return true;
    }

    private void reset() {
        buffer.setLength(0);
        escapeSequnce = false;
        bracketReceived = false;
    }
}
