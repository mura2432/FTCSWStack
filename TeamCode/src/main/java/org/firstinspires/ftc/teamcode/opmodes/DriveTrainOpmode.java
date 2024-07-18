package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

@SuppressWarnings("unused")
@TeleOp(name = "MecanumDriveOpmode")
public class DriveTrainOpmode extends LinearOpMode {
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
        DcMotor driveFrontLeftMotor = hardwareMap.get(DcMotor.class, "driveFrontLeftMotor");
        DcMotor driveBackLeftMotor = hardwareMap.get(DcMotor.class, "driveBackLeftMotor");
        DcMotor driveFrontRightMotor = hardwareMap.get(DcMotor.class, "driveFrontRightMotor");
        DcMotor driveBackRightMotor = hardwareMap.get(DcMotor.class, "driveBackRightMotor");

        // Assumes belt drive for each wheel
        driveFrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        driveBackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

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

            driveFrontLeftMotor.setPower(frontLeftPower);
            driveBackLeftMotor.setPower(backLeftPower);
            driveFrontRightMotor.setPower(frontRightPower);
            driveBackRightMotor.setPower(backRightPower);
        }

        /* Old code
        DcMotor myMotor = hardwareMap.get(DcMotor.class, "myMotor");
        waitForStart();
        double x = 0;
        while (opModeIsActive()){
            myMotor.setPower(Math.sin(x));
            x += 0.01;
        }
        */
    }
}
