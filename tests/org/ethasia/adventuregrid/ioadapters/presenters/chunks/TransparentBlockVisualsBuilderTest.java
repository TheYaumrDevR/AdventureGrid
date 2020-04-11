package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.PortalBlock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TransparentBlockVisualsBuilderTest {
    
    @Test
    public void testGetShapeVertices_noBlockIsSet_resultIsEmpty() {
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        
        testCandidate.build();
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(0));
    } 

    @Test
    public void testGetShapeVertices_noFaceIsHidden_allVerticesAreReturned() {
        float[] expected = {
            0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.5f,
            0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f,
            0.5f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, 0.5f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.5f,
            0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, 0.5f,
            0.5f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.5f,
            0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f,
            0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.5f
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = PortalBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .build();
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length));      
        assertFloatsAreEqual(result, expected);    
    }   
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void assertFloatsAreEqual(float[] result, float[] expected) {
        assertThat(result.length, is(expected.length));
        
        for (int i = 0; i < expected.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    }    
    
    //</editor-fold>
}