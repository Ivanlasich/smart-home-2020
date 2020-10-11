package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIteratorRoom {
    void test(Object room){
        assertTrue(room instanceof Room);
    }
    @Test
    void testIteratorRoom() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        Collection<Room> rooms = smartHome.getRooms();

        rooms.forEach(room -> test(room));
    }

}
