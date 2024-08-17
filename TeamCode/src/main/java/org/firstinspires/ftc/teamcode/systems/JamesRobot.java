package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Drive.JamesDriveTrain;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class JamesRobot {
    public HardwareQueue hardwareQueue;
    public JamesIntake intake;
    public JamesSlides slides;
    public JamesSensors sensors;
    public JamesDeposit deposit;
    public JamesDriveTrain driveTrain;
    public Gamepad gamepad;

    public JamesRobot(HardwareMap hardwareMap){
        hardwareQueue = new HardwareQueue();

        gamepad = new Gamepad();
        sensors = new JamesSensors(hardwareMap, hardwareQueue, this);

        driveTrain = new JamesDriveTrain(hardwareQueue, hardwareMap, sensors);

        intake = new JamesIntake(hardwareQueue, hardwareMap);
        slides = new JamesSlides(hardwareQueue, hardwareMap, sensors);
        deposit = new JamesDeposit(hardwareQueue, hardwareMap, sensors);
    }

    public void update(){
        sensors.update();
        hardwareQueue.update();
        intake.update();
        deposit.update();
    }

}
