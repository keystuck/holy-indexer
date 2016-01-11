/*  Testers for sermon Indexer
 *    Emily Stuckey
 *    1-5-16
 */
 import java.util.*;

public class IndexerTester {

  public void CitTester(){
    SermCit howdy = new SermCit("3", 8);
    SermCit weal = new SermCit("3", 5);
    SermCit howdy2 = new SermCit("2", 19);
    SermCit howdy3 = new SermCit("3", 8);
    System.out.println(howdy + "\n" + weal + "\n" + howdy2 + "\n" + howdy3);
    CitCompare(howdy, howdy3);
    CitCompare(howdy, howdy2);
    CitCompare(howdy2, howdy3);
  }

  public void CitCompare(SermCit a, SermCit b){
    System.out.println("Comparing " + a + " to " + b);
    System.out.println(a.compareTo(b));
  }




  public void IndexEntryTester(){
    SermCit x = new SermCit("3", 2);
    List<SermCit> y = new ArrayList<SermCit>();
    y.add(x);
    IndexEntry bob = new IndexEntry("holy", y);
    System.out.println(bob);
    SermCit z = new SermCit("2", 1);
    bob.add(z);
    System.out.println(bob);
    IndexEntry james = new IndexEntry("berry", "6", 5);
    james.add("3", 7);
    System.out.println(james);
  }

  public void makeIndex(){
    System.out.println("\nIndex running");
    Index jacob = new Index();
    SermCit x = new SermCit("3", 2);
    IndexEntry james = new IndexEntry("berry", "6", 6);
    james.add("3", 7);
    james.add(x);
    jacob.add(james);
    jacob.add(new IndexEntry("buffalo", "2", 1));
    jacob.add(new IndexEntry("berry", "6", 6));
    jacob.add(new IndexEntry("berry", "5", 3));
    jacob.add(new IndexEntry("apple", "2", 7));
    System.out.println(jacob);
  }

  public void testIndexMaker(){
    IndexMaker jerry = new IndexMaker();
  }


  public static void main(String[] args){
    IndexerTester test = new IndexerTester();
    test.makeIndex();
    test.testIndexMaker();

  }
}
