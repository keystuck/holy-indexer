/*  Testers for sermon Indexer
 *    Emily Stuckey
 *    1-5-16
 */

public class IndexerTester {

  public SermCit CitCreator(String word, String serm, int para){
    return new SermCit(word, serm, para);
  }

  public static void main(String[] args){
    IndexerTester test = new IndexerTester();
    SermCit howdy = test.CitCreator("howdy", "3", 8);
    SermCit weal = test.CitCreator("weal", "3", 5);
    SermCit howdy2 = test.CitCreator("howdy", "3", 19);
    SermCit howdy3 = test.CitCreator("howdy", "3", 8);
    System.out.println(howdy + "\n" + weal + "\n" + howdy2 + "\n" + howdy3 + "\n" +
      "howdy before weal? " + (howdy.compareTo(weal) < 0));
    System.out.println("howdy before howdy2? " + (howdy.compareTo(howdy2) < 0));
    System.out.println("howdy before howdy3?" + (howdy.compareTo(howdy3) < 0));
  }
}
