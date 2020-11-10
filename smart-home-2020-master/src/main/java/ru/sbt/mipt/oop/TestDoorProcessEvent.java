package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDoorProcessEvent {
    public TestDoorProcessEvent() {
    }

    @Test
    void doorIsOpen() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        String id = "1";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, id);
        EventProcessor processEvent = new DoorsEventProcessor();
        processEvent.process(smartHome, event);
        smartHome.execute((object) -> {
            if (object instanceof Door && ((Door)object).getId().equals(event.getObjectId())) {
                Assertions.assertTrue(((Door)object).isOpen());
            }

        });
    }

    @Test
    void doorIsClose() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        String id = "3";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, id);
        EventProcessor processEvent = new DoorsEventProcessor();
        processEvent.process(smartHome, event);
        smartHome.execute((object) -> {
            if (object instanceof Door && ((Door)object).getId().equals(event.getObjectId())) {
                Assertions.assertTrue(!((Door)object).isOpen());
            }

        });
    }
}
