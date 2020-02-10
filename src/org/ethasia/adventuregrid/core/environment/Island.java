package org.ethasia.adventuregrid.core.environment;

public class Island {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int HEIGHT_IN_BLOCKS = 256;    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final int xzDimension;
    private final Block[][][] blocks;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accessors">
    

    public int getXzDimension() {
        return xzDimension;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Island(int xzDimension) {
        this.xzDimension = xzDimension;
        blocks = new Block[xzDimension][HEIGHT_IN_BLOCKS][xzDimension];
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public Block getBlockAt(int x, int y, int z) {
        if (positionIsOutOfIslandBounds(x, y, z)) {
            throw new BlockPositionOutOfBoundsException();
        }        
        
        return blocks[x][y][z];
    }

    public void placeBlockAt(Block testBlock, int x, int y, int z) {
        if (positionIsOutOfIslandBounds(x, y, z)) {
            throw new BlockPositionOutOfBoundsException();
        }
        
        blocks[x][y][z] = testBlock;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private boolean positionIsOutOfIslandBounds(int x, int y, int z) {
        return x >= xzDimension 
            || y >= HEIGHT_IN_BLOCKS
            || z >= xzDimension;
    }
    
    //</editor-fold>
}