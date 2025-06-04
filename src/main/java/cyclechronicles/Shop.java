package cyclechronicles;

import java.util.*;
import java.util.logging.*;

/** A small bike shop. */
public class Shop {
    private static final Logger loggger = Logger.getLogger(Shop.class.getName());
    private final Queue<Order> pendingOrders = new LinkedList<>();
    private final Set<Order> completedOrders = new HashSet<>();

    static {
        loggger.setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("shop.csv", true);
            fileHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord logRecord) {
                    return String.format("%s,%s,%s,%s%n",
                            logRecord.getLevel(),
                            logRecord.getSourceMethodName(),
                            logRecord.getSourceClassName(),
                            logRecord.getMessage());
                }
            });
            loggger.addHandler(fileHandler);
            loggger.setUseParentHandlers(false);
        } catch (Exception e) {
            loggger.log(Level.SEVERE, "Failed to set up logging", e);
        }
    }

    /**
     * Accept a repair order.
     *
     * <p>The order will only be accepted if all conditions are met:
     *
     * <ul>
     *   <li>Gravel bikes cannot be repaired in this shop.
     *   <li>E-bikes cannot be repaired in this shop.
     *   <li>There can be no more than one pending order per customer.
     *   <li>There can be no more than five pending orders at any time.
     * </ul>
     *
     * <p>Implementation note: Accepted orders are added to the end of {@code pendingOrders}.
     *
     * @param o order to be accepted
     * @return {@code true} if all conditions are met and the order has been accepted, {@code false}
     *     otherwise
     */
    public boolean accept(Order o) {
        if (o.getBicycleType() == Type.GRAVEL) return false;
        if (o.getBicycleType() == Type.EBIKE) return false;
        if (pendingOrders.stream().anyMatch(x -> x.getCustomer().equals(o.getCustomer())))
            return false;
        if (pendingOrders.size() > 4) return false;

        loggger.info("Add Order" + o.getBicycleType() + " " + o.getCustomer() + " to pendingOrders");
        return pendingOrders.add(o);
    }

    /**
     * Take the oldest pending order and repair this bike.
     *
     * <p>
     * Implementation note: Take the top element from {@code pendingOrders},
     * "repair" the bicycle
     * and put this order in {@code completedOrders}.
     *
     * @return finished order
     */
    public Optional<Order> repair() {
        Order order = pendingOrders.poll();
        if (order == null) return Optional.empty();

        loggger.info("Repaired Order" + order.getBicycleType() + " " + order.getCustomer() + " removed from pendingOrders");
        loggger.info("Add Order" + order.getBicycleType() + " " + order.getCustomer() + " to completedOrders");
        completedOrders.add(order);
        return Optional.of(order);
    }

    /**
     * Deliver a repaired bike to a customer.
     *
     * <p>
     * Implementation note: Find any order in {@code completedOrders} with matching
     * customer and
     * deliver this order. Will remove the order from {@code completedOrders}.
     *
     * @param c search for any completed orders of this customer
     * @return any finished order for given customer, {@code Optional.empty()} if
     *         none found
     */
    public Optional<Order> deliver(String c) {
        for (Order order : completedOrders) {
            if (order.getCustomer().equals(c)) {
                loggger.info("Deliver Order" + order.getBicycleType() + " " + order.getCustomer() + " removed from completedOrders");
                completedOrders.remove(order);
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }
}
