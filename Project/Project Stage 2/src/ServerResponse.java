/**
 * enum ServerResponse
 * Represents a server's response to a customer.
 * The response can be idle, can serve after one customer, can serve after two customer, busy.
 */
public enum ServerResponse{
    /**
     * Indicates the server is idle when the customer arrives,
     */
    IDLE,

    /**
     * Indicates the server can serve the customer after serving the current customer.
     * i.e. there is no one waiting for the service of the server.
     */
    CANSERVEAFTERONECUSTOMER,

    /**
     * Indicates the server can serve the customer after serving the current customer and the waiting customer.
     *
     * <p>Note that this does not mean that the customer has to wait for two customers.
     * it simply indicates that the customer arrives after the current customer leavers.</p>
     */
    CANSERVEDAFTERTWOCUSTOMER,

    /**
     * Indicates the server cannot serve the customer.
     */
    BUSY
}
