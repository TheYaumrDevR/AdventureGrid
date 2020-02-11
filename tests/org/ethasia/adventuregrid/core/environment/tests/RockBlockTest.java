package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.RockBlock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class RockBlockTest {
    
    @Test
    public void testGetBlockType_isRock() {
        RockBlock testCandidate = RockBlock.getInstance();
        
        assertThat(testCandidate, is(notNullValue()));
        assertThat(testCandidate.getBlockType(), is(BlockTypes.ROCK));
    }
}