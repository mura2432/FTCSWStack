package org.firstinspires.ftc.teamcode.systems;

import org.firstinspires.ftc.teamcode.systems.AidenIntake;
import org.firstinspires.ftc.teamcode.systems.AidenSlides;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AidenDeposit {
    public HardwareQueue hardwareQueue;
    public HardwareMap hardwareMap;
    public AidenRevolveArm aidenRevolveArm;
    public AidenV4Arm aidenV4Arm;
    public AidenFlippers aidenFlippers;
    public AidenSlides slides;
    public AidenDeposit(HardwareQueue hardwareQueue, HardwareMap hardwareMap){
        aidenRevolveArm = new AidenRevolveArm(hardwareQueue, hardwareMap);
        aidenV4Arm = new AidenV4Arm(hardwareQueue, hardwareMap);
        aidenFlippers = new AidenFlippers(hardwareQueue, hardwareMap);
        slides = new AidenSlides(hardwareMap, hardwareQueue);
    }
    public enum DepositState{
        BASE,
        GRAB,
        LIFT,
        UP,
        DOWN,
        FLIP,
        LEFTRELEASE,
        RIGHTRELEASE,
        RESET

    }
    AidenDeposit.DepositState depositState = DepositState.BASE;
    public void DepositUpdate(){
        switch (depositState){
            case BASE:
                aidenFlippers.leftOpen();
                aidenFlippers.rightOpen();
                aidenV4Arm.unrotate();
                slides.slidesUpdate();
                break;
            case GRAB:
                aidenFlippers.leftClose();
                aidenFlippers.rightClose();
                break;
            case LIFT:
                aidenV4Arm.rotate();
                break;
            case UP:
                slides.slidesUpdate();
                break;
            case DOWN:
                slides.slidesUpdate();
                break;
            case FLIP:
                aidenRevolveArm.flip();
                break;
            case LEFTRELEASE:
                aidenFlippers.leftOpen();
                break;
            case RIGHTRELEASE:
                aidenFlippers.rightOpen();
                break;
        }
    }
}
