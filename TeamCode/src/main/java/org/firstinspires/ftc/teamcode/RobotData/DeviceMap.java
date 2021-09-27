package org.firstinspires.ftc.teamcode.RobotData;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DeviceMap {
    public DcMotor leftFront = null;
    public DcMotor leftRear = null;
    public DcMotor rightRear = null;
    public DcMotor  rightFront = null;
    public Servo flipper =null;
    public DistanceSensor distanceSensor;
    public DcMotor liftMotor =null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double LIFT_UP_POWER    =  0.45 ;
    public static final double LIFT_DOWN_POWER  = -0.45 ;
    HardwareMap hwMap =null;
    private ElapsedTime period = new ElapsedTime();

public DeviceMap(){}

public void init(HardwareMap hwMap)
{
    leftFront  = hwMap.get(DcMotor.class,"leftFront");
    leftRear    = hwMap.get(DcMotor.class,"leftRear");
    rightFront  = hwMap.get(DcMotor.class,"rightFront");
    rightRear   = hwMap.get(DcMotor.class,"rightRear");
    liftMotor   =hwMap.get(DcMotor.class,"liftMotor");
    rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
    rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

    Rev2mDistanceSensor sensorTOF = (Rev2mDistanceSensor) distanceSensor;

    leftFront.setPower(0);
    leftRear.setPower(0);
    rightFront.setPower(0);
    rightRear.setPower(0);
    liftMotor.setPower(0);

    leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    flipper = hwMap.get(Servo.class,"flipper");

   flipper.setPosition(MID_SERVO);

}
}
