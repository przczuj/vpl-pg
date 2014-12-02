/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.model;

/**
 *
 * @author krzysztof
 */
//class that contains all camera data: position, center looking point
public class VplObserver {
    static double x=0,y=0,z=0;//observer coords
    static double xl=0,yl=0,zl=-1;//looking points 
    static double xangle;//xaxis angle (horizontal, between positive part of x axis and "looking vector")
    static double yangle;//yaxis angle (vertical, between "up" and "looking vector")
}
