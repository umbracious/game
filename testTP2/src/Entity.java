public abstract class Entity {
    protected double x,y;
    protected double w,h;
    protected double vx,vy;
    protected double windowWidth, windowHeight;

    //constructor
    public Entity(double x, double y, double w, double h, double windowWidth, double windowHeight){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.windowWidth=windowWidth;
        this.windowHeight=windowHeight;
    }
    //update x,y positions
    public abstract double[] update(double dt) throws InterruptedException;
}