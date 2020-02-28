package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

public class EarthBlockUvCoordinates extends BlockUvCoordinates {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static EarthBlockUvCoordinates instance;
    
    public static EarthBlockUvCoordinates getInstance() {
        if (null == instance) {
            instance = new EarthBlockUvCoordinates();
        }
        
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final float[] uvCoordinates = {
        0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Back
        0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Right
        0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Front
        0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Left
        0.0625f, 0, 0.0625f, 0.015625f, 0, 0.015625f, 0, 0, // Top
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom     
    };
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private EarthBlockUvCoordinates() {}
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public float[] getUvCoordinates() {
        return uvCoordinates;
    }
    
    //</editor-fold>    
}