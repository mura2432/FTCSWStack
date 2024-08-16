package org.firstinspires.ftc.teamcode.systems;

import static org.firstinspires.ftc.teamcode.utils.priority.PriorityServo.ServoType.SPEED;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class AidenFlippers {
    public Servo leftFlipper;
    public Servo rightFlipper;
    public PriorityServo left, right;
    public AidenFlippers(HardwareQueue hardwareQueue, HardwareMap hardwareMap){
        leftFlipper = hardwareMap.get(Servo.class, "leftFlipper");
        rightFlipper = hardwareMap.get(Servo.class, "rightFlipper");
        left = new PriorityServo(leftFlipper, "leftFlipper", SPEED, 1, 0, 1,0, false, 2, 5);
        right = new PriorityServo(rightFlipper, "rightFlipper", SPEED, 1, 0, 1,0, false, 2, 5);
    }
    public void leftOpen(){
        left.setTargetAngle(Math.PI/2,1.0);
    }
    public void leftClose(){
        left.setTargetAngle(0,1.0);
    }
    public void rightOpen(){
        right.setTargetAngle(Math.PI/2,1.0);
    }
    public void rightClose(){
        right.setTargetAngle(0,1.0);
    }
}
