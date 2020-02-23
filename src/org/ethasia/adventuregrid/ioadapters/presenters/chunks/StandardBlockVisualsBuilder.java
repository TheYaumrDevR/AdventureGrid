package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.math.Vector3;

public class StandardBlockVisualsBuilder {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    // Base vertices
    private static final Vector3[] BV;
    
    static {
        final Vector3[] blockHalfAxes = {
            Vector3.UNIT_X.scaleImmutable(0.25f),
            Vector3.UNIT_Y.scaleImmutable(0.25f),
            Vector3.UNIT_Z.scaleImmutable(0.25f)
        };   
        
        Vector3 origin = new Vector3(0.f, 0.f, 0.f);
        
        BV = new Vector3[] {
            origin.addImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).add(blockHalfAxes[2]),
            origin.addImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).add(blockHalfAxes[2]),
            origin.subtractImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).add(blockHalfAxes[2]),
            origin.subtractImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).add(blockHalfAxes[2]),
            origin.subtractImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).subtract(blockHalfAxes[2]),
            origin.subtractImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).subtract(blockHalfAxes[2]),
            origin.addImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).subtract(blockHalfAxes[2]),
            origin.addImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).subtract(blockHalfAxes[2])
        };         
    }      
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int chunkPosX, chunkPosY, chunkPosZ;
    private Block blockToRender;
    
    private boolean frontFaceOfBlockIsCovered;
    private boolean rightFaceOfBlockIsCovered;
    private boolean backFaceOfBlockIsCovered;
    private boolean leftFaceOfBlockIsCovered;
    private boolean bottomFaceOfBlockIsCovered;
    private boolean topFaceOfBlockIsCovered;
    
    private float[] vertexBuffer;
    private int[] indicesBuffer;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public StandardBlockVisualsBuilder setBlockToCreateDataFrom(Block value) {
        blockToRender = value;
        return this;
    }   
    
    public StandardBlockVisualsBuilder setChunkPositionX(int value) {
        chunkPosX = value;
        return this;
    }
    
    public StandardBlockVisualsBuilder setChunkPositionY(int value) {
        chunkPosY = value;
        return this;
    }

    public StandardBlockVisualsBuilder setChunkPositionZ(int value) {
        chunkPosZ = value;
        return this;
    }   
    
    public StandardBlockVisualsBuilder setFrontFaceOfBlockIsCovered(boolean value) {
        frontFaceOfBlockIsCovered = value;
        return this;
    }
    
    public StandardBlockVisualsBuilder setRightFaceOfBlockIsCovered(boolean value) {
        rightFaceOfBlockIsCovered = value;
        return this;
    } 
    
    public StandardBlockVisualsBuilder setBackFaceOfBlockIsCovered(boolean value) {
        backFaceOfBlockIsCovered = value;
        return this;
    }
    
    public StandardBlockVisualsBuilder setLeftFaceOfBlockIsCovered(boolean value) {
        leftFaceOfBlockIsCovered = value;
        return this;
    } 
    
    public StandardBlockVisualsBuilder setBottomFaceOfBlockIsCovered(boolean value) {
        bottomFaceOfBlockIsCovered = value;
        return this;
    } 

    public StandardBlockVisualsBuilder setTopFaceOfBlockIsCovered(boolean value) {
        topFaceOfBlockIsCovered = value;
        return this;
    }    
    
    public void build() {
        if (null == blockToRender) {
            vertexBuffer = new float[0];
            indicesBuffer = new int[0];
        } else {
            buildVertexBuffer();
            buildIndicesBuffer();
        }
    }
    
    public float[] getShapeVertices() {
        return vertexBuffer;
    }   
    
    public int[] getShapeIndices() {
        return indicesBuffer;
    }
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void buildIndicesBuffer() {
        indicesBuffer = new int[6 * 6];
        
        // front
        indicesBuffer[0] = 0;
        indicesBuffer[1] = 1;
        indicesBuffer[2] = 2;
        indicesBuffer[3] = 2;
        indicesBuffer[4] = 3;
        indicesBuffer[5] = 0;
        
        // right
        indicesBuffer[6] = 4;
        indicesBuffer[7] = 5;
        indicesBuffer[8] = 6;
        indicesBuffer[9] = 6;
        indicesBuffer[10] = 7;
        indicesBuffer[11] = 4;        
        
        // back
        indicesBuffer[12] = 8;
        indicesBuffer[13] = 9;
        indicesBuffer[14] = 10;
        indicesBuffer[15] = 10;
        indicesBuffer[16] = 11;
        indicesBuffer[17] = 8;         
        
        // left
        indicesBuffer[18] = 12;
        indicesBuffer[19] = 13;
        indicesBuffer[20] = 14;
        indicesBuffer[21] = 14;
        indicesBuffer[22] = 15;
        indicesBuffer[23] = 12;         
        
        // bottom
        indicesBuffer[24] = 16;
        indicesBuffer[25] = 17;
        indicesBuffer[26] = 18;
        indicesBuffer[27] = 18;
        indicesBuffer[28] = 19;
        indicesBuffer[29] = 16;          
        
        // top
        indicesBuffer[30] = 20;
        indicesBuffer[31] = 21;
        indicesBuffer[32] = 22;
        indicesBuffer[33] = 22;
        indicesBuffer[34] = 23;
        indicesBuffer[35] = 20;         
    }
    
    private void buildVertexBuffer() {
        translateVertices();
        fillVertexBuffer();
    }
    
    private void translateVertices() {
        Vector3 cubeCenter = new Vector3(0.25f + 0.5f * chunkPosX, 
            0.25f + 0.5f * chunkPosY, 
            0.25f + 0.5f * chunkPosZ);
        BV[0].addImmutableBufferResult(cubeCenter);
        BV[1].addImmutableBufferResult(cubeCenter);
        BV[2].addImmutableBufferResult(cubeCenter);
        BV[3].addImmutableBufferResult(cubeCenter);
        BV[4].addImmutableBufferResult(cubeCenter);
        BV[5].addImmutableBufferResult(cubeCenter);
        BV[6].addImmutableBufferResult(cubeCenter);
        BV[7].addImmutableBufferResult(cubeCenter);
    }
    
    private void fillVertexBuffer() {
        int amountOfUncoveredFaces = getAmountOfUncoveredFaces();
        vertexBuffer = new float[4 * 3 * amountOfUncoveredFaces];
        int currentBufferPosition = 0;
        
        if (!frontFaceOfBlockIsCovered) {
            setFrontFaceVertices(currentBufferPosition);
            currentBufferPosition += 12;
        }
        
        if (!rightFaceOfBlockIsCovered) {
            setRightFaceVertices(currentBufferPosition);
            currentBufferPosition += 12;        
        }
        
        if (!backFaceOfBlockIsCovered) {
            setBackFaceVertices(currentBufferPosition);
            currentBufferPosition += 12;                    
        }
        
        if (!leftFaceOfBlockIsCovered) {
            setLeftFaceVertices(currentBufferPosition);
            currentBufferPosition += 12;            
        }        
        
        if (!bottomFaceOfBlockIsCovered) {
            setBottomFaceVertices(currentBufferPosition);
            currentBufferPosition += 12;                    
        }
         
        if (!topFaceOfBlockIsCovered) {
            setTopFaceVertices(currentBufferPosition);
        } 
    }
    
    private int getAmountOfUncoveredFaces() {
        int result = 0;
        
        if (!frontFaceOfBlockIsCovered) {
            result++;
        }
        
        if (!rightFaceOfBlockIsCovered) {
            result++;
        } 
        
        if (!backFaceOfBlockIsCovered) {
            result++;
        }
        
        if (!leftFaceOfBlockIsCovered) {
            result++;
        }  
        
        if (!bottomFaceOfBlockIsCovered) {
            result++;
        } 
        
        if (!topFaceOfBlockIsCovered) {
            result++;
        }         

        return result;
    }
    
    private void setFrontFaceVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[0].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[0].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[0].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[1].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[1].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[1].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[2].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[2].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[2].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[3].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[3].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[3].getBufferedResultZ();          
    }
    
    private void setRightFaceVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[7].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[7].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[7].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[6].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[6].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[6].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[1].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[1].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[1].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[0].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[0].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[0].getBufferedResultZ();          
    }

    private void setBackFaceVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[4].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[4].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[4].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[5].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[5].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[5].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[6].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[6].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[6].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[7].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[7].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[7].getBufferedResultZ();         
    }

    private void setLeftFaceVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[3].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[3].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[3].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[2].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[2].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[2].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[5].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[5].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[5].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[4].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[4].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[4].getBufferedResultZ();         
    }  
    
    private void setBottomFaceVertices(int currentBufferPosition) {        
        vertexBuffer[currentBufferPosition] = BV[7].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[7].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[7].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[0].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[0].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[0].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[3].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[3].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[3].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[4].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[4].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[4].getBufferedResultZ();             
    }
    
    private void setTopFaceVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[1].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[1].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[1].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[6].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[6].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[6].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[5].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[5].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[5].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[2].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[2].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[2].getBufferedResultZ();            
    }
    
    //</editor-fold>
}