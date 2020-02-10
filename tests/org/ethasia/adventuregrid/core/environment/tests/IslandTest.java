package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockPositionOutOfBoundsException;
import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.Island;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IslandTest {
    
    @Test
    public void testGetBlockAt_blockIsNotInitialized_isNull() {
        Island testCandidate = new Island(64);
        
        Block blockAt = testCandidate.getBlockAt(63, 0, 63);
        
        assertThat(blockAt, is(nullValue()));
    }
    
    @Test
    public void testGetBlockAt_blockIsGrassBlock_returnsBlock() {
        Island testCandidate = new Island(64);
        Block testBlock = new Block(BlockTypes.GRASSY_EARTH);
        
        testCandidate.placeBlockAt(testBlock, 63, 0, 63);
        
        Block blockAt = testCandidate.getBlockAt(63, 0, 63);
        
        assertThat(blockAt, is(testBlock));
    }
    
    @Test
    public void testPlaceBlockAt_placeAtHeight255_works() {
        Island testCandidate = new Island(64);
        Block testBlock = new Block(BlockTypes.EARTH);   
        
        testCandidate.placeBlockAt(testBlock, 63, 255, 63);
        
        Block blockAt = testCandidate.getBlockAt(63, 255, 63);
        
        assertThat(blockAt, is(testBlock));        
    }   
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testPlaceBlockAt_placeAtHeight256_throwsException() {
        Island testCandidate = new Island(64);
        Block testBlock = new Block(BlockTypes.ROCK);   
        
        testCandidate.placeBlockAt(testBlock, 63, 256, 63);        
    }
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testPlaceBlockAt_placeOutsideX_throwsException() {
        Island testCandidate = new Island(64);
        Block testBlock = new Block(BlockTypes.GRASSY_EARTH);   
        
        testCandidate.placeBlockAt(testBlock, 64, 200, 24);         
    }  
    
    @Test(expected = BlockPositionOutOfBoundsException.class)
    public void testPlaceBlockAt_placeOutsideZ_throwsException() {
        Island testCandidate = new Island(64);
        Block testBlock = new Block(BlockTypes.EARTH);   
        
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
}