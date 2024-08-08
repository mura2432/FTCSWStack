package org.firstinspires.ftc.teamcode.systems;

public class DanielPID {
	public double kp;
	public double ki;
	public double kd;

	private double integral;
	private long lastLoopTime;
	private double lastError;
	private boolean firstLoop = true;

	public DanielPID(double p, double i, double d) {
		setPIDConstants(p, i, d);
	}

	public double update(double error) {
		long currentTime = System.nanoTime();

		double derivative = 0;

		if (firstLoop) {
			integral = 0;
			firstLoop = false;
		} else {
			double loopTime = (currentTime - lastLoopTime) / 1.0e9; // Convert from nanoseconds to seconds
			integral += error * loopTime;
			derivative = (error - lastError) / loopTime;
		}

		lastError = error;
		lastLoopTime = currentTime;

		return error * kp + integral * ki + derivative * kd;
	}

	public void resetIntegral() { integral = 0; }

	public void setPIDConstants(double p, double i, double d) {
		kp = p;
		ki = i;
		kd = d;
	}
}
