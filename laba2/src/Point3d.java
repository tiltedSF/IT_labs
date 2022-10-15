 public class Point3d extends Point2d{
    private double zCoord;
    public Point3d(double x, double y, double z) {
        super(x,y);
        zCoord = z;
    }

    public Point3d () {
        this(0, 0, 0);
    }

     public double getZ () {
         return zCoord;
     }

    public void setZ ( double val) {
         zCoord = val;
    }
    public boolean compare( Point3d obj){
        return (this.getX() == obj.getX()) &
                (this.getY() == obj.getY()) &
                (this.getZ() == obj.getZ());
    }

    public double distanceTo( Point3d obj){
        return Math.sqrt(
                Math.pow(this.getX() - obj.getX(), 2) +
                Math.pow(this.getY() - obj.getY(), 2) +
                Math.pow(this.getZ() - obj.getZ(), 2)
        );
    }
}