package org.firstinspires.ftc.teamcode.systems;

import static org.firstinspires.ftc.teamcode.utils.priority.PriorityServo.ServoType.SPEED;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class AidenRevolveArm {
    public Servo revolveArm;
    public PriorityServo revolve;
    public AidenRevolveArm(HardwareQueue hardwareQueue, HardwareMap hardwareMap){
        revolveArm = hardwareMap.get(Servo.class, "revolveArm");
        revolve = new PriorityServo(revolveArm, "revolve", SPEED, 1, 0, 1,0, false, 2, 5);
    }
    public void flip(){
        revolve.setTargetAngle(Math.PI, 1.0);
    }
    public void unflip(){
        revolve.setTargetAngle(Math.PI, 1.0);
    }
}
