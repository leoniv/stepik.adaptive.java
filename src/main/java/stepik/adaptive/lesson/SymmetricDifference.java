package stepik.adaptive.lesson;

import java.util.HashSet;
import java.util.Set;

class SymmtricDifference {
  public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
    Set<Integer> set3 = new HashSet<>(set1);
    set3.addAll(set2);
    set1.retainAll(set2);
    set3.removeAll(set1);
    return set3;
  }
}
