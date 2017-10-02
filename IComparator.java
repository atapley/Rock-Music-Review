interface IComparator<T> {
  
  // compare two T's
  int compare(T t1, T t2);
}

class CompareSong implements IComparator<Song> {

  public int compare(Song s1, Song s2) {
    if (s1.id <= s2.id) {
      return -1;
    }
    else {
      return 1;
    }
  }
}