package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockTypes;

public abstract class BlockVisualsBuilder {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static StandardBlockVisualsBuilder standardBlockVisualsBuilder = new StandardBlockVisualsBuilder();
    private static TransparentBlockVisualsBuilder transparentBlockVisualsBuilder = new TransparentBlockVisualsBuilder();
    
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
            case PORTAL:
                return standardBlockVisualsBuilder;
        }
        
        throw new RuntimeException("BlockVisualsBuilder#fromBlockType does not return an instance for block type: " + blockType);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract BlockVisualsBuilder setBlockToCreateDataFrom(Block value); 
    public abstract BlockVisualsBuilder setChunkPositionX(int value);
    public abstract BlockVisualsBuilder setChunkPositionY(int value);
    public abstract BlockVisualsBuilder setChunkPositionZ(int value);   
    public abstract BlockVisualsBuilder setFrontFaceOfBlockIsCovered(boolean value);
    public abstract BlockVisualsBuilder setRightFaceOfBlockIsCovered(boolean value); 
    public abstract BlockVisualsBuilder setBackFaceOfBlockIsCovered(boolean value);
    public abstract BlockVisualsBuilder setLeftFaceOfBlockIsCovered(boolean value);
    public abstract BlockVisualsBuilder setBottomFaceOfBlockIsCovered(boolean value);
    public abstract BlockVisualsBuilder setTopFaceOfBlockIsCovered(boolean value); 
    public abstract BlockVisualsBuilder setRenderIndexInChunk(int value);
    
    public abstract void build();
    public abstract float[] getShapeVertices();  
    public abstract int[] getShapeIndices();
    public abstract float[] getShapeNormals();
    public abstract float[] getBlockUvCoordinates();   
    public abstract int getAmountOfAddedIndices();
    
    //</editor-fold>
}