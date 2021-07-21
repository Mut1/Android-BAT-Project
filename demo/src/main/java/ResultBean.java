import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Arrays;

public class ResultBean {
    private int code;
    private String message;
    private boolean isOK;

    public ResultBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }
}

class Test {
    public static void main(String[] args) {

        byte[] bytes={-25, -108, -75, -23, -104, -69, -26, -93, -128, -26, -75, -117, -28, -69, -86, -26, -100, -86, -24, -65, -98, -26, -114, -91, 13, 10, 59, 13, 10, 69, 78, 68, 13, 10, 59};
        String s=" ";
        byte[] bytes1 = s.getBytes();


        System.out.printf(Arrays.toString(bytes1));
        byte[] enen = Arrays.copyOfRange(bytes1,0,bytes1.length-1);
        System.out.printf(Arrays.toString(bytes));
        System.out.printf( new String(bytes));

//        Gson gson = new Gson();
//        ResultBean result1 = new ResultBean();
//        result1.setCode(007);
//        result1.setMessage("詹姆斯邦德");
//        result1.setOK(true);
//        String string1 = gson.toJson(result1);
//        System.out.println(string1);
//
//        ResultBean result2 = new ResultBean();
//        result2.setCode(007);
//        result2.setMessage(null);
//        result2.setOK(true);
//        String string2 = gson.toJson(result2);
//        System.out.println(string2);
//
//        GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls();
//        ResultBean result3 = new ResultBean();
//        result3.setCode(007);
//        result3.setMessage(null);
//        result3.setOK(true);
//        String string3 = gsonBuilder.create().toJson(result2);
//        System.out.println(string3);
    }
}