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
        
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = 0; k < 126; k++) {
                    assertThat(result.getBlockAt(i, k, j).getBlockType(), is(BlockTypes.ROCK));
                }
            }
        }
        
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = 126; k < 127; k++) {
                    assertThat(result.getBlockAt(i, k, j).getBlockType(), is(BlockTypes.EARTH));
                }
            }
        } 
        
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = 127; k < 128; k++) {
                    assertThat(result.getBlockAt(i, k, j).getBlockType(), is(BlockTypes.GRASSY_EARTH));
                }
            }
        }        
    }
}