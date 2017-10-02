package ev3.exercises.colorGrab;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;
import ev3.exercises.library.ColorSensor;
import ev3.exercises.library.Lcd;

public class ColorGrab
{
    public static void main(String[] args)
    {
        ColorSensor    color = new ColorSensor(SensorPort.S3);

        System.out.println("ColorGrab");
        Lcd.print(2, "Press to start");
        
        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();    // make sound when ready.

        Button.waitForAnyPress();
        Button.LEDPattern(0);
        
        EV3MediumRegulatedMotor motorA = new EV3MediumRegulatedMotor(MotorPort.A);
        EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
        EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(MotorPort.C);     
        
        
        // run until escape button pressed.
        
        /*while (Button.ESCAPE.isUp())
        {
            Lcd.clear(4);
            Lcd.print(4, "ambient=%.3f", color.getAmbient());
            Delay.msDelay(250);
        }

        Delay.msDelay(1000);

        color.setRedMode();
        color.setFloodLight(Color.RED);
        color.setFloodLight(true);
        
        while (Button.ESCAPE.isUp())
        {
            Lcd.clear(5);
            Lcd.print(5, "red=%.3f", color.getRed());
            Delay.msDelay(250);
        }

        Delay.msDelay(1000);

        color.setRGBMode();
        color.setFloodLight(Color.WHITE);
        
        Color rgb;
        
        while (Button.ESCAPE.isUp())
        {
            rgb = color.getColor();
            
            Lcd.clear(6);
            Lcd.print(6, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
            Delay.msDelay(250);
        }

        Delay.msDelay(1000);*/

        color.setColorIdMode();
        color.setFloodLight(false);
        
        motorA.setSpeed(500);
        motorB.setSpeed(300);
        motorC.setSpeed(300);
        
        motorB.forward();
        motorC.forward();
        
        while (ColorSensor.colorName(color.getColorID())!="White")
        {
        	Lcd.clear(7);
        	Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
        	Delay.msDelay(250);
        }
        
        motorB.stop(true);
        motorC.stop(true);
        
        motorA.rotate(500);
        Delay.msDelay(1000);
        motorA.rotate(-500);
        
        /*while (Button.ESCAPE.isUp())
        {
            Lcd.clear(7);
            Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
            Delay.msDelay(250);
        }*/

        // free up resources.
        color.close();
        motorA.close();
        motorB.close();
        motorC.close();
        
        
        Sound.beepSequence();    // we are done.

        Button.LEDPattern(4);
        Button.waitForAnyPress();
    }

}
