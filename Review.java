import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.*;


// represents the main game class
class Review extends World {
  boolean first = true;
  int songNum = 0;
  int question = 0;
  int score = 0;
  String correct = "";
  boolean last = false;
  ArrayList<Song> choices = new ArrayList<Song>();
  Sound music;
  static ArrayList<Song> songList;
  
  Review() {
    Utilities.initSongList();
    Utilities.quicksort(Review.songList, new CompareSong());
    this.choices = Utilities.initChoices(this.songNum);
  }

  public void onKeyEvent(String ke) {
    if (ke.equals("enter") && this.first) {
      this.first = false;
      music = new Sound("/Users/alex_tapley/Desktop/WAV Files/" + Review.songList.get(songNum).key + ".wav");
      music.play();
    }
    if (Utilities.isNumeric(ke) && !this.first && correct.equals("") && question != 3) {
      Song cur = Review.songList.get(songNum);
      if (Integer.valueOf(ke) == this.choices.indexOf(cur) + 1) {
        score++;
        correct = "yes";
      }
      else {
        correct = "no";
      }
    }
    if (Utilities.isNumeric(ke) && !this.first && correct.equals("")) {
      int cur = Review.songList.get(songNum).decade;
      int answer = 0;
      
      switch (ke) {
      case "0":
        answer = 1920;
        break;
      case "1":
        answer = 1930;
        break;
      case "2":
        answer = 1940;
        break;
      case "3":
        answer = 1950;
        break;
      case "4":
        answer = 1960;
        break;
      case "5":
        answer = 1970;
        break;
      case "6":
        answer = 1980;
        break;
      case "7":
        answer = 1990;
        break;
      case "8":
        answer = 2000;
        break;
      case "9":
        answer = 2010;
        break;
      }
      
      if (answer == cur) {
        correct = "yes";
        score++;
      }
      else {
        correct = "no";
      }
    }
    if (ke.equals("enter") && this.songNum == Review.songList.size() - 1 
        && this.question == 3) {
      this.last = true;
    }
    if (ke.equals("enter") && !last) {
      this.question++;
      correct = "";
      Utilities.reInitID();
      Utilities.quicksort(this.choices, new CompareSong());
    }
    if (this.question == 4 && !last) {
      music.stop();
      this.songNum++;
      music = new Sound("/Users/alex_tapley/Desktop/WAV Files/" + Review.songList.get(songNum).key + ".wav");
      music.play();
      this.choices = Utilities.initChoices(this.songNum);
      this.question = 1;
    }
  }
  
