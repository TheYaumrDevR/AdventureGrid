package org.ethasia.adventuregrid.technical.rendering;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import org.ethasia.adventuregrid.ioadapters.presenters.StandardIslandPresenter;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.VisualChunkData;

import org.ethasia.adventuregrid.ioadapters.presenters.output.ChunkRenderer;

public class ChunkRendererImpl implements ChunkRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final AssetManager assetManager;
    private final Node rootNode;
    private final Texture blockTextureAtlas;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ChunkRendererImpl(AssetManager assetManager) {
        rootNode = new Node();
        this.assetManager = assetManager;
        blockTextureAtlas = loadTextureAtlas();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void renderChunk(VisualChunkData chunkData) {
        String uniqueChunkName = getUniqueChunkName(chunkData);
        
        if (null != rootNode.getChild(uniqueChunkName)) {
            Geometry chunkGeometry = createChunkGeometry(chunkData);
            rootNode.detachChildNamed(uniqueChunkName);
            rootNode.attachChild(chunkGeometry);
        } else {
            Geometry chunkGeometry = createChunkGeometry(chunkData);
            addCollisionShapeToChunk(chunkGeometry);
            rootNode.attachChild(chunkGeometry);
        }        
    }

    @Override
    public Node getRootNode() {
        return rootNode;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Texture loadTextureAtlas() {
        Texture blockTextures;
        
        try {
            blockTextures = assetManager.loadTexture(new TextureKey("Textures/TextureAtlas.png", false));
            blockTextures.setMinFilter(Texture.MinFilter.BilinearNoMipMaps);
            blockTextures.setMagFilter(Texture.MagFilter.Nearest);            
        } catch (Exception ex) {
            // Create dummy texture
            blockTextures = new Texture2D();
        }        
        
        return blockTextures;
    }
    
    private Geometry createChunkGeometry(VisualChunkData chunkData) {
        String uniqueChunkName = getUniqueChunkName(chunkData);
        ChunkMesh chunkMesh = new ChunkMesh();
        chunkMesh.updateMeshBasedOnChunkData(chunkData);
        
        Geometry geometry = new Geometry(uniqueChunkName, chunkMesh);
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        material.setTexture("ColorMap", blockTextureAtlas);
        material.setFloat("AlphaDiscardThreshold", 0.9961f);
        geometry.setMaterial(material);
        
        geometry.setLocalTranslation(chunkData.getWorldX() * StandardIslandPresenter.CHUNK_EDGE_LENGTH_IN_BLOCKS * 0.5f, 0, chunkData.getWorldY() * StandardIslandPresenter.CHUNK_EDGE_LENGTH_IN_BLOCKS * 0.5f);
        
        return geometry;
    }   
    
    private void addCollisionShapeToChunk(Geometry chunkGeometry) {
        CollisionShape chunkCollisionShape = CollisionShapeFactory.createMeshShape(chunkGeometry);
        RigidBodyControl chunkRigidBody = new RigidBodyControl(chunkCollisionShape, 0);
        chunkGeometry.addControl(chunkRigidBody);
    }
    
    private String getUniqueChunkName(VisualChunkData chunkData) {
        int chunkPositionX = chunkData.getWorldX();
        int chunkPositionY = chunkData.getWorldY();
        
        return "Chunk: " + chunkPositionX + ", " + chunkPositionY;        
    }    
    
    //</editor-fold>
}