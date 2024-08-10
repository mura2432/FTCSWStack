package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class JamesSensors {
    private LynxModule controlHub, expansionHub;
    private int slidesEncoder;
    private final HardwareMap hardwareMap;
    private final HardwareQueue hardwareQueue;
    private JamesRobot robot;

    public JamesSensors(HardwareMap hardwareMap, HardwareQueue hardwareQueue, JamesRobot robot){
        this.hardwareMap = hardwareMap;
        this.hardwareQueue = hardwareQueue;
        this.robot = robot;

        initSensors(hardwareMap);
    }

    private void initSensors(HardwareMap hardwareMap){
        controlHub = hardwareMap.get(LynxModule.class, "Control Hub");
        controlHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);

        expansionHub = hardwareMap.get(LynxModule.class, "Expansion Hub");
        expansionHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
    }
    public void update(){
        updateControlHub();
    }

    private void updateControlHub(){
        slidesEncoder = ((PriorityMotor) hardwareQueue.getDevice("rightFront")).motor[0].getCurrentPosition() * -1;
    }

    public double getSlidesEncoder(){
        return slidesEncoder;
    }

}
