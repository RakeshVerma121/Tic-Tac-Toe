import java.util.ArrayList;

// the board class keeps a representation of the board, and updates a winSet if the game is won
public class Board{
    private boolean isXTurn;
    private boolean isFinished;
    private int[][] board;
    private ArrayList<int[]> winSet;

    // constructor, sets up some of our instance variables
    public Board(){
        isXTurn = true;
        isFinished = false;
        winSet = new ArrayList<>();
        board = new int[3][3];
    }
    // put a value on the board, 1 represents X and -1 represents O
    public void put(int x, int y){
        board[x][y] = isXTurn ? 1 : -1;
        if (checkWin(x,y)){ // check if it won at every put
            isFinished = true;
        }
        isXTurn = !isXTurn; // switch from x to o's turn, we handle this here, not at the GUI
    }
    public ArrayList<int[]> getWinSet(){
        return winSet; // return the winset, only called if finished, only finished if won, only won if our winset contains a win
    }
    public boolean isFinished(){
        return isFinished;
    }
    public boolean isXTurn(){
        return isXTurn;
    }
    // check if the game state is a winning one
    public boolean checkWin(int x, int y){
        int target = isXTurn ? 3 : -3; // a turnary, read as int target = 0; if(isXTurn){ target = 3 }else{ target = -3;}
        return (checkVertical(target,y) || checkHorizontal(target,x) || checkDiagonal(target,x,y));
    }
    private boolean checkHorizontal(int target, int x){
        for (int i = 0; i < 3; i++){
            if(board[x][i]== (target == -3 ? -1 : 1)){
                winSet.add(new int[]{x,i});
            }
        }
        // if winSet < 3 then we aint got a winning ticket
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
