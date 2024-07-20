package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
@TeleOp(name = "DanielDriveOpmode")
public class DanielDriveOpmode extends LinearOpMode {
	@Override
	public void runOpMode() {
		// Store gamepad state
		Gamepad currentGamepad1 = new Gamepad();
		Gamepad currentGamepad2 = new Gamepad();
		Gamepad previousGamepad1 = new Gamepad();
		Gamepad previousGamepad2 = new Gamepad();

		// Constants
		double strafeMultiplier = 1.1;

		// Robot configuration
		List<PriorityMotor> driveMotors;
		HardwareQueue hardwareQueue = new HardwareQueue();
		PriorityMotor driveFrontLeftMotor = new PriorityMotor(
			hardwareMap.get(DcMotorEx.class, "driveFrontLeftMotor"),
			"driveFrontLeftMotor",
			3, 5
		);
		PriorityMotor driveBackLeftMotor = new PriorityMotor(
			hardwareMap.get(DcMotorEx.class, "driveBackLeftMotor"),
			"driveBackLeftMotor",
			3, 5
		);
		PriorityMotor driveFrontRightMotor = new PriorityMotor(
			hardwareMap.get(DcMotorEx.class, "driveFrontRightMotor"),
			"driveFrontRightMotor",
			3, 5
		);
		PriorityMotor driveBackRightMotor = new PriorityMotor(
			hardwareMap.get(DcMotorEx.class, "driveBackRightMotor"),
			"driveBackRightMotor",
			3, 5
		);
		driveMotors = Arrays.asList(driveFrontLeftMotor, driveBackLeftMotor, driveFrontRightMotor, driveBackRightMotor);
		for (PriorityMotor motor : driveMotors) {
			hardwareQueue.addDevice(motor);
		}

		// Assumes belt drive for each wheel
		driveFrontRightMotor.motor[0].setDirection(DcMotorSimple.Direction.REVERSE);
		driveBackRightMotor.motor[0].setDirection(DcMotorSimple.Direction.REVERSE);

		waitForStart();
		if (isStopRequested()) return;

		while (opModeIsActive()) {
			// Update stored gamepad state;
			previousGamepad1.copy(currentGamepad1);
			previousGamepad2.copy(currentGamepad2);
			currentGamepad1.copy(gamepad1);
			currentGamepad2.copy(gamepad2);

			// Mecanum drive
			double straight = -currentGamepad1.left_stick_y;
			double strafe = currentGamepad1.left_stick_x * strafeMultiplier;
			double turn = currentGamepad1.right_stick_x;

			double largestPower = Math.max(Math.abs(straight) + Math.abs(strafe) + Math.abs(turn), 1);
			double frontLeftPower = (straight + strafe + turn) / largestPower;
			double backLeftPower = (straight - strafe + turn) / largestPower;
			double frontRightPower = (straight - strafe - turn) / largestPower;
			double backRightPower = (straight + strafe - turn) / largestPower;

			driveFrontLeftMotor.setTargetPower(frontLeftPower);
			driveBackLeftMotor.setTargetPower(backLeftPower);
			driveFrontRightMotor.setTargetPower(frontRightPower);
			driveBackRightMotor.setTargetPower(backRightPower);

			hardwareQueue.update();
		}
	}
}
