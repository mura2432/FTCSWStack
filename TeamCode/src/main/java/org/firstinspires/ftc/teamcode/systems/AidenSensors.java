package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import java.util.List;


public class AidenSensors {
    private LynxModule controlHub, expansionHub;
    public double slidesPos;
    private HardwareQueue hardwareQueue;

    public AidenSensors(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
    public double getSlides(){
        slidesPos = ((PriorityMotor) hardwareQueue.getDevice("slides")).motor[0].getCurrentPosition() * -1;
        return slidesPos;
    }
}
