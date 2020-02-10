package org.ethasia.adventuregrid.core.environment;

public class BlockPositionOutOfBoundsException extends RuntimeException {
    
    public BlockPositionOutOfBoundsException() {
        super("The block position was invalid because it was out of valid bounds.");
    }
}