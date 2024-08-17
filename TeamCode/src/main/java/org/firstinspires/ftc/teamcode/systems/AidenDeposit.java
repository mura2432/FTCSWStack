package org.firstinspires.ftc.teamcode.systems;

import org.firstinspires.ftc.teamcode.opmodes.AidenIntake;
import org.firstinspires.ftc.teamcode.opmodes.AidenSlides;
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
    public AidenDeposit(HardwareQueue hardwareQueue, HardwareMap hardwareMap){
        aidenRevolveArm = new AidenRevolveArm(hardwareQueue, hardwareMap);
        aidenV4Arm = new AidenV4Arm(hardwareQueue, hardwareMap);
        aidenFlippers = new AidenFlippers(hardwareQueue, hardwareMap);
    }
    public enum DepositState{
        LeftFlipperUp,
        LeftFlipperDown,
        RightFlipperUp,
        RightFlipperDown,
        WristFlip,
        WristUnflip,
        ArmUp,
        ArmDown,
        OFF
    }
    AidenDeposit.DepositState depositState = DepositState;
    public void DepositUpdate(){
        switch (depositState){
            case OFF:
                break;
            case LeftFlipperUp:
                aidenFlippers.leftOpen();
                break;
            case LeftFlipperDown:
                aidenFlippers.leftClose();
                break;
            case RightFlipperUp:
                aidenFlippers.rightOpen();
                break;
            case RightFlipperDown:
                aidenFlippers.rightClose();
                break;
            case WristFlip:
                aidenRevolveArm.flip();
                break;
            case WristUnflip:
                aidenRevolveArm.unflip();
                break;
            case ArmUp:
                aidenV4Arm.rotate();
                break;
            case ArmDown:
                aidenV4Arm.unrotate();
                break;
        }
    }
}
