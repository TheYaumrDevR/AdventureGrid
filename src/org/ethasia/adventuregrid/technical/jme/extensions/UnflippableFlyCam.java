package org.ethasia.adventuregrid.technical.jme.extensions;

import com.jme3.input.FlyByCamera;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

public class UnflippableFlyCam extends FlyByCamera {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public UnflippableFlyCam(Camera cam) {
        super(cam);
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected void rotateCamera(float value, Vector3f axis) {
        if (dragToRotate) {
            if (!canRotate) {
                return;
            }
        }

        Matrix3f mat = new Matrix3f();
        mat.fromAngleNormalAxis(rotationSpeed * value, axis);

        Vector3f up = cam.getUp();
        Vector3f left = cam.getLeft();
        Vector3f dir = cam.getDirection();

        mat.mult(up, up);
        mat.mult(left, left);
        mat.mult(dir, dir);
        
        if (up.y >= 0) {
            Quaternion q = new Quaternion();
            q.fromAxes(left, up, dir);
            q.normalizeLocal();

            cam.setAxes(q);              
        }  
    }    
    
    //</editor-fold>
}