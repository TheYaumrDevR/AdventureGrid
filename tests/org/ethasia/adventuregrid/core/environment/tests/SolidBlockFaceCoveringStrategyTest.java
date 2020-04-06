package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.AirBlock;
import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockFaceDirections;
import org.ethasia.adventuregrid.core.environment.EarthBlock;
import org.ethasia.adventuregrid.core.environment.RockBlock;
import org.ethasia.adventuregrid.core.environment.SolidBlockFaceCoveringStrategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SolidBlockFaceCoveringStrategyTest {
    
    @Test
    public void testFaceIsHidden_leftFaceIsCovered_returnsTrue() {
        SolidBlockFaceCoveringStrategy testCandidate = new SolidBlockFaceCoveringStrategy();
        
        Block coveredBlock = RockBlock.getInstance();
        Block coveringBlock = EarthBlock.getInstance();
        
        boolean result = testCandidate.faceIsHidden(coveredBlock, coveringBlock, BlockFaceDirections.LEFT);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testFaceIsHidden_leftFaceIsNotCovered_returnsFalse() {
        SolidBlockFaceCoveringStrategy testCandidate = new SolidBlockFaceCoveringStrategy();
        
        Block coveredBlock = RockBlock.getInstance();
        Block coveringBlock = AirBlock.getInstance();
        
        boolean result = testCandidate.faceIsHidden(coveredBlock, coveringBlock, BlockFaceDirections.LEFT);
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testFaceIsHidden_leftFaceIsNotCovering_returnsFalse() {
        SolidBlockFaceCoveringStrategy testCandidate = new SolidBlockFaceCoveringStrategy();
        
        Block coveredBlock = AirBlock.getInstance();
        Block coveringBlock = EarthBlock.getInstance();
        
        boolean result = testCandidate.faceIsHidden(coveredBlock, coveringBlock, BlockFaceDirections.LEFT);
        
        assertThat(result, is(false));
    }  
}