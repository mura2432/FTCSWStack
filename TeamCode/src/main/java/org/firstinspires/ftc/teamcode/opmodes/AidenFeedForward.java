package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.utils.Utils;

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