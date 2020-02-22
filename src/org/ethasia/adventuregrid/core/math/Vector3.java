package org.ethasia.adventuregrid.core.math;

public class Vector3 {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final Vector3 UNIT_X = new Vector3(1.0f, 0.f, 0.f);
    public static final Vector3 UNIT_Y = new Vector3(0.f, 1.0f, 0.f);
    public static final Vector3 UNIT_Z = new Vector3(0.f, 0.f, 1.0f);
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private float x, y, z;
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    } 
    
    public float getZ() {
        return z;
    }  
        
    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        return this;
    }  
    
    private float bufferedResultX, bufferedResultY, bufferedResultZ;
    
    public float getBufferedResultX() {
        return bufferedResultX;
    }

    public float getBufferedResultY() {
        return bufferedResultY;
    }   
    
    public float getBufferedResultZ() {
        return bufferedResultZ;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public float getSquaredLength() {
        return x * x + y * y + z * z;
    }
    
    public Vector3 scale(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        
        return this;
    }
    
    public Vector3 scaleImmutable(float scalar) {
        float scaledX = x * scalar;
        float scaledY  = y * scalar;
        float scaledZ  = z * scalar;
        
        return new Vector3(scaledX, scaledY, scaledZ);
    }    

    public Vector3 normalize() {
        float squaredLength = getSquaredLength();
        if (0.f == squaredLength || 1.f == squaredLength) {
            return this;
        }
        
        return scale(1.f / (float)Math.sqrt(squaredLength));
    }  
    
    public Vector3 add(final Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        
        return this;
    }
    
    public Vector3 addImmutable(final Vector3 other) {
        float resultX = x + other.x;
        float resultY = y + other.y;
        float resultZ = z + other.z;
        
        return new Vector3(resultX, resultY, resultZ);
    }
    
    public void addImmutableBufferResult(final Vector3 other) {
        bufferedResultX = x + other.x;
        bufferedResultY = y + other.y;
        bufferedResultZ = z + other.z;
    }
    
    public Vector3 subtract(final Vector3 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        
        return this;
    }    
    
    public Vector3 subtractImmutable(Vector3 other) {
        float resultX = x - other.x;
        float resultY = y - other.y;
        float resultZ = z - other.z;
        
        return new Vector3(resultX, resultY, resultZ);
    }    
    
    public float dot(final Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }    
    
    public Vector3 cross(final Vector3 other) {
        float newX = y * other.z - z * other.y;
        float newY = z * other.x - x * other.z;
        float newZ = x * other.y - y * other.x;
        
        x = newX;
        y = newY;
        z = newZ;
        
        return this;
    }    
    
    //</editor-fold>
}