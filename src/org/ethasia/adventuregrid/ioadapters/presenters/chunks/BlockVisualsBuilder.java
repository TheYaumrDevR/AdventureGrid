package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockTypes;

public abstract class BlockVisualsBuilder {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static StandardBlockVisualsBuilder standardBlockVisualsBuilder = new StandardBlockVisualsBuilder();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static BlockVisualsBuilder fromBlockType(BlockTypes blockType) {
        switch (blockType) {
            case GRASSY_EARTH:
                return standardBlockVisualsBuilder;
            case EARTH:
                return standardBlockVisualsBuilder;
            case ROCK:
                return standardBlockVisualsBuilder;
        }
        
        throw new RuntimeException("BlockVisualsBuilder#fromBlockType does not return an instance for block type: " + blockType);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract StandardBlockVisualsBuilder setBlockToCreateDataFrom(Block value); 
    public abstract StandardBlockVisualsBuilder setChunkPositionX(int value);
    public abstract StandardBlockVisualsBuilder setChunkPositionY(int value);
    public abstract StandardBlockVisualsBuilder setChunkPositionZ(int value);   
    public abstract StandardBlockVisualsBuilder setFrontFaceOfBlockIsCovered(boolean value);
    public abstract StandardBlockVisualsBuilder setRightFaceOfBlockIsCovered(boolean value); 
    public abstract StandardBlockVisualsBuilder setBackFaceOfBlockIsCovered(boolean value);
    public abstract StandardBlockVisualsBuilder setLeftFaceOfBlockIsCovered(boolean value);
    public abstract StandardBlockVisualsBuilder setBottomFaceOfBlockIsCovered(boolean value);
    public abstract StandardBlockVisualsBuilder setTopFaceOfBlockIsCovered(boolean value); 
    public abstract StandardBlockVisualsBuilder setRenderIndexInChunk(int value);
    
    public abstract void build();
    public abstract float[] getShapeVertices();  
    public abstract int[] getShapeIndices();
    public abstract float[] getShapeNormals();
    public abstract float[] getBlockUvCoordinates();   
    public abstract int getAmountOfAddedIndices();
    
    //</editor-fold>
}