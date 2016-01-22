/*  Testers for sermon Indexer
 *    Emily Stuckey
 *    1-5-16
 *    This file is for testing while developing.  
 *    For actual use, use SermonIndexMaker
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

/*public void ScriptTester(){
  SermCit bubba = new SermCit("2", 3);
  ScriptureEntry john5 = new ScriptureEntry("John", 5, "7");
  john5.add(bubba);
  System.out.println(john5);
  ScriptureEntry eph3 = new ScriptureEntry("Eph", 3, "2-8");
    eph3.add(bubba);
  System.out.println(eph3);
  ScriptureEntry eph3a = new ScriptureEntry("Eph", 3, "2-6");
  eph3a.add(bubba);
  System.out.println(eph3a);
  ScriptureEntry eph1 = new ScriptureEntry("Eph", 1, "5");
  eph1.add(bubba);
  System.out.println(eph1);
  System.out.println("John to Ephesians: " + john5.compareTo(eph3));
  System.out.println("Eph 3:2-8 v Eph 3: 2-6: " + eph3.compareTo(eph3a));
  eph3.add("3", 2);
  System.out.println(eph3);
}
*/


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
    jacob.add(new IndexEntry("buffalo", "2", 1));

    jacob.add(new IndexEntry("berry", "6", 6));
    System.out.println("jacob? " + jacob);

    jacob.add(new IndexEntry("berry", "5", 3));
    jacob.add(new IndexEntry("apple", "2", 7));

    System.out.println(jacob);
  }

  public void testIndexMaker(){
    IndexMaker jerry = new IndexMaker();
  }

  public void ScriptCitTester(){
    ScriptCit john1 = new ScriptCit("John", 5, "3");
    ScriptCit onePet = new ScriptCit("1 Pet", 2, "7-11");
    ScriptCit oneKing = new ScriptCit("1 Kgs", 2, "7");
    System.out.println("Compare " + john1 + " to " + onePet);
    System.out.println(john1.compareTo(onePet));
    System.out.println("Compare " + onePet + " to " + oneKing);
    System.out.println(onePet.compareTo(oneKing));
  }

  public void ScriptEntryTester(){
    ScriptureEntry indA = new ScriptureEntry("John", 3, "7");
    indA.add("1", 3);
    indA.add("1", 2);
    ScriptureEntry indB = new ScriptureEntry("1 Kgs", 4, "17-20", "4", 12);
    System.out.println(indA);
    System.out.println(indB);
    System.out.print("comparing the above: ");
    System.out.println(indA.compareTo(indB));
  }

  public static void main(String[] args){
    IndexerTester test = new IndexerTester();
    test.ScriptEntryTester();

  }
}
