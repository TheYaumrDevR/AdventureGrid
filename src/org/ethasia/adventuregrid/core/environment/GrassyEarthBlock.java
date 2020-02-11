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