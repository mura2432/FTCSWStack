package org.firstinspires.ftc.teamcode.systems;

import static org.firstinspires.ftc.teamcode.utils.priority.PriorityServo.ServoType.SPEED;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class AidenV4Arm {
    public Servo leftv4arm;
    public Servo rightv4arm;
    public PriorityServo leftarm,rightarm;
    public AidenV4Arm(HardwareQueue hardwareQueue, HardwareMap hardwareMap){
        leftv4arm = hardwareMap.get(Servo.class, "v4arm");
        rightv4arm = hardwareMap.get(Servo.class, "v4arm");
        leftarm = new PriorityServo(leftv4arm, "arm", SPEED, 1, 0, 1,0, false, 2, 5);
        rightarm = new PriorityServo(rightv4arm, "arm", SPEED, 1, 0, 1,0, false, 2, 5);
    }
    public void rotate(){
        leftarm.setTargetAngle(Math.PI*3/2, 1.0);
        rightarm.setTargetAngle(Math.PI*3/2, -1.0);
    }
    public void unrotate(){
        leftarm.setTargetAngle(0, 1.0);
        rightarm.setTargetAngle(0, -1.0);
    }
}
