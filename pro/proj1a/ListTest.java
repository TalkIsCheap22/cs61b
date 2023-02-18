import org.junit.*;
import org.junit.Assert.*;

public class ListTest{

    @Test
    public void TestArrayDeque(){
        ArrayDeque<Integer> datas=new ArrayDeque<>();
        for(int i=0;i<20;++i){
            datas.addFirst(i);
            datas.printDeque();
        }
    }

}