public abstract class Person {
    private String name;
    private int age;

    // конструкторы, геттеры и сеттеры
}

public class Student extends Person {
    private String course;

    // конструкторы, геттеры и сеттеры
}

public class Worker extends Person {
    private String department;

    // конструкторы, геттеры и сеттеры
}

public class Teacher extends Worker {
    private String subject;

    // конструкторы, геттеры и сеттеры
}

public class Employee extends Worker {
    private String position;

    // конструкторы, геттеры и сеттеры
}

public interface IPersonService<T extends Person> {
    void add(T person);

    void remove(T person);

    List<T> getAll();
}

public class TeacherService implements IPersonService<Teacher> {
    private List<Teacher> teachers = new ArrayList<>();

    @Override
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void remove(Teacher teacher) {
        teachers.remove(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return teachers;
    }
}

public class PersonComparator<T extends Person> implements Comparator<T> {
    @Override
    public int compare(T p1, T p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}

public class AccountController {
    public static <T extends Person> double averageAge(List<T> people) {
        if (people.isEmpty()) {
            return 0.0;
        }
        int totalAge = 0;
        for (T person : people) {
            totalAge += person.getAge();
        }
        return (double) totalAge / people.size();
    }
}

public class Main {
    public static void main(String[] args) {
        TeacherService teacherService = new TeacherService();

        // Добавляем учителей
        teacherService.add(new Teacher(/* инициализация */));
        teacherService.add(new Teacher(/* инициализация */));

        // Сортируем и выводим учителей
        List<Teacher> sortedTeachers = teacherService.getAll();
        Collections.sort(sortedTeachers, new PersonComparator<>());
        System.out.println("Отсортированные учителя: " + sortedTeachers);

        // Вычисляем средний возраст
        double averageAge = AccountController.averageAge(sortedTeachers);
        System.out.println("Средний возраст учителей: " + averageAge);
    }
}
