package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;

@Config
public class AidenFeedForward {
    double p;
    double s;
    double lastError = 0;

    public double update(double error){
        double proportion = p * error;
        double ks = s;

        lastError = error;

        return proportion + ks;
    }

    public void updatePID(double p, double s) {
        this.p = p;
        this.s = s;
    }
}