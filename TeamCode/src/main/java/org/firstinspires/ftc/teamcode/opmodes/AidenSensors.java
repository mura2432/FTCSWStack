package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.opmodes.AidenRobot;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class AidenSensors {
    private LynxModule controlHub, expansionHub;
    private int slidesPos;
    private HardwareMap hardwareMap;
    private HardwareQueue hardwareQueue;
    private AidenRobot robot;

    public AidenSensors(HardwareMap hardwareMap, HardwareQueue hardwareQueue, AidenRobot robot){
        this.hardwareMap = hardwareMap;
        this.hardwareQueue = hardwareQueue;
        this.robot = robot;

        updateSensors(hardwareMap);
    }
    public updateSensors(HardwareMap hardwareMap){
        for (LynxModule hub : allHubs) {
            controlHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
            expansionHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
}
