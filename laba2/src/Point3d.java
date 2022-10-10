 public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;

    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d () {
        this(0, 0, 0);
    }
     // get-еры
    public double getX () {
        return xCoord;
    }

    public double getY () {
        return yCoord;
    }

     public double getZ () {
         return zCoord;
     }
    // set-еры
    public void setX ( double val) {
        xCoord = val;
    }

    public void setY ( double val) {
        yCoord = val;
    }

    public void setZ ( double val) {
         zCoord = val;
    }
    //compare 2 obh
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