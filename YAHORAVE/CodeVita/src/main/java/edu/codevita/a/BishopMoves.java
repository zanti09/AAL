package edu.codevita.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 */
public class BishopMoves {

    public static void main(String[] args) {
        BishopMoves bm=new BishopMoves();
        System.out.print("FEN notation: ");
        Scanner scanner = new Scanner(System.in);
        //remove the last part 
        String recordFEN = scanner.nextLine().split("\\s")[0];
        //take each row
        List<String> rows=Arrays.asList(recordFEN.split("/"));
        String[][] board=bm.convertToBoard(rows);
        String[] yNotation=new String[]{"8","7","6","5","4","3","2","1"};
        String[] xNotation=new String[]{"a","b","c","d","e","f","g","h"};
        List<String> movimientosValidos=new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]+" ");  
                String piece=board[i][j];
//                            
            }   
            System.out.println();
        }
    }
    
    private String[][] convertToBoard(List<String> rows){
        String[][] board=new String[8][8];
        int actualColumn=0;
        
        for(int i=0;i<rows.size();i++){
            String row=rows.get(i);
            for(char c:row.toCharArray()){
                try {
                    Integer blanckSpaces=Integer.parseInt(String.valueOf(c));
                    for(int k=0;k<blanckSpaces;k++){
                        board[i][actualColumn]="-";
                        actualColumn++;
                    }                    
                } catch (Exception e) {
                    board[i][actualColumn]=String.valueOf(c);
                    actualColumn++;
                }
            }
            actualColumn=0;
        }
        return board;
    }

}
