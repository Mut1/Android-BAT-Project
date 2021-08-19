#### 输入二维数组

```java
import java.util.Scanner;
import java.util.Stack;
public class solution{
    public static void main(String[] args){
        System.out.println("二维数组的列数：");
        Scanner scan=new Scanner(System.in);
        int r=scan.nextInt();
        int c=scan.nextInt();
        int[][]matrix=new int[r][c];
        scan.nextLine();//用来跳过行列后的回车符
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                matrix[i][j]=scan.nextInt();
                System.out.print(matrix[i][j]+",");
            }
            System.out.println("");
        }
    }
}
```

```java
输入：
4 5
1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0  
输出：
1,0,1,0,0,
1,0,1,1,1,
1,1,1,1,1,
1,0,0,1,0,
```

