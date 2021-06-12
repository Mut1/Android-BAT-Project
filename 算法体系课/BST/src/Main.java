import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        BST<Integer> bst=new BST<>();
        Random random=new Random();
        int n=1000;
        int[] num={5,3,2,4,6,8};
        for (int i = 0; i < num.length; i++) {
            bst.add(num[i]);
        }
        bst.remove(3);


        System.out.println(bst);




    }
}
