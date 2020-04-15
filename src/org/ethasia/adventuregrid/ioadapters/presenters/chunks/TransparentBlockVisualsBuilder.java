package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.Block;
import org.ethasia.adventuregrid.core.math.Vector3;

public class TransparentBlockVisualsBuilder extends BlockVisualsBuilder {
    
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
    
    private Block blockToRender;
    private int chunkPosX, chunkPosY, chunkPosZ;
    private int renderIndexOffsetInChunk;
    
    private boolean frontFaceOfBlockIsCovered;
    private boolean rightFaceOfBlockIsCovered;
    private boolean backFaceOfBlockIsCovered;
    private boolean leftFaceOfBlockIsCovered;
    private boolean bottomFaceOfBlockIsCovered;
    private boolean topFaceOfBlockIsCovered;
    
    private float[] vertexBuffer;
    private int[] indexBuffer;
    private float[] normalBuffer;
    private float[] uvCoordinatesBuffer;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public TransparentBlockVisualsBuilder setBlockToCreateDataFrom(Block value) {
        blockToRender = value;
        return this;
    }    
    
    @Override
    public TransparentBlockVisualsBuilder setChunkPositionX(int value) {
        chunkPosX = value;
        return this;
    }
    
    @Override
    public TransparentBlockVisualsBuilder setChunkPositionY(int value) {
        chunkPosY = value;
        return this;
    }

    @Override
    public TransparentBlockVisualsBuilder setChunkPositionZ(int value) {
        chunkPosZ = value;
        return this;
    }
    
    @Override
    public TransparentBlockVisualsBuilder setRenderIndexInChunk(int value) {
        renderIndexOffsetInChunk = value;
        return this;
    }     
    
    @Override
    public TransparentBlockVisualsBuilder setFrontFaceOfBlockIsCovered(boolean value) {
        frontFaceOfBlockIsCovered = value;
        return this;
    }    
    
    @Override
    public TransparentBlockVisualsBuilder setRightFaceOfBlockIsCovered(boolean value) {
        rightFaceOfBlockIsCovered = value;
        return this;
    }
    
    @Override
    public TransparentBlockVisualsBuilder setBackFaceOfBlockIsCovered(boolean value) {
        backFaceOfBlockIsCovered = value;
        return this;
    } 
    
    @Override
    public TransparentBlockVisualsBuilder setLeftFaceOfBlockIsCovered(boolean value) {
        leftFaceOfBlockIsCovered = value;
        return this;
    }  

    @Override
    public TransparentBlockVisualsBuilder setBottomFaceOfBlockIsCovered(boolean value) {
        bottomFaceOfBlockIsCovered = value;
        return this;
    }
    
    @Override
    public TransparentBlockVisualsBuilder setTopFaceOfBlockIsCovered(boolean value) {
        topFaceOfBlockIsCovered = value;
        return this;
    }    
    
    @Override
    public void build() {
        if (null == blockToRender) {
            vertexBuffer = new float[0];
            indexBuffer = new int[0];
            normalBuffer = new float[0];
            uvCoordinatesBuffer = new float[0];
        } else {
            buildVertexBuffer();
            buildIndexBuffer();
            buildNormalBuffer();
            buildUvCoordinatesBuffer();
        }
    }
    
    @Override
    public float[] getShapeVertices() {
        return vertexBuffer;
    }
    
    @Override
    public int[] getShapeIndices() {
        return indexBuffer;
    }
    
    @Override
    public float[] getShapeNormals() {
        return normalBuffer;
    }
    
    @Override
    public float[] getBlockUvCoordinates() {
        return uvCoordinatesBuffer;
    }
    
    @Override
    public int getAmountOfAddedIndices() {
        return getAmountOfUncoveredFaces() * 8;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void buildVertexBuffer() {
        translateVertices();
        fillVertexBuffer();
    }    
    
    private void buildIndexBuffer() {
        int amountOfUnCoveredFaces = getAmountOfUncoveredFaces();
        indexBuffer = new int[6 * amountOfUnCoveredFaces * 2];
        
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
        }       
    }
    
    private void buildNormalBuffer() {
        int faces = getAmountOfUncoveredFaces();
        int verticesPerFace = 4;
        
        normalBuffer = new float[faces * 2 * verticesPerFace * 3];
        
        int currentBufferIndex = 0;
        
        if (!frontFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, 0.f, -1.f, currentBufferIndex); 
            currentBufferIndex += 12;
        }
        
