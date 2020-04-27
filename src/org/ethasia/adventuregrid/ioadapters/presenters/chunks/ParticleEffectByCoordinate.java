package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

public class ParticleEffectByCoordinate {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final int posX, posY, posZ;
    private final ParticleEffects particleEffect;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accessors">
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }    
    
    public ParticleEffects getParticleEffect() {
        return particleEffect;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ParticleEffectByCoordinate(int posX, int posY, int posZ, ParticleEffects particleEffect) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.particleEffect = particleEffect;
    }
    
    //</editor-fold>
}