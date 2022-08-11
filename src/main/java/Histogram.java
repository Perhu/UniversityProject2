import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


    public class Histogram {

        private static final int BAR_WIDTH = 50;
        private static final int INCREMENT = 10;

        public Histogram() {

            JButton TheStartButton = new JButton("Make a Histogram of 300 rolls");
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(TheStartButton, BorderLayout.SOUTH);

            JFrame frame = new JFrame();
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            TheStartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int width = 12 * BAR_WIDTH;
                    int height =  INCREMENT*70;
                    int BottomPadding = height - 20;
                    HistogramPanel panel = new HistogramPanel(width, height, BottomPadding);
                    //panel.setBorder(new LineBorder(Color.BLACK, 2));
                    JOptionPane.showMessageDialog(null, panel);
                }
            });
        }


        public class HistogramPanel extends JPanel {

            int width;
            int dimHeight;
            int horizon;

            public HistogramPanel(int width, int dimHeight, int horizon) {
                this.width = width;
                this.dimHeight = dimHeight;
                this.horizon = horizon;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = 10+BAR_WIDTH;

                int[] Array= Rolls.RollsAsArray();

                for(int i=0;i<=10;i++){
                    g.drawString(""+i*10,0,horizon-i*(INCREMENT*10));
                }

                g.setColor(Color.BLUE);
                for(int i=0;i<Array.length;i++){
                    System.out.println("Number:"+ (i+2) +" Value:"+Array[i]);
                    int height = Array[i] * INCREMENT;
                    int y = horizon - height;
                    g.fillRect(x, y, BAR_WIDTH - 10, height);
                    g.drawString(""+(i+2), x, horizon + 10);
                    x += BAR_WIDTH;
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, dimHeight);
            }
        }


    }




