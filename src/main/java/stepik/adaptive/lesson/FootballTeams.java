package stepik.adaptive.lesson;

import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class FootballTeams {

  public static void main(String[] argv) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();

    Stream.generate(scanner::nextLine).limit(n + 1).forEach(Team::parseMatchResult);

    Team.getTeams().stream().map(Team::stat).forEach(System.out::println);

    scanner.close();
  }

  static class Team {
    public final String name;
    private List<MatchResult> matchResults = new ArrayList<>();
    protected static Map<String, Team> teams = new HashMap<>();

    public static Team get(String name) {
      if (teams.get(name) == null) {
        teams.put(name, new Team(name));
      }
      return teams.get(name);
    }

    public static void parseMatchResult(String reslt) {
      if (reslt.length() == 0) return;
      MatchResult.parse(reslt);
    }

    public static Collection<Team> getTeams() {
      return teams.values();
    }

    private Team(String name) { this.name = name; }

    public void addMatchResult(MatchResult result) {
      matchResults.add(result);
    }

    public long games() {
      return matchResults.size();
    }

    public long wins() {
      return matchResults
              .stream()
              .filter(m -> !m.isDraw() && m.winner() == this)
              .count();
    }

    public long draws() {
      return matchResults
              .stream()
              .filter(m -> m.isDraw())
              .count();
    }

    public long defeats() {
      return games() - wins() - draws();
    }

    public long points() {
      return wins() * 3 + draws();
    }

    public String stat() {
      return name
             + ":" + games()
             + " " + wins()
             + " " + draws()
             + " " + defeats()
             + " " + points();
    }

    @Override
    public String toString() { return name; }

    static class MatchResult {
      public final Team team1, team2;
      public final Integer goalsOfTeam1, goalsOfTeam2;
      private static List<MatchResult> allResults = new ArrayList<>();

      public static List<MatchResult> getAllResults() { return allResults; }

      public static MatchResult parse(String input) {
        String[] arr = input.split(";");
        return new MatchResult(
            Team.get(arr[0])
            ,Integer.valueOf(arr[1])
            ,Team.get(arr[2])
            ,Integer.valueOf(arr[3]));
      }

      public MatchResult(Team t1, Integer g1, Team t2, Integer g2) {
        team1 = t1;
        goalsOfTeam1 = g1;
        team2 = t2;
        goalsOfTeam2 = g2;
        team1.addMatchResult(this);
        team2.addMatchResult(this);
        allResults.add(this);
      }

      public boolean isDraw() { return winner() == null; }

      public Team winner() {
        if (goalsOfTeam1 > goalsOfTeam2) return team1;
        if (goalsOfTeam2 > goalsOfTeam1) return team2;
        return null;
      }

      @Override
      public String toString() {
        return team1 + ";" + goalsOfTeam1 + ";" + team2 + ";" + goalsOfTeam2;
      }
    }
  }
}
