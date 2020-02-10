package org.ethasia.adventuregrid.core.environment.tests;

import org.ethasia.adventuregrid.core.environment.IndividualBlockFaces;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IndividualBlockFacesTest {
    
    @Test
    public void testBuild_coveringPropertiesAreSetProperly() {
        IndividualBlockFaces.Builder testCandidateBuilder = new IndividualBlockFaces.Builder();
        
        IndividualBlockFaces testCandidate = testCandidateBuilder.setRightFaceIsCovering(true)
                .setFrontFaceIsCovering(false)
                .setLeftFaceIsCovering(true)
                .setBackFaceIsCovering(false)
                .setBottomFaceIsCovering(true)
                .setTopFaceIsCovering(false)
                .build();
        
        assertThat(testCandidate.getRightFaceIsCovering(), is(true));
        assertThat(testCandidate.getFrontFaceIsCovering(), is(false));
        assertThat(testCandidate.getLeftFaceIsCovering(), is(true));
        assertThat(testCandidate.getBackFaceIsCovering(), is(false));
        assertThat(testCandidate.getBottomFaceIsCovering(), is(true));
        assertThat(testCandidate.getTopFaceIsCovering(), is(false));
    }
}