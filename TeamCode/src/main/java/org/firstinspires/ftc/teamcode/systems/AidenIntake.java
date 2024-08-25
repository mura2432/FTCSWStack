package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class AidenIntake extends OpMode {
    PriorityMotor intakeMotor;
    public AidenIntake(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        PriorityMotor intakeMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "IntakeMotor"), "IntakeMotor", 3, 5);
        hardwareQueue.addDevice(intakeMotor);
    }
    public enum IntakeState {
        INTAKE_ON,
        INTAKE_OFF,
        ANTISTALL,
        REVERSE
    };
    IntakeState intakeState = IntakeState.INTAKE_OFF;
    public void updateIntake() {
        switch (intakeState) {
            case INTAKE_ON:
                intakeMotor.setTargetPower(0.45);

                break;
            case INTAKE_OFF:
                intakeMotor.setTargetPower(0);
                break;
            case ANTISTALL:
                intakeMotor.setTargetPower(-0.2);
                break;
            case REVERSE:
                intakeMotor.setTargetPower(-0.5);
                break;
        }
    }
    public void intakeStart(){
        intakeState = IntakeState.INTAKE_ON;
    }
    public void intakeOff(){
        intakeState = IntakeState.INTAKE_OFF;
    }
    public void intakeReverse(){
        intakeState = IntakeState.REVERSE;
    }
}
