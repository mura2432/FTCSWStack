package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class AidenIntake extends OpMode {
    PriorityMotor IntakeMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "IntakeMotor"), "IntakeMotor", 3, 5);
    hq.addDevice(IntakeMotor);
    public enum IntakeState {
        INTAKE_ON,
        INTAKE_OFF,
        ANTISTALL,
        REVERSE
    };
    IntakeState intakeState = IntakeState.INTAKE_ON
    public enum AntiStallState{
        ANTI_STALL_ON,
        ANTI_STALL_OFF
    };
    public enum ReverseState{
        REVERSE_ON,
        REVERSE_OFF
    };
    ReverseState reverseState = ReverseState.REVERSE_OFF;
    AntiStallState antiStallState = AntiStallState.ANTI_STALL_OFF;
    public void updateMotors(){
        switch (intakeState) {
            case INTAKE_ON:
                IntakeMotor.setTargetPower(0.45);

                break;
            case INTAKE_OFF:
                IntakeMotor.setTargetPower(0);
                break;
            case ANTISTALL:
                antiStallState = AntiStallState.ANTI_STALL_ON;
                break;
            case REVERSE:
                reverseState = ReverseState.REVERSE_ON;
                break;
        }
        switch (antiStallState) {
            case ANTI_STALL_ON:
                for(int i=0;i<90;i++){
                    IntakeMotor.setTargetPower(Math.sin(-i));
                }
                break;
            case ANTI_STALL_OFF:
                IntakeMotor.setTargetPower(0);
                break;
        }
        switch (reverseState) {
            case REVERSE_ON:
                IntakeMotor.setTargetPower(-1);
                break;
            case REVERSE_OFF:
                IntakeMotor.setTargetPower(0);
                break;
        }
    }
}
