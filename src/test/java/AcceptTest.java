import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AcceptTest {
    private Shop shop;
    private Order o1;
    private Order o2;
    private Order o3;
    private Order o4;
    private Order o5;
    private Order o6;
    private Order o7;
    private Order o8;


    //Testfälle siehe ÄK&GW Analyse

    @BeforeEach
    public void setup(){
        shop = new Shop();

        //Mock Orders anlegen
        o1 = mock(Order.class);
        when(o1.getBicycleType()).thenReturn(Type.RACE);
        when(o1.getCustomer()).thenReturn("Herbert");

        o2 = mock(Order.class);
        when(o2.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
        when(o2.getCustomer()).thenReturn("Jürgen");

        o3 = mock(Order.class);
        when(o3.getBicycleType()).thenReturn(Type.FIXIE);
        when(o3.getCustomer()).thenReturn("Dieter");

        o4 = mock(Order.class);
        when(o4.getBicycleType()).thenReturn(Type.FIXIE);
        when(o4.getCustomer()).thenReturn("Hildegard");

        o5 = mock(Order.class);
        when(o5.getBicycleType()).thenReturn(Type.RACE);
        when(o5.getCustomer()).thenReturn("Hans");

        o6 = mock(Order.class);
        when(o6.getBicycleType()).thenReturn(Type.EBIKE);
        when(o6.getCustomer()).thenReturn("Merkel");

        o7 = mock(Order.class);
        when(o7.getBicycleType()).thenReturn(Type.GRAVEL);
        when(o7.getCustomer()).thenReturn("Mayonnaise");
        
        o8 = mock(Order.class);
        when(o8.getBicycleType()).thenReturn(Type.RACE);
        when(o8.getCustomer()).thenReturn("Radünz");
    }

    //Könnte man bestimmt schöner mit Paramized Tests machen. 
    //Wie kann man die pendingOrders mit dummy Orders füllen, obwohl die Queue Privat ist?
    @Test 
    public void testAccept1(){
        //RACE, 0, 1

        shop.accept(o2);
        assertTrue(shop.accept(o1));
    }

    @Test
    public void testAccept2(){
        //RACE, 0, 2
        shop.accept(o2);
        shop.accept(o3);
        assertTrue(shop.accept(o1));
    }

    @Test
    public void testAccept3(){
        //SINGLE_SPEED, 0, 4
        shop.accept(o1);
        shop.accept(o3);
        shop.accept(o4);
        shop.accept(o5);
        assertTrue(shop.accept(o2));
    }

    @Test
    public void testAccept4(){
        //FIXIE, 0, 0
        assertTrue(shop.accept(o3));
    }

    @Test 
    public void testAccept5(){
        //EBIKE, 0, 1
        shop.accept(o1);
        assertFalse(shop.accept(o6));
    }

    @Test
    public void testAccept6(){
        //GRAVEL, 0, 2 
        shop.accept(o1);
        shop.accept(o2);
        assertFalse(shop.accept(o7));
    }

    @Test 
    public void testAccept7(){
        //RACE, 1, 3
        shop.accept(o1);
        shop.accept(o3);
        shop.accept(o4);
        assertFalse(shop.accept(o1));
    }

    @Test 
    public void testAccept8(){
        //RACE, 0, 5 
        shop.accept(o1);
        shop.accept(o2);
        shop.accept(o3);
        shop.accept(o4);
        shop.accept(o5);
        assertFalse(shop.accept(o8));
    }
}
