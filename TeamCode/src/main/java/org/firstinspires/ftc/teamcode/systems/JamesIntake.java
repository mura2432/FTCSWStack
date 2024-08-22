package org.firstinspires.ftc.teamcode.systems;

import static java.lang.Math.max;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class JamesIntake {
    private JamesSlides slides;
    private PriorityServo rotateServo, left, right;
    private boolean currDirectionRotated, readyToGrab = false;
    private double holdAngle = Math.PI/2, releaseAngle = Math.PI * 3/2;

    public JamesIntake(HardwareQueue hardwareQueue, HardwareMap hardwareMap, JamesSensors sensors){
        Servo rotate = hardwareMap.get(Servo.class, "rotate");
        rotateServo = new PriorityServo(rotate, "rotateServo", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);

        Servo releaseL = hardwareMap.get(Servo.class, "releaseL");
        Servo releaseR = hardwareMap.get(Servo.class, "releaseR");
        left = new PriorityServo(releaseL, "left", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);
        right = new PriorityServo(releaseR, "right", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);

        slides = new JamesSlides(hardwareQueue, hardwareMap, sensors);

        hardwareQueue.addDevice(left);
        hardwareQueue.addDevice(right);

        currDirectionRotated = false;

    }

    public void update(){

    }

    public void rotateReleaseBox(){
        if(currDirectionRotated){
            rotateServo.setTargetAngle(0.0, 1.0);
        }else{
            rotateServo.setTargetAngle(Math.PI, 1.0);
        }
        currDirectionRotated = !currDirectionRotated;
    }

    public void releaseLeftBall(){
        if(currDirectionRotated){
            right.setTargetAngle(releaseAngle, 1.0);
        }else{
            left.setTargetAngle(releaseAngle, 1.0);
        }
    }

    public void releaseRightBall(){
        if(currDirectionRotated){
            left.setTargetAngle(releaseAngle, 1.0);
        }else{
            right.setTargetAngle(releaseAngle, 1.0);
        }
    }

    public void grabLeftBall(){
        if(currDirectionRotated){
            right.setTargetAngle(holdAngle, 1.0);
        }else{
            left.setTargetAngle(holdAngle, 1.0);
        }
    }

    public void grabRightBall(){
        if(currDirectionRotated){
            left.setTargetAngle(holdAngle, 1.0);
        }else{
            right.setTargetAngle(holdAngle, 1.0);
        }
    }
}
