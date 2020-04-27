package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.ethasia.adventuregrid.ioadapters.presenters.StandardIslandPresenter.CHUNK_EDGE_LENGTH_IN_BLOCKS;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockFaceDirections;
import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.ioadapters.presenters.TechnicalsFactory;
import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class ChunkPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final Set<BlockTypes> SEMI_TRANSPARENT_BLOCK_TYPES;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final List<float[]> floatBuffersOfSemiTransparentBlocksInChunk;
    private final List<int[]> intBuffersOfSemiTransparentBlocksInChunk; 
    
    private final List<float[]> floatBuffersOfOpaqueBlocksInChunk;
    private final List<int[]> intBuffersOfOpaqueBlocksInChunk;    
    
    private final List<ParticleEffectByCoordinate> particleEffectsInOpaqueChunk;
    private final List<ParticleEffectByCoordinate> particleEffectsInTransparentChunk;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    static {
        SEMI_TRANSPARENT_BLOCK_TYPES = new HashSet<>();
        
        SEMI_TRANSPARENT_BLOCK_TYPES.add(BlockTypes.PORTAL);
    }
    
    public ChunkPresenter() {
        floatBuffersOfSemiTransparentBlocksInChunk = new LinkedList<>();
        intBuffersOfSemiTransparentBlocksInChunk = new LinkedList<>();
        
        floatBuffersOfOpaqueBlocksInChunk = new LinkedList<>();
        intBuffersOfOpaqueBlocksInChunk = new LinkedList<>(); 
        
        particleEffectsInOpaqueChunk = new LinkedList<>(); 
        particleEffectsInTransparentChunk = new LinkedList<>(); 
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void presentChunk(Island island, int chunkPositionXInIsland, int chunkPositionYInIsland) {
        VisualChunkData opaqueChunkRenderData = clearRenderDataForNewChunk();
        VisualChunkData semiTransparentChunkRenderData = clearRenderDataForNewChunk();
        
        opaqueChunkRenderData.setIsOpaqueChunk(true);
        semiTransparentChunkRenderData.setIsOpaqueChunk(false);
        
        fillRenderDataForOneChunk(island, chunkPositionXInIsland, chunkPositionYInIsland);
               
        if (intBuffersOfSemiTransparentBlocksInChunk.size() > 0) {
            renderSemiTransparentChunk(semiTransparentChunkRenderData, chunkPositionXInIsland, chunkPositionYInIsland);
        }        
        
        if (intBuffersOfOpaqueBlocksInChunk.size() > 0) {
            renderOpaqueChunk(opaqueChunkRenderData, chunkPositionXInIsland, chunkPositionYInIsland);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private VisualChunkData clearRenderDataForNewChunk() {
        floatBuffersOfSemiTransparentBlocksInChunk.clear();
        intBuffersOfSemiTransparentBlocksInChunk.clear();
        
        floatBuffersOfOpaqueBlocksInChunk.clear();
        intBuffersOfOpaqueBlocksInChunk.clear(); 
        
        particleEffectsInOpaqueChunk.clear();
        particleEffectsInTransparentChunk.clear();
        
        return new VisualChunkData();
    } 
    
    private void fillRenderDataForOneChunk(Island island, int chunkPositionXInIsland, int chunkPositionYInIsland) {
        int amountOfOpaqueBlockVerticesAdded = 0;
        int amountOfSemiTransparentBlockVerticesAdded = 0;
        
        for (int islandX = CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionXInIsland; islandX < CHUNK_EDGE_LENGTH_IN_BLOCKS * (chunkPositionXInIsland + 1); islandX++) {
            for (int islandZ = CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionYInIsland; islandZ < CHUNK_EDGE_LENGTH_IN_BLOCKS * (chunkPositionYInIsland + 1); islandZ++) {
                for (int islandY = 0; islandY < Island.HEIGHT_IN_BLOCKS; islandY++) {
                    if (islandX < island.getXzDimension() && islandZ < island.getXzDimension()) {
                        if (island.getBlockAt(islandX, islandY, islandZ).getBlockType() != BlockTypes.AIR) {                                    
                            int inChunkX = islandX - CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionXInIsland;
                            int inChunkZ = islandZ - CHUNK_EDGE_LENGTH_IN_BLOCKS * chunkPositionYInIsland;
                                  
                            BlockVisualsBuilder blockVisualsBuilder;
                            if (SEMI_TRANSPARENT_BLOCK_TYPES.contains(island.getBlockAt(islandX, islandY, islandZ).getBlockType())) {
                                blockVisualsBuilder = buildBlockVisualsFromBlock(island, islandX, islandY, islandZ, inChunkX, inChunkZ, amountOfSemiTransparentBlockVerticesAdded);
                            } else {
                                blockVisualsBuilder = buildBlockVisualsFromBlock(island, islandX, islandY, islandZ, inChunkX, inChunkZ, amountOfOpaqueBlockVerticesAdded);
                            }
                                                                
                            if (blockVisualsBuilder.getShapeVertices().length > 0) {                                
                                if (SEMI_TRANSPARENT_BLOCK_TYPES.contains(island.getBlockAt(islandX, islandY, islandZ).getBlockType())) {
                                    fillSemiTransparentBlockBuffersWithVisualRenderData(blockVisualsBuilder); 
                                    amountOfSemiTransparentBlockVerticesAdded += blockVisualsBuilder.getAmountOfAddedIndices(); 

                                    if (blockVisualsBuilder.getParticleEffect() != ParticleEffects.NONE) {
                                        particleEffectsInTransparentChunk.add(new ParticleEffectByCoordinate(islandX, islandY, islandZ, blockVisualsBuilder.getParticleEffect()));
                                    }                                    
                                } else {
                                    fillOpaqueBlockBuffersWithVisualRenderData(blockVisualsBuilder); 
                                    amountOfOpaqueBlockVerticesAdded += blockVisualsBuilder.getAmountOfAddedIndices(); 

                                    if (blockVisualsBuilder.getParticleEffect() != ParticleEffects.NONE) {
                                        particleEffectsInOpaqueChunk.add(new ParticleEffectByCoordinate(islandX, islandY, islandZ, blockVisualsBuilder.getParticleEffect()));
                                    }                                     
                                }                                       
                            }
                        }
                    }    
                }
            }                    
        }        
    }  
    
    private void renderSemiTransparentChunk(VisualChunkData chunkRenderData, int chunkPosX, int chunkPosY) {
        ChunkRenderer chunkRenderer = TechnicalsFactory.getInstance().getChunkRendererInstance();        
        
        chunkRenderData.setUpWithNumberOfBlocksInChunk(intBuffersOfSemiTransparentBlocksInChunk.size());
        chunkRenderData.setWorldPosition(chunkPosX, chunkPosY);
                
        for (int k = 0; k < floatBuffersOfSemiTransparentBlocksInChunk.size(); k += 3) {
            chunkRenderData.addVerticesToTemporaryBuffer(floatBuffersOfSemiTransparentBlocksInChunk.get(k));
            chunkRenderData.addNormalsToTemporaryBuffer(floatBuffersOfSemiTransparentBlocksInChunk.get(k + 1));
            chunkRenderData.addUvCoordinatesToTemporaryBuffer(floatBuffersOfSemiTransparentBlocksInChunk.get(k + 2));
        }
                
        for (int k = 0; k < intBuffersOfSemiTransparentBlocksInChunk.size(); k++) {
            chunkRenderData.addIndicesToTemporaryBuffer(intBuffersOfSemiTransparentBlocksInChunk.get(k));
        }
                
        chunkRenderData.addParticleEffectsFrom(particleEffectsInTransparentChunk);
        chunkRenderData.buildChunkData();
        chunkRenderer.renderChunk(chunkRenderData);          
    }
    
    private void renderOpaqueChunk(VisualChunkData chunkRenderData, int chunkPosX, int chunkPosY) {
        ChunkRenderer chunkRenderer = TechnicalsFactory.getInstance().getChunkRendererInstance();        
        
        chunkRenderData.setUpWithNumberOfBlocksInChunk(intBuffersOfOpaqueBlocksInChunk.size());
        chunkRenderData.setWorldPosition(chunkPosX, chunkPosY);
                
        for (int k = 0; k < floatBuffersOfOpaqueBlocksInChunk.size(); k += 3) {
            chunkRenderData.addVerticesToTemporaryBuffer(floatBuffersOfOpaqueBlocksInChunk.get(k));
            chunkRenderData.addNormalsToTemporaryBuffer(floatBuffersOfOpaqueBlocksInChunk.get(k + 1));
            chunkRenderData.addUvCoordinatesToTemporaryBuffer(floatBuffersOfOpaqueBlocksInChunk.get(k + 2));
        }
                
        for (int k = 0; k < intBuffersOfOpaqueBlocksInChunk.size(); k++) {
            chunkRenderData.addIndicesToTemporaryBuffer(intBuffersOfOpaqueBlocksInChunk.get(k));
        }
                
        chunkRenderData.addParticleEffectsFrom(particleEffectsInOpaqueChunk);
        chunkRenderData.buildChunkData();
        chunkRenderer.renderChunk(chunkRenderData);          
    }

    private BlockVisualsBuilder buildBlockVisualsFromBlock(Island island, int x, int y, int z, int inChunkX, int inChunkZ, int currentBlockRenderIndex) {
        Block currentBlock = island.getBlockAt(x, y, z);
        BlockVisualsBuilder blockVisualsBuilder = BlockVisualsBuilder.fromBlockType(currentBlock.getBlockType());
        ParticleEffects blockParticleEffect = ParticleEffectByBlock.getParticleEffectFromBlockType(currentBlock.getBlockType());
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
            .setParticleEffect(blockParticleEffect)
            .build();     
        
        return blockVisualsBuilder;
    }
    
    private void fillSemiTransparentBlockBuffersWithVisualRenderData(BlockVisualsBuilder blockRenderDataBuilder) {
        floatBuffersOfSemiTransparentBlocksInChunk.add(blockRenderDataBuilder.getShapeVertices());
        floatBuffersOfSemiTransparentBlocksInChunk.add(blockRenderDataBuilder.getShapeNormals());
        floatBuffersOfSemiTransparentBlocksInChunk.add(blockRenderDataBuilder.getBlockUvCoordinates());
        intBuffersOfSemiTransparentBlocksInChunk.add(blockRenderDataBuilder.getShapeIndices());        
    }     

    private void fillOpaqueBlockBuffersWithVisualRenderData(BlockVisualsBuilder blockRenderDataBuilder) {
        floatBuffersOfOpaqueBlocksInChunk.add(blockRenderDataBuilder.getShapeVertices());
        floatBuffersOfOpaqueBlocksInChunk.add(blockRenderDataBuilder.getShapeNormals());
        floatBuffersOfOpaqueBlocksInChunk.add(blockRenderDataBuilder.getBlockUvCoordinates());
        intBuffersOfOpaqueBlocksInChunk.add(blockRenderDataBuilder.getShapeIndices());        
    }    
    
    //</editor-fold>
}