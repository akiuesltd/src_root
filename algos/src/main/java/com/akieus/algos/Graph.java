package com.akieus.algos;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aks on 07/08/15.
 */
public class Graph {

    public static void main(String[] args) {
//        printGraphDF(buildPersonWithFriends(), 3);
        printGraphBF(buildPersonWithFriends(), 3);
    }

    private static void print(Collection<Person> collection) {
        for (Person t : collection) {
            System.out.println(t.id + ", ");
        }
    }

    private static void printGraphDF(Person root, int depth) {
        List<Person> visited = new LinkedList<>();
        traverseDF(root, depth, visited);
        print(visited);
    }

    private static void printGraphBF(Person person, int depth) {
        print(traverseBF(person, depth));
    }

    private static void traverseDF(Person person, int depth, List<Person> visited) {
        if (depth == 0) {
            return;
        }

        if (!visited.contains(person)) {
            visited.add(person);
        }

        for (Person friend : person.getFriends()) {
            traverseDF(friend, depth - 1, visited);
        }
    }

    private static List<Person> traverseBF(Person person, int depth) {
        List<Person> visited = new LinkedList<>();

        LinkedList<Person> queue = new LinkedList<>();
        queue.add(person);

        while(true) {
            if (queue.isEmpty()) {
                break;
            }

            Person next = queue.removeFirst();
            if (!visited.contains(next)) {
                visited.add(next);
            }
            for (Person friend : next.getFriends()) {
                if (!visited.contains(friend)) {
                    queue.add(friend);
                }
            }
        }


        return visited;
    }

    public static Person buildPersonWithFriends() {
        Person one = new Person(1);
        Person two = new Person(2);
        Person three = new Person(3);

        one.addFriend(two).addFriend(three);
        two.addFriend(4).addFriend(5).addFriend(one).addFriend(three);
        three.addFriend(6).addFriend(one).addFriend(two);

        return one;
    }


    public static class Person {
        private int id;
        private List<Person> friends;

        public Person(int id) {
            this(id, new LinkedList<>());
        }

        public Person(int id, List<Person> friends) {
            checkNotNull(friends);
            this.id = id;
            this.friends = friends;
        }

        public Person addFriend(int id) {
            return addFriend(new Person(id));
        }

        public Person addFriend(Person friend) {
            friends.add(friend);
            return this;
        }

        public List<Person> getFriends() {
            return friends;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder().append(id);
            if (!friends.isEmpty()) {
                sb.append(": ");
                for (Person friend : friends) {
                    sb.append(friend.id).append(", ");
                }
            }
            return sb.toString();
        }
    }
}
