package org.firstinspires.ftc.teamcode.opmodes;

import android.widget.Button;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Hardware

import org.firstinspires.ftc.teamcode.systems.AidenSensors;
import org.firstinspires.ftc.teamcode.Drive.AidenDriveTrain;
import org.firstinspires.ftc.teamcode.systems.AidenRobot;
import org.firstinspires.ftc.teamcode.utils.ButtonToggle;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class AidenTeleop extends LinearOpMode {
    public Gamepad gamepad1;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareQueue hardwareQueue = new HardwareQueue();
        AidenSensors sensor = new AidenSensors(hardwareMap, hardwareQueue);
        AidenRobot robot= new AidenRobot(hardwareMap);
        AidenDriveTrain driveTrain = new AidenDriveTrain(hardwareQueue, hardwareMap, sensor);
        boolean intakeClicked = false;

        ButtonToggle x = new ButtonToggle();
        ButtonToggle y = new ButtonToggle();
        ButtonToggle b = new ButtonToggle();
        ButtonToggle a = new ButtonToggle();
        ButtonToggle leftBumper = new ButtonToggle();
        ButtonToggle rightBumper = new ButtonToggle();

        while (opModeInInit()) {
            robot.deposit.reset();

            robot.update();
        }

        waitForStart();

        while (opModeIsActive()) {
            robot.driveTrain.drivetrain(gamepad1);
            if (x.isClicked(gamepad1.x)){
                robot.intake.intakeStart();
                intakeClicked = true;
            }
            if (x.isClicked(gamepad1.x )& intakeClicked){
                robot.intake.intakeOff();
                intakeClicked = false;
            }
            if(b.isClicked(gamepad1.b)){
                robot.intake.intakeReverse();
            }

        }

    }
}