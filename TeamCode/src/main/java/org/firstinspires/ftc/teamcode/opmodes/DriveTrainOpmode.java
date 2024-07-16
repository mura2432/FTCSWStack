package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Opmode")
public class DriveTrainOpmode extends LinearOpMode{
    @Override
    public void runOpMode() {
        DcMotor myMotor = hardwareMap.get(DcMotor.class, "myMotor");
        waitForStart();
        double x = 0;
        while (opModeIsActive()){
            myMotor.setPower(Math.sin(x));
            x += 0.01;
        }
    }
}
