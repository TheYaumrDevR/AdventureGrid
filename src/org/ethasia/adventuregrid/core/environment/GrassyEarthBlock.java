package org.ethasia.adventuregrid.core.environment;

public class GrassyEarthBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private GrassyEarthBlock() {
        super(BlockTypes.GRASSY_EARTH);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static GrassyEarthBlock instance;    
    
    public static GrassyEarthBlock getInstance() {
        if (null != instance) {
            return instance;
        }
        
        instance = new GrassyEarthBlock();
        return instance;
    }    
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean getRightFaceIsCovering() {
        return true;
    }
    
    public boolean getFrontFaceIsCovering() {
        return true;
    }

    public boolean getLeftFaceIsCovering() {
        return true;
    }  
    
    public boolean getBackFaceIsCovering() {
        return true;
    } 

    public boolean getBottomFaceIsCovering() {
        return true;
    }
    
    public boolean getTopFaceIsCovering() {
        return true;
    }    
    
    //</editor-fold>       
}