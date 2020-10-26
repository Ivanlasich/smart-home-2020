package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSignalingSmartSignaling {
    @Test
    void testSignaling() {
        SmartSignaling smartSignaling = new SmartSignaling(1234);
        smartSignaling.signaling();
        smartSignaling.deactivate(1234);
        assertTrue(smartSignaling.getStatus() instanceof  DeactivateSmartSignalingStatus);
    }

}
