package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import java.util.LinkedList;
import java.util.List;

import static org.ethasia.adventuregrid.ioadapters.presenters.StandardIslandPresenter.CHUNK_EDGE_LENGTH_IN_BLOCKS;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockFaceDirections;
import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class ChunkPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    List<float[]> floatBuffersOfBlocksInChunk;
    List<int[]> intBuffersOfBlocksInChunk;    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ChunkPresenter() {
        floatBuffersOfBlocksInChunk = new LinkedList<>();
        intBuffersOfBlocksInChunk = new LinkedList<>(); 
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void presentChunk(Island island, int chunkPositionXInIsland, int chunkPositionYInIsland) {
        VisualChunkData chunkRenderData = clearRenderDataForNewChunk();
        fillRenderDataForOneChunk(island, chunkPositionXInIsland, chunkPositionYInIsland);
                
        if (intBuffersOfBlocksInChunk.size() > 0) {
            renderChunk(chunkRenderData, chunkPositionXInIsland, chunkPositionYInIsland);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private VisualChunkData clearRenderDataForNewChunk() {
        floatBuffersOfBlocksInChunk.clear();
        intBuffersOfBlocksInChunk.clear(); 
        
        return new VisualChunkData();
    } 
    
    private void fillRenderDataForOneChunk(Island island, int chunkPositionXInIsland, int chunkPositionYInIsland) {
        int amountOfVerticesAdded = 0;
        
        for (int x = CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionXInIsland; x < CHUNK_EDGE_LENGTH_IN_BLOCKS * (chunkPositionXInIsland + 1); x++) {
            for (int z = CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionYInIsland; z < CHUNK_EDGE_LENGTH_IN_BLOCKS * (chunkPositionYInIsland + 1); z++) {
                for (int y = 0; y < Island.HEIGHT_IN_BLOCKS; y++) {
                    if (x < island.getXzDimension() && z < island.getXzDimension()) {
                        if (island.getBlockAt(x, y, z).getBlockType() != BlockTypes.AIR) {                                    
                            int inChunkX = x - CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionXInIsland;
                            int inChunkZ = z - CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionYInIsland;
                                    
                            BlockVisualsBuilder blockVisualsBuilder = buildBlockVisualsFromBlock(island, x, y, z, inChunkX, inChunkZ, amountOfVerticesAdded);
                                    
                            if (blockVisualsBuilder.getShapeVertices().length > 0) {
                                fillBuffersWithVisualRenderData(blockVisualsBuilder);
                                    
                                amountOfVerticesAdded += blockVisualsBuilder.getAmountOfAddedIndices();                                        
                            }
                        }
                    }    
                }
            }                    
        }        
    }  
    
    private void renderChunk(VisualChunkData chunkRenderData, int chunkPosX, int chunkPosY) {
        ChunkRenderer chunkRenderer = TechnicalsFactory.getInstance().getChunkRendererInstance();        
        
        chunkRenderData.setUpWithNumberOfBlocksInChunk(intBuffersOfBlocksInChunk.size());
        chunkRenderData.setWorldPosition(chunkPosX, chunkPosY);
                
        for (int k = 0; k < floatBuffersOfBlocksInChunk.size(); k += 3) {
            chunkRenderData.addVerticesToTemporaryBuffer(floatBuffersOfBlocksInChunk.get(k));
            chunkRenderData.addNormalsToTemporaryBuffer(floatBuffersOfBlocksInChunk.get(k + 1));
            chunkRenderData.addUvCoordinatesToTemporaryBuffer(floatBuffersOfBlocksInChunk.get(k + 2));
        }
                
        for (int k = 0; k < intBuffersOfBlocksInChunk.size(); k++) {
            chunkRenderData.addIndicesToTemporaryBuffer(intBuffersOfBlocksInChunk.get(k));
        }
                
        chunkRenderData.buildChunkData();
        chunkRenderer.renderChunk(chunkRenderData);          
    }

    private BlockVisualsBuilder buildBlockVisualsFromBlock(Island island, int x, int y, int z, int inChunkX, int inChunkZ, int currentBlockRenderIndex) {
        Block currentBlock = island.getBlockAt(x, y, z);
        BlockVisualsBuilder blockVisualsBuilder = BlockVisualsBuilder.fromBlockType(currentBlock.getBlockType());
        blockVisualsBuilder.setBlockToCreateDataFrom(currentBlock)
            .setChunkPositionX(inChunkX)
            .setChunkPositionY(y)
            .setChunkPositionZ(inChunkZ)
            .setRenderIndexInChunk(currentBlockRenderIndex)
            .setFrontFaceOfBlockIsCovered(island.blockFaceAtPositionIsHidden(BlockFaceDirections.FRONT, x, y, z))
            .setRightFaceOfBlockIsCovered(island.blockFaceAtPositionIsHidden(BlockFaceDirections.RIGHT, x, y, z))
            .setBackFaceOfBlockIsCovered(island.blockFaceAtPositionIsHidden(BlockFaceDirections.BACK, x, y, z))
            .setLeftFaceOfBlockIsCovered(island.blockFaceAtPositionIsHidden(BlockFaceDirections.LEFT, x, y, z))
            .setBottomFaceOfBlockIsCovered(island.blockFaceAtPositionIsHidden(BlockFaceDirections.BOTTOM, x, y, z))
            .setTopFaceOfBlockIsCovered(island.blockFaceAtPositionIsHidden(BlockFaceDirections.TOP, x, y, z))
            .build();     
        
        return blockVisualsBuilder;
    }

    private void fillBuffersWithVisualRenderData(BlockVisualsBuilder blockRenderDataBuilder) {
        floatBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getShapeVertices());
        floatBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getShapeNormals());
        floatBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getBlockUvCoordinates());
        intBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getShapeIndices());        
    }    
    
    //</editor-fold>
}