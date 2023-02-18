package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        /**if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);*/
        int seed=18283;
        MemoryGame game = new MemoryGame(40, 40,seed);
        game.startGame();
    }

    public MemoryGame(int width, int height,int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand=new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String product="";
        for(int i=0;i<n;++i){
            char character=CHARACTERS[rand.nextInt(26)];
            product+=String.valueOf(character);
        }
        return product;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear();
        StdDraw.text(width/2,height/2,s);
        StdDraw.show();
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        int len=letters.length();
        for(int i=0;i<len;++i){
            drawFrame(String.valueOf(letters.charAt(i)));
            try {
                Thread.currentThread().sleep(1000);
                StdDraw.clear();
                Thread.currentThread().sleep(500);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String product="";
        for(int i=0;i<n;++i){
            while(!StdDraw.hasNextKeyTyped()){}
            char thisKey=StdDraw.nextKeyTyped();
            product+=String.valueOf(thisKey);
            drawFrame(product);
        }
        return product;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round=1;
        playerTurn=false;
        gameOver=false;
        while(true){
            drawFrame(String.format("Round:%d",round));
            try {
                Thread.currentThread().sleep(1000);
                String target=generateRandomString(round);
                flashSequence(target);
                playerTurn=true;
                String user=solicitNCharsInput(round);
                if(!user.equals(target)){
                    break;
                }else{
                    playerTurn=false;
                    round+=1;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        gameOver=true;
        drawFrame(String.format("Game Over!You made it to round:%d",round));
        //TODO: Establish Game loop
    }

}
