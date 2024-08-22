package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class AidenSensors {
    private LynxModule controlHub, expansionHub;
    private int slidesPos;
    private HardwareQueue hardwareQueue;

    public AidenSensors(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        this.hardwareQueue = hardwareQueue;
        controlHub = hardwareMap.get(LynxModule.class, "Control Hub");
        controlHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        expansionHub = hardwareMap.get(LynxModule.class, "Expansion Hub");
        expansionHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
    }
    public void update(){
        updateControlHub();
    }

    private void updateControlHub(){
        slidesPos = ((PriorityMotor) hardwareQueue.getDevice("slides")).motor[0].getCurrentPosition() * -1;

    }

}
