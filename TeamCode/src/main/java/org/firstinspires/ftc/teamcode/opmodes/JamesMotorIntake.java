package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import static java.lang.Math.max;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class JamesMotorIntake{
    enum MotorState {
        ON,
        OFF,
        ANTISTALL,
        REVERSE
    };

    MotorState motorState = MotorState.OFF;
    PriorityMotor pm;

    public JamesMotorIntake(HardwareQueue hq){
        pm = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "pm"), "pm", 3, 5);
        hq.addDevice(pm);
    }

    public void update(){
        switch(motorState){
            case OFF:
                pm.setTargetPower(0.0);
                break;
            case ON:
                pm.setTargetPower(1.0);
                break;
            case ANTISTALL:
                pm.setTargetPower(max(pm.getPower() - 0.1, -1.0));
                break;
            case REVERSE:
                pm.setTargetPower(-1.0);
                break;
        }
    }

    public void setOff(){
        motorState = MotorState.OFF;
    }

    public void setON(){
        motorState = MotorState.ON;
    }

    public void setAntiStall(){
        motorState = MotorState.ANTISTALL;
    }

    public void setReverse(){
        motorState = MotorState.REVERSE;
    }

}
