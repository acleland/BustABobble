/** 
 * CirclingBehavior Class
 *  * Copyright (c) 2012 by Jonathan S. Harbour
 */
package game.engine;

public class CirclingBehavior extends Animation {
    private int p_radius;
    private Vec2 p_center;
    private double p_angle;
    private float p_velocity;
    
    public CirclingBehavior(int centerx, int centery, int radius, 
            double angle, float velocity) {
        animating = true;
        this.p_center = new Vec2(centerx,centery);
        this.p_radius = radius;
        this.p_angle = angle;
        this.p_velocity = velocity;
    }
    
    @Override
    public Vec2 adjustPosition(Vec2 original) {
        Vec2 modified = original;
        p_angle += p_velocity;
        modified.x = (int)(p_center.x + (float)(Math.cos(p_angle) *
                p_radius));
        modified.y = (int)(p_center.y + (float)(Math.sin(p_angle) *
                p_radius));
        return modified;
    }

}
