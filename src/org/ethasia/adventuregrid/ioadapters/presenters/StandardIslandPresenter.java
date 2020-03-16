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
        VisualChunkData dataToRender = new VisualChunkData();
        ChunkRenderer chunkRenderer = TechnicalsFactory.getInstance().getChunkRendererInstance();
        
        int chunksPerEdge = 0;
        for (int i = 0; i * CHUNK_EDGE_LENGTH_IN_BLOCKS < island.getXzDimension(); i++) {
            chunksPerEdge++;
        }
        
        List<float[]> floatBuffersOfBlocksInChunk = new LinkedList<>();
        List<int[]> intBuffersOfBlocksInChunk = new LinkedList<>();
        
        for (int i = 0; i < chunksPerEdge; i++) {
            for (int j = 0; j < chunksPerEdge; j++) {
                
                floatBuffersOfBlocksInChunk.clear();
                intBuffersOfBlocksInChunk.clear();
                int currentBlockRenderIndex = 0;
                
                for (int x = CHUNK_EDGE_LENGTH_IN_BLOCKS * i; x < CHUNK_EDGE_LENGTH_IN_BLOCKS * (i + 1); x++) {
                    for (int y = CHUNK_EDGE_LENGTH_IN_BLOCKS * j; y < CHUNK_EDGE_LENGTH_IN_BLOCKS * (j + 1); y++) {
                        for (int z = 0; z < Island.HEIGHT_IN_BLOCKS; z++) {
                            if (x < island.getXzDimension() && y < island.getXzDimension()) {
                                if (island.getBlockAt(x, y, z).getBlockType() != BlockTypes.AIR) {
                                    int inChunkX = x - CHUNK_EDGE_LENGTH_IN_BLOCKS * i;
                                    int inChunkZ = z - CHUNK_EDGE_LENGTH_IN_BLOCKS * j;
                                    
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
                                    
                                    if (blockVisualsBuilder.getShapeVertices().length > 0) {
                                        floatBuffersOfBlocksInChunk.add(blockVisualsBuilder.getShapeVertices());
                                        floatBuffersOfBlocksInChunk.add(blockVisualsBuilder.getShapeNormals());
                                        floatBuffersOfBlocksInChunk.add(blockVisualsBuilder.getBlockUvCoordinates());
                                        intBuffersOfBlocksInChunk.add(blockVisualsBuilder.getShapeIndices());
                                    
                                        currentBlockRenderIndex += blockVisualsBuilder.getAmountOfAddedIndices();                                        
                                    }
                                }
                            }    
                        }
                    }                    
                }
                
                dataToRender.setUpWithNumberOfBlocksInChunk(intBuffersOfBlocksInChunk.size());
                dataToRender.setWorldPosition(i, j);
                
                for (int k = 0; k < floatBuffersOfBlocksInChunk.size(); k += 3) {
                    dataToRender.addVerticesToTemporaryBuffer(floatBuffersOfBlocksInChunk.get(k));
                    dataToRender.addNormalsToTemporaryBuffer(floatBuffersOfBlocksInChunk.get(k + 1));
                    dataToRender.addUvCoordinatesToTemporaryBuffer(floatBuffersOfBlocksInChunk.get(k + 2));
                }
                
                for (int k = 0; k < intBuffersOfBlocksInChunk.size(); k++) {
                    dataToRender.addIndicesToTemporaryBuffer(intBuffersOfBlocksInChunk.get(k));
                }
                
                dataToRender.buildChunkData();
                chunkRenderer.renderChunk(dataToRender);
            }            
        }
    }    
    
    //</editor-fold>    
}