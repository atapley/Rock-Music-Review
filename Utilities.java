import java.util.ArrayList;
import java.util.Random;

class Utilities {

//EFFECT: Sorts the given ArrayList according to the given comparator
  static <T> void quicksort(ArrayList<T> arr, IComparator<T> comp) {
    quicksortHelp(arr, comp, 0, arr.size());
  }

  //EFFECT: sorts the source array according to comp, in the range of indices [loIdx, hiIdx)
  static <T> void quicksortHelp(ArrayList<T> source, IComparator<T> comp,
      int loIdx, int hiIdx) {
    // Step 0: check for completion
    if (loIdx >= hiIdx) {
      return; // There are no items to sort
    }
    // Step 1: select pivot
    T pivot = source.get(loIdx);
    // Step 2: partition items to lower or upper portions of the temp list
    int pivotIdx = partition(source, comp, loIdx, hiIdx, pivot);
    // Step 3: sort both halves of the list
    quicksortHelp(source, comp, loIdx, pivotIdx);
    quicksortHelp(source, comp, pivotIdx + 1, hiIdx);
  }

  //Returns the index where the pivot element ultimately ends up in the sorted source
  static <T> int partition(ArrayList<T> source, IComparator<T> comp,
      int loIdx, int hiIdx, T pivot) {
    int curLo = loIdx;
    int curHi = hiIdx - 1;
    while (curLo < curHi) {
      // Advance curLo until we find a too-big value (or overshoot the end of the list)
      while (curLo < hiIdx && comp.compare(source.get(curLo), pivot) <= 0) {
        curLo = curLo + 1;
      }
      // Advance curHi until we find a too-small value (or undershoot the start of the list)
      while (curHi >= loIdx && comp.compare(source.get(curHi), pivot) > 0) {
        curHi = curHi - 1;
      }
      if (curLo < curHi) {
        swap(source, curLo, curHi);
      }
    }
    swap(source, loIdx, curHi); // place the pivot in the remaining spot
    return curHi;
  }

  // swap the places of these two vertices
  static <T> void swap(ArrayList<T> source, int t1, int t2) {
    T oldt1 = source.get(t1);

    source.set(t1, source.get(t2));
    source.set(t2, oldt1);
  }

