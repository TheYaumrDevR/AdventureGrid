package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

public class PortalBlockUvCoordinates extends BlockUvCoordinates {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static PortalBlockUvCoordinates instance;
    
    public static PortalBlockUvCoordinates getInstance() {
        if (null == instance) {
            instance = new PortalBlockUvCoordinates();
        }
        
        return instance;
    }
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final float[] uvCoordinates = {
        0.9375f, 0.0625f, 0.9375f, 0.046875f, 0.875f, 0.046875f, 0.875f, 0.0625f,
        0.9375f, 0.0625f, 0.9375f, 0.046875f, 0.875f, 0.046875f, 0.875f, 0.0625f,
        0.9375f, 0.0625f, 0.9375f, 0.046875f, 0.875f, 0.046875f, 0.875f, 0.0625f,
        0.9375f, 0.0625f, 0.9375f, 0.046875f, 0.875f, 0.046875f, 0.875f, 0.0625f,
        0.9375f, 0.0625f, 0.9375f, 0.046875f, 0.875f, 0.046875f, 0.875f, 0.0625f,
        0.9375f, 0.0625f, 0.9375f, 0.046875f, 0.875f, 0.046875f, 0.875f, 0.0625f,
    };
    
    //</editor-fold>     
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private PortalBlockUvCoordinates() {}
    
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="BlockUvCoordinates Overrides">
    
    @Override
    public float[] getUvCoordinates() {
        return uvCoordinates;
    }    
    
    //</editor-fold>    
}