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
    
    private float[] vertexBuffer;
    
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
    
    public void build() {
        if (null == blockToRender) {
            vertexBuffer = new float[0];
        } else {
            buildVertexBuffer();
        }
    }
    
    public float[] getShapeVertices() {
        return vertexBuffer;
    }    
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
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
        
        // front
        if (!frontFaceOfBlockIsCovered) {
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
            
            currentBufferPosition += 12;
        }
        
        // right
        if (!rightFaceOfBlockIsCovered) {
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
        
            currentBufferPosition += 12;        
        }
        
        // back
        if (!backFaceOfBlockIsCovered) {
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
        
            currentBufferPosition += 12;                    
        }
        
        // left
        if (!leftFaceOfBlockIsCovered) {
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
        
            currentBufferPosition += 12;            
        }        
        
        // bottom
        if (!bottomFaceOfBlockIsCovered) {
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
        
            currentBufferPosition += 12;                    
        }
        
        // top     
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
    
    private int getAmountOfUncoveredFaces() {
        int result = 1;
        
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

        return result;
    }
    
    //</editor-fold>
}