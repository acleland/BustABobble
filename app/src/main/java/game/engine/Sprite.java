/** 
 * Sprite Class
 * Requires game.engine.Engine to build.
 *  * Copyright (c) 2012 by Jonathan S. Harbour
 */
package game.engine;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.LinkedList;
import java.util.ListIterator;

public class Sprite {
    private Engine p_engine;
    private Canvas p_canvas;
    private Texture p_texture;
    private Paint p_paint;
    public Vec2 position;
    private int p_width, p_height;
    private int p_columns;
    private int p_alpha;
    private LinkedList<Animation> p_anims;
    private int p_frame; 
    private Vec2 p_scale;
    private float p_rotation;
    private Matrix p_mat_translate;
    private Matrix p_mat_scale;
    private Matrix p_mat_rotate;
    private Matrix p_matrix;
    private Bitmap p_frameBitmap;
    private Canvas p_frameCanvas;
    private boolean p_collidable, p_collided;
    private Sprite p_offender;
    private String p_name;
    private int p_identifier;
    private Vec2 p_velocity;
    private boolean p_alive;
    

    public Sprite(Engine engine) {
        this(engine, 0, 0, 1);
    }
    
    public Sprite(Engine engine, int width, int height, int columns) {
        p_engine = engine;
        p_width = width;
        p_height = height;
        p_columns = columns;
        p_canvas = null;
        p_texture = new Texture(engine);
        p_alpha = 255;
        p_paint = new Paint();
        p_anims = new LinkedList<Animation>();
        position = new Vec2(0,0);
        p_frame = 0;
        p_scale = new Vec2(1.0f,1.0f);
        p_rotation = 0.0f;
        p_mat_translate = new Matrix();
        p_mat_scale = new Matrix();
        p_mat_rotate = new Matrix();
        p_matrix = new Matrix();
        p_frameBitmap = null;
        p_frameCanvas = null;
        p_collidable = p_collided = false;
        p_offender = null;
        p_name = "";
        p_identifier = 0;
        p_velocity = new Vec2(0,0);
        p_alive = true; 
    }
    
    public void draw() {
        p_canvas = p_engine.getCanvas();
        
        //fill in size if this sprite is not animated
        if (p_width == 0 || p_height == 0) {
            p_width = p_texture.getBitmap().getWidth();
            p_height = p_texture.getBitmap().getHeight();
        }
        
        //create the frame scratch bitmap
        if (p_frameBitmap == null) {
            p_frameBitmap = Bitmap.createBitmap(p_width, p_height, Config.ARGB_8888);
            p_frameCanvas = new Canvas(p_frameBitmap);
        }

        /**
         * First, copy the animation frame onto a scratch bitmap.
         */
        
        //define the source rect representing one frame
        int u = (p_frame % p_columns) * p_width;
        int v = (p_frame / p_columns) * p_height;
        Rect src = new Rect(u, v, u+p_width, v+p_height);

        //define the destination location
        int x = 0;
        int y = 0;
        int w = p_width;
        int h = p_height;
        Rect dst = new Rect(x, y, x+w, y+h);
        
        //wipe temp bitmap
        p_frameBitmap.eraseColor(Color.TRANSPARENT);

        //copy frame onto temp bitmap
        p_paint.setAlpha(p_alpha);
        p_frameCanvas.drawBitmap(p_texture.getBitmap(), src, dst, p_paint); 
        

        /**
         * Second, draw the scratch bitmap using matrix transforms.
         */
        
        //update transform matrices
        p_mat_scale = new Matrix();
        p_mat_scale.setScale(p_scale.x, p_scale.y);
        
        p_mat_rotate = new Matrix();
        p_mat_rotate.setRotate( (float) Math.toDegrees(p_rotation));
        
        p_mat_translate = new Matrix();
        p_mat_translate.setTranslate(position.x, position.y);
        
        p_matrix = new Matrix(); //set to identity
        p_matrix.postConcat(p_mat_scale);
        p_matrix.postConcat(p_mat_rotate);
        p_matrix.postConcat(p_mat_translate);
        
        //draw frame bitmap onto screen
        p_canvas.drawBitmap(p_frameBitmap, p_matrix, p_paint);
        
    }
    
