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
}