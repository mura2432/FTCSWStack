package org.firstinspires.ftc.teamcode.opmodes;

import android.widget.Button;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Drive.JamesDriveTrain;
import org.firstinspires.ftc.teamcode.systems.JamesRobot;
import org.firstinspires.ftc.teamcode.utils.ButtonToggle;

public class JamesTeleop extends LinearOpMode {
    public Gamepad gamepad1, gamepad2;


    @Override
    public void runOpMode() throws InterruptedException {
        JamesRobot robot = new JamesRobot(hardwareMap);

        //gamepad 1 - all one one controller right now due to simpler robot

        //start intake system
        ButtonToggle a = new ButtonToggle();
        //reset intake system
        ButtonToggle b = new ButtonToggle();

        //start deposit system
        ButtonToggle x = new ButtonToggle();
        //reset deposit system
        ButtonToggle y = new ButtonToggle();
        //actually drop ball
        ButtonToggle leftBumper = new ButtonToggle();

        //rotate release box
        ButtonToggle rightBumper = new ButtonToggle();

        //confirm robot is in reset state
        while (opModeInInit()) {
            robot.intake.reset();
            robot.deposit.reset();

            robot.update();
        }

        waitForStart();

        //honestly with all the functions i wrote in intake and deposit classes
        //i may just call em directly if its not auto. if i do what's the disadvantage of doing so?

        while(opModeIsActive()){
            robot.driveTrain.drive(gamepad1);

            //okay the intake sections is like really scuffed bc (1) i didnt get finite state machines back then and (2) it was for just a motor system i gotta fix this later
            if(a.isClicked(gamepad1.a)){
                //check system here
                robot.intake.ready();
            }

            if(b.isClicked(gamepad1.b)){
                //check system here
                robot.intake.reset();
            }

            //deposit section
            //initiate deposit sequence up to ball drop
            if(x.isClicked(gamepad1.x)){
                robot.deposit.startDepositSetup();
            }

            //drop the balls
            if(leftBumper.isClicked(gamepad1.left_bumper)){
                robot.deposit.startDepositBalls();
            }

            //manual reset mid system or after deposit is finished
            if(y.isClicked(gamepad1.y)){
                robot.deposit.reset();
            }

            //rotate release box button
            if(rightBumper.isClicked(gamepad1.right_bumper)){
                robot.intake.rotateReleaseBox();
            }

            robot.update();
        }

    }
}
