//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestLightProcessEvent {
    public TestLightProcessEvent() {
    }

    @Test
    void lightIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        String id = "1";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, id);
        EventProcessor process = new LightEventProcessor();
        process.process(smartHome, event);
        smartHome.execute((object) -> {
            if (object instanceof Light && ((Light)object).getId().equals(event.getObjectId())) {
                Assertions.assertTrue(((Light)object).isOn());
            }

        });
    }

    @Test
    void lightNotIsOn() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        String id = "3";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, id);
        EventProcessor process = new LightEventProcessor();
        process.process(smartHome, event);
        smartHome.execute((object) -> {
            if (object instanceof Light && ((Light)object).getId().equals(event.getObjectId())) {
                Assertions.assertTrue(!((Light)object).isOn());
            }

        });
    }
}
