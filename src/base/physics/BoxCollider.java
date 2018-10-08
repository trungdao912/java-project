package base.physics;

import base.GameObject;

public class BoxCollider {
    public int width;
    public int height;
    public BoxCollider(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int top(GameObject master) {
        return (int)master.position.y;
    }
    public int bot(GameObject master) {
        return (int)master.position.y + this.height;
    }
    public int left(GameObject master) {
        return (int)master.position.x;
    }
    public int right(GameObject master) {
        return (int)master.position.x + this.width;
    }


    public boolean intersect(Physics other, GameObject master) {
        //oM = otherMaster
        //oB = otherBoxcollider
        GameObject oM = (GameObject) other;
        BoxCollider oB = other.getBoxCollider();
        boolean yIntersect = ((this.top(master) >= oB.top(oM) && this.top(master) <= oB.bot(oM))
                || (this.bot(master) >= oB.top(oM) && this.bot(master) < oB.bot(oM)));
        boolean xIntersect =(this.right(master) >= oB.left(oM) && this.right(master) < oB.right(oM)
                || (this.left(master) >= oB.left(oM)) && this.left(master) < oB.right(oM));
        return yIntersect && xIntersect;
    }
}
