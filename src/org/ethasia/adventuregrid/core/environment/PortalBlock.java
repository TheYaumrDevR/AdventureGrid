package org.ethasia.adventuregrid.core.environment;

public class PortalBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public PortalBlock() {
        super(BlockTypes.PORTAL);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static PortalBlock instance;
    
    public static PortalBlock getInstance() {
        if (null != instance) {
            return instance;
        }        
        
        instance = new PortalBlock();
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Block Overrides">
    
    @Override
    public boolean getRightFaceIsCovering() {
        return false;
    }

    @Override
    public boolean getFrontFaceIsCovering() {
        return false;
    }

    @Override
    public boolean getLeftFaceIsCovering() {
        return false;
    }

    @Override
    public boolean getBackFaceIsCovering() {
        return false;
    }

    @Override
    public boolean getBottomFaceIsCovering() {
        return false;
    }

    @Override
    public boolean getTopFaceIsCovering() {
        return false;
    }    
    
    //</editor-fold>
}