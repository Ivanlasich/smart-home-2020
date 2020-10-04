package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class TestDoorProcessEvent {
    @Test
    void doorIsOpen() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "1";
        SensorEvent event = new SensorEvent(DOOR_OPEN, id);

        ProcessEvent processEvent = new DoorsProcessEvent();
        processEvent.process(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

    @Test
    void doorIsClose() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "3";
        SensorEvent event = new SensorEvent(DOOR_CLOSED, id);

        ProcessEvent processEvent = new DoorsProcessEvent();
        processEvent.process(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    assertTrue(! door.isOpen());
                }
            }
        }
    }
}
