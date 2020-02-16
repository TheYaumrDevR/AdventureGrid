package org.ethasia.adventuregrid.core.math.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import org.ethasia.adventuregrid.core.math.Vector3;
import static org.junit.Assert.assertEquals;

public class Vector3Test {
    
    @Test
    public void testGetters_retrieveSetComponents() {
        Vector3 testCandidate = new Vector3(1.f, 2.f, 3.f);
        
        assertThat(testCandidate.getX(), is(1.f));
        assertThat(testCandidate.getY(), is(2.f));
        assertThat(testCandidate.getZ(), is(3.f));
    }
    
    @Test
    public void testSet_setsFieldsCorrectly() {
        Vector3 testCandidate = new Vector3(1.f, 2.f, 3.f);
        Vector3 changedVector = testCandidate.set(4.5f, 3.2f, 1.5f);
        
        assertThat(changedVector.getX(), is(4.5f));
        assertThat(changedVector.getY(), is(3.2f));
        assertThat(changedVector.getZ(), is(1.5f));
    }
    
    @Test
    public void testGetSquaredLength_calculatesCorrectly() {
        Vector3 testCandidate = new Vector3(4.5f, 3.2f, 1.5f);   
        
        float expected = 4.5f * 4.5f + 3.2f * 3.2f + 1.5f * 1.5f;
        
        assertThat(testCandidate.getSquaredLength(), is(expected));
    }
    
    @Test
    public void testScale_scalesCorrectly() {
        Vector3 testCandidate = new Vector3(2.2f, 5.1f, 3.7f);
        
        float expectedX = 2.2f * 0.6f;
        float expectedY = 5.1f * 0.6f;
        float expectedZ = 3.7f * 0.6f;
        
        testCandidate.scale(0.6f);
        
        assertThat(testCandidate.getX(), is(expectedX));
        assertThat(testCandidate.getY(), is(expectedY));
        assertThat(testCandidate.getZ(), is(expectedZ));      
    }
    
    @Test
    public void testScaleImmutable_scalesAndOriginalVectorIsNotChanged() {    
        Vector3 testCandidate = new Vector3(4.9f, 2.3f, 0.3f);
        
        float expectedX = 4.9f * 0.4f;
        float expectedY = 2.3f * 0.4f;
        float expectedZ = 0.3f * 0.4f;   
        
        Vector3 result = testCandidate.scaleImmutable(0.4f);
        
        assertThat(testCandidate.getX(), is(4.9f));
        assertThat(testCandidate.getY(), is(2.3f));
        assertThat(testCandidate.getZ(), is(0.3f));   
        
        assertThat(result.getX(), is(expectedX));
        assertThat(result.getY(), is(expectedY));
        assertThat(result.getZ(), is(expectedZ));          
    }
    
    @Test
    public void testNormalize_vectorIsLengthOneAfterwards() {
        Vector3 testCandidate = new Vector3(2.2f, 5.1f, 3.7f);
        
        testCandidate.normalize();
        float result = testCandidate.getSquaredLength();
        
        assertThat(result, is(1.f));
    }  
    
    @Test
    public void testAdd_vectorComponentsAreAdded() {
        Vector3 testCandidate = new Vector3(2.2f, 5.1f, 3.7f);
        Vector3 toAdd = new Vector3(1.f, 2.f, 3.f);
        
        testCandidate = testCandidate.add(toAdd);
        
        assertThat(testCandidate.getX(), is(3.2f));
        assertThat(testCandidate.getY(), is(7.1f));
        assertThat(testCandidate.getZ(), is(6.7f));         
    }

    @Test
    public void testSubtract_vectorComponentsAreSubtracted() {    
        Vector3 testCandidate = new Vector3(2.2f, 5.1f, 3.7f);
        Vector3 toSubtract = new Vector3(1.f, 2.f, 3.f);

        testCandidate = testCandidate.subtract(toSubtract);  
        
        assertEquals(1.2f, testCandidate.getX(), 0.001f);
        assertEquals(3.1f, testCandidate.getY(), 0.001f);
        assertEquals(0.7f, testCandidate.getZ(), 0.001f);     
    }
    
    @Test
    public void testDot_isCalculatedCorrectly() {   
        Vector3 testCandidate = new Vector3(2.2f, 5.1f, 3.7f);
        Vector3 secondVector = new Vector3(4.5f, 3.2f, 1.5f); 
        
        float result = testCandidate.dot(secondVector);
        float expected = 2.2f * 4.5f + 5.1f * 3.2f + 3.7f * 1.5f;
        
        assertThat(result, is(expected));
    }

    @Test
    public void testCross_isCalculatedCorrectly() { 
        Vector3 testCandidate = new Vector3(2.2f, 5.1f, 3.7f);
        Vector3 secondVector = new Vector3(4.5f, 3.2f, 1.5f); 

        testCandidate = testCandidate.cross(secondVector);
        
        float expectedX = 5.1f * 1.5f - 3.7f * 3.2f;
        float expectedY = 3.7f * 4.5f - 2.2f * 1.5f;
        float expectedZ = 2.2f * 3.2f - 5.1f * 4.5f;    
        
        assertEquals(expectedX, testCandidate.getX(), 0.001f);
        assertEquals(expectedY, testCandidate.getY(), 0.001f);
        assertEquals(expectedZ, testCandidate.getZ(), 0.001f);        
    }

    @Test
    public void testAddImmutable_originalVectorIsNotChanged() {
        Vector3 testCandidate = new Vector3(1.5f, 9.1f, 4.3f);
        Vector3 toAdd = new Vector3(2.0f, 4.0f, 1.0f);
        
        Vector3 result = testCandidate.addImmutable(toAdd);
        
        assertThat(testCandidate.getX(), is(1.5f));
        assertThat(testCandidate.getY(), is(9.1f));
        assertThat(testCandidate.getZ(), is(4.3f));
        
        assertThat(result.getX(), is(3.5f));
        assertThat(result.getY(), is(13.1f));
        assertThat(result.getZ(), is(5.3f));
    }
    
    @Test
    public void testSubtractImmutable_originalVectorIsNotChanged() {
        Vector3 testCandidate = new Vector3(1.5f, 9.1f, 4.3f);
        Vector3 toSubtract = new Vector3(2.0f, 4.0f, 1.0f);

        Vector3 result = testCandidate.subtractImmutable(toSubtract);
        
        assertThat(testCandidate.getX(), is(1.5f));
        assertThat(testCandidate.getY(), is(9.1f));
        assertThat(testCandidate.getZ(), is(4.3f));     
        
        assertEquals(-0.5f, result.getX(), 0.001f);
        assertEquals(5.1f, result.getY(), 0.001f);
        assertEquals(3.3f, result.getZ(), 0.001f);
    }
}