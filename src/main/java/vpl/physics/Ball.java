/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

/**
 *
 * @author kppx
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import lombok.Getter;
import lombok.Setter;
import vpl.physics.shapes.Shape;

/**
 *
 * @author krzysztof
 */
public class Ball {

    @Getter @Setter private float r;
    @Getter @Setter private float x, y, z;
    @Getter @Setter private float a, b, c;
    @Getter @Setter private float xAngle, yAngle, zAngle;

    public Ball(float r, float x, float y, float z) throws Exception {
        this.r = r;
        this.x = x;
        this.y = y;
        this.z = z;
        //  init();
    }

    public Ball(float r, float x, float y, float z, float a, float b, float c,
            float xAngle, float yAngle, float zAngle) throws Exception {
        this.r = r;
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.c = c;
        this.xAngle = xAngle;
        this.yAngle = yAngle;
        this.zAngle = zAngle;
        //  init();
    }
}