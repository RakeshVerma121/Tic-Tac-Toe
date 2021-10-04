import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.MouseInputAdapter;

public class GameFrame extends JFrame{
    private String mainMenuPath = "assets/mainMenu.png";
    private MainPanel main;

    
    private JMenuBar menuBar;

    private JMenuItem newMenu;
    private JMenuItem mainMenu;

    private Board board;

    public GameFrame(){
        main = new MainPanel();
        menuBar = new MenuBar();
        setJMenuBar(menuBar);
        add(main);
        addListeners();
        setSize(500,550);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void addListeners(){
        main.startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getContentPane().removeAll();
                board = new Board();
                GamePanel boardFrame = new GamePanel(board);
                add(boardFrame);
                repaint();
            }
        });
        newMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getContentPane().removeAll();
                board = new Board();
                GamePanel boardFrame = new GamePanel(board);
                add(boardFrame);
                repaint();
            }
        });
        newMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getContentPane().removeAll();
                add(main);
                repaint();
            }
        });
    }
    public class MainPanel extends JPanel{
        private JButton startButton;
        private ImageImplement mainImage;
        public MainPanel(){
            mainImage = new ImageImplement(new ImageIcon(mainMenuPath).getImage());
            
            startButton = new JButton();

            startButton.setBounds(135,175,225,85);
            startButton.setOpaque(false);
            startButton.setBorderPainted(false);

            add(startButton);
            add(mainImage);
            
            setSize(500,600);
            setLayout(null);
            setVisible(true);
        }
    }
    public class GamePanel extends JPanel{
        private ImageImplement boardImage;
        private JLabel[][] labels;
        public GamePanel(Board board){
            boardImage = new ImageImplement(new ImageIcon("assets/board.png").getImage());
            add(boardImage);
            createLabels(boardImage);
            repaint();
            setSize(500,600);
            setLayout(null);
            setVisible(true);
        }
        public void createLabels(ImageImplement panel){
            labels = new JLabel[3][3];
            for (int i = 0; i < labels.length; i++){
                for (int j = 0; j < labels.length; j++){
                    final int x_coord = i;
                    final int y_coord = j;
                    labels[i][j] = new JLabel();
                    createListeners(x_coord,y_coord);
                    labels[i][j].setForeground(Color.black);
                    labels[i][j].setFont(new Font("Comic Sans",Font.BOLD,50));
                    labels[i][j].setFocusable(true);
                    labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                    labels[i][j].setBounds(43+(i*143),30+(j*136),136,133);
                    panel.add(labels[i][j]);
                }
            }
        }
        public void createListeners(int x_coord, int y_coord){
            labels[x_coord][y_coord].addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent e){
                    int key = e.getKeyCode();
                    if (!board.isFinished()){ 
                        if (key == 88 || key == 79){
                            if (board.isXTurn()){
                                labels[x_coord][y_coord].setText("X");
                                labels[x_coord][y_coord].setEnabled(false);
                                board.put(x_coord,y_coord);
                            } else {
                                labels[x_coord][y_coord].setText("O");
                                labels[x_coord][y_coord].setEnabled(false);
                                board.put(x_coord,y_coord);
                            }
                        }
                        if (board.isFinished()){
                            for (int[] win : board.getWinSet()){
                                labels[win[0]][win[1]].setForeground(Color.red);
                            }
                        }
                    }
                }
                
            });
            
            labels[x_coord][y_coord].addMouseListener(new MouseInputAdapter(){
                @Override
                public void mousePressed(MouseEvent e) {
                    labels[x_coord][y_coord].requestFocus();  
                }
            });
        }
    }
    public class MenuBar extends JMenuBar{
        public MenuBar(){
            JMenu menu = new JMenu("Game Menu");
            add(menu);
            newMenu = new JMenuItem("New Game");
            mainMenu = new JMenuItem("Return to Main Menu");
            menu.add(newMenu);
            menu.add(mainMenu);
        }
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
