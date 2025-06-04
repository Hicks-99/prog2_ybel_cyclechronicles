package cyclechronicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShopTest {
    Shop shop;

    @BeforeEach
    void setup() {
        shop = mock(Shop.class);
    }

    @Test
    void testRepair_Empty() {
        when(shop.repair()).thenReturn(Optional.empty());

        assertTrue(shop.repair().isEmpty());
    }

    @Test
    void testRepair_NotEmpty() {
        Order o = mock(Order.class);
        when(shop.repair()).thenReturn(Optional.of(o));

        assertFalse(shop.repair().isEmpty());
    }

    @Test
    void testRepair_GetFirst() {
        Order o1 = mock(Order.class);
        Order o2 = mock(Order.class);
        when(o1.getCustomer()).thenReturn("Customer 1");
        when(o2.getCustomer()).thenReturn("Customer 2");

        shop.accept(o1);
        shop.accept(o2);

        when(shop.repair()).thenReturn(Optional.of(o1));

        assertEquals(shop.repair().get(), o1);
    }

    @Test
    void testDeliver_Empty() {
        when(shop.deliver(null)).thenReturn(Optional.empty());

        assertTrue(shop.deliver(null).isEmpty());
    }

    @Test
    void testDeliver_NotEmpty() {
        Order o1 = mock(Order.class);
        when(o1.getCustomer()).thenReturn("Costumer");

        shop.accept(o1);

        when(shop.repair()).thenReturn(Optional.of(o1));
        when(shop.deliver("Costumer")).thenReturn(Optional.of(o1));

        assertFalse(shop.deliver("Costumer").isEmpty());
    }

    @Test
    void testDeliver_OrderRemoved() {
        Order o = mock(Order.class);
        when(o.getCustomer()).thenReturn("Costumer");

        shop.accept(o);

        when(shop.repair()).thenReturn(Optional.of(o));
        when(shop.deliver("Costumer")).thenReturn(Optional.of(o));

        assertTrue(shop.deliver("Costumer").isPresent());

        when(shop.deliver("Costumer")).thenReturn(Optional.empty());

        assertTrue(shop.deliver("Costumer").isEmpty());
    }
}