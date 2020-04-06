package org.ethasia.adventuregrid.core.environment;

public class SolidBlockFaceCoveringStrategy implements BlockFaceCoveringStrategy {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public boolean faceIsHidden(Block coveredBlock, Block coveringBlock, BlockFaceDirections blockFace) {
        switch (blockFace) {
            case FRONT:
                return coveringBlock.getBackFaceIsCovering() && coveredBlock.getFrontFaceIsCovering();
            case RIGHT:
                return coveringBlock.getLeftFaceIsCovering() && coveredBlock.getRightFaceIsCovering();
            case BACK:
                return coveringBlock.getFrontFaceIsCovering() && coveredBlock.getBackFaceIsCovering();
            case LEFT:
                return coveringBlock.getRightFaceIsCovering() && coveredBlock.getLeftFaceIsCovering();
            case BOTTOM:
                return coveringBlock.getTopFaceIsCovering() && coveredBlock.getBottomFaceIsCovering();
            case TOP:
                return coveringBlock.getBottomFaceIsCovering() && coveredBlock.getTopFaceIsCovering();
        }
        
        return false;
    }
    
    //</editor-fold>
}