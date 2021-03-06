/** 
 * ThrobAnimation Class
 *  * Copyright (c) 2012 by Jonathan S. Harbour
 */
package game.engine;

public class ThrobAnimation extends Animation {
    private float p_startScale, p_endScale, p_speed;
    private boolean p_started, p_repeat;
    
    public ThrobAnimation(float startScale, float endScale, float speed) {
        this(startScale, endScale, speed, false);
    }

    public ThrobAnimation(float startScale, float endScale, float speed, 
            boolean repeat) { 
        p_started = false;
        animating = true;
        this.p_startScale = startScale;
        this.p_endScale = endScale;
        this.p_speed = speed;
        this.p_repeat = repeat;
    }
    
    @Override
    public Vec2 adjustScale(Vec2 original) {
        Vec2 modified = original;
        if (!p_started) {
            modified.x = p_startScale;
            modified.y = p_startScale;
            p_started = true;
        }
        modified.x += p_speed;
        modified.y += p_speed;
        if (modified.x >= p_endScale)
            p_speed *= -1;
        else if (modified.x <= p_startScale) {
            if (!p_repeat)
                animating = false;
            else
                p_speed *= -1;
        }

        return modified;
    }

}
