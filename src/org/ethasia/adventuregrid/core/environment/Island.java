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
        
        initializeAllBlocksToAir();
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
    
    private void initializeAllBlocksToAir() {
        for (int i = 0; i < xzDimension; i++) {
            for (int j = 0; j < HEIGHT_IN_BLOCKS; j++) {
                for (int k = 0; k < xzDimension; k++) {
                    blocks[i][j][k] = AirBlock.getInstance();
                }              
            }            
        }        
    }
    
    private boolean positionIsOutOfIslandBounds(int x, int y, int z) {
        return x >= xzDimension 
            || y >= HEIGHT_IN_BLOCKS
            || z >= xzDimension;
    }
    
    public boolean blockFaceAtPositionIsHidden(BlockFaceDirections faceType, int x, int y, int z) {
        switch (faceType) {
            case LEFT:
                if (x > 0) {
                    Block currentBlock = blocks[x][y][z];
                    Block neighborBlock = blocks[x - 1][y][z];              
        
                    return currentBlock.getFaceHidingStrategy().faceIsHidden(currentBlock, neighborBlock, faceType);
                }    
                
                break;
            case FRONT:
                if (z < xzDimension - 1) {
                    Block currentBlock = blocks[x][y][z];
                    Block neighborBlock = blocks[x][y][z + 1];
                
                    return currentBlock.getFaceHidingStrategy().faceIsHidden(currentBlock, neighborBlock, faceType);                   
                }
                
                break;
            case RIGHT:
                if (x < xzDimension - 1) {
                    Block currentBlock = blocks[x][y][z];
                    Block neighborBlock = blocks[x + 1][y][z];
                
                    return currentBlock.getFaceHidingStrategy().faceIsHidden(currentBlock, neighborBlock, faceType);                   
                }
                
                break;
            case BACK:
                if (z > 0) {
                    Block currentBlock = blocks[x][y][z];
                    Block neighborBlock = blocks[x][y][z - 1];
                
                    return currentBlock.getFaceHidingStrategy().faceIsHidden(currentBlock, neighborBlock, faceType);     
                }
                
                break;
            case BOTTOM:
                if (y > 0) {
                    Block currentBlock = blocks[x][y][z];
                    Block neighborBlock = blocks[x][y - 1][z];
                
                    return currentBlock.getFaceHidingStrategy().faceIsHidden(currentBlock, neighborBlock, faceType);                    
                }
                
                break;
            case TOP:                
                if (y < HEIGHT_IN_BLOCKS - 1) {
                    Block currentBlock = blocks[x][y][z];
                    Block neighborBlock = blocks[x][y + 1][z];
                    
                    return currentBlock.getFaceHidingStrategy().faceIsHidden(currentBlock, neighborBlock, faceType);                    
                }
                   
                break;
        }
        
        return false;
    }    
    
    //</editor-fold>
}