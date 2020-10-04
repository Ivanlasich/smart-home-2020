package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIteratorLight {
    @Test
    void testIteratorLight() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        Collection<Room> rooms = smartHome.getRooms();
        rooms.forEach(room -> room.getLights().forEach(light -> light.execute(new Action() {
            @Override
            public void begin(Object act) {
                assertTrue(act instanceof Light);
            }
        })));
    }

}
