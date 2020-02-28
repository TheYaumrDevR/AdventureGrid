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
        0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Back
        0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Right
        0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Front
        0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Left
        0.25f, 0, 0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, // Top
        0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, 0.25f, 0.015625f // Bottom     
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