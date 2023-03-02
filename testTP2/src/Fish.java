public class Fish extends Entity{

    protected int level;
    protected boolean startPos; // if false start left, else start right

    public Fish(double x, double y, double w, double h, double windowWidth, double windowHeight, int level) {
        super(x, y, w, h, windowWidth, windowHeight);
        this.level = level;
        if (x>0){
            startPos = true;
        }
        else{
            startPos = false;
        }
    }

    private void initialSpeed() {
        this.vx = (100 * Math.pow(level,1/3.0)+200);
        this.vy = (100 + Math.random()*100);

        if (startPos){
            this.vx = vx*(-1);
        }
    }

    @Override
    public double[] update(double dt) throws InterruptedException {
        double newX = x + vx * dt * 1e-9;
        double newY = y + vy * dt * 1e-9;
        double newVY = vy - 100 * dt;
        double r = w/2.0;
        double leftEdge = newX - r;
        double rightEdge = newX + r;
        double topEdge = newY - r ;
        double bottomEdge = newY + r;

        x = newX;
        y = newY;
        vy = newVY;

        return new double[]{x,y,vy};
    }
}
