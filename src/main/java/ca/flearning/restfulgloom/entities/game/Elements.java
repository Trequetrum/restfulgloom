package ca.flearning.restfulgloom.entities.boardstate;

public class Elements {


    private int fire;
    private int ice;
    private int air;
    private int ground;
    private int light;
    private int dark;



    public int getFire() { return fire; }
    public int getIce() { return ice; }
    public int getAir() { return air; }
    public int getGround() { return ground; }
    public int getLight() { return light; }
    public int getDark() { return dark; }


    private int zeroOneOrTwo(int i) {
        return i < 0 ? 0 : (i > 2 ? 2 : i);
    }

    public void setFire(int fire) {
        this.fire = zeroOneOrTwo(fire);
    }

    public void setIce(int ice) {
        this.ice = zeroOneOrTwo(ice);
    }

    public void setAir(int air) {
        this.air = zeroOneOrTwo(air);
    }

    public void setGround(int ground) {
        this.ground = zeroOneOrTwo(ground);
    }

    public void setLight(int light) {
        this.light = zeroOneOrTwo(light);
    }

    public void setDark(int dark) {
        this.dark = zeroOneOrTwo(dark);
    }
}
