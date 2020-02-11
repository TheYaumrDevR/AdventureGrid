package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.GrassyEarthBlock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class GrassyEarthBlockTest {
 
    @Test
    public void testGetBlockType_isGrassyEarth() {
        GrassyEarthBlock testCandidate = GrassyEarthBlock.getInstance();
        
        assertThat(testCandidate, is(notNullValue()));     
        assertThat(testCandidate.getBlockType(), is(BlockTypes.GRASSY_EARTH));
    }    
}