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

        ProcessEvent processEvent = new DoorsProcessEvent();
        processEvent.process(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("3")) {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

    @Test
    void closeHallDoorLightNotIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "4";
        SensorEvent event = new SensorEvent(DOOR_CLOSED, id);

        ProcessEvent processEvent = new HallDoorProcessEvent();
        processEvent.process(smartHome, event);
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertTrue(! light.isOn());
            }
        }
    }

}
