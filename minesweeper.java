import java.util.Scanner;
import java.util.Random;

public class minesweeper {
    
    public static void main (String[] args) {

        Scanner userInput = new Scanner (System.in);

        int col = 0;
        int row = 0;

        // gets the user input for column while having user error in mind
        boolean userHandle = true;
        System.out.println("Please enter your playing board column size");
        do{
            try {
                String input = userInput.nextLine();
                col = Integer.parseInt(input);
                userHandle = true;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter a valid integer try again");
                userHandle = false;
            }
        } while(userHandle == false);

        // user input for col
        System.out.println("Please enter your playing board row size");
        do{
            try {
                String input = userInput.nextLine();
                row = Integer.parseInt(input);
                userHandle = true;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter a valid integer try again");
                userHandle = false;
            }
        } while(userHandle == false);
        Mine[][] playingboard = new Mine[row][col];
        int mineAmount = (row * col)/3;
        int winningCount = (row * col) - mineAmount;

        setBoard(playingboard, row, col, mineAmount);
        playgame(playingboard, winningCount);

    }
    public static void setBoard( Mine[][] playingboard, int row, int col, int mines){
        Random rand = new Random();

        //initialize the playing board where everything is hidden and is not active
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                playingboard[i][j] = new Mine();

            }
        }

        //activates the random mines
        for(int i = 0; i < mines ; i++){
            playingboard[rand.nextInt(row)][rand.nextInt(col)].setActive(true);

        }
        calcBombs(playingboard, row, col);
        return;
        
    }
    public static void showBoard(Mine[][] playingboard){

        for (int i = 0; i < playingboard.length; i++){
            for (int j = 0; j < playingboard[i].length; j++){
                if (playingboard[i][j].getHidden()){
                    System.out.print("#");
                }
                else{
                    if(!playingboard[i][j].getActive()){
                        System.out.print(playingboard[i][j].getNeighbors());
                    }
                    else{
                        System.out.print(("X"));
                    }
                }
                
            }
            System.out.println();
        }
        return;

    }
    public static void calcBombs(Mine[][] playground, int row, int col){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                
                int count = 0;
                if(i == 0){

                    if(j != 0){
                        //if the mine is not on the left edge
                        if (playground[i][j-1].getActive()){
                            count++;
                        }
                        if (playground[i+1][j-1].getActive()){
                            count++;
                        }
                    }
                    if(j!= col-1){
                        //if the mine is not on the right edge
                        if (playground[i][j+1].getActive()){
                            count++;
                        }
                        if (playground[i+1][j+1].getActive()){
                            count++;
                        }
                    }
                    if(playground[i+1][j].getActive()){
                        count++;
                    }
                    playground[i][j].setNeighbors(count);
                }
                else if(i == row - 1){

                    if(j != 0){
                        //if the mine is not on the left edge
                        if (playground[i-1][j-1].getActive()){
                            count++;
                        }
                        if (playground[i][j-1].getActive()){
                            count++;
                        }
                    }
                    if(j!= col-1){
                        //if the mine is not on the right edge
                        if (playground[i-1][j+1].getActive()){
                            count++;
                        }
                        if (playground[i][j+1].getActive()){
                            count++;
                        }
                    }
                    if (playground[i-1][j].getActive()){
                        count++;
                    }
                    playground[i][j].setNeighbors(count);
                }
                else {
                    if(j!=0){
                        //if mine is not on the left edge
                        if(playground[i-1][j-1].getActive()){
                            count++;
                        }
                        if(playground[i][j-1].getActive()){
                            count++;
                        }
                        if(playground[i+1][j-1].getActive()){
                            count++;
                        }
                    }
                    if(j != row -1){
                        if(playground[i-1][j+1].getActive()){
                            count++;
                        }
                        if(playground[i][j+1].getActive()){
                            count++;
                        }
                        if(playground[i+1][j+1].getActive()){
                            count++;
                        }
                    }
                    if(playground[i-1][j].getActive()){
                        count++;
                    }
                    if(playground[i+1][j].getActive()){
                        count++;
                    }
                    playground[i][j].setNeighbors(count);
                }
            }
        }
        return;

    }
    public static boolean gameOver(Mine[][] playground, int winningCount){
        int row = playground.length;
        int col = playground[1].length;
        int count = 0;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                
                if(i == 0){

                    if(j != 0){
                        //if the mine is not on the left edge
                        if (!playground[i][j-1].getHidden() && !playground[i][j-1].getActive()){
                            count++;
                        }
                        if (!playground[i+1][j-1].getHidden() && !playground[i+1][j-1].getActive()){
                            count++;
                        }
                    }
                    if(j!= col-1){
                        //if the mine is not on the right edge
                        if (!playground[i][j+1].getActive() && !playground[i][j+1].getHidden()){
                            count++;
                        }
                        if (!playground[i+1][j+1].getActive() && !playground[i+1][j+1].getHidden()){
                            count++;
                        }
                    }
                    if(!playground[i+1][j].getActive() && !playground[i+1][j].getHidden()){
                        count++;
                    }
                }
                else if(i == row - 1){

                    if(j != 0){
                        //if the mine is not on the left edge
                        if (!playground[i-1][j-1].getActive() && !playground[i-1][j-1].getHidden()){
                            count++;
                        }
                        if (!playground[i][j-1].getActive() && !playground[i][j-1].getHidden()){
                            count++;
                        }
                    }
                    if(j!= col-1){
                        //if the mine is not on the right edge
                        if (!playground[i-1][j+1].getActive() && !playground[i-1][j+1].getHidden()){
                            count++;
                        }
                        if (!playground[i][j+1].getActive() && !playground[i][j+1].getHidden()){
                            count++;
                        }
                    }
                    if (!playground[i-1][j].getActive() && !playground[i-1][j].getHidden()){
                        count++;
                    }
                }
                else {
                    if(j!=0){
                        //if mine is not on the left edge
                        if(!playground[i-1][j-1].getActive() && !playground[i-1][j-1].getHidden()){
                            count++;
                        }
                        if(!playground[i][j-1].getActive() && !playground[i][j-1].getHidden()){
                            count++;
                        }
                        if(!playground[i+1][j-1].getActive() && !playground[i+1][j-1].getHidden()){
                            count++;
                        }
                    }
                    if(j != row -1){
                        if(!playground[i-1][j+1].getActive() && !playground[i-1][j+1].getHidden()){
                            count++;
                        }
                        if(!playground[i][j+1].getActive() && !playground[i][j+1].getHidden()){
                            count++;
                        }
                        if(!playground[i+1][j+1].getActive() && !playground[i+1][j+1].getHidden()){
                            count++;
                        }
                    }
                    if(!playground[i-1][j].getActive() && !playground[i-1][j].getHidden()){
                        count++;
                    }
                    if(!playground[i+1][j].getActive() && !playground[i+1][j].getHidden()){
                        count++;
                    }
                }
            }
        }
        if(count == winningCount){
            return true;
        }else{
            return false;
        }
    }
    public static void playgame(Mine[][] playingboard, int winningCount){
        Scanner gameInput = new Scanner (System.in);
        int userCol = 0;
        int userRow = 0;
        boolean won = false;

        boolean off = false;
        while(!off){
            showBoard(playingboard);

            // gets the user to choose a place to check
            boolean userHandle = true;
            System.out.println("Where would you like to check? (Enter row then column)");
            do{
                try {
                    String input = gameInput.nextLine();
                    userRow = Integer.parseInt(input);
                    userHandle = true;
                } catch (NumberFormatException e) {
                    System.out.println("You did not enter a valid row try again");
                    userHandle = false;
                } 
            } while(userHandle == false);
            
            do{
                try {
                    String input = gameInput.nextLine();
                    userCol = Integer.parseInt(input);
                    userHandle = true;
                } catch (NumberFormatException e) {
                    System.out.println("You did not enter a valid column try again");
                    userHandle = false;
                } 
            } while(userHandle == false);
            
            //makes sure that the place to check is actually on the board
            do{
                try {
                    playingboard[userRow][userCol].setHidden(false);
                    userHandle = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You entered something that is not on the playingboard");
                    do{
                        try {
                            String input = gameInput.nextLine();
                            userRow = Integer.parseInt(input);
                            userHandle = true;
                        } catch (NumberFormatException b) {
                            System.out.println("You did not enter a valid row try again");
                            userHandle = false;
                        } 
                    } while(userHandle == false);
                    
                    do{
                        try {
                            String input = gameInput.nextLine();
                            userCol = Integer.parseInt(input);
                            userHandle = true;
                        } catch (NumberFormatException b) {
                            System.out.println("You did not enter a valid column try again");
                            userHandle = false;
                        } 
                    } while(userHandle == false);

                    userHandle = false;
                }
            } while (userHandle == false);
            
            
            showBoard(playingboard);

            if(playingboard[userRow][userCol].getActive()){
                won = false;
                off = true;
                System.out.println("Sorry you hit a bomb, get pranked");
            }
            else{
                if(gameOver(playingboard, winningCount)){
                    System.out.println("Congragulations, you have won");
                    won = true;
                    off = true;
                }
            }


        }
    }
}
