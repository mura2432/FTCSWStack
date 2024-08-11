package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.systems.JamesSensors;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class JamesDriveTrain {
    private HardwareQueue hardwareQueue;
    private JamesSensors sensors;
    private PriorityMotor leftFront, leftBack, rightFront, rightBack;

    public JamesDriveTrain(HardwareQueue hardwareQueue, HardwareMap hardwareMap, JamesSensors sensors){
        this.hardwareQueue = hardwareQueue;
        this.sensors = sensors;

        leftFront = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftFront"), "leftFront", 3, 5);
        leftBack = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftBack"), "leftBack", 3, 5);
        rightFront = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "rightFront"), "rightFront", 3, 5);
        rightBack = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "rightBack"), "rightBack", 3, 5);

        hardwareQueue.addDevice(leftFront);
        hardwareQueue.addDevice(leftBack);
        hardwareQueue.addDevice(rightFront);
        hardwareQueue.addDevice(rightBack);
    }

    public void drive(Gamepad gamepad){
        double forward = gamepad.left_stick_y * -1;
        double strafe = gamepad.left_stick_x;
        double turn = 0.75 * gamepad.right_stick_x;

        double denom = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 1.0);
        double leftFrontPower = (forward + strafe + turn)/ denom;
        double leftBackPower = (forward - strafe + turn) / denom;
        double rightFrontPower = (forward - strafe - turn) / denom;
        double rightBackPower = (forward + strafe - turn) / denom;

        setMotorPowers(leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
    }

    public void setMotorPowers(double leftFrontPower, double leftBackPower, double rightFrontPower, double rightBackPower){
        leftFront.setTargetPower(leftFrontPower);
        leftBack.setTargetPower(leftBackPower);
        rightFront.setTargetPower(rightFrontPower);
        rightBack.setTargetPower(rightBackPower);
    }
}
