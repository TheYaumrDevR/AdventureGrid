package org.ethasia.adventuregrid.technical.rendering;

import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;

public class ChunkMesh extends Mesh {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void updateMeshBasedOnChunkData(VisualChunkData chunkData) {
        updateGeometries(chunkData);
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void updateGeometries(VisualChunkData chunkData) {
        updateGeometryVertices(chunkData);
        updateGeometryNormals(chunkData);
        updateGeometryUVs(chunkData);
        updateGeometryIndices(chunkData);
    }    
    
    private void updateGeometryVertices(VisualChunkData chunkData) {
        FloatBuffer vertexBuffer = BufferUtils.createVector3Buffer(chunkData.getVertices().length);
        for (int i = 0; i < chunkData.getVertices().length; i++) {
            vertexBuffer.put(chunkData.getVertices()[i]);
        }
        
        setBuffer(VertexBuffer.Type.Position, 3, vertexBuffer);
        updateBound();
    }  
    
    private void updateGeometryNormals(VisualChunkData chunkData) {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(chunkData.getNormals().length);
        for (int i = 0; i < chunkData.getNormals().length; i++) {
            vertexBuffer.put(chunkData.getNormals()[i]);
        }        
        
        setBuffer(VertexBuffer.Type.Normal, 3, vertexBuffer);
    }     
    
    private void updateGeometryUVs(VisualChunkData chunkData) {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(chunkData.getUvCoordinates().length);
        for (int i = 0; i < chunkData.getUvCoordinates().length; i++) {
            vertexBuffer.put(chunkData.getUvCoordinates()[i]);
        }        
        
        setBuffer(VertexBuffer.Type.TexCoord, 2, vertexBuffer);   
    }   
    
    private void updateGeometryIndices(VisualChunkData chunkData) {
        IntBuffer vertexBuffer = BufferUtils.createIntBuffer(chunkData.getIndices().length);
        for (int i = 0; i < chunkData.getIndices().length; i++) {
            vertexBuffer.put(chunkData.getIndices()[i]);
        }        
        
        setBuffer(VertexBuffer.Type.Index, 3, vertexBuffer);        
    }    
    
    //</editor-fold>
}