    //add an animation technique to this sprite    
    public void addAnimation(Animation anim) { 
        p_anims.add(anim);
    }
    
    //remove all animations
    public void removeAnimations() {
        if (p_anims.isEmpty()) return;
        while (!p_anims.isEmpty()) {
            p_anims.removeFirst();
        }
    }
    
    //run through all of the animations
    public void animate() { 
        if (p_anims.size() == 0) return;
        
        ListIterator<Animation> iterator = p_anims.listIterator();
        while (iterator.hasNext()) {
            Animation anim = iterator.next();
            if (anim.animating) {
                p_frame = anim.adjustFrame(p_frame);
                p_alpha = anim.adjustAlpha(p_alpha);
                p_rotation = anim.adjustRotation(p_rotation);
                p_scale = anim.adjustScale(p_scale);
                position = anim.adjustPosition(position);
                p_velocity = anim.adjustVelocity(p_velocity);
                p_alive = anim.adjustAlive(p_alive);
            }
            else
            {
                p_anims.remove(anim);
                return;
            }
        }
    }
    
   
    /**
     * common get/set and support methods
     */

    public void setAlpha(int alpha) {
        p_alpha = alpha;
    }
    
    public int getAlpha() {
        return p_alpha;
    }
    
    public void setPaint(Paint paint) {
        p_paint = paint;
    }

    public void setTexture(Texture texture) {
        p_texture = texture;
    }
    
    public Texture getTexture() {
        return p_texture;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public Vec2 getPosition() {
        return position;
    }
    
    public int getWidth() {
        return p_width;
    }
    
    public void setWidth(int width) {
        p_width = width;
    }
    
    public int getHeight() {
        return p_height;
    }
    
    public void setHeight(int height) {
        p_height = height;
    }
    
    public Point getSize() {
        return new Point(p_width,p_height);
    }
    
    public int getFrame() {
        return p_frame;
    }
    
    public void setFrame(int frame) {
        p_frame = frame;
    }
    
    public Vec2 getScale() {
        return p_scale;
    }
    
    public void setScale(Vec2 scale) {
        p_scale = scale;
    }
    
    public void setScale(float scale) {
        p_scale = new Vec2(scale,scale);
    }
    
    public float getRotation() {
        return p_rotation;
    }
    
    public void setRotation(float radians) {
        p_rotation = radians;
    }
    
    public boolean getCollidable() {
        return p_collidable;
    }
    
    public void setCollidable(boolean value) {
        p_collidable = value;
    }
    
    public boolean getCollided() {
        return p_collided;
    }
    
    public void setCollided(boolean value) {
        p_collided = value;
    }
    
    public Sprite getOffender() {
        return p_offender;
    }
    
    public void setOffender(Sprite value) {
        p_offender = value;
    }

    public String getName() {
        return p_name;
    }
    
    public void setName(String value) {
        p_name = value;
    }
    
    public int getIdentifier() {
        return p_identifier;
    }
    
    public void setIdentifier(int value) {
        p_identifier = value;
    }
    
    public RectF getBounds() {
        RectF r = new RectF(position.x, position.y,
                position.x + p_width, position.y + p_height);
        return r;
    }

    public RectF getBoundsScaled() {
        RectF r = getBounds();
        r.right = (int) (r.left + r.width() * p_scale.x);
        r.bottom = (int) (r.top + r.height() * p_scale.y);
        return r;
    }
    
    public Vec2 getVelocity() {
        return p_velocity;
    }
    
    public void setVelocity(Vec2 value) {
        p_velocity = value;
    }
    
    public boolean getAlive() { 
        return p_alive;
    }
    
    public void setAlive(boolean value) { 
        p_alive = value;
    }
    
}

