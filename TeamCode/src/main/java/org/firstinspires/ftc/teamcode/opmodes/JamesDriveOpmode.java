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
    //PID Implementation in an OpMode
    public void runOpMode() {
        Gamepad gp = new Gamepad();

        ArrayList<PriorityMotor> motors = new ArrayList<>();
        HardwareQueue hq = new HardwareQueue();

        PriorityMotor flMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "flMotor"), "flMotor", 3, 5);
        PriorityMotor frMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "frMotor"), "frMotor", 3, 5);
        PriorityMotor blMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "blMotor"), "blMotor", 3, 5);
        PriorityMotor brMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "brMotor"), "brMotor", 3, 5);

        JamesPID pid = new JamesPID(0.25, 0.37, 0.56);
        double target = 9.16;

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
            double flError = target - flMotor.motor[0].getCurrentPosition();
            double blError = target - blMotor.motor[0].getCurrentPosition();
            double frError = target - frMotor.motor[0].getCurrentPosition();
            double brError = target - brMotor.motor[0].getCurrentPosition();

            flMotor.setTargetPower(pid.getError(flError));
            blMotor.setTargetPower(pid.getError(blError));
            frMotor.setTargetPower(pid.getError(frError));
            brMotor.setTargetPower(pid.getError(brError));
        }
    }

    //teleOp + hardwareQueue homework
    public void runTeleOp(){
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