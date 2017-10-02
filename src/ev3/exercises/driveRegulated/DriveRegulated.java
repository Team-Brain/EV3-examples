package ev3.exercises.driveRegulated;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
//import ev3.projects.library.*;

public class DriveRegulated
{
    public static void main(String[] args)
    {
    	
    	//motorC = right wheel
    	//motorB = left wheel
    	//motorA = grabbers
        System.out.println("Drive Regulated\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();    // make sound when ready.

        Button.waitForAnyPress();
        
        // create two motor objects to control the motors.
        EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);
        EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
        
        // set motors to 500 degrees/second rotation.
        motorC.setAcceleration(1000);
        motorC.setSpeed(1000);
        
        motorB.setAcceleration(1000);
        motorB.setSpeed(1000);
        
        motorC.forward();    // starts rotation.
        motorB.forward();    // starts rotation.
        
        // wait 3 seconds.
        Delay.msDelay(3000);

        // stop motors with brakes on.
        motorB.stop(true);
        motorC.stop(true);

        Button.waitForAnyPress();

        // demonstrate rotate degrees with wait. One motor runs then other.
        motorC.rotate(360);
        motorB.rotate(360);

        Button.waitForAnyPress();

        // demonstrate rotate degrees without wait. Motors run together.
        motorC.rotate(360, true);
        motorB.rotate(360, true);

        Button.waitForAnyPress();

        // demonstrate rotate to target angle without wait.
        motorC.resetTachoCount();
        motorB.resetTachoCount();
        
        motorC.rotateTo(180, true);
        motorB.rotateTo(180, true);

        Button.waitForAnyPress();

        System.out.println("tach=" + motorC.getTachoCount());
        
        Button.waitForAnyPress();
        
        // free up motor resources.
        motorC.close();
        motorB.close();
        
        Sound.beepSequence();    // we are done.
    }
}
