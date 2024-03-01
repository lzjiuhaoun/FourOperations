package cn.lzj66.util;

import java.util.ArrayList;
import java.util.List;

public class Arithmatic {

    /**
     * 生成的题目总数
     */
    private int total;
    /**
     * 取的最大数字
     */
    private int maxNumber;
    /**
     * 定义操作数
     */
    private final int OPERAT = 4;
    public Arithmatic(){
        this.total = 10;
        this.maxNumber = 100;
    }
    public Arithmatic(int total,int maxNumber){
        this.total = total;
        this.maxNumber = maxNumber;
    }
    /**
     * 创建一个四则运算表达式
     * @return String
     */
    public List<String> create(){
        return make();
    }
    private List<String> make(){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <getTotal();i++){
            String str = makeProcess();
            while ("".equals(str)){
                str = makeProcess();
                break;
            }
            list.add(str);
        }
        return list;
    }
    private String makeProcess(){
        String properFraction1 = getProperFraction();
        String properFraction2 = getProperFraction();
        int param1 = (int)(Math.random() * getMaxNumber() +1);
        int param2 = (int)(Math.random() * getMaxNumber() +1);
        int operate = (int)(Math.random() * OPERAT);
        int temp;
        String str = "";
        switch (operate){
            case 0:
                temp = (int)(Math.random() * 3);
                switch (temp){
                    case 0:
                        str= param1+"+"+param2;
                        break;
                    case 1:
                        str = properFraction1+"+"+param2;
                        break;
                    case 2:
                        str = properFraction2 + "+" + properFraction1;
                        break;
                }
                break;
            case 1:
                temp = (int)(Math.random() * 3);
                if(param1 < param2){
                    int k = param1;
                    param1 = param2;
                    param2 = k;
                }
                switch (temp){
                    case 0:
                        str = param1 + "-" + param2;
                        break;
                    case 1:
                        str = param2 + "-" + properFraction1;
                        break;
                    case 2:
                        str = properFraction2 + "-" + properFraction1;
                        break;
                }
                break;
            case 2:
                temp = (int)(Math.random() * 3);
                switch (temp){
                    case 0:
                        str = param1 + "*" + param2;
                        break;
                    case 1:
                        str = param2 + "*" + properFraction1;
                        break;
                    case 2:
                        str = properFraction2 + "*" + properFraction1;
                        break;
                }
                break;
            case 3:
                str = param1 + "/" + param2;
                break;
        }
        return str;

    }
    /**
     * 获取一个真分数
     * @return String
     */
    private String getProperFraction(){
        int param1 = (int)(Math.random() * getMaxNumber() +1);
        int param2 = (int)(Math.random() * getMaxNumber() +1);
        if(param1 > param2){
            int temp = param1;
            param1 = param2;
            param2 = temp;
        }
        String ret = "("+param1+"/"+param2+")";
        return ret;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMaxNumber() {
        return maxNumber -1;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }
}
