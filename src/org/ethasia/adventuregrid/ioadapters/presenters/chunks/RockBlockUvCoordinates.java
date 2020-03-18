package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

public class RockBlockUvCoordinates extends BlockUvCoordinates {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static RockBlockUvCoordinates instance;
    
    public static RockBlockUvCoordinates getInstance() {
        if (null == instance) {
            instance = new RockBlockUvCoordinates();
        }
        
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final float[] uvCoordinates = {
        0.25f, 0.015625f, 0.25f, 0, 0.1875f, 0, 0.1875f, 0.015625f, // Front
        0.25f, 0.015625f, 0.25f, 0, 0.1875f, 0, 0.1875f, 0.015625f, // Right
        0.25f, 0.015625f, 0.25f, 0, 0.1875f, 0, 0.1875f, 0.015625f, // Back
        0.25f, 0.015625f, 0.25f, 0, 0.1875f, 0, 0.1875f, 0.015625f, // Left
        0.25f, 0.015625f, 0.25f, 0, 0.1875f, 0, 0.1875f, 0.015625f, // Bottom 
        0.25f, 0.015625f, 0.25f, 0, 0.1875f, 0, 0.1875f, 0.015625f // Top  
    };
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private RockBlockUvCoordinates() {}
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public float[] getUvCoordinates() {
        return uvCoordinates;
    }
    
    //</editor-fold>
}