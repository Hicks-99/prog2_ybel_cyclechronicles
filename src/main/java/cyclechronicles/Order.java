package cyclechronicles;

/** An order for a bike shop. */
public class Order {
    private final Type bicycleType;
    private final String customer;

    Order(Type bicycleType, String customer) {
        this.bicycleType = bicycleType;
        this.customer = customer;
    }


    /**
     * Determine the type of bike to be repaired.
     *
     * @return type of bicycle
     */
    public Type getBicycleType() {
        return bicycleType;
    }

    /**
     * Determine the customer who placed this order.
     *
     * @return name of customer
     */
    public String getCustomer() {
        return customer;
    }
}
