package org.ethasia.adventuregrid.core.environment;

public interface BlockFaceHidingStrategy {
    
    public boolean faceIsHidden(Block coveredBlock, Block coveringBlock, BlockFaceDirections blockFace);
}