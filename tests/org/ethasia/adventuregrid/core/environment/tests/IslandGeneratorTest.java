package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.BlockTypes;
import org.ethasia.adventuregrid.core.environment.Island;
import org.ethasia.adventuregrid.core.environment.IslandGenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IslandGeneratorTest {
    
    @Test
    public void testGenerateIsland_generatesIslandWithRockAndEarthLayer() {
        IslandGenerator testCandidate = new IslandGenerator();
        
        Island result = testCandidate.generateIsland(64);
        
        assertThat(result.getXzDimension(), is(64));
        assertThatLayersFromToAreOfType(0, 126, result, BlockTypes.ROCK); 
        assertThatLayersFromToAreOfType(126, 127, result, BlockTypes.EARTH); 
        assertThatLayersFromToAreOfType(127, 128, result, BlockTypes.GRASSY_EARTH);       
    }
    
    private void assertThatLayersFromToAreOfType(int fromHeight, int toHeight, Island result, BlockTypes expected) {
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = fromHeight; k < toHeight; k++) {
                    assertThat(result.getBlockAt(i, k, j).getBlockType(), is(expected));
                }
            }
        }        
    }
}