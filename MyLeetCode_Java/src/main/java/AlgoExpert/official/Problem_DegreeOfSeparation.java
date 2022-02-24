package AlgoExpert.official;

import java.util.*;

public class Problem_DegreeOfSeparation {
    // O(v + e) time | O(v) space - where v is the number of vertices (people) in the
    // friends graph and e is the number of edges (total friends) in the friends graph
    public static String degreesOfSeparation(
            Map<String, String[]> friendsLists, String personOne, String personTwo) {
        var degreesOne = getDegreesOfSeparation(friendsLists, personOne);
        var degreesTwo = getDegreesOfSeparation(friendsLists, personTwo);
        var numDegreesOverSixOne = getNumDegreesOverSix(friendsLists, degreesOne);
        var numDegreesOverSixTwo = getNumDegreesOverSix(friendsLists, degreesTwo);
        if (numDegreesOverSixOne == numDegreesOverSixTwo) return "";
        return numDegreesOverSixOne < numDegreesOverSixTwo ? personOne : personTwo;
    }

    public static Map<String, Integer> getDegreesOfSeparation(
            Map<String, String[]> friendsLists, String origin) {
        var degrees = new HashMap<String, Integer>();
        var visited = new HashSet<String>();
        var queue = new ArrayDeque<PersonInfo>();
        queue.add(new PersonInfo(origin, 0));
        while (queue.size() > 0) {
            var info = queue.pollFirst();
            degrees.put(info.person, info.degree);
            for (var friend : friendsLists.get(info.person)) {
                if (visited.contains(friend)) continue;
                visited.add(friend);
                queue.add(new PersonInfo(friend, info.degree + 1));
            }
        }
        for (var person : friendsLists.keySet()) {
            if (!visited.contains(person)) degrees.put(person, Integer.MAX_VALUE);
        }
        return degrees;
    }

    public static int getNumDegreesOverSix(
            Map<String, String[]> friendsLists, Map<String, Integer> degrees) {
        var numDegreesOverSix = 0;
        for (var person : friendsLists.keySet()) {
            var distance = degrees.get(person);
            if (distance > 6) {
                numDegreesOverSix++;
            }
        }
        return numDegreesOverSix;
    }

    static class PersonInfo {
        public String person;
        public int degree;

        public PersonInfo(String person, int degree) {
            this.person = person;
            this.degree = degree;
        }
    }
}
