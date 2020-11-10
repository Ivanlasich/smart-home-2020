package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDeactivateSmartSignaling {
    @Test
    void testDeactivate() {
        SmartSignaling smartSignaling = new SmartSignaling(1234);
        smartSignaling.activate(1234);
        smartSignaling.deactivate(1234);
        assertTrue(smartSignaling.getStatus() instanceof  DeactivateSmartSignalingStatus);
    }

    @Test
    void testWrongPass(){
        SmartSignaling smartSignaling = new SmartSignaling(1234);
        smartSignaling.activate(1234);
        smartSignaling.deactivate(1233);
        assertTrue(smartSignaling.getStatus() instanceof SignalingSmartSignalingStatus);
    }

}


