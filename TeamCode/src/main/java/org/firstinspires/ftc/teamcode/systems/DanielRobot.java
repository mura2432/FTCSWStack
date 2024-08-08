package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class DanielRobot {
	public HardwareQueue hardwareQueue;
	public DanielIntake intake;

	public DanielRobot(HardwareMap hardwareMap) {
		hardwareQueue = new HardwareQueue();
		intake = new DanielIntake(hardwareMap, hardwareQueue);
	}

	public void update(){
		intake.update();
		hardwareQueue.update();
	}
}
