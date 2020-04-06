package org.ethasia.adventuregrid.core.environment;

public interface BlockFaceCoveringStrategy {
    
    public boolean faceIsHidden(Block coveredBlock, Block coveringBlock, BlockFaceDirections blockFace);
}