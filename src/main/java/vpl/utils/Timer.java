/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.utils;

/**
 *
 * @author krzysztof
 */
public class Timer { //class designed "static"

    private static long delta = 0;
    private static long lastTime = 0;
    private static long time = 0;

    private Timer() {//empty no-parameter constructor to disable creating objects of Timer 
    }

    public static double getDelta() {
        delta = time - lastTime;
        return delta;
    }

    public static void measureTime() {
        lastTime = time;
        time = System.currentTimeMillis();
    }

    public static long getTime() {
        measureTime();
        return time;
    }
}
