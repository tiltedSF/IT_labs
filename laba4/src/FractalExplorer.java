import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {

    private final int size;

    private JImageDisplay imageDisplay;

    private final FractalGenerator generator;

    private final Rectangle2D.Double range;

    public FractalExplorer(int size){

        this.size = size;
        this.generator = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        generator.getInitialRange(range);

    }

    public void createAndShowGUI(){

        JFrame frame = new JFrame("fractal renderer");

        imageDisplay = new JImageDisplay(size, size);
        frame.add(imageDisplay, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset display");
        resetButton.addActionListener(new ResetActionListener());
        frame.add(resetButton, BorderLayout.SOUTH);

        imageDisplay.addMouseListener(new EmphasizeActionListener());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void drawFractal(){

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                int numIters = generator.numIterations(
                        FractalGenerator.getCoord(range.x, range.x + range.width, size, x),
                        FractalGenerator.getCoord(range.y, range.y + range.width, size, y)
                );


                int rgbColor = 0;
                if(numIters != -1){
                    float hue = 0.7f + (float) numIters / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }

                imageDisplay.drawPixel(x, y, rgbColor);

            }
        }

        imageDisplay.repaint();

    }

    private class ResetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            imageDisplay.clearImage();
            generator.getInitialRange(range);
            drawFractal();

        }
    }

    private class EmphasizeActionListener extends MouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

            double x = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
            double y = FractalGenerator.getCoord(range.y, range.y + range.width, size, e.getY());

            generator.recenterAndZoomRange(range, x, y, 0.5);
            drawFractal();

        }
    }

    public static void main(String[] args){

        FractalExplorer app = new FractalExplorer(800);

        app.createAndShowGUI();
        app.drawFractal();

    }

}
