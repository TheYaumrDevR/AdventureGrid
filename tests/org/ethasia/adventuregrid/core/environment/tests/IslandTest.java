package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.AirBlock;
import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockFaceDirections;
import org.ethasia.adventuregrid.core.environment.BlockPositionOutOfBoundsException;
import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.EarthBlock;
import org.ethasia.adventuregrid.core.environment.GrassyEarthBlock;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.environment.RockBlock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IslandTest {
    
    @Test
    public void testGetBlockAt_blockIsNotInitialized_isAir() {
        Island testCandidate = new Island(64);
        
        Block blockAt = testCandidate.getBlockAt(63, 0, 63);
        
        assertThat(blockAt.getBlockType(), is(BlockTypes.AIR));
    }
    
    @Test
    public void testGetBlockAt_blockIsGrassBlock_returnsBlock() {
        Island testCandidate = new Island(64);
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 63, 0, 63);
        
        Block blockAt = testCandidate.getBlockAt(63, 0, 63);
        
        assertThat(blockAt, is(testBlock));
    }
    
    @Test
    public void testPlaceBlockAt_placeAtHeight255_works() {
        Island testCandidate = new Island(64);
        Block testBlock = EarthBlock.getInstance(); 
        
        testCandidate.placeBlockAt(testBlock, 63, 255, 63);
        
        Block blockAt = testCandidate.getBlockAt(63, 255, 63);
        
        assertThat(blockAt, is(testBlock));        
    }   
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testPlaceBlockAt_placeAtHeight256_throwsException() {
        Island testCandidate = new Island(64);
        Block testBlock = RockBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 63, 256, 63);        
    }
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testPlaceBlockAt_placeOutsideX_throwsException() {
        Island testCandidate = new Island(64);
        Block testBlock = GrassyEarthBlock.getInstance();   
        
        testCandidate.placeBlockAt(testBlock, 64, 200, 24);         
    }  
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testPlaceBlockAt_placeOutsideZ_throwsException() {
        Island testCandidate = new Island(64);
        Block testBlock = EarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 54, 13, 64);         
    }     
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testGetBlockAt_getFromHeight256_throwsException() {
        Island testCandidate = new Island(64);
        
        Block blockAt = testCandidate.getBlockAt(63, 256, 63);        
    }
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testGetBlockAt_getFromOutsideX_throwsException() {
        Island testCandidate = new Island(64);
        
        Block blockAt = testCandidate.getBlockAt(64, 123, 52);        
    } 
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testGetBlockAt_getFromOutsideZ_throwsException() {
        Island testCandidate = new Island(64);
        
        Block blockAt = testCandidate.getBlockAt(32, 222, 64);        
    } 
    
    @Test
    public void testGetXzDimension_returnsSetDimension() {
        Island testCandidate = new Island(48);
        
        int result = testCandidate.getXzDimension();
        
        assertThat(result, is(48));
    }
    
    @Test
    public void testBlockFaceAtPositionIsHidden_leftFaceOfRockIsNeighboredByCoveringFace_returnsTrue() {
        Island testCandidate = new Island(64);
        
        Block testBlock = RockBlock.getInstance();
        Block neighbor = EarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 5, 0, 0);
        testCandidate.placeBlockAt(neighbor, 4, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.LEFT, 5, 0, 0);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testBlockFaceAtPositionIsHidden_leftFaceOfRockIsNeighboredByAir_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = RockBlock.getInstance();
        Block neighbor = AirBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 5, 0, 0);
        testCandidate.placeBlockAt(neighbor, 4, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.LEFT, 5, 0, 0);
        
        assertThat(result, is(false));
    }  
    
    @Test
    public void testBlockFaceAtPositionIsHidden_blockFaceIsNotCovering_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = AirBlock.getInstance();
        Block neighbor = GrassyEarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 5, 0, 0);
        testCandidate.placeBlockAt(neighbor, 4, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.LEFT, 5, 0, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_atLeftEdgeOfIsland_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = RockBlock.getInstance();
        testCandidate.placeBlockAt(testBlock, 0, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.LEFT, 0, 0, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_frontFaceOfEarthIsNeighboredByCoveringFace_returnsTrue() {
        Island testCandidate = new Island(64);
        
        Block testBlock = EarthBlock.getInstance();
        Block neighbor = GrassyEarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 0, 7);
        testCandidate.placeBlockAt(neighbor, 0, 0, 8);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.FRONT, 0, 0, 7);
        
        assertThat(result, is(true));
    }   
    
    @Test
    public void testBlockFaceAtPositionIsHidden_frontFaceOfEarthIsNeighboredByAir_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = EarthBlock.getInstance();
        Block neighbor = AirBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 0, 7);
        testCandidate.placeBlockAt(neighbor, 0, 0, 8);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.FRONT, 0, 0, 7);
        
        assertThat(result, is(false));
    } 
    
    @Test
    public void testBlockFaceAtPositionIsHidden_frontBlockFaceIsNotCovering_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = AirBlock.getInstance();
        Block neighbor = EarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 0, 7);
        testCandidate.placeBlockAt(neighbor, 0, 0, 8);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.FRONT, 0, 0, 7);
        
        assertThat(result, is(false));
    } 
    
    @Test
    public void testBlockFaceAtPositionIsHidden_atFrontEdgeOfIsland_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = EarthBlock.getInstance();
        testCandidate.placeBlockAt(testBlock, 0, 0, 63);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.FRONT, 0, 0, 63);
        
        assertThat(result, is(false));
    }   
    
    @Test
    public void testBlockFaceAtPositionIsHidden_rightFaceOfGrassyEarthBlockIsNeighboredByCoveringFace_returnsTrue() {
        Island testCandidate = new Island(64);
        
        Block testBlock = GrassyEarthBlock.getInstance();
        Block neighbor = RockBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 45, 0, 0);
        testCandidate.placeBlockAt(neighbor, 46, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.RIGHT, 45, 0, 0);
        
        assertThat(result, is(true));
    }  

    @Test
    public void testBlockFaceAtPositionIsHidden_rightFaceOfEarthIsNeighboredByAir_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = GrassyEarthBlock.getInstance();
        Block neighbor = AirBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 45, 0, 0);
        testCandidate.placeBlockAt(neighbor, 46, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.RIGHT, 45, 0, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_rightBlockFaceIsNotCovering_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = AirBlock.getInstance();
        Block neighbor = EarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 45, 0, 0);
        testCandidate.placeBlockAt(neighbor, 46, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.RIGHT, 45, 0, 0);
        
        assertThat(result, is(false));
    } 
    
    @Test
    public void testBlockFaceAtPositionIsHidden_atRightEdgeOfIsland_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = GrassyEarthBlock.getInstance();
        testCandidate.placeBlockAt(testBlock, 63, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.RIGHT, 63, 0, 0);
        
        assertThat(result, is(false));
    }   
    
    @Test
    public void testBlockFaceAtPositionIsHidden_backFaceOfRockBlockIsNeighboredByCoveringFace_returnsTrue() {
        Island testCandidate = new Island(64);
        
        Block testBlock = RockBlock.getInstance();
        Block neighbor = RockBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 0, 24);
        testCandidate.placeBlockAt(neighbor, 0, 0, 23);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BACK, 0, 0, 24);
        
        assertThat(result, is(true));
    } 

    @Test
    public void testBlockFaceAtPositionIsHidden_backFaceOfRockIsNeighboredByAir_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = RockBlock.getInstance();
        Block neighbor = AirBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 0, 24);
        testCandidate.placeBlockAt(neighbor, 0, 0, 23);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BACK, 0, 0, 24);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_backBlockFaceIsNotCovering_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = AirBlock.getInstance();
        Block neighbor = RockBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 0, 24);
        testCandidate.placeBlockAt(neighbor, 0, 0, 23);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BACK, 0, 0, 24);
        
        assertThat(result, is(false));
    } 
    
    @Test
    public void testBlockFaceAtPositionIsHidden_atBackEdgeOfIsland_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = EarthBlock.getInstance();
        testCandidate.placeBlockAt(testBlock, 0, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BACK, 0, 0, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_bottomFaceOfEarthBlockIsNeighboredByCoveringFace_returnsTrue() {
        Island testCandidate = new Island(64);
        
        Block testBlock = EarthBlock.getInstance();
        Block neighbor = EarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 5, 0);
        testCandidate.placeBlockAt(neighbor, 0, 4, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BOTTOM, 0, 5, 0);
        
        assertThat(result, is(true));
    } 

    @Test
    public void testBlockFaceAtPositionIsHidden_bottomFaceOfEarthIsNeighboredByAir_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = EarthBlock.getInstance();
        Block neighbor = AirBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 5, 0);
        testCandidate.placeBlockAt(neighbor, 0, 4, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BOTTOM, 0, 5, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_bottomBlockFaceIsNotCovering_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = AirBlock.getInstance();
        Block neighbor = GrassyEarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 5, 0);
        testCandidate.placeBlockAt(neighbor, 0, 4, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BOTTOM, 0, 5, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_atBottomEdgeOfIsland_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = EarthBlock.getInstance();
        testCandidate.placeBlockAt(testBlock, 0, 0, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.BOTTOM, 0, 0, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_topFaceOfGrassyEarthBlockIsNeighboredByCoveringFace_returnsTrue() {
        Island testCandidate = new Island(64);
        
        Block testBlock = GrassyEarthBlock.getInstance();
        Block neighbor = GrassyEarthBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 4, 0);
        testCandidate.placeBlockAt(neighbor, 0, 5, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.TOP, 0, 4, 0);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testBlockFaceAtPositionIsHidden_topFaceOfGrassyEarthIsNeighboredByAir_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = GrassyEarthBlock.getInstance();
        Block neighbor = AirBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 4, 0);
        testCandidate.placeBlockAt(neighbor, 0, 5, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.TOP, 0, 4, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_topBlockFaceIsNotCovering_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = AirBlock.getInstance();
        Block neighbor = RockBlock.getInstance();
        
        testCandidate.placeBlockAt(testBlock, 0, 4, 0);
        testCandidate.placeBlockAt(neighbor, 0, 5, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.TOP, 0, 4, 0);
        
        assertThat(result, is(false));
    }

    @Test
    public void testBlockFaceAtPositionIsHidden_atTopEdgeOfIsland_returnsFalse() {
        Island testCandidate = new Island(64);
        
        Block testBlock = GrassyEarthBlock.getInstance();
        testCandidate.placeBlockAt(testBlock, 0, 255, 0);
        
        boolean result = testCandidate.blockFaceAtPositionIsHidden(BlockFaceDirections.TOP, 0, 255, 0);
        
        assertThat(result, is(false));
    }    
}