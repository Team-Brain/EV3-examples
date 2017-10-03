package ev3.exercises.IRColorGrab;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;
import ev3.exercises.library.ColorSensor;
import ev3.exercises.library.IRSensor;
import ev3.exercises.library.Lcd;



public class IRColorGrab {
	public static void main(String[] args)
    {
		float					range = 0, prev=0;
        IRSensor    			irs 	= new IRSensor(SensorPort.S4);
        ColorSensor    			color 	= new ColorSensor(SensorPort.S3);
        EV3MediumRegulatedMotor motorA 	= new EV3MediumRegulatedMotor(MotorPort.A);
        EV3LargeRegulatedMotor 	motorB 	= new EV3LargeRegulatedMotor(MotorPort.B);
        EV3LargeRegulatedMotor 	motorC 	= new EV3LargeRegulatedMotor(MotorPort.C);
        
        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();    // make sound when ready.

        Button.waitForAnyPress();
        
        range = setup(color, motorA, motorB, motorC, irs, 500);
        
        findObject(range, motorB, motorC, irs);
        
        if(checkColor(color, "White"))
        {
        	pickup(motorA, 600);
        
        	turn(motorB, motorC, 900, "left");
        
        	Delay.msDelay(1000);
        
        	pickup(motorA, -600);
        }
        
        // free up resources.
        free(color, motorA, motorB, motorC, irs);
                
        Sound.beepSequence();    // we are done!
        Button.waitForAnyPress();
    
    }
	static float setup(ColorSensor color, EV3MediumRegulatedMotor motorA, EV3LargeRegulatedMotor motorB, EV3LargeRegulatedMotor motorC, IRSensor irs,int speed)
    {
    	float range;
		color.setColorIdMode();
        color.setFloodLight(false);
        
        motorA.setSpeed(speed);
        motorB.setSpeed(speed);
        motorC.setSpeed(speed);
        
        range = irs.getRange();
        Lcd.print(7, "range=");
        return range;
    }
	static void findObject(float range, EV3LargeRegulatedMotor motorB, EV3LargeRegulatedMotor motorC, IRSensor irs)
	{
		float prev=0;
		motorB.forward();
        motorC.forward();
        
        while (range > 0)
        {
            Lcd.clear(7, 7, 10);
            Lcd.print(7, 7, "%.2f", range);
            Delay.msDelay(200);

            range = irs.getRange();
            if(prev==0 && range==0) break;
            prev=range;
        }
        
        motorB.stop(true);
        motorC.stop(true);
        return;
	}
	static void pickup(EV3MediumRegulatedMotor motorA, int degrees)
	{
		motorA.rotate(degrees);
		return;
	}
	static void turn(EV3LargeRegulatedMotor motorB, EV3LargeRegulatedMotor motorC, int degrees, String direction)
	{
		if(direction=="left")
		{
			motorB.rotate(-degrees, true);
			motorC.rotate(degrees);
		}
		else if(direction=="right")
		{
			motorB.rotate(degrees, true);
			motorC.rotate(-degrees);
		}
		return;
	}
	static void free(ColorSensor color, EV3MediumRegulatedMotor motorA, EV3LargeRegulatedMotor motorB, EV3LargeRegulatedMotor motorC, IRSensor irs)
	{
		irs.close();
    	color.close();
    	motorA.close();
    	motorB.close();
    	motorC.close();
    	return;
	}
	static boolean checkColor(ColorSensor color, String wantedColor)
	{
		boolean rightColor=false;
		String readColor = ColorSensor.colorName(color.getColorID());
		if(readColor==wantedColor)
		{
			rightColor=true;
			Lcd.clear(7);
			Lcd.clear(8);
        	Lcd.print(7, "The color is right!");
        	Lcd.print(8, "It's %s", readColor);
		}
		else
		{
			Lcd.clear(7);
			Lcd.clear(8);
			Lcd.print(7,  "The color is not right!");
			Lcd.print(8, "It's %s", readColor);
		}
		return rightColor;
	}
}
