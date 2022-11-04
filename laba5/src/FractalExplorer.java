import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer{

    private final int size;

    private JImageDisplay imageDisplay;

    private FractalGenerator generator;

    private final Rectangle2D.Double range;

    private JComboBox <FractalGenerator> comboBoxSelector;

    public FractalExplorer(int size){

        this.size = size;
        this.generator = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        generator.getInitialRange(range);

    }

    public void createAndShowGUI(){

        JFrame frame = new JFrame("fractal renderer");

        imageDisplay = new JImageDisplay(size, size);

        JButton resetButton = new JButton("Reset display");
        resetButton.addActionListener(new ResetActionListener());

        JButton saveButton = new JButton("Save Image");
        saveButton.addActionListener(new SaveActionListener());

        imageDisplay.addMouseListener(new EmphasizeActionListener());

        JLabel label = new JLabel("Fractal:");
        comboBoxSelector = new JComboBox<>();
        comboBoxSelector.addItem(new Mandelbrot());
        comboBoxSelector.addItem(new Tricorn());
        comboBoxSelector.addItem(new BurningShip());
        comboBoxSelector.addActionListener(new ComboBoxSelectItemActionListener());

        JPanel panelComboBox = new JPanel();
        JPanel panelButtons = new JPanel();
        panelComboBox.add(label, BorderLayout.CENTER);
        panelComboBox.add(comboBoxSelector, BorderLayout.CENTER);
        panelButtons.add(resetButton, BorderLayout.CENTER);
        panelButtons.add(saveButton, BorderLayout.CENTER);

        frame.add(imageDisplay, BorderLayout.CENTER);
        frame.add(panelComboBox, BorderLayout.NORTH);
        frame.add(panelButtons, BorderLayout.SOUTH);
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

    private class ComboBoxSelectItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            generator = (FractalGenerator) comboBoxSelector.getSelectedItem();
            generator.getInitialRange(range);
            drawFractal();
        }
    }

    private class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser chooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);
            int t = chooser.showSaveDialog(imageDisplay);

            if (t == JFileChooser.APPROVE_OPTION){

                try {
                    ImageIO.write(imageDisplay.image, "png", chooser.getSelectedFile());
                } catch (Exception ee) {

                    JOptionPane.showMessageDialog(
                            imageDisplay,
                            ee.getMessage(),
                            "Cannot Save Image",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    public static void main(String[] args){

        FractalExplorer app = new FractalExplorer(800);

        app.createAndShowGUI();
        app.drawFractal();

    }

}
