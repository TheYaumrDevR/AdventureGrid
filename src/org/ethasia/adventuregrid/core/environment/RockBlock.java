package org.ethasia.adventuregrid.core.environment;


public class RockBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private RockBlock() {
        super(BlockTypes.ROCK);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static RockBlock instance;
    
    public static RockBlock getInstance() {
        if (null != instance) {
            return instance;
        }
        
        instance = new RockBlock();
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