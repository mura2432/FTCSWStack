package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")
@TeleOp(name = "AidenDriveOpmode")
public class AidenDriveOpmode extends LinearOpMode {
	@Override
	public void runOpMode() {
		// SETUP HERE

		waitForStart();
		if (isStopRequested()) return;

		while (opModeIsActive()) {
			// MAIN LOOP HERE
		}
	}
}
