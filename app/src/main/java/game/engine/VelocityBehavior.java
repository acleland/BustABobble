/** 
 * VelocityBehavior class - derived from Animation 
 * Requires game.engine.Engine to build.
 *  * Copyright (c) 2012 by Jonathan S. Harbour
 */

package game.engine;

public class VelocityBehavior extends Animation {
    private double p_angle;
    private double p_multiplier;
    private double p_velX, p_velY;
    private int p_lifetime;
    private Timer p_timer;
    
    
    public VelocityBehavior(double angleDegrees, float speedMultiplier, 
            int lifetime) {
        animating = true;
        p_lifetime = lifetime;
        p_timer = new Timer();
        p_angle = angleDegrees;
        p_multiplier = speedMultiplier;
        double angleRadians = Math.toRadians(p_angle);
        //calculate X velocity
        p_velX = Math.cos(angleRadians) * p_multiplier;
        //calculate Y velocity
        p_velY = Math.sin(angleRadians) * p_multiplier;
    }
    
    @Override
    public Vec2 adjustPosition(Vec2 original) {
        Vec2 modified = new Vec2(original.x,original.y);
        modified.x +=  p_velX;
        modified.y += p_velY;
        return modified;
    }

    @Override
    public boolean adjustAlive(boolean original) {
        boolean modified = original;
        if (p_lifetime > 0) {
            if (p_timer.stopwatch(p_lifetime)) {
                modified = false;
            }
        }
        return modified;
    }
}

