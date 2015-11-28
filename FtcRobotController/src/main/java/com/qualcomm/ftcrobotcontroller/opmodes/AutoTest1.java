package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by etiennelunetta on 11/28/15.
 */
public class AutoTest1 extends OpMode {

    //editable distances referenced to by all Autonomous codes

    public long timeA = 0;              //first distance for position 2
    public double timeB = 0.0;              //first distance for position 1
    public double timeC = 0.0;              //extra distance is going to far mountain
    public double timeD = 0.0;              //final distance for al all autonomous to mountain

    public double speed = 0.08;             //speed during straight line driving
    public double targetHeading = 0.0;      //constant gyro angle
    public double gain = 0.007;             //gain for correcting error

    //Non editable variables
    private double steeringError;
    private double leftPower;
    private double rightPower;
    private int currentHeading = 0;
    private double steeringAdjustment = 0;
    DcMotor rightMotor;
    DcMotor leftMotor;
    DcMotor upDown;
    DcMotor climb1;
    DcMotor climb2;
    DcMotor rotation;
    Servo claw;
    Servo arm;
    GyroSensor sensorGyro;

    //DcMotor upDown;
    //Servo rotation;

    @Override
    public void init(){

        declarations();

    }

    public void runOpMode() throws InterruptedException {

        gyroInit();

    }

    @Override
    public void loop(){

        autonomousLoop1();

        straightLineFunctionality();

        telemetry.addData("time", "elapsed time: " + Double.toString(this.time));
        telemetry.addData("1. h", String.format("%03d", currentHeading));
    }


      //+++++++++$$$$$$$$$$+++++++++//
     //_______//FUNCTIONS//________//
    //+++++++++$$$$$$$$$$+++++++++//
    public void declarations(){

            rightMotor = hardwareMap.dcMotor.get("m1");
            leftMotor = hardwareMap.dcMotor.get("m2");
            climb1 = hardwareMap.dcMotor.get("m4");
            climb2 = hardwareMap.dcMotor.get("m5");
            climb1 = climb2;
    }

    public void gyroInit() throws InterruptedException {

        // write some device information (connection info, name and type)
        // to the log file.
        hardwareMap.logDevices();

        // get a reference to our GyroSensor object.
        sensorGyro = hardwareMap.gyroSensor.get("gyro");

        // calibrate the gyro.
        sensorGyro.calibrate();

        // make sure the gyro is calibrated.
        while (sensorGyro.isCalibrating()) {
            Thread.sleep(50);
        }
    }

    public void autonomousLoop1(){

        if (this.time <= timeA) {

        } else if (this.time > timeA && this.time <= timeB + timeA) {
            straightLineFunctionality();
        } else if (this.time > timeB && this.time <= timeA + timeB + timeC) {
            turn135();
        } else if (this.time > timeC && this.time <= timeA + timeB + timeC + timeD) {
            straightLineFunctionality();
        }else {
            stop();
        }
        /**
        } else if (this.time > 20 && this.time <= 25) {
            NegativeTurn135();
        }else if (this.time > 20 && this.time <= 30) {
            NegativeTurn45();
        } else if (this.time > 20 && this.time <= 30) {
            NegativeTurn90();
        } else {
            stop();
        } **/






    }

    public void straightLineFunctionality(){

        currentHeading = sensorGyro.getHeading();
        if (currentHeading > 180){
            currentHeading -= 360;
        }

        steeringError = currentHeading - targetHeading;

        steeringAdjustment = steeringError * gain;

        rightPower = (speed - steeringAdjustment);
        leftPower = (speed + steeringAdjustment);

        if (rightPower < 0.0) {
            rightPower = 0.0;
        }
        if (leftPower < 0.0) {
            leftPower = 0.0;
        }
        if (rightPower > 1.0) {
            rightPower = 1.0;
        }
        if (leftPower > 1.0) {
            leftPower = 1.0;
        }



        rightMotor.setPower(rightPower);
        leftMotor.setPower(leftPower);


            sensorGyro.resetZAxisIntegrator();

    }

    public void turningFunctionality(){

        currentHeading = sensorGyro.getHeading();
        if (currentHeading > 180){
            currentHeading -= 360;
        }

        steeringError = currentHeading - targetHeading;

        steeringAdjustment = steeringError * gain;

        rightPower = (steeringAdjustment);
        leftPower = (steeringAdjustment);

        if (rightPower < 0.0) {
            rightPower = 0.0;
        }
        if (leftPower < 0.0) {
            leftPower = 0.0;
        }
        if (rightPower > 1.0) {
            rightPower = 1.0;
        }
        if (leftPower > 1.0) {
            leftPower = 1.0;
        }



        rightMotor.setPower(rightPower);
        leftMotor.setPower(leftPower);


        sensorGyro.resetZAxisIntegrator();

    }

    public void turn45(){
        targetHeading = currentHeading + 45;
        straightLineFunctionality();

    }
    public void turn90(){
        targetHeading = currentHeading + 90;
        straightLineFunctionality();
    }
    public void turn135(){
        targetHeading = currentHeading + 135;
        straightLineFunctionality();
    }
    public void NegativeTurn45(){
        targetHeading = currentHeading - 45;
        straightLineFunctionality();

    }
    public void NegativeTurn90(){
        targetHeading = currentHeading - 90;
        straightLineFunctionality();
    }
    public void NegativeTurn135(){
        targetHeading = currentHeading - 135;
        straightLineFunctionality();
    }
}







