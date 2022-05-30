package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name= "FSL")
public class FiniteStateLiftMid extends OpMode {
    private DcMotor carry;
    private Servo bucket;

    ElapsedTime liftTimer = new ElapsedTime();

    final double DUMP_IDLE = - 0.5; //servo position to travel
    final double DUMP_DEPOSIT = 0.7; //Servo Position to Dump
    final double DUMP_TIME =.5; // time for dump instead of sleep
    final int LIFT_LOW = 0;// encoder for lower level
    final int LIFT_HIGH = 500;//encoder for high level

    public enum liftState{
        START,
        EXTEND,
        DUMP,
        RETRACT
    }

    liftState state = liftState.START;

    public void init(){
        liftTimer.reset();
        carry = hardwareMap.get(DcMotor.class, "carry");
        carry.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        carry.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bucket = hardwareMap.get(Servo.class, "bucket");

    }

    public void loop(){
        switch (state){
            case START:
                if (gamepad1.x) {
                    carry.setTargetPosition(LIFT_HIGH);
                    state = liftState.EXTEND;
                }
                break;
            case EXTEND:
                if (Math.abs(carry.getCurrentPosition() - LIFT_HIGH) < 10) {
                    bucket.setPosition(DUMP_DEPOSIT);
                    liftTimer.reset();
                    state = liftState.DUMP;
                }
                break;
            case DUMP:
                if (liftTimer.seconds() >= DUMP_TIME) {
                    bucket.setPosition(DUMP_IDLE);
                    carry.setTargetPosition(LIFT_LOW);
                    state = liftState.RETRACT;
                }
                break;
            case RETRACT:
                if (Math.abs(carry.getCurrentPosition() - LIFT_LOW) <10){
                    state = liftState.START;
                }

                //Drive stuff here
                //just to addsomethin

        }
    }
}
