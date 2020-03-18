package org.ethasia.adventuregrid.ioadapters.presenters;

import java.util.LinkedList;
import java.util.List;
import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockFaceDirections;
import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.interactors.output.IslandPresenter;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.BlockVisualsBuilder;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class StandardIslandPresenter implements IslandPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int CHUNK_EDGE_LENGTH_IN_BLOCKS = 16;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IslandPresenter Overrides">
    
    @Override
    public void presentIsland(Island island) {        
        int chunkCountPerIslandEdge = calculateChunkCountPerIslandEdge(island);
        
        List<float[]> floatBuffersOfBlocksInChunk = new LinkedList<>();
        List<int[]> intBuffersOfBlocksInChunk = new LinkedList<>();
        
        for (int i = 0; i < chunkCountPerIslandEdge; i++) {
            for (int j = 0; j < chunkCountPerIslandEdge; j++) {
                
                VisualChunkData chunkRenderData = clearRenderDataForNewChunk(floatBuffersOfBlocksInChunk, intBuffersOfBlocksInChunk);
                fillRenderDataForOneChunk(i, j, island, floatBuffersOfBlocksInChunk, intBuffersOfBlocksInChunk);
                
                if (intBuffersOfBlocksInChunk.size() > 0) {
                    renderChunk(chunkRenderData, i, j, floatBuffersOfBlocksInChunk, intBuffersOfBlocksInChunk);
                }
            }            
        }
    }  
    
    private int calculateChunkCountPerIslandEdge(Island island) {
        int result = 0;
        for (int i = 0; i * CHUNK_EDGE_LENGTH_IN_BLOCKS < island.getXzDimension(); i++) {
            result++;
        }  
        
        return result;
    }
    
    private VisualChunkData clearRenderDataForNewChunk(List<float[]> floatBuffersOfBlocksInChunk, List<int[]> intBuffersOfBlocksInChunk) {
        floatBuffersOfBlocksInChunk.clear();
        intBuffersOfBlocksInChunk.clear(); 
        
        return new VisualChunkData();
    }
    
    private void fillRenderDataForOneChunk(int i, int j, Island island, List<float[]> floatBuffersOfBlocksInChunk, List<int[]> intBuffersOfBlocksInChunk) {
        int amountOfVerticesAdded = 0;
        
        for (int x = CHUNK_EDGE_LENGTH_IN_BLOCKS * i; x < CHUNK_EDGE_LENGTH_IN_BLOCKS * (i + 1); x++) {
            for (int z = CHUNK_EDGE_LENGTH_IN_BLOCKS * j; z < CHUNK_EDGE_LENGTH_IN_BLOCKS * (j + 1); z++) {
                for (int y = 0; y < Island.HEIGHT_IN_BLOCKS; y++) {
                    if (x < island.getXzDimension() && z < island.getXzDimension()) {
                        if (island.getBlockAt(x, y, z).getBlockType() != BlockTypes.AIR) {                                    
                            int inChunkX = x - CHUNK_EDGE_LENGTH_IN_BLOCKS * i;
                            int inChunkZ = z - CHUNK_EDGE_LENGTH_IN_BLOCKS * j;
                                    
                            BlockVisualsBuilder blockVisualsBuilder = buildBlockVisualsFromBlock(island, x, y, z, inChunkX, inChunkZ, amountOfVerticesAdded);
                                    
                            if (blockVisualsBuilder.getShapeVertices().length > 0) {
                                fillBuffersWithVisualRenderData(floatBuffersOfBlocksInChunk, intBuffersOfBlocksInChunk, blockVisualsBuilder);
                                    
                                amountOfVerticesAdded += blockVisualsBuilder.getAmountOfAddedIndices();                                        
                            }
                        }
                    }    
                }
            }                    
        }        
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
    
    private void fillBuffersWithVisualRenderData(List<float[]> floatBuffersOfBlocksInChunk,  List<int[]> intBuffersOfBlocksInChunk, BlockVisualsBuilder blockRenderDataBuilder) {
        floatBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getShapeVertices());
        floatBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getShapeNormals());
        floatBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getBlockUvCoordinates());
        intBuffersOfBlocksInChunk.add(blockRenderDataBuilder.getShapeIndices());        
    }
    
    private void renderChunk(VisualChunkData chunkRenderData, int chunkPosX, int chunkPosY, List<float[]> floatBuffersOfBlocksInChunk,  List<int[]> intBuffersOfBlocksInChunk) {
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
    
    //</editor-fold>    
}