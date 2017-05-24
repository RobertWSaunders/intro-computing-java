import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by robertsaunders on 2017-04-22.
 */
public class Run {


    public ArrayList<ClassBee> classBees;

    public ClassBee interate() {
        Iterator<ClassBee> i = classBees.iterator();

    }

    public static void main(String[] args) {

        switch(0...5) {
            case 3:
                System.out.print("l");
                break;
            case
        }


        ClassSea sea = new ClassSea();

        //
        System.out.println(sea.methodOne("swallow"));
        System.out.println(sea.methodOne("fuzzy ", "bunny"));
        System.out.println(sea.methodOne("giant ", "fuzzy ", "bunny"));
        sea.methodTwo("laden");
        System.out.println(sea.methodThree("laden swallow"));



        ClassBee seaDoo = new ClassSea();



        System.out.println(seaDoo.methodOne("giant ", "green ", "bunny"));
        System.out.println(seaDoo.methodOne("swallow"));
        System.out.println(seaDoo.methodOne("green ", "banana"));
        seaDoo.methodTwo("laden ", "swallow");


        ClassEh seaBreeze = new ClassSea();
        System.out.println(seaBreeze.methodOne("mouse"));
        seaBreeze.methodTwo("feathered");
        //System.out.println(seaBreeze.methodThree("fuzzy"));
        ClassEh beeJam = new ClassBee();
        System.out.println(beeJam.methodOne("camel"));
        beeJam.methodTwo("fuzzy camel");
        ClassEh ehWhat = new ClassEh();
        System.out.println(ehWhat.methodOne("elephant"));
    } // end main
}
