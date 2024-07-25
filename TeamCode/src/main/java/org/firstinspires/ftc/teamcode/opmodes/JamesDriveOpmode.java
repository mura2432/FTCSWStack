package org.firstinspires.ftc.teamcode.opmodes;

import java.util.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
@TeleOp(name = "James")
public class JamesDriveOpmode extends LinearOpMode {
    @Override
    public void runOpMode() {
        Gamepad gp = new Gamepad();

        ArrayList<PriorityMotor> motors = new ArrayList<>();
        HardwareQueue hq = new HardwareQueue();

        PriorityMotor flMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "flMotor"), "flMotor", 3, 5);
        PriorityMotor frMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "frMotor"), "frMotor", 3, 5);
        PriorityMotor blMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "blMotor"), "blMotor", 3, 5);
        PriorityMotor brMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "brMotor"), "brMotor", 3, 5);

        motors.add(flMotor);
        motors.add(frMotor);
        motors.add(blMotor);
        motors.add(brMotor);

        for(PriorityMotor pm : motors){
            hq.addDevice(pm);
        }

        //reverse for direction
        //note use motor.[0] because could have mutliple motors, but we only have one so reference the first motor only
        flMotor.motor[0].setDirection(DcMotorSimple.Direction.REVERSE);
        blMotor.motor[0].setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(isStopRequested()) return;

        //Question! How do i implement it again?

        while(opModeIsActive()){
            double straight_y = -gp.left_stick_y;
            double strafe_x = gp.left_stick_x;
            double turn_rx = gp.right_stick_x;

            double denom = Math.max(Math.abs(straight_y) + Math.abs(strafe_x) + Math.abs(turn_rx), 1);
            double flPower = (straight_y + strafe_x + turn_rx) / denom;
            double blPower = (straight_y - strafe_x + turn_rx) / denom;
            double frPower = (straight_y - strafe_x - turn_rx) / denom;
            double brPower = (straight_y + strafe_x - turn_rx) / denom;

            flMotor.setTargetPower(flPower);
            frMotor.setTargetPower(blPower);
            blMotor.setTargetPower(frPower);
            brMotor.setTargetPower(brPower);

            hq.update();
        }
    }
}


//		First Version:
//		DcMotor fLMotor = hardwareMap.get(DcMotor.class, "fLMotor");
//		DcMotor fRMotor = hardwareMap.get(DcMotor.class, "fRMotor");
//		DcMotor bLMotor = hardwareMap.get(DcMotor.class, "bLMotor");
//		DcMotor bRMotor = hardwareMap.get(DcMotor.class, "bRMotor");
//
//		fLMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//		bLMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//		waitForStart();
//
//		while (opModeIsActive()){
//			double y = -gamepad1.left_stick_y;
//			double x = gamepad1.left_stick_x;
//
//			//only need right stick x because well ur turning so you cant exactly turn in the direction ur facing
//			double r = gamepad1.right_stick_x;
//
//			//for scaling down to 1 bc of the limiter
//			double d = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(r), 1);
//
//			fLMotor.setPower((y + x + r)/d);
//			bLMotor.setPower((y - x + r)/d);
//			fRMotor.setPower((y - x - r)/d);
//			bRMotor.setPower((y + x - r)/d);