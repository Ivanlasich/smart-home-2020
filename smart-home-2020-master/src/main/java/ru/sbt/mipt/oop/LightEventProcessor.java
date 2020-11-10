package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class LightEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            smartHome.execute(object -> {
                if (object instanceof Light){
                    Light light = (Light) object;
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " in room " + " was turned on.");
                        } else {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " in room " + " was turned off.");
                        }
                    }
                }
                else return;
            });
        }
    }
}