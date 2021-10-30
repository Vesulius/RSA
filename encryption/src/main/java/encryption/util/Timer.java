package encryption.util;

/**
 * 
 * Simple time measuring tool
 * 
 * <p>
 * Uses System.currentTimeMillis to calculate how much time has elapsed.
 * </p>
 * 
 */
public class Timer {

    private long time;
    private long laptime;
    private int laps;

    /**
     * 
     * Starts timer
     * 
     */
    public void start() {
        this.laps = 0;
        this.time = System.currentTimeMillis();
        this.laptime = System.currentTimeMillis();
    }

    /**
     * 
     * Lap function
     * 
     * @return double time in ms since last lap or start
     */
    public double lap() {
        this.laps++;
        double returnValue = System.currentTimeMillis() - this.laptime;
        this.laptime = System.currentTimeMillis();
        return returnValue;
    }

    /**
     * 
     * Stops timer and gives time
     * 
     * @return double time since start or average lap time if laps were used
     */
    public double stop() {
        if (laps == 0) laps++;
        return 1.0 * (System.currentTimeMillis() - this.time) / laps;
    }
}
