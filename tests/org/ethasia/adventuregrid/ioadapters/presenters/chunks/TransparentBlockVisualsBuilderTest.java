package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.environment.EarthBlock;
import org.ethasia.adventuregrid.core.environment.GrassyEarthBlock;
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
    
    @Test
    public void testGetShapeVertices_frontFaceIsHidden_frontFaceVerticesMissingInResult() {
        float[] expected = {
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.0f, 6.5f, 9.0f, 2.0f, 6.5f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f,
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
            .setFrontFaceOfBlockIsCovered(true)
            .build();    
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length)); 
        assertFloatsAreEqual(result, expected);            
    }

    @Test
    public void testGetShapeVertices_rightFaceIsHidden_rightFaceVerticesMissingInResult() {
        float[] expected = {
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.0f, 6.5f, 9.0f, 2.0f, 6.5f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
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
            .setRightFaceOfBlockIsCovered(true)
            .build();    
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length)); 
        assertFloatsAreEqual(result, expected);           
    }    
    
    @Test
    public void testGetShapeVertices_backFaceIsHidden_backFaceVerticesMissingInResult() {
        float[] expected = {
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.0f, 6.5f, 9.0f, 2.0f, 6.5f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
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
            .setBackFaceOfBlockIsCovered(true)
            .build();    
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length)); 
        assertFloatsAreEqual(result, expected);           
    } 
    
    @Test
    public void testGetShapeVertices_leftFaceIsHidden_leftFaceVerticesMissingInResult() {
        float[] expected = {
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.0f, 6.5f, 9.0f, 2.0f, 6.5f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.0f, 6.5f, 8.5f, 2.0f, 6.5f, 8.5f, 2.0f, 6.0f,
            9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f
        };       
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance();

        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(17)
            .setChunkPositionY(4)
            .setChunkPositionZ(12)
            .setLeftFaceOfBlockIsCovered(true)
            .build();    
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length)); 
        assertFloatsAreEqual(result, expected);         
    }

    @Test
    public void testGetShapeVertices_bottomFaceIsHidden_bottomFaceVerticesMissingInResult() {
        float[] expected = {
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f
        };       
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance();

        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(17)
            .setChunkPositionY(4)
            .setChunkPositionZ(12)
            .setBottomFaceOfBlockIsCovered(true)    
            .build();    
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length)); 
        assertFloatsAreEqual(result, expected);         
    } 
    
    @Test
    public void testGetShapeVertices_topFaceIsHidden_topFaceVerticesMissingInResult() {
        float[] expected = {
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.0f, 6.5f, 9.0f, 2.0f, 6.5f, 9.0f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.5f, 9.0f, 2.5f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.0f, 6.5f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.5f, 6.5f, 9.0f, 2.0f, 6.5f,
            8.5f, 2.0f, 6.0f, 8.5f, 2.5f, 6.0f, 9.0f, 2.5f, 6.0f, 9.0f, 2.0f, 6.0f,
            8.5f, 2.0f, 6.5f, 8.5f, 2.5f, 6.5f, 8.5f, 2.5f, 6.0f, 8.5f, 2.0f, 6.0f,
            9.0f, 2.0f, 6.0f, 9.0f, 2.0f, 6.5f, 8.5f, 2.0f, 6.5f, 8.5f, 2.0f, 6.0f
        };       
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance();

        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(17)
            .setChunkPositionY(4)
            .setChunkPositionZ(12)
            .setTopFaceOfBlockIsCovered(true) 
            .build();    
        
        float[] result = testCandidate.getShapeVertices();
        
        assertThat(result.length, is(expected.length)); 
        assertFloatsAreEqual(result, expected);        
    } 
    
    @Test
    public void testGetShapeVertices_fourFacesAreHidden_resultOnlyHas48Vertices() {
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setTopFaceOfBlockIsCovered(true)
            .setBottomFaceOfBlockIsCovered(true)
            .setBackFaceOfBlockIsCovered(true)
            .setLeftFaceOfBlockIsCovered(true)
            .setBlockToCreateDataFrom(testBlock)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();

        assertThat(result.length, is(48));        
    }  
    
    @Test
    public void testGetShapeIndices_noBlockIsSet_returnsEmptyBuffer() {
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        
        testCandidate.build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertThat(result.length, is(0));
    }  
    
    @Test
    public void testGetShapeIndices_noFaceIsHidden_allIndicesAreReturned() {
        int[] expected = {
            0, 1, 2, 2, 3, 0,
            4, 5, 6, 6, 7, 4,
            8, 9, 10, 10, 11, 8,
            12, 13, 14, 14, 15, 12,
            16, 17, 18, 18, 19, 16,
            20, 21, 22, 22, 23, 20,
            24, 25, 26, 26, 27, 24,
            28, 29, 30, 30, 31, 28,
            32, 33, 34, 34, 35, 32,
            36, 37, 38, 38, 39, 36,
            40, 41, 42, 42, 43, 40,
            44, 45, 46, 46, 47, 44
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .build();

        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);       
    } 
    
    @Test
    public void testGetShapeIndices_sixthBlockInChunk_indicesAreTranslatedByBlockIndex() {
        int[] expected = {
            120, 121, 122, 122, 123, 120,
            124, 125, 126, 126, 127, 124,
            128, 129, 130, 130, 131, 128,
            132, 133, 134, 134, 135, 132,
            136, 137, 138, 138, 139, 136,
            140, 141, 142, 142, 143, 140,
            144, 145, 146, 146, 147, 144,
            148, 149, 150, 150, 151, 148,
            152, 153, 154, 154, 155, 152,
            156, 157, 158, 158, 159, 156,
            160, 161, 162, 162, 163, 160,
            164, 165, 166, 166, 167, 164
        };        
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setRenderIndexInChunk(120)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);         
    }

    @Test
    public void testGetshapeIndices_frontFaceIsCovered_frontFaceIndicesAreNotPresent() {
        int[] expected = {
            120, 121, 122, 122, 123, 120,
            124, 125, 126, 126, 127, 124,
            128, 129, 130, 130, 131, 128,
            132, 133, 134, 134, 135, 132,
            136, 137, 138, 138, 139, 136,
            140, 141, 142, 142, 143, 140,
            144, 145, 146, 146, 147, 144,
            148, 149, 150, 150, 151, 148,
            152, 153, 154, 154, 155, 152,
            156, 157, 158, 158, 159, 156
        }; 
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setFrontFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(120)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);        
    }

    @Test
    public void testGetshapeIndices_rightFaceIsCovered_rightFaceIndicesAreNotPresent() {
        int[] expected = {
            120, 121, 122, 122, 123, 120,
            124, 125, 126, 126, 127, 124,
            128, 129, 130, 130, 131, 128,
            132, 133, 134, 134, 135, 132,
            136, 137, 138, 138, 139, 136,
            140, 141, 142, 142, 143, 140,
            144, 145, 146, 146, 147, 144,
            148, 149, 150, 150, 151, 148,
            152, 153, 154, 154, 155, 152,
            156, 157, 158, 158, 159, 156
        }; 
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setRightFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(120)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);        
    }

    @Test
    public void testGetshapeIndices_backFaceIsCovered_backFaceIndicesAreNotPresent() {
        int[] expected = {
            120, 121, 122, 122, 123, 120,
            124, 125, 126, 126, 127, 124,
            128, 129, 130, 130, 131, 128,
            132, 133, 134, 134, 135, 132,
            136, 137, 138, 138, 139, 136,
            140, 141, 142, 142, 143, 140,
            144, 145, 146, 146, 147, 144,
            148, 149, 150, 150, 151, 148,
            152, 153, 154, 154, 155, 152,
            156, 157, 158, 158, 159, 156
        }; 
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setBackFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(120)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);        
    }

    @Test
    public void testGetshapeIndices_leftFaceIsCovered_leftFaceIndicesAreNotPresent() {
        int[] expected = {
            120, 121, 122, 122, 123, 120,
            124, 125, 126, 126, 127, 124,
            128, 129, 130, 130, 131, 128,
            132, 133, 134, 134, 135, 132,
            136, 137, 138, 138, 139, 136,
            140, 141, 142, 142, 143, 140,
            144, 145, 146, 146, 147, 144,
            148, 149, 150, 150, 151, 148,
            152, 153, 154, 154, 155, 152,
            156, 157, 158, 158, 159, 156
        }; 
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setLeftFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(120)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);        
    } 
    
    @Test
    public void testGetshapeIndices_bottomFaceIsCovered_bottomFaceIndicesAreNotPresent() {
        int[] expected = {
            120, 121, 122, 122, 123, 120,
            124, 125, 126, 126, 127, 124,
            128, 129, 130, 130, 131, 128,
            132, 133, 134, 134, 135, 132,
            136, 137, 138, 138, 139, 136,
            140, 141, 142, 142, 143, 140,
            144, 145, 146, 146, 147, 144,
            148, 149, 150, 150, 151, 148,
            152, 153, 154, 154, 155, 152,
            156, 157, 158, 158, 159, 156
        }; 
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setBottomFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(120)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);        
    }

    @Test
    public void testGetshapeIndices_topFaceIsCovered_topFaceIndicesAreNotPresent() {
        int[] expected = {
            120, 121, 122, 122, 123, 120,
            124, 125, 126, 126, 127, 124,
            128, 129, 130, 130, 131, 128,
            132, 133, 134, 134, 135, 132,
            136, 137, 138, 138, 139, 136,
            140, 141, 142, 142, 143, 140,
            144, 145, 146, 146, 147, 144,
            148, 149, 150, 150, 151, 148,
            152, 153, 154, 154, 155, 152,
            156, 157, 158, 158, 159, 156
        }; 
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setTopFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(120)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);        
    } 
    
    @Test
    public void testGetAmountOfAddedIndices_allFacesVisible_added48() {        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .build();        
        
        int result = testCandidate.getAmountOfAddedIndices();
        
        assertThat(result, is(48));
    }
    
    @Test
    public void testGetAmountOfAddedIndices_oneFaceHidden_added40() {        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setLeftFaceOfBlockIsCovered(true)
            .build();        
        
        int result = testCandidate.getAmountOfAddedIndices();
        
        assertThat(result, is(40));
    } 
    
    @Test
    public void testGetAmountOfAddedIndices_oneFaceHidden_added32() {        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setLeftFaceOfBlockIsCovered(true)
            .setTopFaceOfBlockIsCovered(true)
            .build();        
        
        int result = testCandidate.getAmountOfAddedIndices();
        
        assertThat(result, is(32));
    }    
    
    @Test
    public void testGetShapeNormals_noBlockIsSet_returnsEmptyBuffer() {
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        
        testCandidate.build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertThat(result.length, is(0));
    } 
    
    @Test
    public void testGetShapeNormals_noBlockFacesCovered_returnsFullNormalsBuffer() {
        float[] expected = {
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }   
    
    @Test
    public void testGetShapeNormals_frontFaceIsCovered_frontFaceNormalsAreNotReturned() {
        float[] expected = {
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setFrontFaceOfBlockIsCovered(true)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }   
    
    @Test
    public void testGetShapeNormals_rightFaceIsCovered_rightFaceNormalsAreNotReturned() {
        float[] expected = {
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setRightFaceOfBlockIsCovered(true)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }

    @Test
    public void testGetShapeNormals_backFaceIsCovered_backFaceNormalsAreNotReturned() {
        float[] expected = {
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setBackFaceOfBlockIsCovered(true)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }  
    
    @Test
    public void testGetShapeNormals_leftFaceIsCovered_leftFaceNormalsAreNotReturned() {
        float[] expected = {
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setLeftFaceOfBlockIsCovered(true)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }

    @Test
    public void testGetShapeNormals_bottomFaceIsCovered_bottomFaceNormalsAreNotReturned() {
        float[] expected = {
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setBottomFaceOfBlockIsCovered(true)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }   
    
    @Test
    public void testGetShapeNormals_topFaceIsCovered_topFaceNormalsAreNotReturned() {
        float[] expected = {
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f,
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
        };
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setTopFaceOfBlockIsCovered(true)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }    
    
    @Test
    public void testGetBlockUvCoordinates_noBlockIsSet_returnsEmptyBuffer() {
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        
        testCandidate.build();
        
        float[] result = testCandidate.getBlockUvCoordinates();
        
        assertThat(result.length, is(0));
    }

    @Test
    public void testGetBlockUvCoordinates_portalBlockIsSet_returnsPortalBlockUvs() {
        float[] expected = mergeArrays(PortalBlockUvCoordinates.getInstance().getBackUvCoordinates(), PortalBlockUvCoordinates.getInstance().getUvCoordinates());
        
        TransparentBlockVisualsBuilder testCandidate = new TransparentBlockVisualsBuilder();
        Block testBlock = PortalBlock.getInstance(); 

        testCandidate.setBlockToCreateDataFrom(testBlock)
            .build();     
        
        float[] result = testCandidate.getBlockUvCoordinates();
        
        assertFloatsAreEqual(result, expected);        
    }      
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void assertFloatsAreEqual(float[] result, float[] expected) {
        assertThat(result.length, is(expected.length));
        
        for (int i = 0; i < expected.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    }   
    
    private void assertIntegersAreEqual(int[] result, int[] expected) {
        assertThat(result.length, is(expected.length));
        
        for (int i = 0; i < expected.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    }    
    
    private float[] mergeArrays(float[] firstArray, float[] secondArray) {
        float[] result = new float[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, result, 0, firstArray.length);  
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);  
        
        return result;
    }
    
    //</editor-fold>
}