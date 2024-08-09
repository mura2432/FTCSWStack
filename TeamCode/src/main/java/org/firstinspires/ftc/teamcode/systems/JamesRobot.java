package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class JamesRobot {
    public HardwareQueue hardwareQueue;
    public JamesMotorIntake intake;
    public JamesSlides slides;
    public JamesSensors sensors;

    public JamesRobot(HardwareMap hardwareMap){
        hardwareQueue = new HardwareQueue();

        sensors = new JamesSensors(hardwareMap, hardwareQueue, this);

        intake = new JamesMotorIntake(hardwareQueue, hardwareMap);
        slides = new JamesSlides(hardwareQueue, hardwareMap, sensors);
    }

    public void update(){
        sensors.update();
        hardwareQueue.update();
        intake.update();
    }

}
