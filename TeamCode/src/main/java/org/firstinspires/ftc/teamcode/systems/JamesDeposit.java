package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class JamesDeposit {
    private JamesSlides slides;
    private PriorityServo armServos, rotateServo, left, right;
    private boolean currDirectionRotated, readyToStart = false, ready = false, finished = false;
    private double holdAngle = Math.PI/2, releaseAngle = Math.PI * 3/2, armAngleUp = Math.PI, armAngleDown = 0.0, targetHeight = 1.0;

    public enum DepositStates{
        STANDBY,
        CLOSE,
        TURN,
        LIFT,
        DEPOSITReady,
        DEPOSIT
    };

    private DepositStates depositStates;

    public JamesDeposit(HardwareQueue hardwareQueue, HardwareMap hardwareMap, JamesSensors sensors){
        //v4 bar
        Servo leftServo = hardwareMap.get(Servo.class, "leftServo");
        Servo rightServo = hardwareMap.get(Servo.class, "rightServo");
        armServos = new PriorityServo(new Servo[]{leftServo, rightServo}, "servos", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0, new double[]{1.0, -1.0});

        //rotating
        Servo rotate = hardwareMap.get(Servo.class, "rotate");
        rotateServo = new PriorityServo(rotate, "rotateServo", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);

        //ball release
        Servo releaseL = hardwareMap.get(Servo.class, "releaseL");
        Servo releaseR = hardwareMap.get(Servo.class, "releaseR");
        left = new PriorityServo(releaseL, "left", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);
        right = new PriorityServo(releaseR, "right", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);

        slides = new JamesSlides(hardwareQueue, hardwareMap, sensors);

        hardwareQueue.addDevice(armServos);
        hardwareQueue.addDevice(rotateServo);
        hardwareQueue.addDevice(left);
        hardwareQueue.addDevice(right);

        currDirectionRotated = false;
        depositStates = DepositStates.STANDBY;
    }

    public void update(){
        switch(depositStates) {
            // wait for intake to be finished and set parameters
            case STANDBY:
                releaseLeftBall();
                releaseRightBall();
                setArmAngle(armAngleDown);
                slides.setTarget(0.0);

                //check if ready now
                if(readyToStart){depositStates = DepositStates.CLOSE; readyToStart = false;}
                break;
            // flip arm
            case TURN:
                setArmAngle(armAngleUp);

                //if arm angle is reached, transition to LIFT state
                if(armServos.inPosition()){
                    depositStates = DepositStates.LIFT;
                }
                break;
            // raise arm up
            case LIFT:
                slides.setTarget(targetHeight);

                if(slides.getCurrPos() == targetHeight) {depositStates = DepositStates.DEPOSITReady;}
                break;
        }
        slides.update();
    }

    public void reset(){
        depositStates = DepositStates.STANDBY;
    }

    public void startDepositSetup() {readyToStart = true;}

    public void startDepositBalls(){ready = true;}

    public void setTargetHeight(double target){targetHeight = target;}

    public void setArmAngle(double target){armServos.setTargetAngle(target, 1.0);}

    public void rotateReleaseBox(){
        if(currDirectionRotated){
            rotateServo.setTargetAngle(armAngleDown, 1.0);
        }else{
            rotateServo.setTargetAngle(armAngleUp, 1.0);
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

    public void toggleLeftFlipper(){
        if(currDirectionRotated){
            if(right.getCurrentAngle() == holdAngle){
                releaseRightBall();
            }else{
                grabRightBall();
            }
        }else{
            if(left.getCurrentAngle() == holdAngle){
                releaseLeftBall();
            }else{
                grabLeftBall();
            }
        }
    }

    public void toggleRightFlipper(){
        if(currDirectionRotated){
            if(left.getCurrentAngle() == holdAngle){
                releaseLeftBall();
            }else{
                grabLeftBall();
            }
        }else{
            if(left.getCurrentAngle() == holdAngle){
                releaseRightBall();
            }else{
                grabRightBall();
            }
        }
    }
}
