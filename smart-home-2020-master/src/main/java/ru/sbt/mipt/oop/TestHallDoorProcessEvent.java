//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHallDoorProcessEvent {
    public TestHallDoorProcessEvent() {
    }

    @Test
    void doorNotChange() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        String id = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, id);
        EventProcessor processEvent = new DoorsEventProcessor();
        processEvent.process(smartHome, event);
        smartHome.execute((object) -> {
            if (object instanceof Door && ((Door)object).getId().equals("3")) {
                Assertions.assertTrue(((Door)object).isOpen());
            }

        });
    }

    @Test
    void closeHallDoorLightNotIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        String id = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, id);
        EventProcessor processEvent = new HallDoorEventProcessor();
        processEvent.process(smartHome, event);
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Assertions.assertTrue(!((Light)object).isOn());
            }

        });
    }
}
