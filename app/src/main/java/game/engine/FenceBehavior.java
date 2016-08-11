/** 
 * FenceBehavior Class
 *  * Copyright (c) 2012 by Jonathan S. Harbour
 */
package game.engine;
import android.graphics.RectF;

public class FenceBehavior extends Animation {
    private RectF p_fence;
    
    public FenceBehavior(RectF fence) {
        p_fence = fence;
        animating = true; 
    }
    
    @Override
    public Vec2 adjustPosition(Vec2 original) {
        Vec2 modified = original;
        
        if (modified.x < p_fence.left)
            modified.x = p_fence.left;
        else if (modified.x > p_fence.right)
            modified.x = p_fence.right;
        if (modified.y < p_fence.top)
            modified.y = p_fence.top;
        else if (modified.y > p_fence.bottom)
            modified.y = p_fence.bottom;

        return modified;
    }
    
}
