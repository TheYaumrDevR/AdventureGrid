package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.PortalBlock;
import org.ethasia.adventuregrid.core.environment.RockBlock;
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
    
    @Test
    public void testGetShapeVertices_blockIsTranslated_allVerticesAreReturned() {
        float[] expected = {
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.0f, 6.5f, 9.0f, 2.0f, 6.5f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.0f, 6.5f, 8.5f, 2.0f, 6.5f, 8.5f, 2.0f, 6.0f,
            9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f
        };       
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance();

        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(17)
            .setChunkPositionY(4)
            .setChunkPositionZ(12)
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