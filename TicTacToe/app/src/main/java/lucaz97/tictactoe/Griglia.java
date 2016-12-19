package lucaz97.tictactoe;

/**
 * Created by lucaz on 06/07/2016.
 */
public class Griglia {
    private int[][] grill = new int[3][3];

    public Griglia(){

    }

    public Griglia(int[] a){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){

                grill[row][col] = a[(int)row * 3 + col];
            }
        }
    }

    public int getRowState(int row){
        int state = 0;
        int playerCounter = 0;
        int compCounter = 0;
        for(int col = 0; col <3; col++){
            if(grill[row][col] == 1){
                playerCounter = playerCounter + 1;
            }
            if(grill[row][col] == 2){
                compCounter = compCounter + 1;
            }
        }
        if(playerCounter == 3){
            state = 1;
        } else if(compCounter == 3){
            state = 2;
        }else if(playerCounter + compCounter == 3){
            state = 4;
        }
        //System.out.println(playerCounter);
        return state;
    }

    public int getColState(int col){
        int state = 0;
        int playerCounter = 0;
        int compCounter = 0;
        for(int row = 0; row < 3; row++){
            if(grill[row][col] == 1){
                playerCounter = playerCounter + 1;
            }
            if(grill[row][col] == 2){
                compCounter = compCounter + 1;
            }
        }
        if(playerCounter == 3){
            state = 1;
        } else if(compCounter == 3){
            state = 2;
        }else if(playerCounter + compCounter == 3){
            state = 4;
        }
        return state;
    }

    public int getDiagonalsState(){
        int state = 0;
        int playerCounter = 0;
        int compCounter = 0;

        for(int i = 0; i < 3; i++){
            if(grill[i][i] == 1){
                playerCounter = playerCounter + 1;

            }
            if(grill[i][i] == 2){
                compCounter = compCounter + 1;
            }
        }
        System.out.println("ply1 '\' count: " + playerCounter);
        System.out.println("ply2 '\' count: " + compCounter);
        if(playerCounter == 3){
            state = 1;
            return state;
        } else if(compCounter == 3){
            state = 2;
            return state;
        }

        state = 0;
        playerCounter = 0;
        compCounter = 0;

        for(int row = 0; row < 3 ; row++){
            if(grill[row][2 - row] == 1){
                playerCounter = playerCounter + 1;
            }
            if(grill[row][2-row] == 2){
                compCounter = compCounter + 1;
            }
        }
        System.out.println("ply1 / count: " + playerCounter);
        System.out.println("ply2 / count: " + compCounter);
        if(playerCounter == 3){
            state = 1;
        } else if(compCounter == 3){
            state = 2;
        }else if(playerCounter + compCounter == 3){
            state = 4;
        }

        return state;
    }


    public void setCell(int row, int col, int val){
        grill[row][col] = val;
    }

    public int getCell(int row, int col){
        return grill[row][col];
    }

    public void print(){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){

                System.out.println(grill[row][col]);
            }
        }
    }


}
