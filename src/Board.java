
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;
public class Board{
   
    private JLabel[][] labels;
    private boolean xTurn;
    private boolean gameOver;
    private int[][] board;
    private ArrayList<int[]> winSet;
    public Board(){
        xTurn = true;
        gameOver = false;
        winSet = new ArrayList<>();
        board = new int[3][3];
    }
    public void createLabels(ImageImplement panel){
        labels = new JLabel[3][3];
        for (int i = 0; i < labels.length; i++){
            for (int j = 0; j < labels.length; j++){
                final int x_coord = i;
                final int y_coord = j;
                labels[i][j] = new JLabel();
                labels[i][j].setForeground(Color.black);
                labels[i][j].addKeyListener(new KeyAdapter(){
                    public void keyPressed(KeyEvent e){
                        int key = e.getKeyCode();
                        if (!gameOver){ 
                            if (key == 88){
                                if (xTurn){
                                    labels[x_coord][y_coord].setText("X");
                                    labels[x_coord][y_coord].setEnabled(false);
                                    board[x_coord][y_coord] = 1;
                                    checkWin(x_coord,y_coord);
                                    xTurn = !xTurn;
                                }
                            }
                            if (key == 79){
                                if (!xTurn){
                                    labels[x_coord][y_coord].setText("O");
                                    labels[x_coord][y_coord].setEnabled(false);
                                    board[x_coord][y_coord] = -1;
                                    checkWin(x_coord,y_coord);
                                    xTurn = !xTurn;
                                }
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
                labels[i][j].setFont(new Font("Comic Sans",Font.BOLD,50));
                labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                labels[i][j].setBounds(43+(i*143),30+(j*136),136,133);
                panel.add(labels[i][j]);


            }

        }
    }
    // we could extend our code later to check for a win and make a new board
    public void checkWin(int x, int y){
        int target = xTurn ? 3 : -3;
        if (checkVertical(target,y) || checkHorizontal(target,x) || checkDiagonal(target,x,y)){
            for (int[] win : winSet){
                labels[win[0]][win[1]].setForeground(Color.RED);
                gameOver = true;
            }
        }
    }
    private boolean checkHorizontal(int target, int x){
        for (int i = 0; i < 3; i++){
            if(board[x][i]== (target == -3 ? -1 : 1)){
                winSet.add(new int[]{x,i});
            }
        }
        if (winSet.size() == 3){
            return true;
        }
        winSet.clear();
        return false;
    }
    private boolean checkVertical(int target, int y){
        for (int i = 0; i < 3; i++){
            if(board[i][y] == (target == -3 ? -1 : 1)){
                winSet.add(new int[]{i,y});
            }
        }
        if(winSet.size() == 3){
            return true;
        }
        winSet.clear();
        return false;

    }
    private boolean checkDiagonal(int target, int x, int y){
        int count = 0;
        count = board[2][0] + board[1][1] + board[0][2];
        if (count == target){
            winSet.add(new int[]{2,0});
            winSet.add(new int[]{1,1});
            winSet.add(new int[]{0,2});
            return true;
        }
        winSet.clear();
        count = board[0][0] + board[1][1] + board[2][2];
        if (count == target){
            winSet.add(new int[]{0,0});
            winSet.add(new int[]{1,1});
            winSet.add(new int[]{2,2});
            return true;
        }
        return false;
    }
}
