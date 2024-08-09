package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.hardware.lynx.LynxModule;

public class DanielSensors {
	private final DanielRobot robot;

	private double slidesEncoder;

	public DanielSensors(DanielRobot robot) {
		this.robot = robot;

		LynxModule controlHub = robot.hardwareMap.get(LynxModule.class, "Control Hub");
		controlHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);

		LynxModule expansionHub = robot.hardwareMap.get(LynxModule.class, "Expansion Hub");
		expansionHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
	}

	public void update() {
		slidesEncoder = robot.slides.slidesMotor.motor[0].getCurrentPosition();
	}

	public double getSlidesPosition() { return slidesEncoder; }
}
