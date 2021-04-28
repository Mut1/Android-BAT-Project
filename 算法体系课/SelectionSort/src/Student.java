public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object student) {

        if (this == student)
            return true;
        if (student == null)
            return false;
        if (this.getClass() != student.getClass())
            return false;
        Student another = (Student) student;
        return this.name.toLowerCase().equals(another.name.toLowerCase());
    }

    @Override
    public int compareTo(Student another) {
//        if (this.score < another.score)
//            return -1;
//        else if (this.score > another.score)
//            return 1;
//        else
//            return 0;
        return this.score-another.score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {

        Array<Student> arr=new Array<>();
        arr.addLast(new Student("alice",100));
        arr.addLast(new Student("bob",66));
        arr.addLast(new Student("charlie",88));
        System.out.println(arr);
    }
}
