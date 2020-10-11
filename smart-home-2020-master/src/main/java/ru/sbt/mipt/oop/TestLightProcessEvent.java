package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.SensorEventType.*;


public class TestLightProcessEvent {
    @Test
    void lightIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "1";
        SensorEvent event = new SensorEvent(LIGHT_ON, id);

        EventProcessor process = new LightEventProcessor();
        process.process(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    assertTrue(light.isOn());
                }
            }
        }
    }

    @Test
    void lightNotIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "3";
        SensorEvent event = new SensorEvent(LIGHT_OFF, id);

        EventProcessor process = new LightEventProcessor();
        process.process(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    assertTrue(!light.isOn());
                }
            }
        }
    }

}
