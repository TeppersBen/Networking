package com.engine.utils;

public class TickHandler {

    /** Max amount of allowed ticks each second **/
    public static final int TICKS_EACH_SECOND = 7;

    public interface Activity {
        void process();
    }

    private Activity before;
    private Activity during;
    private Activity after;
    private int ticks = TICKS_EACH_SECOND;

    /**
     * This is a process which is executed before the thread loop cycle starts
     * @param before Activity process
     * @return TickHandler
     */
    public TickHandler setBefore(Activity before) {
        this.before = before;
        return this;
    }

    /**
     * This is a process which is executed each thread loop,
     * this depends on your ticks, 5 ticks is equals to 5 loops.
     * @param during Activity process
     * @return TickHandler
     */
    public TickHandler setDuring(Activity during) {
        this.during = during;
        return this;
    }

    /**
     * This is a process which is executed after the thread loop cycle is finished.
     * @param after Activity process
     * @return TickHandler
     */
    public TickHandler setAfter(Activity after) {
        this.after = after;
        return this;
    }

    /**
     * Set the amount of ticks during the execution cycle
     * @param ticks int
     * @return TickHandler
     */
    public TickHandler setTicks(int ticks) {
        this.ticks = ticks;
        return this;
    }

    /**
     * Executes the thread using the existing/setup fields.<br>
     * If some of these fields are not setup, this object will
     * ignore null objects.<br>
     * The default tick speed is equals to the MAX_TICK_SPEED/s.
     */
    public void execute() {
        Thread t = new Thread(()->{
            int timer = 0;
            if (before != null) before.process();
            do {
                if (during != null) during.process();
                try {
                    Thread.sleep(1000/TICKS_EACH_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while ((timer += 1) < ticks);
            if (after != null) after.process();
        });
        t.start();
    }
}
