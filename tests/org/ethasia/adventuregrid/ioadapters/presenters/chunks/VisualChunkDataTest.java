package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class VisualChunkDataTest {
    
    @Test
    public void testSetWorldPosition_isSetCorrectly() {
        VisualChunkData testCandidate = new VisualChunkData();
        
        testCandidate.setWorldPosition(5, 9);
        
        assertThat(testCandidate.getWorldX(), is(5));
        assertThat(testCandidate.getWorldY(), is(9));
    }
    
    @Test
    public void testAddVerticesToTemporaryBuffer_getFlattenedVertices_containsAllAddedVertices() {
        VisualChunkData testCandidate = new VisualChunkData();
        
        float[] vertices = { 0.2f, 0.3f, 0.4f, 0.3f, 0.7f, 0.1f };
        float[] secondVertices = { 0.3f, 0.4f, 0.5f, 0.6f, 0.8f, 0.3f };
        
        testCandidate.setUpWithNumberOfBlocksInChunk(2);
        testCandidate.addVerticesToTemporaryBuffer(vertices);
        testCandidate.addVerticesToTemporaryBuffer(secondVertices);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]);  
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);      
        
        testCandidate.buildChunkData();
        float[] result = testCandidate.getVertices();
        
        assertThat(result.length, is(12));
        assertThat(result[0], is(0.2f));
        assertThat(result[1], is(0.3f));
        assertThat(result[2], is(0.4f));
        assertThat(result[3], is(0.3f));
        assertThat(result[4], is(0.7f));
        assertThat(result[5], is(0.1f));
        assertThat(result[6], is(0.3f));
        assertThat(result[7], is(0.4f));    
        assertThat(result[8], is(0.5f));
        assertThat(result[9], is(0.6f));
        assertThat(result[10], is(0.8f));
        assertThat(result[11], is(0.3f));         
    }
    
    @Test
    public void testAddIndicesToTemporaryBuffer_getFlattenedIndices_containsAllAddedIndices() {
        VisualChunkData testCandidate = new VisualChunkData();
        
        int[] indices = { 2, 4 };
        int[] secondIndices = { 5, 7 };
        int[] thirdIndices = { 9, 8 };
        
        testCandidate.setUpWithNumberOfBlocksInChunk(3);
        testCandidate.addIndicesToTemporaryBuffer(indices);
        testCandidate.addIndicesToTemporaryBuffer(secondIndices);
        testCandidate.addIndicesToTemporaryBuffer(thirdIndices);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]); 
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);        
        
        testCandidate.buildChunkData();
        int[] result = testCandidate.getIndices();
        
        assertThat(result.length, is(6));
        assertThat(result[0], is(2));
        assertThat(result[1], is(4));
        assertThat(result[2], is(5));
        assertThat(result[3], is(7));
        assertThat(result[4], is(9));
        assertThat(result[5], is(8));       
    } 
    
    @Test
    public void testAddNormalsToTemporaryBuffer_getFlattenedNormals_containsAllAddedNormals() {    
        VisualChunkData testCandidate = new VisualChunkData();
        
        float[] normals = { 1.4f, 2.3f };
        float[] secondNormals = { 5.4f, 7.1f };
        float[] thirdNormals = { 0.4f, 1.5f };     
        
        testCandidate.setUpWithNumberOfBlocksInChunk(3);
        testCandidate.addNormalsToTemporaryBuffer(normals);
        testCandidate.addNormalsToTemporaryBuffer(secondNormals);
        testCandidate.addNormalsToTemporaryBuffer(thirdNormals);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);   
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);
        testCandidate.addUvCoordinatesToTemporaryBuffer(new float[0]);
        
        testCandidate.buildChunkData();
        float[] result = testCandidate.getNormals();  
        
        assertThat(result.length, is(6));
        assertThat(result[0], is(1.4f));
        assertThat(result[1], is(2.3f));
        assertThat(result[2], is(5.4f));
        assertThat(result[3], is(7.1f));
        assertThat(result[4], is(0.4f));
        assertThat(result[5], is(1.5f));         
    }
    
    @Test
    public void testAddUvCoordinatesToTemporaryBuffer_getFlattenedUvCoordinates_containsAllAddedUvCoordinates() {    
        VisualChunkData testCandidate = new VisualChunkData();
        
        float[] uvCoordinates = { 0.4f, 1.5f };
        float[] secondUvCoordinates = { 9.1f, 4.4f };
        float[] thirdUvCoordinates = { 7.7f, 8.3f };     
        
        testCandidate.setUpWithNumberOfBlocksInChunk(3);
        testCandidate.addUvCoordinatesToTemporaryBuffer(uvCoordinates);
        testCandidate.addUvCoordinatesToTemporaryBuffer(secondUvCoordinates);
        testCandidate.addUvCoordinatesToTemporaryBuffer(thirdUvCoordinates);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]);
        testCandidate.addNormalsToTemporaryBuffer(new float[0]);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addIndicesToTemporaryBuffer(new int[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);
        testCandidate.addVerticesToTemporaryBuffer(new float[0]);   
        
        testCandidate.buildChunkData();
        float[] result = testCandidate.getUvCoordinates();  
        
        assertThat(result.length, is(6));
        assertThat(result[0], is(0.4f));
        assertThat(result[1], is(1.5f));
        assertThat(result[2], is(9.1f));
        assertThat(result[3], is(4.4f));
        assertThat(result[4], is(7.7f));
        assertThat(result[5], is(8.3f));         
    }    
}