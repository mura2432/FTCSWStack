package org.firstinspires.ftc.teamcode.opmodes;

import static java.lang.Math.max;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class DanielIntake {
	public enum IntakeState {
		ON,
		OFF,
		ANTISTALL,
		REVERSE
	}

	public static final double antiStallRampStep = 0.1;

	public PriorityMotor intakeMotor;
	private IntakeState intakeState;

	public DanielIntake(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
		PriorityMotor intakeMotor = new PriorityMotor(
				hardwareMap.get(DcMotorEx.class, "intakeMotor"),
				"intakeMotor",
				3, 5
		);
		hardwareQueue.addDevice(intakeMotor);
	}

	public void update(){
		switch(intakeState){
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
				throw new IllegalStateException("Unexpected value for intakeState: " + intakeState);
		}
	}

	public IntakeState getIntakeState() { return intakeState; }

	public void setOff() { intakeState = IntakeState.OFF; }

	public void setOn() { intakeState = IntakeState.ON; }

	public void setAntiStall() { intakeState = IntakeState.ANTISTALL; }

	public void setReverse() { intakeState = IntakeState.REVERSE; }
}
