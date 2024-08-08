package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class DanielSlides {
	public enum SlidesMotorState {
		ON,
		OFF
	}

	public static final double KP = 1;

	public PriorityMotor slidesMotor;
	private SlidesMotorState slidesMotorState = SlidesMotorState.OFF;

	private double currentPosition;
	private double targetPosition;

	public DanielSlides(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
		PriorityMotor slidesMotor = new PriorityMotor(
				hardwareMap.get(DcMotorEx.class, "slidesMotor"),
				"slidesMotor",
				3, 5
		);
		hardwareQueue.addDevice(slidesMotor);
	}

	public void update(){
		switch(slidesMotorState){
			case OFF:
				slidesMotor.setTargetPower(0.0);
				break;
			case ON:
				currentPosition = slidesMotor.motor[0].getCurrentPosition();
				slidesMotor.setTargetPower(Utils.minMaxClip((targetPosition - currentPosition) * KP, -1.0, 1.0));
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

	public void setTargetPosition(double targetPosition_) { targetPosition = targetPosition_; }
}
