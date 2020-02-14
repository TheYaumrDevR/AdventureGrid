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
}