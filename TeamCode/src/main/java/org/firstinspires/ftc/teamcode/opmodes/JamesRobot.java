package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class JamesRobot {
    public HardwareQueue hardwareQueue;
    public HardwareMap hardwareMap;
    public JamesMotorIntake intake;

    public JamesRobot(){
        hardwareQueue = new HardwareQueue();
        hardwareMap = new HardwareMap();
        intake = new JamesMotorIntake(hardwareQueue, hardwareMap);
    }

    public void update(){
        hardwareQueue.update();
        intake.update();
    }

}
