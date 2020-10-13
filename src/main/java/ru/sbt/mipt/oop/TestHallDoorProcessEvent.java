package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class TestHallDoorProcessEvent {
    @Test
    void doorNotChange() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "4";
        SensorEvent event = new SensorEvent(DOOR_OPEN, id);

        EventProcessor processEvent = new DoorsEventProcessor();
        processEvent.process(smartHome, event);

        smartHome.execute(object -> {
            if (object instanceof Door) {
                if (((Door)object).getId().equals("3")) {
                    assertTrue(((Door)object).isOpen());
                }
            }
        });
    }

    @Test
    void closeHallDoorLightNotIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "4";
        SensorEvent event = new SensorEvent(DOOR_CLOSED, id);

        EventProcessor processEvent = new HallDoorEventProcessor();
        processEvent.process(smartHome, event);

        smartHome.execute(object -> {
            if (object instanceof Light) {
                assertTrue(!((Light) object).isOn());
            }
        });
    }

}
