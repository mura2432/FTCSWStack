package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class AidenRobot {
    public class AidenRobot(){
        public HardwareQueue hardwareQueue;
        public HardwareMap hardwareMap;
        public JamesMotorIntake intake;

    }
    public void update(){
        hardwareQueue.update();
        aidenIntake.updateMotors();
    }

}
