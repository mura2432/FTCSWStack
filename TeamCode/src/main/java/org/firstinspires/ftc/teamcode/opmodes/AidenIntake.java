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
    public AidenIntake(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        PriorityMotor intakeMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "IntakeMotor"), "IntakeMotor", 3, 5);
        this.intakeState = IntakeState;
        hardwareQueue.addDevice(intakeMotor);
    }
    public HardwareQueue hardwareQueue;
    hardwareQueue.addDevice(intakeMotor);
    public enum IntakeState {
        INTAKE_ON,
        INTAKE_OFF,
        ANTISTALL,
        REVERSE
    };
    IntakeState intakeState = IntakeState;
    public void updateMotors(){
        switch (intakeState) {
            case INTAKE_ON:
                intakeMotor.setTargetPower(0.45);

                break;
            case INTAKE_OFF:
                intakeMotor.setTargetPower(0);
                break;
            case ANTISTALL:
                
                break;
            case REVERSE:
                intakeMotor.setTargetPower(-0.5);
                break;
    }
}
