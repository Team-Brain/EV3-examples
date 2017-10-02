package ev3.exercises.driveSquare;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveSquare
{
    public static void main(String[] args)
    {
        System.out.println("Drive in a Square\nand Stop\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
        UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

        for (int i = 0; i < 4; i++)
        {
            // set motors to 50% power.
            motorC.setPower(75);
            motorB.setPower(75);

            // wait 2 seconds.
            Delay.msDelay(2000);

            // stop motors with brakes on. 
            motorC.stop();
            motorB.stop();

            // turn right by reversing the right motor.
            motorC.backward();
            motorB.forward();
 
            // make the turn.
            motorC.setPower(75);
            motorB.setPower(75);

            // adjust time to get a 90% turn.
            Delay.msDelay(1000);

            motorC.stop();
            motorB.stop();

            // set right motor back to forward motion.
            motorC.forward();
            motorB.forward();
        }

        // free up motor resources. 
        motorC.close(); 
        motorB.close();
 
        Sound.beepSequence(); // we are done.
    }
}