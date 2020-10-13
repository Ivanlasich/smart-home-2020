package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIteratorRoom {
    void test(Object room){
        assertTrue(room instanceof Room);
    }
    @Test
    void testIteratorRoom() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        smartHome.execute(object -> {
            if (object instanceof Room) {
                assertTrue(object instanceof Room);
            }
        });
    }
}
