package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.EarthBlock;
import org.ethasia.adventuregrid.core.environment.GrassyEarthBlock;
import org.ethasia.adventuregrid.core.environment.RockBlock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class StandardBlockVisualsBuilderTest {
    
    @Test
    public void testGetShapeVertices_noBlockIsSet_resultIsEmpty() {
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        
        testCandidate.build();
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(0));
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
        float[] expected = {
            3.f, 6.5f, 1.5f, 3.f, 7.f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 6.5f, 1.5f,
            3.f, 6.5f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 7.f, 1.5f, 3.f, 6.5f, 1.5f,
            2.5f, 6.5f, 1.0f, 2.5f, 7.f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 6.5f, 1.0f,
            2.5f, 6.5f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 7.f, 1.0f, 2.5f, 6.5f, 1.0f,
            3.f, 6.5f, 1.0f, 3.f, 6.5f, 1.5f, 2.5f, 6.5f, 1.5f, 2.5f, 6.5f, 1.0f,
            3.f, 7.f, 1.5f, 3.f, 7.f, 1.0f, 2.5f, 7.f, 1.0f, 2.5f, 7.f, 1.5f
        };        
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance();

        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .build();    
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length)); 
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }
    }     
    
    @Test
    public void testGetShapeVertices_frontFaceIsHidden_frontFaceVerticesMissingInResult() {
        float[] expected = {
            3.f, 6.5f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 7.f, 1.5f, 3.f, 6.5f, 1.5f,
            2.5f, 6.5f, 1.0f, 2.5f, 7.f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 6.5f, 1.0f,
            2.5f, 6.5f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 7.f, 1.0f, 2.5f, 6.5f, 1.0f,
            3.f, 6.5f, 1.0f, 3.f, 6.5f, 1.5f, 2.5f, 6.5f, 1.5f, 2.5f, 6.5f, 1.0f,
            3.f, 7.f, 1.5f, 3.f, 7.f, 1.0f, 2.5f, 7.f, 1.0f, 2.5f, 7.f, 1.5f
        };            
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setFrontFaceOfBlockIsCovered(true)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();
        
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    }

    @Test
    public void testGetShapeVertices_rightFaceIsHidden_rightFaceVerticesMissingInResult() {
        float[] expected = {
            3.f, 6.5f, 1.5f, 3.f, 7.f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 6.5f, 1.5f,
            2.5f, 6.5f, 1.0f, 2.5f, 7.f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 6.5f, 1.0f,
            2.5f, 6.5f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 7.f, 1.0f, 2.5f, 6.5f, 1.0f,
            3.f, 6.5f, 1.0f, 3.f, 6.5f, 1.5f, 2.5f, 6.5f, 1.5f, 2.5f, 6.5f, 1.0f,
            3.f, 7.f, 1.5f, 3.f, 7.f, 1.0f, 2.5f, 7.f, 1.0f, 2.5f, 7.f, 1.5f
        };              
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setRightFaceOfBlockIsCovered(true)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();
        
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    } 
    
    @Test
    public void testGetShapeVertices_backFaceIsHidden_backFaceVerticesMissingInResult() {
        float[] expected = {
            3.f, 6.5f, 1.5f, 3.f, 7.f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 6.5f, 1.5f,
            3.f, 6.5f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 7.f, 1.5f, 3.f, 6.5f, 1.5f,
            2.5f, 6.5f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 7.f, 1.0f, 2.5f, 6.5f, 1.0f,
            3.f, 6.5f, 1.0f, 3.f, 6.5f, 1.5f, 2.5f, 6.5f, 1.5f, 2.5f, 6.5f, 1.0f,
            3.f, 7.f, 1.5f, 3.f, 7.f, 1.0f, 2.5f, 7.f, 1.0f, 2.5f, 7.f, 1.5f
        };              
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setBackFaceOfBlockIsCovered(true)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();
        
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    } 
    
    @Test
    public void testGetShapeVertices_leftFaceIsHidden_leftFaceVerticesMissingInResult() {
        float[] expected = {
            3.f, 6.5f, 1.5f, 3.f, 7.f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 6.5f, 1.5f,
            3.f, 6.5f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 7.f, 1.5f, 3.f, 6.5f, 1.5f,
            2.5f, 6.5f, 1.0f, 2.5f, 7.f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 6.5f, 1.0f,
            3.f, 6.5f, 1.0f, 3.f, 6.5f, 1.5f, 2.5f, 6.5f, 1.5f, 2.5f, 6.5f, 1.0f,
            3.f, 7.f, 1.5f, 3.f, 7.f, 1.0f, 2.5f, 7.f, 1.0f, 2.5f, 7.f, 1.5f
        };              
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setLeftFaceOfBlockIsCovered(true)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();
        
        for (int i = 0; i < result.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    }    
}