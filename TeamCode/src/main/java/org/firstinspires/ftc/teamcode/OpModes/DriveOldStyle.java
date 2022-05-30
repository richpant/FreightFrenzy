package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "DriveOldStyle")
public class DriveOldStyle extends OpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private Servo flipper;
    private DcMotor liftMotor;
    public static final double MID_SERVO       =  0.5 ;
    public static final double LIFT_UP_POWER    =  0.45 ;
    public static final double LIFT_DOWN_POWER  = -0.45 ;
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo
    @Override
    public void init(){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        flipper = hardwareMap.get(Servo.class,"flipper");
        liftMotor = hardwareMap.get(DcMotor.class,"liftMotor");
       // rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
       // rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.addData("Say", "Hello Driver");
    }
    @Override
    public void loop() {
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        leftFront.setPower(left);
        leftRear.setPower(left);
       rightFront.setPower(right);
       rightRear.setPower(right);

        // Use gamepad left & right Bumpers to open and close the claw
        if (gamepad1.right_bumper)
            clawOffset += CLAW_SPEED;
        else if (gamepad1.left_bumper)
            clawOffset -= CLAW_SPEED;

        // Move both servos to new position.  Assume servos are mirror image of each other.
        clawOffset = Range.clip(clawOffset, -0.5, 0.5);
     flipper.setPosition(MID_SERVO + clawOffset);


        // Use gamepad buttons to move the arm up (Y) and down (A)
        if (gamepad1.y)
          liftMotor.setPower(LIFT_UP_POWER);
        else if (gamepad1.a)
            liftMotor.setPower(LIFT_DOWN_POWER);
        else
            liftMotor.setPower(0.0);

        // Send telemetry message to signify robot running;
        telemetry.addData("claw",  "Offset = %.2f", clawOffset);
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
