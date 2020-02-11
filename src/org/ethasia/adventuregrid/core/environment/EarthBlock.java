package org.ethasia.adventuregrid.core.environment;

public class EarthBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private EarthBlock() {
        super(BlockTypes.EARTH);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static EarthBlock instance;
    
    public static EarthBlock getInstance() {
        if (null != instance) {
            return instance;
        }
        
        instance = new EarthBlock();
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