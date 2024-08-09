package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class DanielSlides {
	public enum SlidesMotorState {
		ON,
		OFF
	}

	public static double KP = 1;
	public static double powerConstantTerm = 0.05;

	public final DanielRobot robot;
	public final PriorityMotor slidesMotor;
	private SlidesMotorState slidesMotorState = SlidesMotorState.OFF;

	private double currentPosition;
	private double targetPosition;

	public DanielSlides(DanielRobot robot) {
		this.robot = robot;
		slidesMotor = new PriorityMotor(
				robot.hardwareMap.get(DcMotorEx.class, "slidesMotor"),
				"slidesMotor",
				3, 5
		);
		robot.hardwareQueue.addDevice(slidesMotor);
	}

	public void update() {
		switch(slidesMotorState){
			case OFF:
				slidesMotor.setTargetPower(0.0);
				break;
			case ON:
				currentPosition = robot.sensors.getSlidesPosition();
				slidesMotor.setTargetPower(Utils.minMaxClip((targetPosition - currentPosition) * KP + powerConstantTerm, -1.0, 1.0));
				break;
			default:
				throw new IllegalStateException("Unexpected value for slidesMotorState: " + slidesMotorState);
		}
	}

	public SlidesMotorState getSlidesMotorState() { return slidesMotorState; }

	public void setOff() { slidesMotorState = SlidesMotorState.OFF; }

	public void setOn() { slidesMotorState = SlidesMotorState.ON; }

	public double getCurrentPosition() { return currentPosition; }

	public double getTargetPosition() { return targetPosition; }

	public void setTargetPosition(double targetPosition) { this.targetPosition = targetPosition; }
}
