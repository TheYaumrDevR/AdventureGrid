package org.ethasia.adventuregrid.technical.rendering;

import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh.Type;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import org.ethasia.adventuregrid.ioadapters.presenters.chunks.ParticleEffectByCoordinate;

public class ParticleEmitterFromEffectFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static AssetManager assetManager;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void setAssetManager(AssetManager value) {
        assetManager = value;
    }
    
    public static  ParticleEmitter createParticleEmitterFromEffectData(ParticleEffectByCoordinate particleEffectData) {
        switch (particleEffectData.getParticleEffect()) {
            case PORTAL:
                return createPortalParticleEffect(particleEffectData);
        }
        
        return null;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static ParticleEmitter createPortalParticleEffect(ParticleEffectByCoordinate particleEffectData) {
        String particleEffectName = "Particles Portal: " + particleEffectData.getPosX() + ", " + particleEffectData.getPosY() + ", " + particleEffectData.getPosZ();
        ParticleEmitter result = new ParticleEmitter(particleEffectName, Type.Point, 90);
        
        Material particleMaterial = new Material(assetManager,
            "Common/MatDefs/Misc/Particle.j3md");
        particleMaterial.setTexture("Texture", assetManager.loadTexture(
            "Effects/Spark.png"));
        result.setMaterial(particleMaterial);
        result.setImagesX(1);
        result.setImagesY(1);
        
        result.setLocalTranslation(0.25f + 0.5f * particleEffectData.getPosX(), 0.25f + 0.5f * particleEffectData.getPosY(), 0.25f + 0.5f * particleEffectData.getPosZ());
        result.setEndSize(0.2f);
        result.setStartColor(new ColorRGBA(0.067f, 0.8f, 1.f, 1.f));
        result.setEndColor(new ColorRGBA(0.067f, 0.8f, 1.f, 1.f));
        result.getParticleInfluencer().setInitialVelocity(new Vector3f(0.f, 0.f, 0.3f));
        result.getParticleInfluencer().setVelocityVariation(1.f);
        result.setGravity(Vector3f.ZERO);
        result.setShape(new EmitterSphereShape(Vector3f.ZERO, 0.3f));
        result.emitAllParticles();
        
        return result;
    }
    
    //</editor-fold>
}