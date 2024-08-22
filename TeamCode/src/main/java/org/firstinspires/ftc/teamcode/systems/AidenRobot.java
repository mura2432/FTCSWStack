package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Drive.AidenDriveTrain;
import org.firstinspires.ftc.teamcode.Drive.JamesDriveTrain;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class AidenRobot{
    public HardwareQueue hardwareQueue;
    public AidenIntake intake;
    public AidenSlides slides;
    public AidenSensors sensor;
    public AidenDeposit deposit;
    public AidenDriveTrain driveTrain;

    public AidenRobot(HardwareMap hardwareMap){
        hardwareQueue = new HardwareQueue();
        sensor = new AidenSensors(hardwareMap, hardwareQueue);
        driveTrain = new AidenDriveTrain();
        intake = new AidenIntake(hardwareMap, hardwareQueue);
        slides = new AidenSlides(hardwareMap, hardwareQueue);
        deposit = new AidenDeposit(hardwareQueue, hardwareMap);
    }

    public void update(){
        intake.updateIntake();
        deposit.DepositUpdate();
        slides.slidesUpdate();
        sensor.updateSensors();
        driveTrain.
    }

}
}