  public static void initSongList() {
    Review.songList = new ArrayList<Song>();

    Review.songList.add(Song.Youve_Lost_That_Lovin_Feel);
    Review.songList.add(Song.Goodnight_Irene);
    Review.songList.add(Song.Downhearted_Blues);
    Review.songList.add(Song.Crazy_Man_Crazy);
    Review.songList.add(Song.Iron_Man);
    Review.songList.add(Song.Like_A_Rolling_Stone);
    Review.songList.add(Song.Masters_Of_War);
    Review.songList.add(Song.More_Than_A_Feeling);   
    Review.songList.add(Song.Born_In_The_USA);
    Review.songList.add(Song.Thatll_Be_The_Day);          
    Review.songList.add(Song.Can_The_Circle_Be_Unbroken);          
    Review.songList.add(Song.Blue_Suede_Shoes);          
    Review.songList.add(Song.Rock_And_Roll_Music);          
    Review.songList.add(Song.Sunshine_Of_Your_Love);          
    Review.songList.add(Song.Take_It_Easy);          
    Review.songList.add(Song.Jailhouse_Rock);          
    Review.songList.add(Song.Evil_Ways);          
    Review.songList.add(Song.Aint_That_A_Shame);          
    Review.songList.add(Song.Ive_Got_A_Crush_On_You);          
    Review.songList.add(Song.How_Do_You_Do_It);          
    Review.songList.add(Song.Go_Your_Own_Way);          
    Review.songList.add(Song.The_Message);          
    Review.songList.add(Song.Great_Balls_Of_Fire);          
    Review.songList.add(Song.Evil_Is_Going_On);          
    Review.songList.add(Song.I_Cant_Explain);          
    Review.songList.add(Song.Im_Dreaming_Of_A_White_Christmas);          
    Review.songList.add(Song.The_Number_Of_The_Beast);          
    Review.songList.add(Song.White_Rabbit);          
    Review.songList.add(Song.SHake_Rattle_And_Roll);          
    Review.songList.add(Song.Breaking_The_Law);          
    Review.songList.add(Song.Whole_Lotta_Love);          
    Review.songList.add(Song.Im_Sitting_On_Top_of_The_World);          
    Review.songList.add(Song.Lets_Go_Crazy);          
    Review.songList.add(Song.Tutti_Frutti_LR);          
    Review.songList.add(Song.Rock_Island_Line);          
    Review.songList.add(Song.Caldonia);          
    Review.songList.add(Song.Like_A_Virgin);          
    Review.songList.add(Song.Master_Of_Puppets);          
    Review.songList.add(Song.Billie_Jean);          
    Review.songList.add(Song.Straight_Edge);          
    Review.songList.add(Song.Money);          
    Review.songList.add(Song.Nights_In_White_Satin);          
    Review.songList.add(Song.Shout_At_The_Devil);          
    Review.songList.add(Song.Supremacy);          
    Review.songList.add(Song.My_Best_Friends_Girl);          
    Review.songList.add(Song.Smells_Like_Teen_Spirit);          
    Review.songList.add(Song.Tutti_Frutti_PB);          
    Review.songList.add(Song.Show_Me_The_Way);          
    Review.songList.add(Song.Please_Dont_Talk_About_Me_When_Im_Gone);          
    Review.songList.add(Song.Please_Please_Me);          
    Review.songList.add(Song.Dont_Believe_The_Hype);          
    Review.songList.add(Song.Bohemian_Rhapsody);          
    Review.songList.add(Song.Airbag);          
    Review.songList.add(Song.Bulls_On_Parade);          
    Review.songList.add(Song.Blitzkrieg_Bop);          
    Review.songList.add(Song.Crossroad_Blues);          
    Review.songList.add(Song.Rocket_88);          
    Review.songList.add(Song.Rock_Box);          
    Review.songList.add(Song.Schools_Out);          
    Review.songList.add(Song.Anarchy_In_The_UK);          
    Review.songList.add(Song.Smoke_On_The_Water);          
    Review.songList.add(Song.Black_Hole_Sun);          
    Review.songList.add(Song.Space_Oddity);          
    Review.songList.add(Song.Psycho_Killer);          
    Review.songList.add(Song.Good_Vibrations);          
    Review.songList.add(Song.Surfin_USA);          
    Review.songList.add(Song.A_Day_In_The_Life);          
    Review.songList.add(Song.Mr_Tambourine_Man);          
    Review.songList.add(Song.Shboom);          
    Review.songList.add(Song.Purple_Haze);          
    Review.songList.add(Song.Tom_Dooley);          
    Review.songList.add(Song.You_Really_Got_Me);          
    Review.songList.add(Song.The_One_I_Love);          
    Review.songList.add(Song.Monkey_Gone_To_Heaven);          
    Review.songList.add(Song.Dont_Stand_So_Close_To_Me);          
    Review.songList.add(Song.Color_Me_Impressed);          
    Review.songList.add(Song.I_Cant_Get_No_Satisfaction);          
    Review.songList.add(Song.Paint_It_Black);          
    Review.songList.add(Song.Will_You_Still_Love_Me_Tomorrow);          
    Review.songList.add(Song.Search_And_Destroy);          
    Review.songList.add(Song.Wont_Get_Fooled_Again);          
    Review.songList.add(Song.Heart_Full_of_Soul);          
    Review.songList.add(Song.Tomorrow_Never_Knows);          
    Review.songList.add(Song.Pride);          
    Review.songList.add(Song.Panama);          
    Review.songList.add(Song.Ashes_Of_American_Flags);          
    Review.songList.add(Song.Roundabout);          
  }
  
  static void reInitID() {
    for (Song s: Review.songList) {
      s.id = new Random().nextInt(200);
    }
  }

  public static ArrayList<Song> initChoices(int current) {
    ArrayList<Song> choices = new ArrayList<Song>();
    choices.add(Review.songList.get(current));
    while (choices.size() < 4) {
      Song randomSong = Review.songList.get((new Random().nextInt(86)));
      
      if (!choices.contains(randomSong)) {
        choices.add(randomSong);
      }
      else {
        
      }
    }
    Utilities.reInitID();
    Utilities.quicksort(choices, new CompareSong());
    return choices;
  }
  
  public static boolean isNumeric(String key) {
    return key.equals("1") || key.equals("2")
        || key.equals("3") || key.equals("4")
        || key.equals("5") || key.equals("6")
        || key.equals("7") || key.equals("8")
        || key.equals("9") || key.equals("0");
  }
}