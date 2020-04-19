package tictac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


//currently need to use playerX and playO move arrays to
//continually remove whatever space was used i.e if
//x takes a1, go into the array and remove a1 from x's array

//this will allow me to run a check every turn to see if either player is missing the required pieces to win! i.e playerOMoves is missing a1, b2, c3. That is a diagonal win.
public class game extends Frame implements ActionListener {

    //buttons
    private Button a1;
    private Button a2;
    private Button a3;

    private Button b1;
    private Button b2;
    private Button b3;

    private Button c1;
    private Button c2;
    private Button c3;

    //button array to create for panel
    Button[] btns = {a1, a2, a3, b1, b2, b3, c1, c2, c3};

    //button names as strings for comparison purposes
    String[] btnsNames = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};

    //arrays to add player moves to
    String[][] solution = {
            {"a1", "b1", "c1"},
            {"a2", "b2", "c2"},
            {"a3", "b3", "c3"}
    };

    

    //turn counter;
    int turn = 1;

    //player x counter
    int xTurn = 0;

    //player o counter
    int oTurn = 0;

    //score counter
    private int score = 0;

    public game() {
        Panel panelSpace = new Panel(new GridLayout(3, 3));

        //construct and add buttons to panel
        for (int i = 0; i <= 8; i++) {
            btns[i] = new Button(btnsNames[i]);
            add(btns[i]);
            btns[i].addActionListener(this);
            panelSpace.add(btns[i]);
        }

        //general window info
        setLayout(new BorderLayout());
        add(panelSpace, BorderLayout.CENTER);
        setTitle("Tic Tac Toe");
        setSize(900, 900);
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

            new game();
    }

    //checks if pressed button is currently in use
    //if not returns that the move is legal
    //param e = clicked button
    //return legal = label of button is empty
    public boolean moveCheck(Button e) {
        boolean legal;
        if (e.getLabel() != "X" && e.getLabel() != "O") {
            legal = true;
            return legal;
        } else {
            legal = false;
            return legal;
        }


    }

    public void move(ActionEvent e) {
        String findName;
        for (int i = 0; i < btns.length; i++) {
            if (e.getSource() == btns[i]) {
                if (moveCheck(btns[i]) == true && (turn % 2) == 0) {
                    turn++;
                    findName = btns[i].getLabel();
                    btns[i].setLabel("X");
                    for (int d = 0; d < 3; d++)
                    {
                        for (int j = 0; j < 3; j++)
                        {
                            if (findName.equals(solution[d][j])) {
                                solution[d][j] = "X";
                            }
                        }
                    }
                } else if (moveCheck(btns[i]) == true && (turn % 2) != 0) {
                    turn++;
                    findName = btns[i].getLabel();
                    btns[i].setLabel("O");
                    for (int k = 0; k < 3; k++)
                    {
                        for (int v = 0; v <3; v++)
                        {
                            if (findName.equals(solution[k][v])) {
                                solution[k][v] = "O";
                            }
                        }
                    }
                }
            }
        }
        if (winCheck())
        {
            System.out.println("YOU WIN!");
            resetBoard();
        }
    }
    //public void moveList(int a, String n)
   // {
      //  playerXMoves[xTurn] = n;
       // xTurn++;
    //    Arrays.sort(playerXMoves);
   // }
    public boolean winCheck() {
        for (int k = 0; k < 3; k++)
        {
            for (int v = 0; v <3; v++)
            {
                System.out.print(solution[v][k] + " ");
            }
            System.out.println(" ");
        }
        boolean gameOver = false;
        int xWins = 0;
        int oWins = 0;
        //looping through the rows
        for (int row = 0; row < 3; row++) {
            xWins = 0;
            oWins = 0;
            System.out.println("NEW LOOP");
            for (int col = 0; col < 3; col++) {
                if (solution[row][col].equals("O")) {
                    System.out.println("HIT O");
                    oWins++;
                }
                if (solution[row][col].equals("X")) {
                    System.out.println("HIT X");
                    xWins++;
                }
                System.out.println("Current Row: " + row);
                System.out.println("Current col: " + col);
            }
            if (oWins == 3) {
                return gameOver = true;
            }
            if (xWins == 3) {
                return gameOver = true;
            }
            System.out.println("O score: " + oWins);
            System.out.println("X score: " + xWins);
        }
        //looping through columns
        for (int row = 0; row < 3; row++) {
            xWins = 0;
            oWins = 0;
            System.out.println("NEW LOOP");
            for (int col = 0; col < 3; col++) {
                if (solution[col][row].equals("O")) {
                    System.out.println("HIT O");
                    oWins++;
                }
                if (solution[col][row].equals("X")) {
                    System.out.println("HIT X");
                    xWins++;
                }
                System.out.println("Current Col: " + row);
                System.out.println("Current Row: " + col);
            }
            if (oWins == 3) {
                return gameOver = true;
            }
            if (xWins == 3) {
                return gameOver = true;
            }
            System.out.println("O score: " + oWins);
            System.out.println("X score: " + xWins);
        }
            if (btns[0].getLabel().equals("O") && btns[4].getLabel().equals("O") && btns[8].getLabel().equals("O")) {
                System.out.println("O WINS");
                return gameOver = true;
            }
            if (btns[0].getLabel().equals("X") && btns[4].getLabel().equals("X") && btns[8].getLabel().equals("X")) {
                System.out.println("X WINS");
                return gameOver = true;
            }
        if (btns[2].getLabel().equals("O") && btns[4].getLabel().equals("O") && btns[6].getLabel().equals("O")) {
            System.out.println("O WINS");
            return gameOver = true;
        }
        if (btns[2].getLabel().equals("X") && btns[4].getLabel().equals("X") && btns[6].getLabel().equals("X")) {
            System.out.println("X WINS");
            return gameOver = true;
        }
            return gameOver;
        }
    public void resetBoard()
    {
        int counterBtn = 0;
        for (Button b : btns) {
            b.setLabel(btnsNames[counterBtn]);
            counterBtn++;
        }
        if ((turn % 2) == 0) {
            JOptionPane.showMessageDialog(null, "Congrats, O Wins!");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Congrats, X Wins!");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move(e);
    }
}