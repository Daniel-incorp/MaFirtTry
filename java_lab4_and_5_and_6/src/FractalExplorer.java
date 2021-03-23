import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;

public class FractalExplorer {

    /**
     * length of fractal in pixels
     */
    private int length;

    private JImageDisplay jImageDisplay;

    private FractalGenerator fractalGenerator;

    private Rectangle2D.Double aDouble;

    private JComboBox<FractalGenerator> jComboBox;
    private Button btnSave;
    private Button btnReset;

    public FractalExplorer (int l){
        length = l;

        aDouble = new Rectangle2D.Double();

        new Mandelbrot().getInitialRange(aDouble);

        fractalGenerator = new Mandelbrot();
    }

    /**
     * Creates: JFrame with JImageDispaly in which fractal is painted,
     * Buttons ResetDisplay and save in file, JPanel and ActionListener-s for them,
     * JComboBox containing 3 FractalGenerator-s.
     */
    public void createAndShowGUI(){
        JFrame frame = new JFrame("Fractals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jImageDisplay = new JImageDisplay(length,length);
        jImageDisplay.addMouseListener(new MyMouseListener().mouseListener);

        JPanel jPanelForComboBox = new JPanel();

        jComboBox = new JComboBox<>();
        jComboBox.addItem(new Mandelbrot());
        jComboBox.addItem(new Tricorn());
        jComboBox.addItem(new BurningShip());

        jComboBox.addActionListener(e -> {
            fractalGenerator = (FractalGenerator) jComboBox.getSelectedItem();

                                            // на всякий случай (getInitialRange говорит о возможности NullPointException).
            if (fractalGenerator != null) { // В теории такое может быть только, если jComboBox.getSelectedItem() вернёт null
                fractalGenerator.getInitialRange(aDouble);
                drawFractal();
                jImageDisplay.repaint();
            }
        });

        jPanelForComboBox.add(new JLabel("Fractal"));
        jPanelForComboBox.add(jComboBox);


        JPanel jPanelForButtons = new JPanel();

        btnReset = new Button("Reset Display");
        ActionListener actionListenerForBtnReset = e -> {
            fractalGenerator.getInitialRange(aDouble);
            drawFractal();
            jImageDisplay.repaint();
        };
        btnReset.addActionListener(actionListenerForBtnReset);

        btnSave = new Button("Save Image");
        ActionListener actionListenerForBtnSave = e -> {

            JFileChooser jFileChooser = new JFileChooser();

            FileFilter fileFilter = new FileNameExtensionFilter("PNG Images", "png");
            jFileChooser.setFileFilter(fileFilter);
            jFileChooser.setAcceptAllFileFilterUsed(false);

            if (jFileChooser.showDialog(frame, "Save") == JFileChooser.APPROVE_OPTION){

                try {
                    ImageIO.write(jImageDisplay.getBufferedImage(), "png", jFileChooser.getSelectedFile());

                } catch (IOException ioException){
                    JOptionPane.showMessageDialog(frame, ioException.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        btnSave.addActionListener(actionListenerForBtnSave);

        jPanelForButtons.add(btnSave);
        jPanelForButtons.add(btnReset);

        frame.add(jImageDisplay, BorderLayout.CENTER);
        frame.add(jPanelForButtons, BorderLayout.SOUTH);

        frame.add(jPanelForComboBox, BorderLayout.NORTH);

        //not my code
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);



    }


    private int rowsRemaining;
    private void drawFractal (){

        double xCoord;
        double yCoord;

        int numIters;

        enableUI(false);
        rowsRemaining = length;
        for (int y = 0; y < length; y++){
            FractalWorker fractalWorker = new FractalWorker(y);
            fractalWorker.execute();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(400);
        fractalExplorer.createAndShowGUI();

        fractalExplorer.drawFractal();
    }

        class MyMouseListener extends MouseAdapter {
        public  MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (rowsRemaining <= 0) {
                    double xCord;
                    double yCord;

                    xCord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, length, e.getX());
                    yCord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, length, e.getY());

                    FractalGenerator.recenterAndZoomRange(aDouble, xCord, yCord, 0.5);

                    drawFractal();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };


    }

    private void enableUI (boolean val){
        if (val){
            btnReset.setEnabled(true);
            btnSave.setEnabled(true);
            jComboBox.setEnabled(true);
        } else {
            btnReset.setEnabled(false);
            btnSave.setEnabled(false);
            jComboBox.setEnabled(false);
        }
    }

    private class FractalWorker extends SwingWorker<Object, Object>{

        private int y;
        private int[] pixelsRGB;

        private FractalWorker (int y){
            this.y = y;
        }

        @Override
        protected Object doInBackground() throws Exception {
            pixelsRGB = new int[length + 1];

            double xCoord;
            double yCoord;

            int numIters;

            yCoord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, length, y);

                for (int x = 0; x < length; x++){

                    xCoord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, length, x);

                    numIters = fractalGenerator.numIterations(xCoord,yCoord);

                    if (numIters != -1){
                        float hue = 0.7f + (float) numIters / 200f;
                        pixelsRGB[x] = Color.HSBtoRGB(hue, 0.74f, 0.74f);
                    }
                    else pixelsRGB[x] = 0;
                }
            return null;
        }

        @Override
        protected void done() {
            super.done(); // я не знаю что это делает
            for (int x = 0; x < length; x++){
                jImageDisplay.drawPixel(x, y, pixelsRGB[x]);
            }
            jImageDisplay.repaint(1,y, length, 1);

            rowsRemaining--;
            if (rowsRemaining <= 0) enableUI(true);
        }
    }
}
