package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by etiennelunetta on 11/14/15.
 */
public class C4TankDrive extends OpMode {

    DcMotor motorRight;
    DcMotor motorLeft;


    @Override
    public void init(){

        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop(){

        double left = -gamepad1.right_stick_y;
        double right = -gamepad1.left_stick_y;

        motorLeft.setPower(left);
        motorRight.setPower(right);


    }
}
