package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.utils.Utils;

public class AidenPID {
    double kp, ki, kd;

    double reference = 0.0;
    double integralSum = 0.0;
    double lastError = 0.0;
    double loopTime = 0.0;
    long lastTime = System.nanoTime();
    boolean first = true;

    public double getError(double error){
        if(first){
            lastTime = System.nanoTime() - 10000000;
        }
        double encoderPosition = armMotor.getPosition();
        long currentTime = System.nanoTime();
        loopTime = (currentTime - lastTime)/1000000000.0;
        lastTime = currentTime;
        double derivative = kd * (error - lastError)/loopTime;
        integralSum += error * loopTime*ki;
        lastError = error;
        first = false;
        return Utils.minMaxClip(error *kp + integralSum + derivative, 0, 1);
    }
}
