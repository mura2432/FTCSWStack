package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.utils.Utils;

public class JamesFeedForward {
    public double p, s, lastError;

    public JamesFeedForward(double p, double s){
        this.p = p;
        this.s = s;
    }

    public double update(double error, double min, double max){
        lastError = error;
        return Utils.minMaxClip(p * error + s , min, max);
    }

    public void updateFeedForward(double p, double s){
        this.p = p;
        this.s = s;
    }

}
