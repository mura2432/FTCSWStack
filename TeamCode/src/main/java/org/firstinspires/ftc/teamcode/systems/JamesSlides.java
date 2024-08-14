package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class JamesSlides {
    public enum SlidesState{
        ON,
        OFF
    };

    double target, currPos;
    final double gravity = 0.1, kstatic = 0.2, ticksToInches = 0.05, slowDownDistance = 0.75;

    PriorityMotor pm;
    HardwareQueue hardwareQueue;
    JamesSensors sensors;
    SlidesState ss = SlidesState.OFF;

    public JamesSlides(HardwareQueue hardwareQueue, HardwareMap hardwareMap, JamesSensors sensor){
        pm = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "pm"), "pm", 3, 5);
        hardwareQueue.addDevice(pm);
        this.sensors = sensor;
    }

    public void update(){
        switch(ss){
            case ON:
                //double currPos = pm.motor[0].getCurrentPosition();
                currPos = sensors.getSlidesEncoder();
                pm.setTargetPower(Utils.minMaxClip(feedForward(), -1.0, 1.0));
                break;
            case OFF:
                pm.setTargetPower(0.0);
                break;
        }
    }

    public double getCurrPos(){
        return currPos;
    }

    public void setTarget(double target){
        this.target = target;
    }

    public double feedForward(){
        double error = target - currPos;
        return error/slowDownDistance + gravity + kstatic * Math.signum(error);
    }
}
