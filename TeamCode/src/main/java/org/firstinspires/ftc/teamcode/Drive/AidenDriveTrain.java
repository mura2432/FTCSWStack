package org.firstinspires.ftc.teamcode.Drive;

import android.renderscript.RenderScript;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.systems.AidenSensors;
import org.firstinspires.ftc.teamcode.systems.JamesSensors;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;


public class AidenDriveTrain{
    public HardwareQueue hardwareQueue;
    public HardwareMap hardwareMap;
    public AidenSensors sensor;
    public PriorityMotor leftF, leftB, rightF, rightB;

    public AidenDriveTrain(HardwareQueue hardwareQueue, HardwareMap hardwareMap, AidenSensors sensors){
        this.hardwareQueue = hardwareQueue;
        this.sensor = sensor;

        leftF = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftFront"), "leftFront", 3, 5);
        leftB = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftBack"), "leftBack", 3, 5);
        rightF = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "rightFront"), "rightFront", 3, 5);
        rightB = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "rightBack"), "rightBack", 3, 5);

        hardwareQueue.addDevice(leftF);
        hardwareQueue.addDevice(leftB);
        hardwareQueue.addDevice(rightF);
        hardwareQueue.addDevice(rightB);
    }
    public void drivetrain(Gamepad gamepad){
        double forward = gamepad.left_stick_y * -1;
        double strafe = gamepad.left_stick_x * 1.1;
        double turn = 0.9 * gamepad.right_stick_x;

        double denom = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 1.0);
        double leftFrontPower = (forward + strafe + turn)/ denom;
        double leftBackPower = (forward - strafe + turn) / denom;
        double rightFrontPower = (forward - strafe - turn) / denom;
        double rightBackPower = (forward + strafe - turn) / denom;

        leftF.setTargetPower(leftFrontPower);
        leftB.setTargetPower(leftBackPower);
        rightF.setTargetPower(rightFrontPower);
        rightB.setTargetPower(rightBackPower);
    }
}