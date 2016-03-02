

import java.util.Scanner;

public class Connect4 
{

    public static boolean putDisk(char[][] field, int column, char color) 
    {
        if (field[0][column] != ' ')
            return false;

        for (int row = 0; row < 7; ++row)
        {
                if (field[row][column] != ' ')
                {
                  field[row-1][column] = color;
                  return true;
               }
        }
       
        field[6][column] = color;
        return true;
    }


    
    private static char getWinnerInColumns(char[][] field) {
        
        for (int column = 0; column < 7; ++column) {
            int count = 0;
            
            for (int row = 1; row < 7; ++row) {
                if (field[row][column] != ' ' &&
                    field[row][column] == field[row-1][column])
                    ++count;
                else
                    count = 1;

              
                if (count >= 4) {
                    
                    return field[row][column];
                }
            }
        }
        
        return ' ';
    }
 
    public static char getWinner(char[][] field) 
    {
        char winner = getWinnerInColumns(field);
        if (winner != ' ') return winner;
    
            
        for (int i = 0; i < field.length; ++i)
            for (int j = 0; j < field[i].length; ++j)
                if (field[i][j] == ' ') return ' ';

        return 'C';
    }

    public static void printField(char[][] field) {
        for (int row = 0; row < 7; ++row) {
            System.out.print("| ");
            for (int col = 0; col < 7; ++col)
                System.out.print(field[row][col] + "| ");
            System.out.println();
        }

        // Print bottom line
        for (int col = 0; col < 7; ++col)
            System.out.print("---");
        System.out.println();
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
     
        char[][] field = new char[7][7];

        for (int i = 0; i < 7; ++i)
            for (int j = 0; j < 7; ++j)
                field[i][j] = ' ';
        
        printField(field);

    
        boolean isRed = true;
        while (true) {
            if (isRed)
                System.out.println("Red's turn!");            
            else 
                System.out.println("Yellow's turn!");
            System.out.print("Choose column (1-7) for a disk:");
           
            int column = input.nextInt();
            if (column < 1 || column > 7) {
                System.out.println("Column should be from 1 to 7");
                continue;
            }
            
            if (!putDisk(field, column - 1, isRed ? 'R' : 'Y')) {
                System.out.println("This column is filled! Choose another one.");
                continue;
            }

            printField(field);

            
            char result = getWinner(field);
            if (result == 'D') {
                System.out.println("It is a draw!");
                break;
            }
            else if (result == 'R') {
                System.out.println("Red win!");
                break;
            }
            else if (result == 'Y') {
                System.out.println("Yellow win!");
                break;
            }
           
            isRed = !isRed;
        }
    }
}
