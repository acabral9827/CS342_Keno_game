import javafx.scene.Scene;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Project2JavaFXTest {

    static Set<Integer> testingSetA;
    static Set<Integer> testingSetB;
    static Set<Integer> testingSetC;
    static Set<Integer> testingSetD;
    static Set<Integer> testingSetE;
    static Set<Integer> testingSetF;
    static Set<Integer> testingSetG;
    static Set<Integer> testingSetH;
    static Set<Integer> testingSetI;
    static Set<Integer> testingSetJ;
    static Set<Integer> testingSetK;
    static Set<Integer> testingSetL;
    static Set<Integer> testingSetM;

    JavaFXTemplate obj = new JavaFXTemplate();


    @BeforeAll
    static void setUp()
    {
        testingSetA = new HashSet<>(10);
        testingSetB = new HashSet<>(10);
        testingSetC = new HashSet<>(10);
        testingSetD = new HashSet<>(10);
        testingSetE = new HashSet<>(10);
        testingSetF = new HashSet<>(10);
        testingSetG = new HashSet<>(10);
        testingSetH = new HashSet<>(10);
        testingSetI = new HashSet<>(10);
        testingSetJ = new HashSet<>(10);
        testingSetK = new HashSet<>(10);
        testingSetL = new HashSet<>(10);
        testingSetM = new HashSet<>(10);


    }

    @Test
    void testSetWorksCorrectly()
    {
        testingSetA.add(20);
        testingSetA.add(30);
        testingSetA.add(40);
        testingSetA.add(50);
        testingSetA.add(10);

        assertEquals(5, testingSetA.size(), "wrong value");
    }

    @Test
    void testSetWorksWithRandomNumbers()
    {
        Random ran = new Random();
        int randomNum = ran.nextInt(80)+1;

        testingSetB.add(randomNum);

        for ( int i = 0; i < 10; i++ )
        {
            if ( !testingSetB.contains(randomNum) )
            {
                testingSetB.add(randomNum);
                assertEquals(true, testingSetB.contains(randomNum), "wong value");
            }
        }
    }

    @Test
    void testSetIntersection()
    {
        testingSetC.add(20);
        testingSetC.add(30);
        testingSetC.add(40);
        testingSetC.add(50);
        testingSetC.add(10);

        testingSetD.add(20);
        testingSetD.add(70);
        testingSetD.add(15);
        testingSetD.add(62);
        testingSetD.add(40);

        testingSetE = testingSetC;
        testingSetE.retainAll(testingSetD);

        assertTrue(testingSetE.contains(20), "wrong value");
        assertTrue(testingSetE.contains(40), "wrong value");
    }

    @Test
    void earningsMethodTest1()
    {

        testingSetF.add(0);
        testingSetG.add(1);
        assertEquals(0, obj.earningsMethod(testingSetF, testingSetG, 1));
        //should test if our 1 spot games work properly
        testingSetG.add(0);

        assertEquals(2,obj.earningsMethod(testingSetF, testingSetG, 1));

    }

    @Test
    void earningsMethodTest2()
    {

        testingSetH.add(0);
        testingSetH.add(1);
        testingSetH.add(2);
        testingSetH.add(3);

        testingSetI.add(0);
        testingSetI.add(1);
        testingSetI.add(2);
        testingSetI.add(3);

        //should test if our 4 spot games work properly

        assertEquals(75,obj.earningsMethod(testingSetH, testingSetI, 4));

    }
    @Test
    void earningsMethodTest3()
    {

        testingSetJ.add(0);
        testingSetJ.add(1);
        testingSetJ.add(2);
        testingSetJ.add(3);
        testingSetJ.add(4);
        testingSetJ.add(5);


        testingSetK.add(0);
        testingSetK.add(1);
        testingSetK.add(2);
        testingSetK.add(3);
        testingSetK.add(4);
        testingSetK.add(5);

        //should test if our 8 spot games work properly

        assertEquals(50,obj.earningsMethod(testingSetJ, testingSetK, 8));

    }

    @Test
    void earningsMethodTest4()
    {

        testingSetL.add(0);
        testingSetL.add(1);
        testingSetL.add(2);
        testingSetL.add(3);
        testingSetL.add(4);


        testingSetM.add(0);
        testingSetM.add(1);
        testingSetM.add(2);
        testingSetM.add(3);
        testingSetM.add(4);


        //should test if our 10 spot games work properly

        assertEquals(2,obj.earningsMethod(testingSetL, testingSetM, 10));

    }

//    @Test
//    void sceneTesting() throws Exception
//    {
//        Stage testingStage = new Stage();
//        obj.start(testingStage);
//
//        assertEquals(testingStage, obj.startupScene().getClass(), "wrong value");
//    }

    @Test
    void testG1()
    {
        assertEquals(0, obj.drawMoneyResult);
    }

    @Test
    void testG2()
    {
        assertEquals(200, obj.overallCash);
    }

    @Test
    void testG4()
    {
        assertEquals(0, obj.currentDrawingsCount);
    }

    @Test
    void testG5()
    {
        assertEquals(0, obj.selectedDrawCount);
    }

    @Test
    void testG6()
    {
        assertEquals(0, obj.typeOfSpotGame);
    }

    @Test
    void testG7()
    {
        assertEquals(0, obj.count);
    }

    @Test
    void testMaximumSelections1()
    {
        obj.setMaxPresses(1);
        assertEquals(1, obj.maximumPressesAllowed);
    }

    @Test
    void testMaximumSelections4()
    {
        obj.setMaxPresses(4);
        assertEquals(4, obj.maximumPressesAllowed);
    }

    @Test
    void testMaximumSelections8()
    {
        obj.setMaxPresses(8);
        assertEquals(8, obj.maximumPressesAllowed);
    }

    @Test
    void testMaximumSelections10()
    {
        obj.setMaxPresses(10);
        assertEquals(10, obj.maximumPressesAllowed);
    }

    @Test
    void testSetDrawCount1()
    {
        obj.setDrawCount(1);
        assertEquals(1, obj.selectedDrawCount);
    }

    @Test
    void testSetDrawCount4()
    {
        obj.setDrawCount(4);
        assertEquals(4, obj.selectedDrawCount);
    }

    @Test
    void testSetDrawCount8()
    {
        obj.setDrawCount(8);
        assertEquals(8, obj.selectedDrawCount);
    }

    @Test
    void testSetDrawCount10()
    {
        obj.setDrawCount(10);
        assertEquals(10, obj.selectedDrawCount);
    }

}
