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
    
    private float[] vertexBuffer;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public StandardBlockVisualsBuilder setBlockToCreateDataFrom(Block testBlock) {
        return this;
    }    
    
    public void build() {
        buildVertexBuffer();
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
        Vector3 cubeCenter = new Vector3(0.25f, 0.25f, 0.25f);
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
        vertexBuffer = new float[4 * 3 * 6];
        
        // front
        vertexBuffer[0] = BV[0].getBufferedResultX();
        vertexBuffer[1] = BV[0].getBufferedResultY();
        vertexBuffer[2] = BV[0].getBufferedResultZ();
        
        vertexBuffer[3] = BV[1].getBufferedResultX();
        vertexBuffer[4] = BV[1].getBufferedResultY();
        vertexBuffer[5] = BV[1].getBufferedResultZ();
        
        vertexBuffer[6] = BV[2].getBufferedResultX();
        vertexBuffer[7] = BV[2].getBufferedResultY();
        vertexBuffer[8] = BV[2].getBufferedResultZ();
        
        vertexBuffer[9] = BV[3].getBufferedResultX();
        vertexBuffer[10] = BV[3].getBufferedResultY();
        vertexBuffer[11] = BV[3].getBufferedResultZ();
        
        // right
        vertexBuffer[12] = BV[7].getBufferedResultX();
        vertexBuffer[13] = BV[7].getBufferedResultY();
        vertexBuffer[14] = BV[7].getBufferedResultZ();
        
        vertexBuffer[15] = BV[6].getBufferedResultX();
        vertexBuffer[16] = BV[6].getBufferedResultY();
        vertexBuffer[17] = BV[6].getBufferedResultZ();
        
        vertexBuffer[18] = BV[1].getBufferedResultX();
        vertexBuffer[19] = BV[1].getBufferedResultY();
        vertexBuffer[20] = BV[1].getBufferedResultZ();
        
        vertexBuffer[21] = BV[0].getBufferedResultX();
        vertexBuffer[22] = BV[0].getBufferedResultY();
        vertexBuffer[23] = BV[0].getBufferedResultZ();        
        
        // back
        vertexBuffer[24] = BV[4].getBufferedResultX();
        vertexBuffer[25] = BV[4].getBufferedResultY();
        vertexBuffer[26] = BV[4].getBufferedResultZ();
        
        vertexBuffer[27] = BV[5].getBufferedResultX();
        vertexBuffer[28] = BV[5].getBufferedResultY();
        vertexBuffer[29] = BV[5].getBufferedResultZ();
        
        vertexBuffer[30] = BV[6].getBufferedResultX();
        vertexBuffer[31] = BV[6].getBufferedResultY();
        vertexBuffer[32] = BV[6].getBufferedResultZ();
        
        vertexBuffer[33] = BV[7].getBufferedResultX();
        vertexBuffer[34] = BV[7].getBufferedResultY();
        vertexBuffer[35] = BV[7].getBufferedResultZ();          
        
        // left
        vertexBuffer[36] = BV[3].getBufferedResultX();
        vertexBuffer[37] = BV[3].getBufferedResultY();
        vertexBuffer[38] = BV[3].getBufferedResultZ();
        
        vertexBuffer[39] = BV[2].getBufferedResultX();
        vertexBuffer[40] = BV[2].getBufferedResultY();
        vertexBuffer[41] = BV[2].getBufferedResultZ();
        
        vertexBuffer[42] = BV[5].getBufferedResultX();
        vertexBuffer[43] = BV[5].getBufferedResultY();
        vertexBuffer[44] = BV[5].getBufferedResultZ();
        
        vertexBuffer[45] = BV[4].getBufferedResultX();
        vertexBuffer[46] = BV[4].getBufferedResultY();
        vertexBuffer[47] = BV[4].getBufferedResultZ();           
        
        // bottom
        vertexBuffer[48] = BV[7].getBufferedResultX();
        vertexBuffer[49] = BV[7].getBufferedResultY();
        vertexBuffer[50] = BV[7].getBufferedResultZ();
        
        vertexBuffer[51] = BV[0].getBufferedResultX();
        vertexBuffer[52] = BV[0].getBufferedResultY();
        vertexBuffer[53] = BV[0].getBufferedResultZ();
        
        vertexBuffer[54] = BV[3].getBufferedResultX();
        vertexBuffer[55] = BV[3].getBufferedResultY();
        vertexBuffer[56] = BV[3].getBufferedResultZ();
        
        vertexBuffer[57] = BV[4].getBufferedResultX();
        vertexBuffer[58] = BV[4].getBufferedResultY();
        vertexBuffer[59] = BV[4].getBufferedResultZ();         
        
        // top     
        vertexBuffer[60] = BV[1].getBufferedResultX();
        vertexBuffer[61] = BV[1].getBufferedResultY();
        vertexBuffer[62] = BV[1].getBufferedResultZ();
        
        vertexBuffer[63] = BV[6].getBufferedResultX();
        vertexBuffer[64] = BV[6].getBufferedResultY();
        vertexBuffer[65] = BV[6].getBufferedResultZ();
        
        vertexBuffer[66] = BV[5].getBufferedResultX();
        vertexBuffer[67] = BV[5].getBufferedResultY();
        vertexBuffer[68] = BV[5].getBufferedResultZ();
        
        vertexBuffer[69] = BV[2].getBufferedResultX();
        vertexBuffer[70] = BV[2].getBufferedResultY();
        vertexBuffer[71] = BV[2].getBufferedResultZ();  
    }
    
    //</editor-fold>
}