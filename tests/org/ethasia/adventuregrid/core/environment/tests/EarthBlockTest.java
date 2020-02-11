package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.EarthBlock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EarthBlockTest {

    @Test
    public void testGetBlockType_isEarth() {
        EarthBlock testCandidate = EarthBlock.getInstance();
        
        assertThat(testCandidate, is(notNullValue()));
        assertThat(testCandidate.getBlockType(), is(BlockTypes.EARTH));
    }   
    
    @Test
    public void testGetFaceIsCovering_allAreCovering() {
        EarthBlock testCandidate = EarthBlock.getInstance();
        
        assertThat(testCandidate.getRightFaceIsCovering(), is(true));
        assertThat(testCandidate.getFrontFaceIsCovering(), is(true));
        assertThat(testCandidate.getLeftFaceIsCovering(), is(true));
        assertThat(testCandidate.getBackFaceIsCovering(), is(true));
        assertThat(testCandidate.getBottomFaceIsCovering(), is(true));
        assertThat(testCandidate.getTopFaceIsCovering(), is(true));
    }     
}