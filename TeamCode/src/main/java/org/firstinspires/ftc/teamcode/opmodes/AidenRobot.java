package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.AidenDriveTrain;
import org.firstinspires.ftc.teamcode.systems.AidenSensors;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class AidenRobot{
    public HardwareMap hardwareMap = new HardwareMap();
    public AidenSensors sensors;
    public AidenDriveTrain drivetrain;
    public AidenIntake intake;
    public AidenSlides slides;

    public HardwareQueue hardwareQueue = new HardwareQueue();\

    public AidenRobot(HardwareMap hardwareMap) {
       this.hardwareMap = hardwareMap;
        sensors = new AidenSensors();
        drivetrain = new AidenDriveTrain();
        intake = new AidenIntake();
        slides - new AidenSlides();
    }
    public void update(){
        hardwareQueue.update();
        intake.updateIntake();
        slides.slidesUpdate();
    }
}