package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class JamesSlides {
    public enum SlideState{
        ON,
        OFF
    };

    double target;
    PriorityMotor pm;
    SlideState ss = SlideState.OFF;
    JamesFeedForward ff;
    public JamesSlides(double target, PriorityMotor pm, HardwareQueue hardwareQueue){
        this.target = target;
        this.pm = pm;
        this.ff = new JamesFeedForward(1.5, 1.0);
    }

    public void update(){
        switch(ss){
            case ON:
                pm.setTargetPower(ff.update(target - pm.getPower(), -1.0, 1.0));
                break;
            case OFF:
                pm.setTargetPower(0.0);
                break;
        }
    }

    public void setTarget(double target){
        this.target = target;
    }
}
