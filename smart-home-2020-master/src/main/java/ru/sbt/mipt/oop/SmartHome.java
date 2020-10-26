package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    Collection<Room> rooms;
    SmartSignaling smartSignaling;

    public void setSmartSignaling(SmartSignaling smartSignaling) {
        this.smartSignaling = smartSignaling;
    }

    public SmartHome() {
        rooms = new ArrayList<>();
    }


    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }


    public SmartSignaling getSmartSignaling() {
        return smartSignaling;
    }

    @Override
    public void execute(Action action) {
        rooms.forEach(room -> {room.execute(action);});
        action.begin(this);
    }
}
