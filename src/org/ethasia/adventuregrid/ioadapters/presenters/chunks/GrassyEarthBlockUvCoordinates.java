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
        0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Back
        0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Right
        0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Front
        0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Left
        0.1875f, 0, 0.1875f, 0.015625f, 0.125f, 0.015625f, 0.125f, 0, // Top
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom        
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
    
    //</editor-fold>
}