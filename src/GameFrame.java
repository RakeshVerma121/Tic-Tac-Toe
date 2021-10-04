import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
    private String mainMenuPath = "assets/mainMenu.png";
    private MainPanel main;

    
    private JMenuBar menuBar;

    private JMenuItem newMenu;
    private JMenuItem bestMove;
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
                remove(main);
                board = new Board();
                GamePanel boardFrame = new GamePanel(board);
                add(boardFrame);
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
            startButton.setOpaque(true);
            startButton.setContentAreaFilled(true);
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
        public GamePanel(Board board){
            boardImage = new ImageImplement(new ImageIcon("assets/board.png").getImage());
            add(boardImage);
            board.createLabels(boardImage);
            repaint();
            setSize(500,600);
            setLayout(null);
            setVisible(true);
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
