/**
 * The ClockDisplay class implements a digital clock display for a
 * US-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 am (midnight) to 11:59 am (one minute before 
 * noon) and 12:00 pm (noon) to 11:59 pm (one minute before midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 *
 *TLDR: store american time, display american time 
 *
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String merridianString;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        merridianString = "A.M.";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        merridianString = "A.M.";
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if(hours.getValue() == 0) {
                
                //allows us to keep track of AM/PM cycle when minutes tick houts from 11 to 0
                if(merridianString.equals("A.M.")) 
                {
                    merridianString = "P.M.";
                }
                else 
                {
                    merridianString = "A.M.";
                }
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour % 12);
        minutes.setValue(minute);
        if(hour < 12) // set merridian based of given time
        {
            merridianString = "A.M.";
        }
        else 
        {
            merridianString = "P.M.";
        }
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        int tempHourValue = hours.getValue();
        if (tempHourValue == 0)
        {
            tempHourValue = 12;
        }
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue()+ " " +
                        merridianString;
    }
}
