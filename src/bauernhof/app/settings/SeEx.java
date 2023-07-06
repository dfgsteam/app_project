package bauernhof.app.settings;

public class SeEx extends Exception {
    private static final long serialVersionUID=1l;
    public SeEx() {
        super();
    }
    public SeEx(String message) {
        super(message);
    }
    public SeEx(String message, Throwable cause) {
        super(message, cause);
    }
    public SeEx(Throwable cause) {
        super(cause);
    }
}
