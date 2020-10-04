package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorsProcessEvent implements  ProcessEvent {


    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        SensorEventType type = event.getType();
        if (type == SensorEventType.DOOR_OPEN || type == SensorEventType.DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Door){
                    Door door = (Door) object;
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " in room " + " was opened.");
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room " + " was closed.");

                        }
                    }
                }
                else return;
            });
        }
    }
}