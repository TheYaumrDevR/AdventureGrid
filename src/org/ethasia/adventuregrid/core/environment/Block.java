package org.ethasia.adventuregrid.core.environment;

public class Block {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockTypes blockType;
    
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
}