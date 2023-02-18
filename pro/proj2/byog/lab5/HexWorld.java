package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private int size;
    private TETile[][] world;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    public HexWorld(int size){
        this.size=size;
        TERenderer ter=new TERenderer();
        ter.initialize(width(),height());

        world=new TETile[width()][height()];
        fillWith(0,0,width(),height(),Tileset.NOTHING);
        addHexagon(0,size,Tileset.FLOWER);
        ter.renderFrame(world);
    }

    private void fillWith(int x1,int y1,int x2,int y2,TETile tile){
        for(int i=x1;i<x2;++i){
            for(int j=y1;j<y2;++j){
                world[i][j]=tile;
            }
        }
    }

    /** returns the size of the hexagons */
    public int size(){return size;}

    /** returns the width of the canvas */
    public int width(){return 11*size-6;}

    /** returns the height of the canvas */
    public int height(){return 10*size;}

    /** returns the start position of x for the line-th line*/
    private int startX(int xPos,int line){return Math.min(Math.abs(line-size),Math.abs(line-size-1))+xPos;}

    /** returns the length of the line-th line*/
    private int lineLen(int line){return 3*size-2-2*Math.min(Math.abs(line-size),Math.abs(line-size-1));}

    /** returns which line the line-th line is on*/
    private int getY(int yPos,int line){return yPos-size+line;}

    private void addHexagon(int xPos,int yPos,TETile tile){
        int lineNum=2*size;
        for(int i=1;i<lineNum+1;++i){
            int line=getY(yPos,i);
            int start=startX(xPos,line);
            int len=lineLen(line);
            for(int j=0;j<len;++j){
                world[start+j][line]=tile;
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.FLOWER;
            default: return Tileset.WATER;
        }
    }

    public static void main(String args[]){
        HexWorld hexWorld=new HexWorld(3);
    }
}
