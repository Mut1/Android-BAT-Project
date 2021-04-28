
public class LinearSearch {

    private LinearSearch() {
    }

    private static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++)
            if (data[i].equals(target))
                return i;
        return -1;
    }

    public static void main(String[] args) {
      //  int []dataSize={1000000,10000000};
        int []dataSize={10,10};
        for (int n : dataSize) {
            Integer[] data = ArrayGenerator.generateOrderArray(n);
            long start = System.nanoTime();
            for (int k = 0; k < 100; k++)
                LinearSearch.search(data, n);
            long endTime = System.nanoTime();
            double time = (endTime - start) / 1000000000.0;
            System.out.println("n="+n+",100 runs:"+time + "s");
        }



        Student[] students = {new Student("Alice"),
                new Student("BoBo"),
                new Student("Charles")};
        Student bobo = new Student("BoBO");
        int res3 = LinearSearch.search(students, bobo);
      //  System.out.println(res3);

    }

}
