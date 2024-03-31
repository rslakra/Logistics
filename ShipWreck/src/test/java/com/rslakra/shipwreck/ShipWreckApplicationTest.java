package com.rslakra.shipwreck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rslakra.shipwreck.controller.HomeController;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ShipWreckApplicationTest {

    @Test
    public void testApp() {
        String result = new HomeController().home();
        assertEquals("Shipwreck welcomes you!", result);
    }
}
