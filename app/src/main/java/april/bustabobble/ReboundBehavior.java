/**
 * ReboundBehavior class - derived from Animation
 * Requires game.engine.Engine to build.
 */
package april.bustabobble;
import android.graphics.*;
import game.engine.*;

public class ReboundBehavior extends Animation {
    private RectF p_bounds;
    private Vec2 p_velocity;
    private Point p_size;

    public ReboundBehavior(RectF bounds, Point size, Vec2 velocity) {
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
            p_velocity.x *= -1;
        else if (modified.x > p_bounds.right-p_size.x)
            p_velocity.x *= -1;

        if (modified.y < p_bounds.top) {
           // Stick to Top
            p_velocity = new Vec2(0,0);
            modified.y = p_bounds.top;
        }
        else if (modified.y > p_bounds.bottom-p_size.y)
            p_velocity.y *= -1;

        return modified;
    }

}
