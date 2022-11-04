import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{

    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range){

        range.x = -2;
        range.y = -2.5;
        range.height = 4;
        range.width = 4;

    }

    public int numIterations(double x, double y){

        double currentY = 0;
        double currentX = 0;
        int numIters = 0;

        while (numIters < MAX_ITERATIONS) {
            numIters++;

            double newX = currentX * currentX - currentY * currentY + x;
            double newY = Math.abs(2 * currentX * currentY) + y;

            currentX = newX;
            currentY = newY;

            if ((currentX * currentX + currentY * currentY) > 4)
                break;
        }
        if (numIters == MAX_ITERATIONS) {
            return -1;
        } else {
            return numIters;
        }
    }

    public String toString() {
        return "BurningShip";
    }

}