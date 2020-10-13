package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class TestLightProcessEvent {

    @Test
    void lightIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "1";
        SensorEvent event = new SensorEvent(LIGHT_ON, id);

        EventProcessor process = new LightEventProcessor();
        process.process(smartHome, event);


        smartHome.execute(object -> {
            if (object instanceof Light) {
                if (((Light) object).getId().equals(event.getObjectId())) {
                    assertTrue(((Light)object).isOn());
                }

            }
        });
    }

    @Test
    void lightNotIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");

        String id = "3";
        SensorEvent event = new SensorEvent(LIGHT_OFF, id);

        EventProcessor process = new LightEventProcessor();
        process.process(smartHome, event);


        smartHome.execute(object -> {
            if (object instanceof Light) {
                if (((Light) object).getId().equals(event.getObjectId())) {
                    assertTrue(!((Light) object).isOn());
                }
            }
        });

    }

}
