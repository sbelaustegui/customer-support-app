package resolvit.challenges.exercise1.payload.response;

public class SupportResponse {

    private String message;
    private boolean allBusy;

    public SupportResponse(String message, boolean allBusy) {
        this.message = message;
        this.allBusy = allBusy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAllBusy() {
        return allBusy;
    }

    public void setAllBusy(boolean allBusy) {
        this.allBusy = allBusy;
    }
}
