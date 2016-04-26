package mysite.exception;

@SuppressWarnings("serial")
public class GuestBookGetListException extends RuntimeException {
    public GuestBookGetListException() {
        super("Exception occusrs:  gettings guest list");
    }
    public GuestBookGetListException(String message) {
        super(message);
    }
}
