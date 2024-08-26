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
        int depo = 1;

        ButtonToggle x = new ButtonToggle();
        ButtonToggle y = new ButtonToggle();
        ButtonToggle b = new ButtonToggle();
        ButtonToggle a = new ButtonToggle();
        ButtonToggle leftBumper = new ButtonToggle();
        ButtonToggle rightBumper = new ButtonToggle();
        ButtonToggle leftTrigger = new ButtonToggle();
        ButtonToggle rightTrigger = new ButtonToggle();

        while (opModeInInit()) {
            robot.deposit.reset();

            robot.update();
        }

        waitForStart();

        while (opModeIsActive()) {
            robot.driveTrain.drivetrain(gamepad1);
            if (x.isClicked(gamepad1.x) & !intakeClicked){
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
            if(y.isClicked(gamepad1.y)){
                robot.slides;
                //for slides to go up
            }
            if(a.isClicked(gamepad1.a)){
                robot.slides;
                //for slide to go down
            }
            if(leftBumper.isClicked(gamepad1.left_bumper)){
                robot.deposit.leftR();

            }
            if(rightBumper.isClicked(gamepad1.right_bumper)){
                robot.deposit.rightR();

            }
            if(leftTrigger.isClicked(gamepad1.left_trigger)& depo == 3){
                robot.deposit.base();
                depo = 1;
            }
            if(leftTrigger.isClicked(gamepad1.left_trigger)& depo == 1){
                robot.deposit.grab();
                depo += 1;
            }
            if(leftTrigger.isClicked(gamepad1.left_trigger)& depo == 2){
                robot.deposit.lift();
                depo += 1;
            }
            if(rightTrigger.isClicked(gamepad1.right_trigger)){
                robot.deposit.flip();
            }
        }

    }
}