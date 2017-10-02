import tester.*;

class ExamplesReview {
  Review testReview = new Review();
  
  void testInit(Tester t) {
  t.checkExpect(Review.songList.size(), 87);
  }
  
  //  // RUN THE GAME
  void testRunGame(Tester t) {

    Review r = new Review();
    r.bigBang(600, 600, 0.00001);
  }
}