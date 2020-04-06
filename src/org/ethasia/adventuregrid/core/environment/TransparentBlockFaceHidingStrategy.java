package org.ethasia.adventuregrid.core.environment;

public class TransparentBlockFaceHidingStrategy implements BlockFaceHidingStrategy {

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public boolean faceIsHidden(Block coveredBlock, Block coveringBlock, BlockFaceDirections blockFace) {
        return coveringBlock.getBlockType() != BlockTypes.AIR;
    }    
    
    //</editor-fold>    
}