package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.ftcrobotcontroller.opmodes.CircleGyro;

/**
 * Created by etiennelunetta on 11/14/15.
 */
public class C4TankDrive extends OpMode {

    DcMotor right;
    DcMotor left;


    @Override
    public void init(){

        right = hardwareMap.dcMotor.get("m1");
        left = hardwareMap.dcMotor.get("m2");
        left.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop(){
    // write the values to the motors
        right.setPower(-gamepad1.right_stick_y);
        left.setPower(-gamepad1.left_stick_y);



    }




}
