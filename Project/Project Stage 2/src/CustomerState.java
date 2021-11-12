/**
 * enum CustomerState
 * used to indicate a customer's state,
 * which can be arrives, waits, served, leaves, or done.
 */

public enum CustomerState{
    /**
     * Indicates the customer arrives
     */
    ARRIVES("arrives"),

    /**
     * Indicates the customer has been served
     */
    SERVED("served"),

    /**
     * Indicates the customer is done
     */
    DONE("done serving"),

    /**
     * Indicates the customer waits
     */
    WAITS("waits to be served"),

    /**
     * Indicates the customer leaves
     */
    LEAVES("leaves");

    private final String state;

    /**
     * Constructor for States.
     * @param state the string representation of customer's state
     */
    private CustomerState(String state) {
        this.state = state;
    }

    /**
     * Returns the state in string format.
     * @return state
     */
    public String getState() {
        return state;
    }

}