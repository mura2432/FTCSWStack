package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class AidenRobot{
    public HardwareQueue hardwareQueue;
    public HardwareMap hardwareMap;
    public AidenIntake intake;
    public AidenSlides slides;

    public AidenRobot(){
        hardwareQueue = new HardwareQueue();
        hardwareMap = new HardwareMap();
        intake = new AidenIntake(hardwareQueue, hardwareMap);
        slides = new AidenSlides(hardwareQueue, hardwareMap);
    }
    public void update(){
        hardwareQueue.update();
        intake.updateIntake();
        slides.slidesUpdate();
    }
}