package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.utils.Utils;

public class JamesPID {
    double kp, ki, kd;

    public JamesPID(double kp, double ki, double kd){
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    double reference = 0.0;
    double integralSum = 0.0;
    double lastError = 0.0;
    double loopTime = 0.0;
    long lastLoopTime = System.nanoTime();
    boolean first = true;

    public double getError(double error){
        if(first) lastLoopTime = System.nanoTime() - 10000000;

        //update t of the integral
        long currentTime = System.nanoTime();
        loopTime = (currentTime - lastLoopTime)/1000000000.0;
        lastLoopTime = currentTime;

        double proportion = kp * error;
        //Riemann Sum Nutshell
        integralSum += error * ki * loopTime;
        //Secant Line Approximation
        double derivative = kd * (error - lastError)/loopTime;

        lastError = error;
        first = false;

        return Utils.minMaxClip(proportion + integralSum + derivative, 0, 1);
    }

}
