package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class DanielRobot {
	public final HardwareMap hardwareMap;
	public final HardwareQueue hardwareQueue;
	public final DanielIntake intake;
	public final DanielSlides slides;
	public final DanielSensors sensors;

	public DanielRobot(HardwareMap hardwareMap) {
		this.hardwareMap = hardwareMap;
		hardwareQueue = new HardwareQueue();
		intake = new DanielIntake(this);
		slides = new DanielSlides(this);
		sensors = new DanielSensors(this);
	}

	public void update() {
		sensors.update();

		intake.update();
		slides.update();

		hardwareQueue.update();
	}
}
