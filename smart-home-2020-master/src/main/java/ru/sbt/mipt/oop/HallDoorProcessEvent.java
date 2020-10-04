package ru.sbt.mipt.oop;

public class HallDoorProcessEvent implements ProcessEvent {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Room){
                    Room room = (Room) object;
                    if (room.getName().equals("hall")){
                        room.execute(nextObjects -> {
                            if (nextObjects instanceof Door){
                                Door nextDoor = (Door) nextObjects;
                                if (nextDoor.getId().equals(event.getObjectId())){
                                    smartHome.execute(nextLightObject ->{
                                        if (nextLightObject instanceof Light){
                                            Light light = (Light) nextLightObject;
                                            light.setOn(false);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            Command comm = new BaseCommand();
                                            comm.process(command);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}
