//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestIteratorRoom {
    public TestIteratorRoom() {
    }

    void test(Object room) {
        Assertions.assertTrue(room instanceof Room);
    }

    @org.junit.Test
    void testIteratorRoom() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        smartHome.execute((object) -> {
            if (object instanceof Room) {
                Assertions.assertTrue(object instanceof Room);
            }

        });
    }
}
