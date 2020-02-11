package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.AirBlock;
import org.ethasia.adventuregrid.core.environment.BlockTypes;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AirBlockTest {

    @Test
    public void testGetBlockType_isAir() {
        AirBlock testCandidate = AirBlock.getInstance();
        
        assertThat(testCandidate, is(notNullValue()));
        assertThat(testCandidate.getBlockType(), is(BlockTypes.AIR));
    }  

    @Test
    public void testGetFaceIsCovering_noneAreCovering() {
        AirBlock testCandidate = AirBlock.getInstance();
        
        assertThat(testCandidate.getRightFaceIsCovering(), is(false));
        assertThat(testCandidate.getFrontFaceIsCovering(), is(false));
        assertThat(testCandidate.getLeftFaceIsCovering(), is(false));
        assertThat(testCandidate.getBackFaceIsCovering(), is(false));
        assertThat(testCandidate.getBottomFaceIsCovering(), is(false));
        assertThat(testCandidate.getTopFaceIsCovering(), is(false));
    }    
}