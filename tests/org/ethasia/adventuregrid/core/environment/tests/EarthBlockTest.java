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
}