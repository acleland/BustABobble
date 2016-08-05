package game.engine;

/**
 * Created by Andronius on 7/31/16.
 */
public class Vec2Test {

    public static void main(String[] args) {
        Vec2 unity = new Vec2(0,1);
        Vec2 unitx = new Vec2(1, 0);
        Vec2 upperleft = new Vec2(-1, 1);
        Vec2 upperright = new Vec2(1, 1);
        Vec2 lowerleft = new Vec2(-1, -1);
        Vec2 lowerright = new Vec2(1, -1);
        Vec2 down = new Vec2(0, -1);
        Vec2 left = new Vec2(-1, 0);

        System.out.println("Angle of upper left wrt unity: " + upperleft.getAngle(unity));
        System.out.println("Angle of upper right wrt unity: " + upperright.getAngle(unity));
        System.out.println("Angle of lower left wrt unity: " + lowerleft.getAngle(unity));
        System.out.println("Angle of lower right wrt unity: " + lowerright.getAngle(unity));
        System.out.println("Angle of down wrt unity: " + down.getAngle(unity));
        System.out.println("Angle of left wrt unity: " + left.getAngle(unity));
        System.out.println();

        System.out.println("Angle of upper left wrt unitx: " + upperleft.getAngle(unitx));
        System.out.println("Angle of upper right wrt unitx: " + upperright.getAngle(unitx));
        System.out.println("Angle of lower left wrt unitx: " + lowerleft.getAngle(unitx));
        System.out.println("Angle of lower right wrt unitx: " + lowerright.getAngle(unitx));
        System.out.println("Angle of down wrt unitx: " + down.getAngle(unitx));
        System.out.println("Angle of left wrt unix: " + left.getAngle(unitx));
        System.out.println();

        System.out.println("cosAngles:");
        System.out.println("Angle of upper left wrt unity: " + upperleft.cosAngle(unity));
        System.out.println("Angle of upper right wrt unity: " + upperright.cosAngle(unity));
        System.out.println("Angle of lower left wrt unity: " + lowerleft.cosAngle(unity));
        System.out.println("Angle of lower right wrt unity: " + lowerright.cosAngle(unity));
        System.out.println("Angle of down wrt unity: " + down.cosAngle(unity));
        System.out.println("Angle of left wrt unity: " + left.cosAngle(unity));
        System.out.println();

        System.out.println("Angle of upper left wrt unitx: " + upperleft.cosAngle(unitx));
        System.out.println("Angle of upper right wrt unitx: " + upperright.cosAngle(unitx));
        System.out.println("Angle of lower left wrt unitx: " + lowerleft.cosAngle(unitx));
        System.out.println("Angle of lower right wrt unitx: " + lowerright.cosAngle(unitx));
        System.out.println("Angle of down wrt unitx: " + down.cosAngle(unitx));
        System.out.println("Angle of left wrt unix: " + left.cosAngle(unitx));

    }
}
