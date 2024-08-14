package org.firstinspires.ftc.teamcode.opmodes;

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
    double slidesPower = 1.0;
    double targetPosition;


    public AidenSlides(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        slideMotor = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "slides"), "slides", 2, 5, 1);
        this.slidesState = SlidesState.OFF;
        hardwareQueue.addDevice(slideMotor);
    }

    public void slidesUpdate(){
        switch (slidesState){
            case ON:
                double currentPosition = slidesMotor.motor[0].getCurrentPosition();
                slidesMotor.setTargetPower(slidesPower);
                slideMotor.setTargetPower(Utils.minMaxClip((targetPosition - currentPosition) * kp+powerConstant, -1.0, 1.0));
                break;
            case OFF:
                slideMotor.setTargetPower(0.0);
                break;
        }
    }
}
