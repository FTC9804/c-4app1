package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by etiennelunetta on 11/14/15.
 */
public class C4DisplayBot extends OpMode {


    double RotationPosition;
    Servo Rotation;
    DcMotor right;
    DcMotor left;
    DcMotor upDown;

    final static double ROTATION_MIN_RANGE  = 0.20;
    final static double ROTATION_MAX_RANGE  = 0.90;

    @Override
    public void init(){

        Rotation = hardwareMap.servo.get("s1");
        right = hardwareMap.dcMotor.get("m1");
        left = hardwareMap.dcMotor.get("m2");
        upDown = hardwareMap.dcMotor.get("m3");

        RotationPosition = 0.5;

        Rotation.setPosition(RotationPosition);
        left.setDirection(DcMotor.Direction.REVERSE);


    }

    @Override
    public void loop(){

        // write the values to the motors
        right.setPower(-gamepad1.right_stick_y);
        left.setPower(-gamepad1.left_stick_y);

        upDown.setPower(gamepad2.left_stick_y);


        if (gamepad2.a) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            RotationPosition = 0.7;

            Rotation.setPosition(RotationPosition);

        }else if (gamepad2.y){

            RotationPosition = 0.3;

            Rotation.setPosition(RotationPosition);

        } else if (gamepad2.x) {

            RotationPosition = 0.5;

            Rotation.setPosition((RotationPosition));

        }

        // clip the position values so that they never exceed their allowed range.
        RotationPosition = Range.clip(RotationPosition, ROTATION_MIN_RANGE, ROTATION_MAX_RANGE);

        Rotation.setPosition(RotationPosition);


    }


}
