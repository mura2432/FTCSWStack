package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class AidenRobot {
    public HardwareQueue hardwareQueue = new HardwareQueue();
    public AidenIntake aidenIntake = new AidenIntake(hardwareQueue);

    public void update(){
        hardwareQueue.update();
        aidenIntake.updateMotors();
    }

}
