package cn.lzj66.util;

import java.util.ArrayList;
import java.util.List;

public class Create {
    /**
     * 生成总的题目数
     */
    private int total;
    /**
     * 每个数字取得的最大值
     */
    private int maxNumber;
    /**
     * 难度系数，也就是运算符的个数
     */
    private int difficult;

    public Create() {
        this(10,10,5);
    }
    public Create(int total) {
        this(total,10,5);
    }
    public Create(int total, int maxNumber) {
        this(total,maxNumber,5);
    }
    public Create(int total, int maxNumber, int difficult) {
        this.total = total;
        this.maxNumber = maxNumber;
        this.difficult = difficult;
    }

    /**
     * 生成一个数或者分数
     * @return String
     */

    public String createNumber(){
        int random = (int) (Math.random() * 10 +1);
        int num1 = 1,num2 = 1;
        String str = "";
        if(random*1.2 >= getDifficult()){//难度越大，真分数越多
            num1 = (int)(Math.random() * (getMaxNumber() -1)) + 1;//数字范围1-maxNumber
            str = String.valueOf(num1);
        }else{//生成分数
            num1 = (int)(Math.random() * (getMaxNumber() -1)) + 1;//分子
            num2 = (int)(Math.random() * (getMaxNumber() -1)) + 1;//分母
            if(num1 > num2){//分子分母交换，生成式子都是真分数
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
//            str = "(" + String.valueOf(num1) + "/" + String.valueOf(num2) + ")";
            str =  String.valueOf(num1) + "/" + String.valueOf(num2);
        }
        return str;
    }

    /**
     * 生成运算符
     * @return String
     */
    public String createOperate(){
        String str = "";
        int random = (int)(Math.random() * 4);
        switch (random){
            case 0:
                str = "+";
                break;
            case 1:
                str = "-";
                break;
            case 2:
                str = "*";
                break;
            case 3:
                str = "÷";
                break;
        }
        return str;
    }

    /**
     * 添加括号
     * @param str String
     * @return String
     */
    public String addBracket(String str){
        return "(" + str + ")";
    }

    /**
     * 生成表达式
     * @return String
     */
    public String createQuestion(){
        String str = createNumber();
        int coe = (int)(Math.random() * (getDifficult() - 1)) + 1;
        int r = (int)( Math.random() * coe);
        int count = 0;
        for(int i = 0 ;i<coe; i++){
            int prior = (int)(Math.random() * 10) + 1;
            if(prior < 5){
                str = str + createOperate() + createNumber();
            }else{
                str = createNumber() + createOperate() + str;
            }
            if(r < coe -1 && count != 1){
                str = addBracket(str);
                count++;
            }
//            if( (i-1) == r ){
//                str = addBracket(str);
//            }
//            str = addBracket(str);
//            if( r == coe/2){ //随机数等于最大难度一半的时候加括号
//                str = addBracket(str);
//            }
        }
        return str;
    }

    /**
     * 生成表达式集
     * @return
     */
    public List createList(){
        List<String> list = new ArrayList<String>();
        String str = "1+1";
        for (int i = 0 ;i<getTotal();i++){
            str = createQuestion();
            list.add(str);
        }
        return list;
    }
    public static int getMaxNumberByDifficult(int difficult){
        switch (difficult){
            case 1:return 10;
            case 2:return 30;
            case 3:return 50;
            case 4:return 70;
            case 5:return 100;
            default: return 10;
        }
    }
    public static int getDifficultBySelf(int difficult){
        switch (difficult){
            case 1:return 3;
            case 2:return 4;
            case 3:return 5;
            case 4:return 5;
            case 5:return 5;
            default:return 4;
        }
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }
}
