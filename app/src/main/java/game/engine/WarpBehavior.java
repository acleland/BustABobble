/** 
 * WarpBehavior class - derived from Animation 
 * Requires game.engine.Engine to build.
 */
package game.engine;

import android.graphics.Point;
import android.graphics.RectF;

public class WarpBehavior extends Animation {
    private RectF p_bounds;
    private Vec2 p_velocity;
    private Point p_size;
    
    public WarpBehavior(RectF bounds, int w, int h, Vec2 velocity) {
        this(bounds, new Point(w,h), velocity);
    }
    
    public WarpBehavior(RectF bounds, Point size, Vec2 velocity) {
        animating = true;
        p_bounds = bounds;
        p_velocity = velocity;
        p_size = size;
    }
    
    @Override
    public Vec2 adjustPosition(Vec2 original) {
        Vec2 modified = original;
        modified.x += p_velocity.x;
        modified.y += p_velocity.y;
        
        if (modified.x < p_bounds.left)
            modified.x = p_bounds.right-p_size.x;
        else if (modified.x > p_bounds.right-p_size.x)
            modified.x = p_bounds.left;
        
        if (modified.y < p_bounds.top)
            modified.y = p_bounds.bottom-p_size.y;
        else if (modified.y > p_bounds.bottom-p_size.y)
            modified.y = p_bounds.top;
        
        return modified;
    }

}
