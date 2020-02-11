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