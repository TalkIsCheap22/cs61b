import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars(){
        Character[] ch=new Character[]{'a','b'};
        boolean exampleTrue=offByOne.equalChars(ch[0],ch[1]);
        assertEquals(true,exampleTrue);
        boolean exampleFalse=offByOne.equalChars(ch[0],ch[0]);
        assertEquals(false,exampleFalse);
    }
    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
