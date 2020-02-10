package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.BlockTypes;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class BlockTest {
    
    @Test
    public void testCreateWithType_typeIsSet() {
        Block testCandidate = new Block(BlockTypes.ROCK);
        BlockTypes blockType = testCandidate.getBlockType();
        
        assertThat(blockType, is(BlockTypes.ROCK));
    }
}