/** 
 * Animation Class 
 * Requires game.engine.Engine to build.
 */
package game.engine;


public class Animation {
    public boolean animating;
    
    public Animation() {
        animating = false;
    }

    public int adjustFrame(int original) {
        return original;
    }
    
    public int adjustAlpha(int original) {
        return original;
    }
    
    public Vec2 adjustScale(Vec2 original) {
        return original;
    }
    
    public float adjustRotation(float original) {
        return original;        
    }

    public Vec2 adjustPosition(Vec2 original) {
        return original;
    }
    
    public Vec2 adjustVelocity(Vec2 original) {
        return original;
    }
    
    public boolean adjustAlive(boolean original) {
        return original;
    }
}


