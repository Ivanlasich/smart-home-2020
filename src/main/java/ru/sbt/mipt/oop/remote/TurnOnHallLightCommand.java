package ru.sbt.mipt.oop.remote;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class TurnOnHallLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room){
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(light -> {
                        if (light instanceof Light) {
                            ((Light) light).setOn(true);
                        }
                    });
                }
            }
        });
    }
}