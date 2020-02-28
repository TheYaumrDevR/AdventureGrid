package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.BlockTypes;

public abstract class BlockUvCoordinates {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public static BlockUvCoordinates fromBlockType(BlockTypes blockType) {
        switch (blockType) {
            case GRASSY_EARTH:
                return GrassyEarthBlockUvCoordinates.getInstance();
            case EARTH:
                return EarthBlockUvCoordinates.getInstance();
            case ROCK:
                return RockBlockUvCoordinates.getInstance(); 
            default:
                throw new UnsupportedOperationException("UV coordinates for block type " + blockType + " not found. Please add them.");
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract float[] getUvCoordinates();    
    
    //</editor-fold>
}