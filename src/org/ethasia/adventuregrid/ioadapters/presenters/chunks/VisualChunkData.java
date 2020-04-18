package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

public class VisualChunkData {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private boolean isOpaqueChunk;
    
    private int worldX;
    private int worldY;
    
    private float[][] verticesOfBlocks;   
    private int[][] indicesOfBlocks;
    private float[][] normalsOfBlocks;
    private float[][] uvCoordinatesOfBlocks;
    
    private int currentVertexAmount;    
    private int currentIndexAmount;
    private int currentNormalsAmount;
    private int currentUvAmount;
    
    private int numberOfTimesVerticesAdded;    
    private int numberOfTimesIndicesAdded;
    private int numberOfTimesNormalsAdded;
    private int numberOfTimesUvAdded;
    
    private float[] allVerticesFlattened; 
    private int[] allIndicesFlattened;
    private float[] allNormalsFlattened;
    private float[] allUvCoordinatesFlattened;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean isOpaqueChunk() {
        return isOpaqueChunk;
    }
    
    public void setIsOpaqueChunk(boolean value) {
        isOpaqueChunk = value;
    }
    
    public void setWorldPosition(int x, int y) {
        worldX = x;
        worldY = y;
    }
    
    public int getWorldX() {
        return worldX;
    }
    
    public int getWorldY() {
        return worldY;
    }
    
    public float[] getVertices() {
        return allVerticesFlattened;
    }
    
    public int[] getIndices() {
        return allIndicesFlattened;
    }
    
    public float[] getNormals() {
        return allNormalsFlattened;
    }
    
    public float[] getUvCoordinates() {
        return allUvCoordinatesFlattened;
    }
    
    public void setUpWithNumberOfBlocksInChunk(int numberOfBlocksInChunk) {
        verticesOfBlocks = new float[numberOfBlocksInChunk][];
        indicesOfBlocks = new int[numberOfBlocksInChunk][];
        normalsOfBlocks = new float[numberOfBlocksInChunk][];
        uvCoordinatesOfBlocks = new float[numberOfBlocksInChunk][];
    }
    
    public void addVerticesToTemporaryBuffer(float[] vertices) {
        verticesOfBlocks[numberOfTimesVerticesAdded] = vertices;
        currentVertexAmount += vertices.length;
        numberOfTimesVerticesAdded++;        
    }
    
    public void addIndicesToTemporaryBuffer(int[] indices) {
        indicesOfBlocks[numberOfTimesIndicesAdded] = indices;
        currentIndexAmount += indices.length;   
        numberOfTimesIndicesAdded++;
    }  
    
    public void addNormalsToTemporaryBuffer(float[] normals) {
        normalsOfBlocks[numberOfTimesNormalsAdded] = normals;
        currentNormalsAmount += normals.length;
        numberOfTimesNormalsAdded++;        
    }
    
    public void addUvCoordinatesToTemporaryBuffer(float[] uvCoordinates) {
        uvCoordinatesOfBlocks[numberOfTimesUvAdded] = uvCoordinates;
        currentUvAmount += uvCoordinates.length;
        numberOfTimesUvAdded++;        
    }
    
    public void buildChunkData() {
        flattenVertices();
        flattenIndices();
        flattenNormals();
        flattenUvs();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void flattenVertices() {
        allVerticesFlattened = new float[currentVertexAmount];
        int k = 0;  
        
        for (int i = 0; i < verticesOfBlocks.length; i++) {
            for (int j = 0; j < verticesOfBlocks[i].length; j++) {
                allVerticesFlattened[k] = verticesOfBlocks[i][j];
                k++;
            }
        }        
    } 
    
    private void flattenIndices() {
        allIndicesFlattened = new int[currentIndexAmount];
        int k = 0;  
        
        for (int i = 0; i < indicesOfBlocks.length; i++) {
            for (int j = 0; j < indicesOfBlocks[i].length; j++) {
                allIndicesFlattened[k] = indicesOfBlocks[i][j];
                k++;
            }
        }        
    } 
    
    private void flattenNormals() {
        allNormalsFlattened = new float[currentNormalsAmount];
        int k = 0;  
        
        for (int i = 0; i < normalsOfBlocks.length; i++) {
            for (int j = 0; j < normalsOfBlocks[i].length; j++) {
                allNormalsFlattened[k] = normalsOfBlocks[i][j];
                k++;
            }
        }        
    }    
    
    private void flattenUvs() {
        allUvCoordinatesFlattened = new float[currentUvAmount];
        int k = 0;  
        
        for (int i = 0; i < uvCoordinatesOfBlocks.length; i++) {
            for (int j = 0; j < uvCoordinatesOfBlocks[i].length; j++) {
                allUvCoordinatesFlattened[k] = uvCoordinatesOfBlocks[i][j];
                k++;
            }
        }        
    }    
    
    //</editor-fold>
}