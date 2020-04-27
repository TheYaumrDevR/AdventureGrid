package org.ethasia.adventuregrid.ioadapters.presenters.chunks;

import org.ethasia.adventuregrid.core.environment.BlockTypes;

public class ParticleEffectByBlock {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static ParticleEffects getParticleEffectFromBlockType(BlockTypes blockType) {
        switch (blockType) {
            case PORTAL:
                return ParticleEffects.PORTAL;
            default:
                return ParticleEffects.NONE;
        }
    }
    
    //</editor-fold>
}