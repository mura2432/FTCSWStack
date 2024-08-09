package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class JamesSensors {
    private int slidesEncoder;
    private final HardwareMap hardwareMap;
    private final HardwareQueue hardwareQueue;
    private JamesRobot robot;

    public JamesSensors(HardwareMap hardwareMap, HardwareQueue hardwareQueue, JamesRobot robot){
        this.hardwareMap = hardwareMap;
        this.hardwareQueue = hardwareQueue;
        this.robot = robot;
    }

    public void update(){
        slidesEncoder = ((PriorityMotor) hardwareQueue.getDevice("rightFront")).motor[0].getCurrentPosition() * -1;
    }

    public double getSlidesEncoder(){
        return slidesEncoder;
    }

}
