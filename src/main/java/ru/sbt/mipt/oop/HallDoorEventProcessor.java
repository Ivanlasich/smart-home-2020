package ru.sbt.mipt.oop;

public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Room){
                    Room room = checkParent(object);
                    if (room!=null){
                        room.execute(nextObjects -> {
                            doorManage(nextObjects, smartHome, event);
                        });
                    }
                }
            });
        }
    }
    private Room checkParent(Object object) {
        if (object instanceof Room) {
            Room room = (Room) object;
            if (room.getName().equals("hall")) {
                return room;
            }
        }
        return null;
    }

    private void doorManage(Object nextObjects, SmartHome smartHome, SensorEvent event){
        if (nextObjects instanceof Door){
            Door nextDoor = (Door) nextObjects;
            if (nextDoor.getId().equals(event.getObjectId())){
                smartHome.execute(nextLightObject ->{
                    lightOff(smartHome);
                });
            }
        }
    }

    private void lightOff(SmartHome smartHome) {
        smartHome.execute(nextLightObject ->{
            if (nextLightObject instanceof Light){
                Light light = (Light) nextLightObject;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandHandler comm = new BaseCommandHandler();
                comm.process(command);
            }
        });
    }
}
