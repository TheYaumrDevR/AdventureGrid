package org.ethasia.adventuregrid.core.environment;

public class IndividualBlockFaces {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final boolean rightFaceIsCovering;
    private final boolean frontFaceIsCovering;
    private final boolean leftFaceIsCovering;
    private final boolean backFaceIsCovering;
    private final boolean bottomFaceIsCovering;
    private final boolean topFaceIsCovering;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accessors">
    
    public boolean getRightFaceIsCovering() {
        return rightFaceIsCovering;
    }
    
    public boolean getFrontFaceIsCovering() {
        return frontFaceIsCovering;
    } 
    
    public boolean getLeftFaceIsCovering() {
        return leftFaceIsCovering;
    }  

    public boolean getBackFaceIsCovering() {
        return backFaceIsCovering;
    }

    public boolean getBottomFaceIsCovering() {
        return bottomFaceIsCovering;
    } 

    public boolean getTopFaceIsCovering() {
        return topFaceIsCovering;
    }      
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private IndividualBlockFaces(Builder source) {
        rightFaceIsCovering = source.rightFaceIsCovering;
        frontFaceIsCovering = source.frontFaceIsCovering;
        leftFaceIsCovering = source.leftFaceIsCovering;
        backFaceIsCovering = source.backFaceIsCovering;
        bottomFaceIsCovering = source.bottomFaceIsCovering;
        topFaceIsCovering = source.topFaceIsCovering;
    }
    
    //</editor-fold>

    public static class Builder {

        private boolean rightFaceIsCovering;
        private boolean frontFaceIsCovering;
        private boolean leftFaceIsCovering;
        private boolean backFaceIsCovering;
        private boolean bottomFaceIsCovering;
        private boolean topFaceIsCovering;
        
        public Builder setRightFaceIsCovering(boolean value) {
            rightFaceIsCovering = value;
            return this;
        }        
        
        public Builder setFrontFaceIsCovering(boolean value) {
            frontFaceIsCovering = value;
            return this;
        }

        public Builder setLeftFaceIsCovering(boolean value) {
            leftFaceIsCovering = value;
            return this;
        }  
        
        public Builder setBackFaceIsCovering(boolean value) {
            backFaceIsCovering = value;
            return this;
        }

        public Builder setBottomFaceIsCovering(boolean value) {
            bottomFaceIsCovering = value;
            return this;
        }
        
        public Builder setTopFaceIsCovering(boolean value) {
            topFaceIsCovering = value;
            return this;
        }          
        
        public IndividualBlockFaces build() {
            return new IndividualBlockFaces(this);
        }
    }    
}