import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    @Override
    public void getInitialRange (Rectangle2D.Double range){
        range.x = -2.0;
        range.y = -1.5;

        range.width =  3;
        range.height = 3;
    }

    public static final int MAX_ITERATIONS = 2000;
    @Override
    public int numIterations (double x, double y){

        ComplexNumber c = new ComplexNumber(x,y);
        ComplexNumber z = new ComplexNumber();

        int iterations = 0;

        //(x^2 + y^2)
        while (z.getX() * z.getX() + z.getY() * z.getY() <= 4 &&
                iterations < MAX_ITERATIONS){

            double tempZ = z.getX(); // saving old material part of z for z.setY();
            //(Zx^2 + Cx -Zy^2)
            z.setX(z.getX() * z.getX() + c.getX() - z.getY() * z.getY());
            //(2*Zy*Zx + Cy)
            z.setY(2 * tempZ * z.getY() + c.getY());

            iterations++;
        }
        if (iterations >= MAX_ITERATIONS) return -1;
        return iterations;

    }

    @Override
    public String toString() {
        return "Mandelbrot";
    }
}
