package ru.sbt.mipt.oop.remote;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlImpl implements rc.RemoteControl{
    private Map<String, Command> buttons = new HashMap<>();



    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if(buttons.containsKey(buttonCode)){
            Command command = buttons.get(buttonCode);
            command.execute();
        }
    }
    public void registerButton(String buttonCode, Command command){
        if (buttons.containsKey(buttonCode)) {
            this.changeButton(buttonCode, command);
        } else {
            buttons.put(buttonCode, command);
        }
    }

    public void changeButton(String buttonCode, Command command){
        if (buttons.containsKey(buttonCode)) {
            buttons.replace(buttonCode, command);
        }else {
            this.registerButton(buttonCode, command);
        }
    }
}
