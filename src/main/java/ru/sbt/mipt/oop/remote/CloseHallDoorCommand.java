package ru.sbt.mipt.oop.remote;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class CloseHallDoorCommand implements Command{
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room){
                Room room = (Room) object;
                if (room.getName().equals("hall")){
                    room.execute(door -> {
                        if (door instanceof Door){
                            ((Door) door).setOpen(false);
                        }
                    });
                }
            }
        });
    }
}