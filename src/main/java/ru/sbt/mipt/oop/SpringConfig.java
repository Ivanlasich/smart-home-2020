package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.remote.*;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringConfig {

    @Bean
    public List<EventProcessor> events(){
        List<EventProcessor> events = Arrays.asList(new LightEventProcessor(), new DoorsEventProcessor(), new HallDoorEventProcessor(), new SignalingEventProcessor());
        return events;
    }

    @Bean
    public SmartHome smartHome() {
        HomeReader reader = new JsonHomeReader();
        SmartSignaling smartSignaling = new SmartSignaling(1234);

        if(reader != null) {
            SmartHome smartHome = reader.homeReader("smart-home-1.js");
            smartHome.setSmartSignaling(smartSignaling);
            return smartHome;
        }
        return null;
    }

    @Bean
    public EventProcessor lightEventProcessor(){
        return new LightEventProcessor();
    }

    @Bean
    public EventProcessor doorsEventProcessor(){
        return new DoorsEventProcessor();
    }

    @Bean
    public EventProcessor hallDoorEventProcessor(){
        return new HallDoorEventProcessor();
    }


    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome, List<EventProcessor> events) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        for (EventProcessor processEvent : events){
            sensorEventsManager.registerEventHandler(new EventProcessorAdapter(processEvent, smartHome));
        }
        return sensorEventsManager;
    }

    @Bean
    int alarmCode() {
        return 1234;
    }
    @Bean
    String rcId() {
        return "rcId";
    }

    @Bean
    Command activateSmartSignalingCommand(SmartHome smartHome, int alarmCode) {
        Command activateSmartSignalingCommand = new ActivateSmartSignalingCommand(smartHome, alarmCode);
        return activateSmartSignalingCommand;
    }

    @Bean
    Command closeHallDoorCommand(SmartHome smartHome) {
        Command closeHallDoorCommand = new CloseHallDoorCommand(smartHome);
        return closeHallDoorCommand;
    }

    @Bean
    Command signalingSmartSignalingStatusCommand(SmartHome smartHome) {
        Command signalingSmartSignalingStatusCommand = new SignalingSmartSignalingStatusCommand(smartHome);
        return signalingSmartSignalingStatusCommand;
    }

    @Bean
    Command turnOffAllLightsCommand(SmartHome smartHome) {
        Command turnOffAllLightsCommand = new TurnOffAllLightsCommand(smartHome);
        return turnOffAllLightsCommand;
    }


    @Bean
    Command turnOnAllLightsCommand(SmartHome smartHome) {
        Command turnOnAllLightsCommand = new TurnOnAllLightsCommand(smartHome);
        return turnOnAllLightsCommand;
    }

    @Bean
    Command turnOnHallLightCommand(SmartHome smartHome) {
        Command turnOnHallLightCommand = new TurnOnHallLightCommand(smartHome);
        return turnOnHallLightCommand;
    }

    @Bean
    public RemoteControl remoteControl(Command activateSmartSignalingCommand, Command closeHallDoorCommand,
                                       Command signalingSmartSignalingStatusCommand, Command turnOffAllLightsCommand,
                                       Command turnOnAllLightsCommand, Command turnOnHallLightCommand) {
        RemoteControlImpl remoteControl = new RemoteControlImpl();
        remoteControl.registerButton("A", activateSmartSignalingCommand);
        remoteControl.registerButton("B", closeHallDoorCommand);
        remoteControl.registerButton("C", signalingSmartSignalingStatusCommand);
        remoteControl.registerButton("D", turnOffAllLightsCommand);
        remoteControl.registerButton("1", turnOnAllLightsCommand);
        remoteControl.registerButton("2", turnOnHallLightCommand);
        return remoteControl;
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry(RemoteControl remoteControl, String rcId) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl, rcId);
        return remoteControlRegistry;
    }
}