        if (!rightFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(-1.f, 0.f, 0.f, currentBufferIndex); 
            currentBufferIndex += 12;
        }

        if (!backFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, 0.f, 1.f, currentBufferIndex); 
            currentBufferIndex += 12;            
        }

        if (!leftFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(1.f, 0.f, 0.f, currentBufferIndex); 
            currentBufferIndex += 12;            
        }        

        if (!bottomFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, 1.f, 0.f, currentBufferIndex); 
            currentBufferIndex += 12;            
        }        

        if (!topFaceOfBlockIsCovered) {
            addNormalForFaceWithValueStartingAtIndex(0.f, -1.f, 0.f, currentBufferIndex);     
            currentBufferIndex += 12;
        }        
        
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
            currentBufferIndex += 12;
        }         
    }
    
    private void buildUvCoordinatesBuffer() {
        int faces = getAmountOfUncoveredFaces();
        int uvPerFace = 4;
        float[] blockUvCoordinates = BlockUvCoordinates.fromBlockType(blockToRender.getBlockType()).getUvCoordinates();
        float[] blockUvBackCoordinates = BlockUvCoordinates.fromBlockType(blockToRender.getBlockType()).getBackUvCoordinates();

        uvCoordinatesBuffer = new float[faces * 2 * uvPerFace * 2];
        int currentBufferIndex = 0;
        int currentSourceBufferIndex = 0;
          
        if (!frontFaceOfBlockIsCovered) {
            copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvBackCoordinates, currentSourceBufferIndex);
                
            currentBufferIndex += 8;
        }
            
        if (!rightFaceOfBlockIsCovered) {
            currentSourceBufferIndex = 8;
            copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvBackCoordinates, currentSourceBufferIndex);
                
            currentBufferIndex += 8;
        }

        if (!backFaceOfBlockIsCovered) {
            currentSourceBufferIndex = 16;
            copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvBackCoordinates, currentSourceBufferIndex);
                
            currentBufferIndex += 8;
        }
            
        if (!leftFaceOfBlockIsCovered) {
            currentSourceBufferIndex = 24;
            copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvBackCoordinates, currentSourceBufferIndex);
                
            currentBufferIndex += 8;
        }

        if (!bottomFaceOfBlockIsCovered) {
            currentSourceBufferIndex = 32;
            copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvBackCoordinates, currentSourceBufferIndex);
                
            currentBufferIndex += 8;
        }

        if (!topFaceOfBlockIsCovered) {
            currentSourceBufferIndex = 40;
            copyUvCoordinatesFromSourceBufferToUvBuffer(currentBufferIndex, blockUvBackCoordinates, currentSourceBufferIndex);
            
            currentBufferIndex += 8;
        }        
        
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
        vertexBuffer = new float[4 * 3 * amountOfUncoveredFaces * 2];
        int currentBufferPosition = 0;
        
        if (!frontFaceOfBlockIsCovered) {
            setFrontFaceBackVertices(currentBufferPosition);
            currentBufferPosition += 12;            
        }

        if (!rightFaceOfBlockIsCovered) {
            setRightFaceBackVertices(currentBufferPosition);
            currentBufferPosition += 12;                      
        }
        
        if (!backFaceOfBlockIsCovered) {
            setBackFaceBackVertices(currentBufferPosition);
            currentBufferPosition += 12;                     
        }
        
        if (!leftFaceOfBlockIsCovered) {
            setLeftFaceBackVertices(currentBufferPosition);
            currentBufferPosition += 12;             
        }
        
        if (!bottomFaceOfBlockIsCovered) {
            setBottomFaceBackVertices(currentBufferPosition);
            currentBufferPosition += 12;                     
        }    
        
        if (!topFaceOfBlockIsCovered) {
            setTopFaceBackVertices(currentBufferPosition);
            currentBufferPosition += 12;                    
        }
        
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
    
    private void setFrontFaceBackVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[3].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[3].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[3].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[2].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[2].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[2].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[1].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[1].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[1].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[0].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[0].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[0].getBufferedResultZ();          
    }
    
    private void setRightFaceBackVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[0].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[0].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[0].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[1].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[1].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[1].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[6].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[6].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[6].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[7].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[7].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[7].getBufferedResultZ();          
    }    
    
    private void setBackFaceBackVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[7].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[7].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[7].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[6].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[6].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[6].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[5].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[5].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[5].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[4].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[4].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[4].getBufferedResultZ();         
    } 
    
    private void setLeftFaceBackVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[4].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[4].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[4].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[5].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[5].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[5].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[2].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[2].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[2].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[3].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[3].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[3].getBufferedResultZ();         
    }     
    
    private void setBottomFaceBackVertices(int currentBufferPosition) {        
        vertexBuffer[currentBufferPosition] = BV[4].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[4].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[4].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[3].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[3].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[3].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[0].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[0].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[0].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[7].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[7].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[7].getBufferedResultZ();             
    }    
    
    private void setTopFaceBackVertices(int currentBufferPosition) {
        vertexBuffer[currentBufferPosition] = BV[2].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 1] = BV[2].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 2] = BV[2].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 3] = BV[5].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 4] = BV[5].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 5] = BV[5].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 6] = BV[6].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 7] = BV[6].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 8] = BV[6].getBufferedResultZ();
        
        vertexBuffer[currentBufferPosition + 9] = BV[1].getBufferedResultX();
        vertexBuffer[currentBufferPosition + 10] = BV[1].getBufferedResultY();
        vertexBuffer[currentBufferPosition + 11] = BV[1].getBufferedResultZ();            
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
    
    private void addNextFaceIndicesToBuffer(int currentBufferIndex, int faceOffset) {
        indexBuffer[currentBufferIndex] = 0 + faceOffset + renderIndexOffsetInChunk;
        indexBuffer[currentBufferIndex + 1] = 1 + faceOffset + renderIndexOffsetInChunk;
        indexBuffer[currentBufferIndex + 2] = 2 + faceOffset + renderIndexOffsetInChunk;
        indexBuffer[currentBufferIndex + 3] = 2 + faceOffset + renderIndexOffsetInChunk;
        indexBuffer[currentBufferIndex + 4] = 3 + faceOffset + renderIndexOffsetInChunk;
        indexBuffer[currentBufferIndex + 5] = 0 + faceOffset + renderIndexOffsetInChunk;         
    }

    private void addNormalForFaceWithValueStartingAtIndex(float normalX, float normalY, float normalZ, int index) {
        normalBuffer[index] = normalX;
        normalBuffer[index + 1] = normalY;
        normalBuffer[index + 2] = normalZ;
        normalBuffer[index + 3] = normalX;
        normalBuffer[index + 4] = normalY;
        normalBuffer[index + 5] = normalZ;
        normalBuffer[index + 6] = normalX;
        normalBuffer[index + 7] = normalY;
        normalBuffer[index + 8] = normalZ;
        normalBuffer[index + 9] = normalX;
        normalBuffer[index + 10] = normalY;
        normalBuffer[index + 11] = normalZ;         
    }  
    
    private void copyUvCoordinatesFromSourceBufferToUvBuffer(int destintationBufferStartingIndex, float[] sourceBuffer, int sourceDataStartingIndex) {
        uvCoordinatesBuffer[destintationBufferStartingIndex] = sourceBuffer[sourceDataStartingIndex];
        uvCoordinatesBuffer[destintationBufferStartingIndex + 1] = sourceBuffer[sourceDataStartingIndex + 1];
        uvCoordinatesBuffer[destintationBufferStartingIndex + 2] = sourceBuffer[sourceDataStartingIndex + 2];
        uvCoordinatesBuffer[destintationBufferStartingIndex + 3] = sourceBuffer[sourceDataStartingIndex + 3];
        uvCoordinatesBuffer[destintationBufferStartingIndex + 4] = sourceBuffer[sourceDataStartingIndex + 4];
        uvCoordinatesBuffer[destintationBufferStartingIndex + 5] = sourceBuffer[sourceDataStartingIndex + 5];
        uvCoordinatesBuffer[destintationBufferStartingIndex + 6] = sourceBuffer[sourceDataStartingIndex + 6];
        uvCoordinatesBuffer[destintationBufferStartingIndex + 7] = sourceBuffer[sourceDataStartingIndex + 7];        
    }    
    
    //</editor-fold>
}