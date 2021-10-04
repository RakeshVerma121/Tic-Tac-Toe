
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
public class Board{
   
    private JLabel[][] labels;
    private boolean xTurn;
    private int[][] board;
    private int drawPointX;
    private int drawPointY;
    private boolean leftRight;
    private boolean rightLeft;
    public Board(){
        xTurn = true;
        drawPointX = 0;
        drawPointY = 0;
        board = new int[3][3];
    }
    public void createLabels(JPanel panel){
        labels = new JLabel[3][3];
        for (int i = 0; i < labels.length; i++){
            for (int j = 0; j < labels.length; j++){
                final int x_coord = i;
                final int y_coord = j;
                labels[i][j] = new JLabel();
                labels[i][j].addKeyListener(new KeyAdapter(){
                    public void keyPressed(KeyEvent e){
                        int key = e.getKeyCode();
                        if (key == 88){
                            if (xTurn){
                                labels[x_coord][y_coord].setText("X");
                                labels[x_coord][y_coord].setEnabled(false);
                                board[x_coord][y_coord] = 1;
                                if (checkWin(x_coord,y_coord)){
                                    
                                }
                                xTurn = !xTurn;
                            }
                        }
                        if (key == 79){
                            if (!xTurn){
                                labels[x_coord][y_coord].setText("O");
                                labels[x_coord][y_coord].setEnabled(false);
                                board[x_coord][y_coord] = -1;
                                xTurn = !xTurn;
                            }
                        }
                    }
                });
                labels[i][j].addMouseListener(new MouseInputAdapter(){
                    @Override
                    public void mousePressed(MouseEvent e) {
                        labels[x_coord][y_coord].requestFocus();  
                    }
                });
                labels[i][j].setFocusable(true);
                labels[i][j].setFont(new Font("Comic Sans",Font.PLAIN,50));
                labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                labels[i][j].setBounds(43+(i*143),30+(j*136),136,133);
                labels[i][j].setOpaque(true);
                labels[i][j].setVisible(true);
                panel.add(labels[i][j]);


            }

        }
    }
    // we could extend our code later to check for a win and make a new board
    public boolean checkWin(int x, int y){
        int target = xTurn ? 3 : -3;
        return(checkVertical(target,y) || checkHorizontal(target,x) || checkDiagonal(target,x,y));
    }
    private boolean checkHorizontal(int target, int x){
        int count = 0;
        for (int i = 0; i < 3; i++){
            count += board[x][i];
        }
        return (count == target);
    }
    private boolean checkVertical(int target, int y){
        int count = 0;
        for (int i = 0; i < 3; i++){
            count += board[i][y];
        }
        return (count == target);
    }
    private boolean checkDiagonal(int target, int x, int y){
        int count = 0;
        if (!(x == 0 && y == 0) || !(x == 1 && y == 1) || !(x == 2 && y == 2)){
            return false;
        }
        count = board[2][0] + board[1][1] + board[0][2];
        if (count == target){
            rightLeft = true;
            return true;
        }
        count = board[0][0] + board[1][1] + board[2][2];
        if (count == target){
            leftRight = true;
            return true;
        }
        return false;
    }
}
