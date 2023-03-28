import java.util.ArrayList;

public class SortObjects {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();

        people.add(new Person("Bob", 21));
        people.add(new Person("Alice", 20));
        people.add(new Person("Charlie", 19));

        // Person[] people = new Person[3];
        //
        // people[0] = new Person("Bob", 21);
        // people[1] = new Person("Alice", 20);
        // people[2] = new Person("Charlie", 19);

        // BubbleSort(people);

        ArrayList<Person> peopleSorted = new ArrayList<Person>(people);
        peopleSorted = BubbleSortArrayList(peopleSorted);

        for (Person person : people) {
            System.out.println(person.getName() + " " + person.getAge());
        }

        System.out.println();

        for (Person person : peopleSorted) {
            System.out.println(person.getName() + " " + person.getAge());
        }
    }

    private static ArrayList<Person> BubbleSortArrayList(ArrayList<Person> array) {
        int bottom = array.size() - 2;
        Person temp;
        boolean exchanged = true;
        while (exchanged) {
            exchanged = false;
            for (int i = 0; i <= bottom; i++) {
                if (array.get(i).getAge() > array.get(i + 1).getAge()) {
                    temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    exchanged = true;
                }
            }
            bottom--;
        }
        return array;
    }

    private static void BubbleSort(Person[] array) {
        int bottom = array.length - 2;
        Person temp;
        boolean exchanged = true;
        while (exchanged) {
            exchanged = false;
            for (int i = 0; i <= bottom; i++) {
                if (array[i].getAge() > array[i + 1].getAge()) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    exchanged = true;
                }
            }
            bottom--;
        }
    }
}
