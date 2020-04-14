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
        0.0625f, 0.015625f, 0.0625f, 0, 0, 0, 0, 0.015625f, // Front
        0.0625f, 0.015625f, 0.0625f, 0, 0, 0, 0, 0.015625f, // Right
        0.0625f, 0.015625f, 0.0625f, 0, 0, 0, 0, 0.015625f, // Back
        0.0625f, 0.015625f, 0.0625f, 0, 0, 0, 0, 0.015625f, // Left
        0.0625f, 0.015625f, 0.0625f, 0, 0, 0, 0, 0.015625f, // Bottom  
        0.0625f, 0.015625f, 0.0625f, 0, 0, 0, 0, 0.015625f, // Top
    };
    
    private final float[] backUvCoordinates = {
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Front
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Right
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Back
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Left
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Bottom  
        0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f, // Top
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
    
    @Override
    public float[] getBackUvCoordinates() {
        return backUvCoordinates;
    }
    
    //</editor-fold>    
}