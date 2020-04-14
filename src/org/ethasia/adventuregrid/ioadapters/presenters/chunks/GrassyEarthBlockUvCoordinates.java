package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

public class GrassyEarthBlockUvCoordinates extends BlockUvCoordinates {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static GrassyEarthBlockUvCoordinates instance;
    
    public static GrassyEarthBlockUvCoordinates getInstance() {
        if (null == instance) {
            instance = new GrassyEarthBlockUvCoordinates();
        }
        
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final float[] uvCoordinates = {
        0.125f, 0.015625f, 0.125f, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Front
        0.125f, 0.015625f, 0.125f, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Right
        0.125f, 0.015625f, 0.125f, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Back
        0.125f, 0.015625f, 0.125f, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Left
        0.0625f, 0.015625f, 0.0625f, 0, 0, 0, 0, 0.015625f, // Bottom 
        0.1875f, 0.015625f, 0.1875f, 0, 0.125f, 0, 0.125f, 0.015625f // Top
    };
    
    private final float[] backUvCoordinates = {
        0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, 0.125f, 0.015625f, // Front
        0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, 0.125f, 0.015625f, // Right
        0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, 0.125f, 0.015625f, // Back
        0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, 0.125f, 0.015625f, // Left
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Bottom 
        0.125f, 0.015625f, 0.125f, 0, 0.1875f, 0, 0.1875f, 0.015625f // Top
    };    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private GrassyEarthBlockUvCoordinates() {}
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public float[] getUvCoordinates() {
        return uvCoordinates;
    }
    
    @Override
    public float[] getBackUvCoordinates() {
        return backUvCoordinates;
    }    
    
    //</editor-fold>
}