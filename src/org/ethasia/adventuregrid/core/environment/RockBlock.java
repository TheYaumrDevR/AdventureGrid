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
    
    @Override
    public boolean getRightFaceIsCovering() {
        return true;
    }
    
    @Override
    public boolean getFrontFaceIsCovering() {
        return true;
    }

    @Override
    public boolean getLeftFaceIsCovering() {
        return true;
    }  
    
    @Override
    public boolean getBackFaceIsCovering() {
        return true;
    } 

    @Override
    public boolean getBottomFaceIsCovering() {
        return true;
    }
    
    @Override
    public boolean getTopFaceIsCovering() {
        return true;
    }    
    
    //</editor-fold>      
}