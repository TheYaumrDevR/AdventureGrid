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
        assertFloatsAreEqual(result, expected);    
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
        assertFloatsAreEqual(result, expected);    
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
        
        assertFloatsAreEqual(result, expected);            
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
        
        assertFloatsAreEqual(result, expected);            
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
        
        assertFloatsAreEqual(result, expected);           
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
        
        assertFloatsAreEqual(result, expected);            
    }

    @Test
    public void testGetShapeVertices_bottomFaceIsHidden_bottomFaceVerticesMissingInResult() {
        float[] expected = {
            3.f, 6.5f, 1.5f, 3.f, 7.f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 6.5f, 1.5f,
            3.f, 6.5f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 7.f, 1.5f, 3.f, 6.5f, 1.5f,
            2.5f, 6.5f, 1.0f, 2.5f, 7.f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 6.5f, 1.0f,
            2.5f, 6.5f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 7.f, 1.0f, 2.5f, 6.5f, 1.0f,
            3.f, 7.f, 1.5f, 3.f, 7.f, 1.0f, 2.5f, 7.f, 1.0f, 2.5f, 7.f, 1.5f
        };             
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setBottomFaceOfBlockIsCovered(true)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();
        
        assertFloatsAreEqual(result, expected);        
    } 
    
    @Test
    public void testGetShapeVertices_topFaceIsHidden_topFaceVerticesMissingInResult() {
        float[] expected = {
            3.f, 6.5f, 1.5f, 3.f, 7.f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 6.5f, 1.5f,
            3.f, 6.5f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 7.f, 1.5f, 3.f, 6.5f, 1.5f,
            2.5f, 6.5f, 1.0f, 2.5f, 7.f, 1.0f, 3.f, 7.f, 1.0f, 3.f, 6.5f, 1.0f,
            2.5f, 6.5f, 1.5f, 2.5f, 7.f, 1.5f, 2.5f, 7.f, 1.0f, 2.5f, 6.5f, 1.0f,
            3.f, 6.5f, 1.0f, 3.f, 6.5f, 1.5f, 2.5f, 6.5f, 1.5f, 2.5f, 6.5f, 1.0f
        };              
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setTopFaceOfBlockIsCovered(true)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();
        
        assertFloatsAreEqual(result, expected);
    }
    
    @Test
    public void testGetShapeVertices_fourFacesAreHidden_resultOnlyHas24Vertices() {
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance();
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setChunkPositionX(5)
            .setChunkPositionY(13)
            .setChunkPositionZ(2)
            .setTopFaceOfBlockIsCovered(true)
            .setLeftFaceOfBlockIsCovered(true)
            .setFrontFaceOfBlockIsCovered(true)
            .setRightFaceOfBlockIsCovered(true)
            .setBlockToCreateDataFrom(testBlock)
            .build();   
        
        float[] result = testCandidate.getShapeVertices();

        assertThat(result.length, is(24));
    }    

    @Test
    public void testGetShapeIndices_noBlockIsSet_returnsEmptyBuffer() {
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        
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
            20, 21, 22, 22, 23, 20
        };
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
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
            140, 141, 142, 142, 143, 140
        };        
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setRenderIndexInChunk(5)
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
            136, 137, 138, 138, 139, 136
        }; 
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setFrontFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(5)
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
            136, 137, 138, 138, 139, 136
        }; 
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setRightFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(5)
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
            136, 137, 138, 138, 139, 136
        }; 
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setBackFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(5)
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
            136, 137, 138, 138, 139, 136
        }; 
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setLeftFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(5)
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
            136, 137, 138, 138, 139, 136
        }; 
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setBottomFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(5)
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
            136, 137, 138, 138, 139, 136
        }; 
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = GrassyEarthBlock.getInstance(); 
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setTopFaceOfBlockIsCovered(true)
            .setRenderIndexInChunk(5)
            .build();
        
        int[] result = testCandidate.getShapeIndices();
        
        assertIntegersAreEqual(result, expected);        
    } 
    
    @Test
    public void testGetShapeNormals_noBlockIsSet_returnsEmptyBuffer() {
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();
        
        testCandidate.build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertThat(result.length, is(0));
    }
    
    @Test
    public void testGetShapeNormals_noBlockFacesCovered_returnsFullNormalsBuffer() {
        float[] expected = {
            0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f,
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = EarthBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }
    
    @Test
    public void testGetShapeNormals_frontFaceIsCovered_frontFaceNormalsAreNotReturned() {
        float[] expected = {
            1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f,
            0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f,
            -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f,
            0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f, 0.f, -1.f, 0.f,
            0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f, 0.f, 1.f, 0.f
        };
        
        StandardBlockVisualsBuilder testCandidate = new StandardBlockVisualsBuilder();
        Block testBlock = RockBlock.getInstance();   
        
        testCandidate.setBlockToCreateDataFrom(testBlock)
            .setFrontFaceOfBlockIsCovered(true)
            .build();
        
        float[] result = testCandidate.getShapeNormals();
        
        assertFloatsAreEqual(result, expected);
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void assertFloatsAreEqual(float[] result, float[] expected) {
        for (int i = 0; i < expected.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    }
    
    private void assertIntegersAreEqual(int[] result, int[] expected) {
        for (int i = 0; i < expected.length; i++) {
            assertThat(result[i], is(equalTo(expected[i])));
        }        
    }    
    
    //</editor-fold>
}