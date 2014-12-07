	
package vpl.physics.controller;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import lombok.Getter;

public class PhysicsExecutionTask {
    private static PhysicsExecutionTask instance;
    
    private PhysicsExecutionTask() {
        model = Model.getInstance();
    }

    public static PhysicsExecutionTask getInstance() {
        if (instance == null) {
            instance = new PhysicsExecutionTask();
        }
        return instance;
    }
        
    ScheduledFuture<?> scheduleFuture;
    public final static int TICK_RATE_MILLISEC = 50;
    Model model;

    public void start() {
        if (scheduleFuture == null) {
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            scheduleFuture = executor.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    System.out.println("task");
                    model.getPhysics().update();
                    model.refreshView(Model.AN_RIGID_BODY_CHANGED);
                    model.setTime(model.getTime() + TICK_RATE_MILLISEC);
                }
            }, /*first execution delay*/ 0, TICK_RATE_MILLISEC, TimeUnit.MILLISECONDS);
        }
            }
           
        public void pause() {
            if (scheduleFuture != null) {
                scheduleFuture.cancel(false);
                scheduleFuture = null;
            }
        }
       
        public void stop() {
            if (scheduleFuture != null) {
                scheduleFuture.cancel(false);
                scheduleFuture = null;
            }
        }
    }
