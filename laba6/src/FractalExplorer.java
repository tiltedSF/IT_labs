import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.SwingWorker;

public class FractalExplorer{

    private final int size;

    private JImageDisplay imageDisplay;

    private FractalGenerator generator;

    private final Rectangle2D.Double range;

    private JComboBox <FractalGenerator> comboBoxSelector;

    private int rowsRemaining = 0;

    private JImageDisplay displayImage;

    private JButton saveButton;

    private JButton resetButton;

    public FractalExplorer(int size){

        this.size = size;
        this.generator = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        generator.getInitialRange(range);

    }

    public void createAndShowGUI(){

        JFrame frame = new JFrame("fractal renderer");

        imageDisplay = new JImageDisplay(size, size);

        resetButton = new JButton("Reset display");
        resetButton.addActionListener(new ResetActionListener());

        saveButton = new JButton("Save Image");
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

        enableUI(false);
        rowsRemaining = size;

        for (int y = 0; y < size; y++) {
            FractalWorker drawRow = new FractalWorker(y);
            drawRow.execute();
        }

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

            if(rowsRemaining != 0){
                return;
            }

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

    class FractalWorker extends SwingWorker<Object, Object>{

        private int yCoord;

        private ArrayList<Integer> rowRGB;

        public FractalWorker(int selectedY) {
            this.yCoord = selectedY;
        }

        @Override
        public Object doInBackground(){

            rowRGB = new ArrayList<>(size);

            for (int x = 0; x < size; x++) {

                int numIters = generator.numIterations(
                        FractalGenerator.getCoord(range.x, range.x + range.width, size, x),
                        FractalGenerator.getCoord(range.y, range.y + range.width, size, yCoord)
                );

                int rgbColor = 0;
                if(numIters != -1){
                    float hue = 0.7f + (float) numIters / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }

                rowRGB.add(rgbColor);

            }

            return null;

        }

        @Override
        public void done() {

            for(int x = 0; x < size; x++){
                imageDisplay.drawPixel(x, yCoord, rowRGB.get(x));
            }

            imageDisplay.repaint(0, 0, yCoord, size, 1);

            rowsRemaining--;
            if(rowsRemaining == 0){
                enableUI(true);
            }

        }

    }

    void enableUI(boolean val){

        saveButton.setEnabled(val);
        resetButton.setEnabled(val);
        comboBoxSelector.setEnabled(val);

    }

    public static void main(String[] args){

        FractalExplorer app = new FractalExplorer(800);

        app.createAndShowGUI();
        app.drawFractal();

    }

}
