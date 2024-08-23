package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
@Config

public class AidenSlides{
    public enum SlidesState {
        ON,
        OFF
    };
    public PriorityMotor slidesMotor;
    public PriorityMotor slideMotor;
    public SlidesState slidesState;
    double kp = 1;
    double powerConstant = 0.1;
    double slidesPower;
    double targetPosition;
    public AidenSensors sensor;


    public AidenSlides(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        sensor = new AidenSensors(hardwareMap, hardwareQueue);
        slideMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "slides"), "slides", 2, 5, 1);
        this.slidesState = SlidesState.OFF;
        hardwareQueue.addDevice(slideMotor);
    }

    public void slidesUpdate(){
        double currentPosition = sensor.getSlides();
        switch (slidesState){
            case ON:
                slidesMotor.setTargetPower(slidesPower);
                slideMotor.setTargetPower(Utils.minMaxClip((targetPosition - currentPosition) * kp+powerConstant, -1.0, 1.0));
                break;
            case OFF:
                slideMotor.setTargetPower(0.0);
                break;
        }
    }
}
