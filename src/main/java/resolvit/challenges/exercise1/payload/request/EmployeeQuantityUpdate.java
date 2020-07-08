package resolvit.challenges.exercise1.payload.request;

public class EmployeeQuantityUpdate {

    private String type;
    private int quantity;

    public EmployeeQuantityUpdate(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
