package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

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
                pm.setTargetPower(pm.getPower() - 0.1);
                break;
            case REVERSE:
                pm.setTargetPower(-1.0);
                break;
        }
    }


}
