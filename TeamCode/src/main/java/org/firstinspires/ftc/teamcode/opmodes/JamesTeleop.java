package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Drive.JamesDriveTrain;
import org.firstinspires.ftc.teamcode.systems.JamesRobot;

public class JamesTeleop extends LinearOpMode {
    public Gamepad gamepad1, gamepad2;


    @Override
    public void runOpMode() throws InterruptedException {
        JamesRobot robot = new JamesRobot(hardwareMap);
        //not sure what gamepad2 is for...
        waitForStart();

        while(opModeIsActive()){
            robot.driveTrain.drive(gamepad1);
            robot.update();
        }

    }
}
