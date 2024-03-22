import java.util.Scanner; //importing Scanner
public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String board[][] = new String[ROW][COL];
    private static void clearBoard(){
        for (int row = 0; row < ROW; row++){ //for each row, go through the columns
            for (int col = 0; col < COL; col++){
                board[row][col] = " "; //makes each cell into a space
            }
        }
    }
    private static void showBoard(){
        for (int row = 0; row < ROW; row++){ //for each row, go through the columns
            for (int col = 0; col < COL; col++){
                System.out.print(board[row][col]); //prints out what is inside each value in the table
                if (col < 2){ //detects if int col < 2 because I don't want to print 3 |
                    System.out.print("|");
                }

            }
            System.out.print("\n");
        }
    }
    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" ")){// checks if it is a space?
            retVal = true; //sets retValue to true
        }
        else {
            retVal = false; //sets retValue to false
        }
        return retVal;
    }
    private static boolean isColWin(String Player){
        for(int row = 0; row < 3; row++){
            if(board[0][row].equals(Player) && board[1][row].equals(Player) && board[2][row].equals(Player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String Player){
        for(int col = 0; col < 3; col++){
            if(board[col][0].equals(Player) && board[col][1].equals(Player) && board[col][2].equals(Player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagWin(String Player){
        if (board[0][0].equals(Player) && board[1][1].equals(Player) && board[2][2].equals(Player)){
            return true;
        }
        else if (board[0][2].equals(Player) && board[1][1].equals(Player) && board[2][0].equals(Player)){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean isWin(String Player){
        if(isColWin(Player) || isRowWin(Player) || isDiagWin(Player))
        {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //putting Scanner into main method

        clearBoard();//clears the board


        String player = "X";

        int count = 0; //variable for # of moves
        int rowChoice;
        int colChoice;
        boolean validMove = false; //defining variables
        boolean gameGoing = true;
        String YN = "Y";


        while (YN.equals("Y")) { //loop while players wants to play
            gameGoing = true; //variable to keep the game going
            showBoard(); //shows the board

            while (gameGoing) {
                validMove = false; //auto makes the valid move false so that the loop can run

                do {
                    rowChoice = SafeInput.getRangedInt(in, player + " what row would you like to move in?", 1, 3) - 1;
                    colChoice = SafeInput.getRangedInt(in, player + ", what column would you like to move in?", 1, 3) - 1; //outputing for user to make their choice on the graph

                    if (isValidMove(rowChoice, colChoice)) {
                        board[rowChoice][colChoice] = player; //sets the player's choice on the board
                        validMove = true; //player made valid move
                    } else {
                        System.out.println("Invalid move, choose again"); //player did not make valid move
                    }
                } while (!validMove); //loop runs until player enters a valid loop

                showBoard(); //shows the board again

                if (isWin(player)) { //loop to detect if the player has won
                    gameGoing = false; //stops the game
                    System.out.println("Player " + player + " wins!"); //outputs that the player wins
                } else if (count == 8) { //loop to detect if there is a tie
                    gameGoing = false; //stops the game
                    System.out.println("The game is a tie."); //outputs game is tie
                }

                count += 1; //makes variable count go up by one. Count detects for ties

                if (player.equals("X")){
                    player = "O";
                }
                else if (player.equals("O")){
                    player = "X";
                } //script to change who the player is
            }

            YN = SafeInput.getYNConfirm(in, "Would you like to play again? (Y/N)"); //asks if player wants top play again using SafeInputMethod
            if (YN.equals("Y")) { //detects if player entered Y
                clearBoard(); //clears the board

                player = "X"; //auto sets the player to 'X'
                count = 0; //sets amount of moves back to 0
            }
            else{
                System.out.println("Thanks for playing!!!");
            }
        }



       // String playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");
    }

}