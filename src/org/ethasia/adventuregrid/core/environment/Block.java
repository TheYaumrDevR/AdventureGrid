package org.ethasia.adventuregrid.core.environment;

public abstract class Block {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    protected final BlockTypes blockType;
    
    //</editor-fold>
 
    //<editor-fold defaultstate="collapsed" desc="Accessors">
    
    public BlockTypes getBlockType() {
        return blockType;
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Block(BlockTypes blockType) {
        this.blockType = blockType;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public abstract boolean getRightFaceIsCovering();
    public abstract boolean getFrontFaceIsCovering();
    public abstract boolean getLeftFaceIsCovering();
    public abstract boolean getBackFaceIsCovering();
    public abstract boolean getBottomFaceIsCovering();
    public abstract boolean getTopFaceIsCovering();  
    
    //</editor-fold>    
}