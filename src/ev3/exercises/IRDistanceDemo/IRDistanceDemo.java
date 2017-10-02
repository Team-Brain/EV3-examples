package ev3.exercises.IRDistanceDemo;

import ev3.exercises.library.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class IRDistanceDemo
{
    public static void main(String[] args)
    {
        float                range;
        IRSensor     irs = new IRSensor(SensorPort.S4);
        
        System.out.println("IR Demo");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();    // make sound when ready.

        Button.waitForAnyPress();
        
        range = irs.getRange();

        Lcd.print(7, "range=");

        // run until we find an obstacle within 1/4 of a meter.
        
        while (range > .25)
        {
            Lcd.clear(7, 7, 10);
            Lcd.print(7, 7, "%.3f", range);
            Delay.msDelay(500);

            range = irs.getRange();
        }
        
        // free up resources.
        irs.close();
        
        Sound.beepSequence();    // we are done.

        Lcd.clear(7, 7, 10);
        Lcd.print(7, 7, "%.3f", range);

        Button.waitForAnyPress();
    }
}