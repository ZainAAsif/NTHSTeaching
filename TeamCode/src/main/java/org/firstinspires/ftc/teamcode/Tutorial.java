package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


// This defines the program as a TeleOp program instead of an Autonomous one.

@TeleOp

/*
  This is making the class Tutorial (this entire file) as a public class, which means it is visible
  to any other file in the entire program.

  It makes the class inherit characteristics from the "LinearOpMode" class that was written by FTC.
  You'll be using "LinearOpMode" for anything that is actually a teleop or autonomous program.
 */
public class Tutorial extends LinearOpMode {

    //These two lines define what type of object we're creating, as well as what we call it for the rest of the file.

    private DcMotor TestMotor;
    private Servo TestServo;

    /*
      "runOpMode" here is a Method. It's essentially another program written somewhere else in the files.

      Every time you call the "LinearOpMode" method, it requires you to have the "runOpMode" method.

      It needs to be overridden as the premade FTC code creates it as a method, but you need your code to run
      instead of what is in the method already.
     */

    @Override
    public void runOpMode() throws InterruptedException {

        /*
        These two lines of code map the named motor/servo to the actual hardware type they are. This defines
        what commands they can take, like setting power for motors or setting position for servos.
         */

        TestMotor = hardwareMap.get(DcMotor.class, "TestMotor");
        TestServo = hardwareMap.get(Servo.class, "TestServo");

        // This line serves to set the servo to it's midpoint position during initialization

        TestServo.setPosition(0.5);

        /*
        Telemetry is important. It helps you figure out what is going on with the robot, and can also
        help you find issues in your code. This is here to let you know the initialization is complete
        and it is ready for you to start the actual teleop program.

        Every time you change telemetry, if you actually want to update it on the phone, you need to
        have a telemetry.update line.
         */

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Waiting for operator to press the start button

        waitForStart();

        /*
         This is declaring a variable that is used in the next method. It's useful to declare what
         might be used throughout the whole program at the beginning, but this is just being used for
         this method, so it's here instead.
         */

        double tgtPower = 0;

        /*
         While is a type of loop that will keep repeating as long as the condition is active. In this
         case, it will keep looping as long as the opMode is active.
         */

        while (opModeIsActive()){

            /*
            This sets the power of the "tgtPower" variable to be equal to the y-axis of the left
            stick of gamepad 1.

            It then sets the power of the motor to be equal to "tgtPower"

            I did this so we could see if there was any issue with the input device (gamepad) or
            motor using telemetry.
             */

            tgtPower = gamepad1.left_stick_y;

            TestMotor.setPower(tgtPower);

            /*
            This next group of code is a series of "if" and "else if" statements.
            "if" is a condition that will run if what is in the parentheses after it is true.
            "else if" is a condition that can be used after another condition. It will be ran if the
            first one is false.

            In this case, it's looking for x to be pressed on gamepad 1. If it is, it will move the
            servo to the 0 position.

            If it is not, then it will move to check the next condition, and so on.
            It's set up to move to various positions when the X, Y, A, and B buttons are pressed.
             */

            if (gamepad1.x){
                TestServo.setPosition(0);
            } else if (gamepad1.x) {
                TestServo.setPosition(0.33);
            } else if (gamepad1.b) {
                TestServo.setPosition((0.66));
            } else if (gamepad1.y) {
                TestServo.setPosition(1);
            }

            /*
            The telemetry is currently set up to display a few helpful things on the driver station.
            It will tell you the program's status, whether the motor is actually running at the
            target power, and what position the servo is in.
             */

            telemetry.addData("Status","Running");
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", TestMotor.getPower());
            telemetry.addData("Servo Position", TestServo.getPosition());
            telemetry.update();

        }

    }
}
