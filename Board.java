public class TTTBoard{
    private char[][]board;

    public TTTBoard()
    {
        board = new char[3][3];
        reset();
    }

    public void reset()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col]= '-';
            }
        }
    }

    public String toString()
    {
        String result = "\n";
        for(int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                result += board[row][col] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public boolean placeXorO(char player, int row, int col)
    {
        if(board[row-1][col-1]=='-')
        {
            board[row-1][col-1] = player;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean full()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(board[row][col] == '-')
                {
                    return false;
                }
            }
        }
        return true;
    }

    public char getWinner()
    {
        String[]win = new String[8];
        int x =0;
        for(int y =0; y < 8; y++)
        {
            win[y] = "";
        }

        for(int row = 0; row <3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                win[x] += board[row][col];
            }
            x++;
        }

        for(int col = 0; col <3; col++)
        {
            for (int row = 0; row < 3; row++)
            {
                win[x] += board[row][col];
            }
            x++;
        }

        win[x] += (board[0][2] + board[1][1] + board[2][0]);
        x++;
        win[x] += (board[0][0] + board[1][1] + board[2][2]);

        char winner = '-';
        for(int i = 0; i < 8; i++)
        {
            if (win[i].equals("XXX"))
            {
                winner = 'X';
            }
            if(win[i].equals("OOO"))
            {
                winner = 'O';
            }
        }
        return winner;
    }
}
