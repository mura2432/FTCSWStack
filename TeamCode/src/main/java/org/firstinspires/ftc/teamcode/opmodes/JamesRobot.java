package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class JamesRobot {
    public HardwareQueue hq;
    public JamesMotorIntake mi;

    public JamesRobot(){
        hq = new HardwareQueue();
        mi = new JamesMotorIntake(hq);
    }

    public void update(){
        hq.update();
        mi.update();
    }

}
