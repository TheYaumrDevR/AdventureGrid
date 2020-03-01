package org.ethasia.adventuregrid.core.input;

import org.ethasia.adventuregrid.core.environment.Island;

public interface IslandGenerator {
    
    public Island generateIsland(int edgeLength);
}