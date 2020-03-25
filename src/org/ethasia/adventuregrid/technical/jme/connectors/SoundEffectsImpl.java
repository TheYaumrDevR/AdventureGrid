package org.ethasia.adventuregrid.technical.jme.connectors;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;

public class SoundEffectsImpl {

    //<editor-fold defaultstate="collapsed" desc="Static Properties">
    
    private static AssetManager assetManager;
    public static void setAssetManager(AssetManager value) {
        assetManager = value;
    }
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private AudioNode walkOnRockSound;
    
    //</editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public SoundEffectsImpl() {
        if (null != assetManager) {
            initAudioNodes();
        }
    }
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="SoundEffects Overrides">
    
    public void playWalkOnRockSoundEffect() {
        if (null != walkOnRockSound) {
            walkOnRockSound.playInstance();
        }
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void initAudioNodes() {
        walkOnRockSound = null;
        
        if (null != walkOnRockSound) {
            walkOnRockSound.setPositional(false);
            walkOnRockSound.setReverbEnabled(false);
            walkOnRockSound.setLooping(false);             
        }  
    }
    
    //</editor-fold>    
}