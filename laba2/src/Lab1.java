import java.util.Scanner;

public class Lab1 {

    static public double computeArea(Point3d point1, Point3d point2, Point3d point3){

        double a = point1.distanceTo(point2);
        double b = point1.distanceTo(point3);
        double c = point2.distanceTo(point3);
        double p = (a+b+c)/2;

        if (point1.compare(point2) | point1.compare(point3) | point2.compare(point3)){
            System.out.println("Пользователь, вы ввели такие точки, что какая-то из точек равна другой!");
            return 1;
        }
        return (Math.sqrt(p*(p-a)*(p-b)*(p-c)));

    }
    static public void main(String[] args){
        Scanner in = new Scanner(System.in);

        Point3d point1 = new Point3d(in.nextInt(), in.nextInt(), in.nextInt());
        Point3d point2 = new Point3d(in.nextInt(), in.nextInt(), in.nextInt());
        Point3d point3 = new Point3d(in.nextInt(), in.nextInt(), in.nextInt());

        in.close();

        System.out.println(computeArea(point1, point2, point3));

    }

}
