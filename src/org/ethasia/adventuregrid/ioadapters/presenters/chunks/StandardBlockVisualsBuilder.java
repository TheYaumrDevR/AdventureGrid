package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.math.Vector3;

public class StandardBlockVisualsBuilder extends BlockVisualsBuilder {
    
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
    private int renderIndexOffsetInChunk;
    private Block blockToRender;
    
    private boolean frontFaceOfBlockIsCovered;
    private boolean rightFaceOfBlockIsCovered;
    private boolean backFaceOfBlockIsCovered;
    private boolean leftFaceOfBlockIsCovered;
    private boolean bottomFaceOfBlockIsCovered;
    private boolean topFaceOfBlockIsCovered;
    
    private float[] vertexBuffer;
    private int[] indicesBuffer;
    private float[] normalsBuffer;
    private float[] uvBuffer;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public StandardBlockVisualsBuilder setBlockToCreateDataFrom(Block value) {
        blockToRender = value;
        return this;
    }   
    
    @Override
    public StandardBlockVisualsBuilder setChunkPositionX(int value) {
        chunkPosX = value;
        return this;
    }
    
    @Override
    public StandardBlockVisualsBuilder setChunkPositionY(int value) {
        chunkPosY = value;
        return this;
    }

    @Override
    public StandardBlockVisualsBuilder setChunkPositionZ(int value) {
        chunkPosZ = value;
        return this;
    }   
    
    @Override
    public StandardBlockVisualsBuilder setFrontFaceOfBlockIsCovered(boolean value) {
        frontFaceOfBlockIsCovered = value;
        return this;
    }
    
    @Override
    public StandardBlockVisualsBuilder setRightFaceOfBlockIsCovered(boolean value) {
        rightFaceOfBlockIsCovered = value;
        return this;
    } 
    
    @Override
    public StandardBlockVisualsBuilder setBackFaceOfBlockIsCovered(boolean value) {
        backFaceOfBlockIsCovered = value;
        return this;
    }
    
    @Override
    public StandardBlockVisualsBuilder setLeftFaceOfBlockIsCovered(boolean value) {
        leftFaceOfBlockIsCovered = value;
        return this;
    } 
    
    @Override
    public StandardBlockVisualsBuilder setBottomFaceOfBlockIsCovered(boolean value) {
        bottomFaceOfBlockIsCovered = value;
        return this;
    } 

    @Override
    public StandardBlockVisualsBuilder setTopFaceOfBlockIsCovered(boolean value) {
        topFaceOfBlockIsCovered = value;
        return this;
    } 
    
    @Override
    public StandardBlockVisualsBuilder setRenderIndexInChunk(int value) {
        renderIndexOffsetInChunk = value;
        return this;
    }
    
    @Override
    public void build() {
        if (null == blockToRender) {
            vertexBuffer = new float[0];
            indicesBuffer = new int[0];
            normalsBuffer = new float[0];
            uvBuffer = new float [0];
        } else {
            buildVertexBuffer();
            buildIndicesBuffer();
            buildNormalsBuffer();
            buildUvBuffer();
        }
    }
    
    @Override
    public float[] getShapeVertices() {
        return vertexBuffer;
    }   
    
    @Override
    public int[] getShapeIndices() {
        return indicesBuffer;
    }
    
    @Override
    public float[] getShapeNormals() {
        return normalsBuffer;
    }
    
    @Override
    public float[] getBlockUvCoordinates() {
        return uvBuffer;
    }
    
    @Override
    public int getAmountOfAddedIndices() {
        return getAmountOfUncoveredFaces() * 4;
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
    
    private void buildIndicesBuffer() {
        int amountOfUnCoveredFaces = getAmountOfUncoveredFaces();
        indicesBuffer = new int[6 * amountOfUnCoveredFaces];
        
        int faceOffset = 0;
        int currentBufferIndex = 0;
        
        if (!frontFaceOfBlockIsCovered) {
            addNextFaceIndicesToBuffer(currentBufferIndex, faceOffset);
            currentBufferIndex += 6;
            faceOffset += 4;
        }
        
        if (!rightFaceOfBlockIsCovered) {
            addNextFaceIndicesToBuffer(currentBufferIndex, faceOffset);
            currentBufferIndex += 6;
            faceOffset += 4;
        }       
        
        if (!backFaceOfBlockIsCovered) {
            addNextFaceIndicesToBuffer(currentBufferIndex, faceOffset);
            currentBufferIndex += 6;
            faceOffset += 4;
        }            
        
        if (!leftFaceOfBlockIsCovered) {
            addNextFaceIndicesToBuffer(currentBufferIndex, faceOffset);
            currentBufferIndex += 6;
            faceOffset += 4;
        }            
        
        if (!bottomFaceOfBlockIsCovered) {
            addNextFaceIndicesToBuffer(currentBufferIndex, faceOffset);
            currentBufferIndex += 6;
            faceOffset += 4;
        }           
        
        if (!topFaceOfBlockIsCovered) {
            addNextFaceIndicesToBuffer(currentBufferIndex, faceOffset);
            currentBufferIndex += 6;
            faceOffset += 4;
        }           
    }
    
    private void addNextFaceIndicesToBuffer(int currentBufferIndex, int faceOffset) {
        indicesBuffer[currentBufferIndex] = 0 + faceOffset + renderIndexOffsetInChunk;
        indicesBuffer[currentBufferIndex + 1] = 1 + faceOffset + renderIndexOffsetInChunk;
        indicesBuffer[currentBufferIndex + 2] = 2 + faceOffset + renderIndexOffsetInChunk;
        indicesBuffer[currentBufferIndex + 3] = 2 + faceOffset + renderIndexOffsetInChunk;
        indicesBuffer[currentBufferIndex + 4] = 3 + faceOffset + renderIndexOffsetInChunk;
        indicesBuffer[currentBufferIndex + 5] = 0 + faceOffset + renderIndexOffsetInChunk;         
    }    
    
    private void buildNormalsBuffer() {
        int faces = getAmountOfUncoveredFaces();
        int verticesPerFace = 4;
        
        normalsBuffer = new float[faces * verticesPerFace * 3];
        
        int currentBufferIndex = 0;
        
        if (!frontFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, 0.f, 1.f, currentBufferIndex); 
            currentBufferIndex += 12;
        }
        
        if (!rightFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(1.f, 0.f, 0.f, currentBufferIndex); 
            currentBufferIndex += 12;
        }

        if (!backFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, 0.f, -1.f, currentBufferIndex); 
            currentBufferIndex += 12;            
        }

