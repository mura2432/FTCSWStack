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

        //NOTE: Intake class is currenlty blank because getting to the ball(intake process)
        //      is currenlty user controlled and does not need a finite state machine

        //start deposit system
        ButtonToggle x = new ButtonToggle();
        //reset deposit system
        ButtonToggle y = new ButtonToggle();

        //toggle left flipper to grab or release
        ButtonToggle leftBumper = new ButtonToggle();
        //toggle right flipper to grab or release
        ButtonToggle rightBumper = new ButtonToggle();

        //rotate box
        ButtonToggle a = new ButtonToggle();
        //slides height
        ButtonToggle hat = new ButtonToggle();

        //confirm robot is in reset state
        while (opModeInInit()) {
            robot.deposit.reset();

            robot.update();
        }

        waitForStart();

        //honestly with all the functions i wrote in intake and deposit classes
        //i may just call em directly if its not auto. if i do what's the disadvantage of doing so?

        while(opModeIsActive()){
            robot.driveTrain.drive(gamepad1);

            if(x.isClicked(gamepad1.x)){
                robot.deposit.startDepositSetup();
            }

            if(y.isClicked(gamepad1.y)){
                robot.deposit.reset();
            }

            if(leftBumper.isClicked(gamepad1.left_bumper)){
                robot.deposit.toggleLeftFlipper();
            }

            if(rightBumper.isClicked(gamepad1.left_bumper)){
                robot.deposit.toggleRightFlipper();
            }

            if(a.isClicked(gamepad1.a)){
                robot.intake.rotateReleaseBox();
            }

            if(hat.isHeld(gamepad1.dpad_up, 10)){
                robot.slides.setTarget(Math.min(1.0, robot.slides.getCurrPos() + 0.05));
            }

            if(hat.isHeld(gamepad1.dpad_down, 10)){
                robot.slides.setTarget(Math.max(0.0, robot.slides.getCurrPos() - 0.05));
            }

            robot.update();
        }

    }
}