  public WorldScene makeScene() {
    WorldScene output = new WorldScene(600, 600);
    WorldImage start = new TextImage("Press Enter to Begin!",
        28, FontStyle.BOLD, Color.BLACK);
    WorldImage instruction1 = new TextImage("1. Listen to the song",
        20, FontStyle.REGULAR, Color.BLACK);
    WorldImage instruction2 = new TextImage("2. Press the number key of correct answer",
        20, FontStyle.REGULAR, Color.BLACK);
    WorldImage space = new TextImage("", 20, FontStyle.REGULAR, Color.BLACK);
    WorldImage correctTitle = 
        new TextImage("Correct! Right Answer: " + Review.songList.get(songNum).title,
            20, FontStyle.BOLD, Color.GREEN);
    WorldImage correctArtist = 
        new TextImage("Correct! Right Answer: " + Review.songList.get(songNum).artist,
            20, FontStyle.BOLD, Color.GREEN);
    WorldImage wrongTitle = 
        new TextImage("Wrong! Right Answer: " + Review.songList.get(songNum).title,
            20, FontStyle.BOLD, Color.RED);
    WorldImage wrongArtist = 
        new TextImage("Wrong! Right Answer: " + Review.songList.get(songNum).artist,
            20, FontStyle.BOLD, Color.RED);
    WorldImage correctDec = 
        new TextImage("Correct! Right Answer: " + Review.songList.get(songNum).decade + "s",
            20, FontStyle.BOLD, Color.GREEN);
    WorldImage wrongDec = 
        new TextImage("Wrong! Right Answer: " + Review.songList.get(songNum).decade + "s",
            20, FontStyle.BOLD, Color.RED);
    if (this.first) {
      output.placeImageXY(start, 300, 200);
      output.placeImageXY(instruction1, 300, 300);
      output.placeImageXY(instruction2, 300, 350);
      return output;
    }
    if (this.question == 1) {
      output = new WorldScene(600, 600);
      WorldImage questitle = new TextImage("What is the name of this song?",
          28, FontStyle.BOLD, Color.BLACK);
      WorldImage title1 = new TextImage("1. " + this.choices.get(0).title,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage title2 = new TextImage("2. " + this.choices.get(1).title,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage title3 = new TextImage("3. " + this.choices.get(2).title,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage title4 = new TextImage("4. " + this.choices.get(3).title,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage titles = new AboveAlignImage(AlignModeX.LEFT, questitle, space, space,
          title1, space, title2, space, title3, space, title4);
      if (correct.equals("yes")) {
        output.placeImageXY(correctTitle, 300, 500);
      }
      if (correct.equals("no")) {
        output.placeImageXY(wrongTitle, 300, 500);
      }
      output.placeImageXY(titles, 300, 300);
      return output;
    }
    if (this.question == 2) {
      output = new WorldScene(600, 600);
      WorldImage quesart = new TextImage("Who is the artist of this song?",
          28, FontStyle.BOLD, Color.BLACK);
      WorldImage art1 = new TextImage("1. " + this.choices.get(0).artist,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage art2 = new TextImage("2. " + this.choices.get(1).artist,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage art3 = new TextImage("3. " + this.choices.get(2).artist,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage art4 = new TextImage("4. " + this.choices.get(3).artist,
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage artists = new AboveAlignImage(AlignModeX.LEFT, quesart, space, space,
          art1, space, art2, space, art3, space, art4);
      output.placeImageXY(artists, 300, 300);
      if (correct.equals("yes")) {
        output.placeImageXY(correctArtist, 300, 500);
      }
      if (correct.equals("no")) {
        output.placeImageXY(wrongArtist, 300, 500);
      }
      return output;
    }
    if (this.question == 3) {
      output = new WorldScene(600, 600);
      WorldImage quesdec = new TextImage("Which decade is this song from?",
          28, FontStyle.BOLD, Color.BLACK);
      WorldImage dec23 = new TextImage("0. 1920s                1. 1930s",
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage dec45 = new TextImage("2. 1940s                3. 1950s",
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage dec67 = new TextImage("4. 1960s                5. 1970s",
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage dec89 = new TextImage("6. 1980s                7. 1990s",
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage dec01 = new TextImage("8. 2000s                9. 2010s",
          20, FontStyle.REGULAR, Color.BLACK);
      WorldImage decades = new AboveImage(quesdec, space, space, dec23, space,
          dec45, space, dec67, space, dec89, space, dec01);
      output.placeImageXY(decades, 300, 300);
      if (correct.equals("yes")) {
        output.placeImageXY(correctDec, 300, 500);
      }
      if (correct.equals("no")) {
        output.placeImageXY(wrongDec, 300, 500);
      }
      return output;
    }
    return null;
  }
  
//end the game
 public WorldEnd worldEnds() {
   if (this.last) {
     return new WorldEnd(true, this.lastScene());
   }
   else {
     return new WorldEnd(false, this.makeScene());
   }
 }

 // end of game screen
 public WorldScene lastScene() {
   WorldScene output = new WorldScene(600, 600);
   WorldImage finalScene = new TextImage("Your score is a " + 
       Integer.toString((int)Math.round(this.score / 261) * 100),
       20, FontStyle.REGULAR, Color.BLACK);
   if (this.songNum >= Review.songList.size() && this.last == true) {
     output.placeImageXY(finalScene, 300, 300);
     return output;
   }
   return output;
 }
}

// represents a song title, artist, decade, and style
class Song {
  int id;
  String title;
  String artist;
  int decade;
  String key;
  Sound music;
  
  Song(String title, String artist, int decade, String key) {
    this.id = new Random().nextInt(100);
    this.title = title;
    this.artist = artist;
    this.decade = decade;
    this.key = key;
    this.music = null;
  }
  
  public static final Song Youve_Lost_That_Lovin_Feel = 
      new Song("You've Lost That Lovin' Feel", "The Righteous Brothers", 1980, "Youve_Lost_That_Lovin_Feel");
  public static final Song Goodnight_Irene = 
      new Song("Goodnight Irene", "The Weavers", 1940, "Goodnight_Irene");
  public static final Song Downhearted_Blues = 
      new Song("Downhearted Blues", "Bessie Smith", 1920, "Downhearted_Blues");
  public static final Song Crazy_Man_Crazy = 
      new Song("Crazy, Man, Crazy", "Bill Haley and His Comets", 1950, "Crazy_Man_Crazy");
  public static final Song Iron_Man = 
      new Song("Iron Man", "Black Sabbath", 1970, "Iron_Man");
  public static final Song Like_A_Rolling_Stone = 
      new Song("Like A Rolling Stone", "Bob Dylan", 1960, "Like_A_Rolling_Stone");
  public static final Song Masters_Of_War = 
      new Song("Masters of War", "Bob Dylan", 1960, "Masters_Of_War");
  public static final Song More_Than_A_Feeling = 
      new Song("More Than a Feeling", "Boston", 1970, "More_Than_A_Feeling");
  public static final Song Born_In_The_USA = 
      new Song("Born in the USA", "Bruce Springsteen", 1980, "Born_In_The_USA");
  public static final Song Thatll_Be_The_Day = 
      new Song("That'll Be the Day", "Buddy Holly", 1950, "Thatll_Be_The_Day");
  public static final Song Can_The_Circle_Be_Unbroken = 
      new Song("Can the Circle Be Unbroken", "Carter Family", 1930, "Can_The_Circle_Be_Unbroken");
  public static final Song Blue_Suede_Shoes = 
      new Song("Blue Suede Shoes", "Carl Perkins", 1950, "Blue_Suede_Shoes");
  public static final Song Rock_And_Roll_Music = 
      new Song("Rock and Roll Music", "Chuck Berry", 1950, "Rock_And_Roll_Music");
  public static final Song Sunshine_Of_Your_Love = 
      new Song("Sunshine of Your Love", "Cream", 1960, "Sunshine_Of_Your_Love");
  public static final Song Take_It_Easy = 
      new Song("Take It Easy", "The Eagles", 1970, "Take_It_Easy");
  public static final Song Jailhouse_Rock = 
      new Song("Jailhouse Rock", "Elvis", 1950, "Jailhouse_Rock");
  public static final Song Evil_Ways = 
      new Song("Evil Ways", "Santana", 1960, "Evil_Ways");
  public static final Song Aint_That_A_Shame = 
      new Song("Ain't That a Shame", "Fats Domino", 1950, "Aint_That_A_Shame");
  public static final Song Ive_Got_A_Crush_On_You = 
      new Song("I've Got a Crush on You", "Frank Sinatra", 1940, "Ive_Got_A_Crush_On_You");
  public static final Song How_Do_You_Do_It = 
      new Song("How Do You Do It", "Gerry & the Pacemakers", 1960, "How_Do_You_Do_It");
  public static final Song Go_Your_Own_Way = 
      new Song("Go Your Own Way", "Fleetwood Mac", 1970, "Go_Your_Own_Way");
  public static final Song The_Message = 
      new Song("The Message", "Grandmaster Flash", 1980, "The_Message");
  public static final Song Great_Balls_Of_Fire = 
      new Song("Great Balls of Fire", "Jerry Lee Lewis", 1960, "Great_Balls_Of_Fire");
  public static final Song Evil_Is_Going_On = 
      new Song("Evil Is Going On", "Howlin' Wolf", 1950, "Evil_Is_Going_On");
  public static final Song I_Cant_Explain = 
      new Song("I Can't Explain", "The Who", 1970, "I_Cant_Explain");
  public static final Song Im_Dreaming_Of_A_White_Christmas = 
      new Song("I'm Dreaming of a White Christmas", "Bing Crosby", 1940, "Im_Dreaming_Of_A_White_Christmas");
  public static final Song The_Number_Of_The_Beast = 
      new Song("The Number of the Beast", "Iron Maiden", 1980, "The_Number_Of_The_Beast");
  public static final Song White_Rabbit = 
      new Song("White Rabbit", "Jefferson Airplane", 1960, "White_Rabbit");
  public static final Song SHake_Rattle_And_Roll = 
      new Song("Shake, Rattle and Roll", "Big Joe Turner", 1950, "SHake_Rattle_And_Roll");
  public static final Song Breaking_The_Law = 
      new Song("Breaking the Law", "Judas Priest", 1980, "Breaking_The_Law");
  public static final Song Whole_Lotta_Love = 
      new Song("Whole Lotta Love", "Led Zeppelin", 1960, "Whole_Lotta_Love");
  public static final Song Im_Sitting_On_Top_of_The_World = 
      new Song("I'm Sitting on Top of the World", "Les Paul & Mary Ford", 1950, "Im_Sitting_On_Top_of_The_World");
  public static final Song Lets_Go_Crazy = 
      new Song("Let's Go Crazy", "Prince", 1980, "Lets_Go_Crazy");
  public static final Song Tutti_Frutti_LR = 
      new Song("Tutti Frutti", "Little Richard", 1950, "Tutti_Frutti_LR");
  public static final Song Rock_Island_Line = 
      new Song("Rock Island Line", "Lonnie Donegan", 1950, "Rock_Island_Line");
  public static final Song Caldonia = 
      new Song("Caldonia", "Louis Jordan", 1940, "Caldonia");
  public static final Song Like_A_Virgin = 
      new Song("Like A Virgin", "Madonna", 1980, "Like_A_Virgin");
  public static final Song Master_Of_Puppets = 
      new Song("Master of Puppets", "Metallica", 1980, "Master_Of_Puppets");
  public static final Song Billie_Jean = 
      new Song("Billie Jean", "Michael Jackson", 1980, "Billie_Jean");
  public static final Song Straight_Edge = 
      new Song("Straight Edge", "Minor Threat", 1980, "Straight_Edge");
  public static final Song Money = 
      new Song("Money", "Pink Floyd", 1970, "Money");
  public static final Song Nights_In_White_Satin = 
      new Song("Nights In White Satin", "Moody Blues", 1960, "Nights_In_White_Satin");
  public static final Song Shout_At_The_Devil = 
      new Song("Shout at the Devil", "Motley Crue", 1980, "Shout_At_The_Devil");
  public static final Song Supremacy = 
      new Song("Supremacy", "Muse", 2010, "Supremacy");
  public static final Song My_Best_Friends_Girl = 
      new Song("My Best Friend's Girl", "The Cars", 1970, "My_Best_Friends_Girl");
  public static final Song Smells_Like_Teen_Spirit = 
      new Song("Smells Like Teen Spirit", "Nirvana", 1990, "Smells_Like_Teen_Spirit");
  public static final Song Tutti_Frutti_PB = 
      new Song("Tutti Frutti", "Pat Boone", 1950, "Tutti_Frutti_PB");
  public static final Song Show_Me_The_Way = 
      new Song("Show Me The Way", "Peter Frampton", 1970, "Show_Me_The_Way");
  public static final Song Please_Dont_Talk_About_Me_When_Im_Gone = 
      new Song("Please Don't Talk About Me When I'm Gone", "Bob Wills", 1930, "Please_Dont_Talk_About_Me_When_Im_Gone");
  public static final Song Please_Please_Me = 
      new Song("Please, Please Me", "The Beatles", 1960, "Please_Please_Me");
  public static final Song Dont_Believe_The_Hype = 
      new Song("Don't Believe the Hype", "Public Enemy", 1980, "Dont_Believe_The_Hype");
  public static final Song Bohemian_Rhapsody = 
      new Song("Bohemian Rhapsody", "Queen", 1970, "Bohemian_Rhapsody");
  public static final Song Airbag = 
      new Song("Airbag", "Radiohead", 1990, "Airbag");
  public static final Song Bulls_On_Parade = 
      new Song("Bulls on Parade", "Rage Against the Machine", 1990, "Bulls_On_Parade");
  public static final Song Blitzkrieg_Bop = 
      new Song("Blitzkrieg Bop", "The Ramones", 1970, "Blitzkrieg_Bop");
  public static final Song Crossroad_Blues = 
      new Song("Crossroad Blues", "Robert Johnson", 1930, "Crossroad_Blues");
  public static final Song Rocket_88 = 
      new Song("Rocket 88", "Jackie Brenston", 1950, "Rocket_88");
  public static final Song Rock_Box = 
      new Song("Rock Box", "Run DMC", 1980, "Rock_Box");
  public static final Song Schools_Out = 
      new Song("Schools Out", "Alice Cooper", 1970, "Schools_Out");
  public static final Song Anarchy_In_The_UK = 
      new Song("Anarchy in the UK", "Sex Pistols", 1970, "Anarchy_In_The_UK");
  public static final Song Smoke_On_The_Water = 
      new Song("Smoke on the Water", "Deep Purple", 1970, "Smoke_On_The_Water");
  public static final Song Black_Hole_Sun = 
      new Song("Black Hole Sun", "Soundgarden", 1990, "Black_Hole_Sun");
  public static final Song Space_Oddity = 
      new Song("Space Oddity", "David Bowie", 1960, "Space_Oddity");
  public static final Song Psycho_Killer = 
      new Song("Psycho Killer", "The Talking Heads", 1970, "Psycho_Killer");
  public static final Song Good_Vibrations = 
      new Song("Good Vibrations", "The Beach Boys", 1960, "Good_Vibrations");
  public static final Song Surfin_USA = 
      new Song("Surfin' USA", "The Beach Boys", 1960, "Surfin_USA");
  public static final Song A_Day_In_The_Life = 
      new Song("A Day in the Life", "The Beatles", 1960, "A_Day_In_The_Life");
  public static final Song Mr_Tambourine_Man = 
      new Song("Mr. Tambourine Man", "The Byrds", 1960, "Mr_Tambourine_Man");
  public static final Song Shboom = 
      new Song("Sh-Boom", "The Chords", 1950, "Shboom");
  public static final Song Purple_Haze = 
      new Song("Purple Haze", "The Jimi Hendrix Experience", 1970, "Purple_Haze");
  public static final Song Tom_Dooley = 
      new Song("Tom Dooley", "The Kingston Trio", 1950, "Tom_Dooley");
  public static final Song You_Really_Got_Me = 
      new Song("You Really Got Me", "The Kinks", 1960, "You_Really_Got_Me");
  public static final Song The_One_I_Love = 
      new Song("The One I Love", "REM", 1980, "The_One_I_Love");
  public static final Song Monkey_Gone_To_Heaven = 
      new Song("Monkey Gone To Heaven", "The Pixies", 1980, "Monkey_Gone_To_Heaven");
  public static final Song Dont_Stand_So_Close_To_Me = 
      new Song("Don't Stand So Close to Me", "The Police", 1980, "Dont_Stand_So_Close_To_Me");
  public static final Song Color_Me_Impressed = 
      new Song("Color Me Impressed", "The Replacements", 1980, "Color_Me_Impressed");
  public static final Song I_Cant_Get_No_Satisfaction = 
      new Song("I Can't Get No Satisfaction", "The Rolling Stones", 1960, "I_Cant_Get_No_Satisfaction");
  public static final Song Paint_It_Black = 
      new Song("Paint It Black", "The Rolling Stones", 1970, "Paint_It_Black");
  public static final Song Will_You_Still_Love_Me_Tomorrow = 
      new Song("Will You Still Love Me Tomorrow?", "The Shirelles", 1960, "Will_You_Still_Love_Me_Tomorrow");
  public static final Song Search_And_Destroy = 
      new Song("Search and Destroy", "Iggy Pop and the Stooges", 1980, "Search_And_Destroy");
  public static final Song Wont_Get_Fooled_Again = 
      new Song("Won't Get Fooled Again", "The Who", 1970, "Wont_Get_Fooled_Again");
  public static final Song Heart_Full_of_Soul = 
      new Song("Heart Full of Soul", "The Yardbirds", 1960, "Heart_Full_of_Soul");
  public static final Song Tomorrow_Never_Knows = 
      new Song("Tomorrow Never Knows", "The Beatles", 1960, "Tomorrow_Never_Knows");
  public static final Song Pride = 
      new Song("Pride (In the Name of Love)", "U2", 1980, "Pride");
  public static final Song Panama = 
      new Song("Panama", "Van Halen", 1980, "Panama");
  public static final Song Ashes_Of_American_Flags = 
      new Song("Ashes of American Flags", "Wilco", 2000, "Ashes_Of_American_Flags");
  public static final Song Roundabout = 
      new Song("Roundabout", "Yes", 1970, "Roundabout");
}