        if (!leftFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(-1.f, 0.f, 0.f, currentBufferIndex); 
            currentBufferIndex += 12;            
        }        

        if (!bottomFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, -1.f, 0.f, currentBufferIndex); 
            currentBufferIndex += 12;            
        }        

        if (!topFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, 1.f, 0.f, currentBufferIndex);         
        }        
    }
    
    private void addNormalForFaceWithValueStartingAtIndex(float normalX, float normalY, float normalZ, int index) {
        normalsBuffer[index] = normalX;
        normalsBuffer[index + 1] = normalY;
        normalsBuffer[index + 2] = normalZ;
        normalsBuffer[index + 3] = normalX;
        normalsBuffer[index + 4] = normalY;
        normalsBuffer[index + 5] = normalZ;
        normalsBuffer[index + 6] = normalX;
        normalsBuffer[index + 7] = normalY;
        normalsBuffer[index + 8] = normalZ;
        normalsBuffer[index + 9] = normalX;
        normalsBuffer[index + 10] = normalY;
        normalsBuffer[index + 11] = normalZ;         
    }
    
    private void buildUvBuffer() {
        
        int faces = getAmountOfUncoveredFaces();
        int uvPerFace = 4;
        float[] blockUvCoordinates = BlockUvCoordinates.fromBlockType(blockToRender.getBlockType()).getUvCoordinates();

        if (6 == faces) {
            uvBuffer = blockUvCoordinates;
        } else {
            uvBuffer = new float[faces * uvPerFace * 2];
            int currentBufferIndex = 0;
            int currentSourceBufferIndex = 0;
            
            if (!frontFaceOfBlockIsCovered) {
                copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvCoordinates, currentSourceBufferIndex);
                
                currentBufferIndex += 8;
            }
            
            if (!rightFaceOfBlockIsCovered) {
                currentSourceBufferIndex = 8;
                copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvCoordinates, currentSourceBufferIndex);
                
                currentBufferIndex += 8;
            }

            if (!backFaceOfBlockIsCovered) {
                currentSourceBufferIndex = 16;
                copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvCoordinates, currentSourceBufferIndex);
                
                currentBufferIndex += 8;
            }
            
            if (!leftFaceOfBlockIsCovered) {
                currentSourceBufferIndex = 24;
                copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvCoordinates, currentSourceBufferIndex);
                
                currentBufferIndex += 8;
            }

            if (!bottomFaceOfBlockIsCovered) {
                currentSourceBufferIndex = 32;
                copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvCoordinates, currentSourceBufferIndex);
                
                currentBufferIndex += 8;
            }

            if (!topFaceOfBlockIsCovered) {
                currentSourceBufferIndex = 40;
                copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvCoordinates, currentSourceBufferIndex);
            }            
        }
    }
    
    private void copyUvCoordinatesFromSourceBufferToUvBuffer(int destintationBufferStartingIndex, float[] sourceBuffer, int sourceDataStartingIndex) {
        uvBuffer[destintationBufferStartingIndex] = sourceBuffer[sourceDataStartingIndex];
        uvBuffer[destintationBufferStartingIndex + 1] = sourceBuffer[sourceDataStartingIndex + 1];
        uvBuffer[destintationBufferStartingIndex + 2] = sourceBuffer[sourceDataStartingIndex + 2];
        uvBuffer[destintationBufferStartingIndex + 3] = sourceBuffer[sourceDataStartingIndex + 3];
        uvBuffer[destintationBufferStartingIndex + 4] = sourceBuffer[sourceDataStartingIndex + 4];
        uvBuffer[destintationBufferStartingIndex + 5] = sourceBuffer[sourceDataStartingIndex + 5];
        uvBuffer[destintationBufferStartingIndex + 6] = sourceBuffer[sourceDataStartingIndex + 6];
        uvBuffer[destintationBufferStartingIndex + 7] = sourceBuffer[sourceDataStartingIndex + 7];        
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
    
    //</editor-fold>
}