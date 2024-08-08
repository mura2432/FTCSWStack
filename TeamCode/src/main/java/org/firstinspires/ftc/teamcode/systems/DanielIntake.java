package org.firstinspires.ftc.teamcode.systems;

import static java.lang.Math.max;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class DanielIntake {
	public enum IntakeMotorState {
		ON,
		OFF,
		ANTISTALL,
		REVERSE
	}

	public static final double antiStallRampStep = 0.1;

	public PriorityMotor intakeMotor;
	private IntakeMotorState intakeMotorState = IntakeMotorState.OFF;

	public DanielIntake(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
		PriorityMotor intakeMotor = new PriorityMotor(
				hardwareMap.get(DcMotorEx.class, "intakeMotor"),
				"intakeMotor",
				3, 5
		);
		hardwareQueue.addDevice(intakeMotor);
	}

	public void update(){
		switch(intakeMotorState){
			case OFF:
				intakeMotor.setTargetPower(0.0);
				break;
			case ON:
				intakeMotor.setTargetPower(1.0);
				break;
			case ANTISTALL:
				intakeMotor.setTargetPower(max(intakeMotor.getPower() - antiStallRampStep, -1.0));
				break;
			case REVERSE:
				intakeMotor.setTargetPower(-1.0);
				break;
			default:
				throw new IllegalStateException("Unexpected value for intakeMotorState: " + intakeMotorState);
		}
	}

	public IntakeMotorState getIntakeMotorState() { return intakeMotorState; }

	public void setOff() { intakeMotorState = IntakeMotorState.OFF; }

	public void setOn() { intakeMotorState = IntakeMotorState.ON; }

	public void setAntiStall() { intakeMotorState = IntakeMotorState.ANTISTALL; }

	public void setReverse() { intakeMotorState = IntakeMotorState.REVERSE; }
}
