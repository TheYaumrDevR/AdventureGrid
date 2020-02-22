package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.EarthBlock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class StandardBlockVisualsBuilderTest {
    
    @Test
    public void testGetShapeVertices_noBlockIsSet_resultIsEmpty() {
        
    }    
    
    @Test
    public void testGetShapeVertices_noFaceIsHidden_allVerticesAreReturned() {
        float[] expected = {
            0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, 0.5f,
            0.5f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.5f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.5f
        };
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .build();
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length));
        
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }
    }
    
    @Test
    public void testGetShapeVertices_blockIsTranslated_allVerticesAreReturned() {
        
    }     
    
    @Test
    public void testGetShapeVertices_frontFaceIsHidden_frontFaceVerticesMissingInResult() {
        
    }    
}