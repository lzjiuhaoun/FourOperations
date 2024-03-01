package cn.lzj66.util;

import java.io.IOException;

public class Test {
    private String isInteger(String str){
        String param = "^[0-9]+([.][0]{2}$)";
        if(str.matches(param)){
            return Integer.toString((int)Double.parseDouble(param));
        }else{
            return str;
        }
    }
    public static void main(String[] args) throws IOException{
//        System.out.println("input：");
//        Scanner sc = new Scanner(System.in);
//        String input = "1.05";
//        String param = "^[0-9]+[.]+[0]{2}$";
//        if(input.matches(param)){
//            System.out.println(Integer.toString((int)Double.parseDouble(input)));
//        }else{
//            System.out.println(input);
//        }
//        if (!input.matches("^[0-9]+([.]{0,1}[0-9]+){0,1}$")){
//            System.out.println("this is not a number");
//        }else if (input.matches("^[0-9]+$")){
//            System.out.print("this is a integer");
//        }else {
//            System.out.print("this is a double");
//        }
//        Create create = new Create(10,10,3);
//        List<String> list = create.createList();
//        for (String item :list){
//            System.out.println("题目是："+item);
//        }
//        String str = "-39";
//        String param = "^(\\-\\d+)|(\\-\\d+\\/\\d+)|(\\d+\\/\\-\\d+)$";//考虑三种情况 -6,-5/6,11/-2
//        if(str.matches(param)) {
//            System.out.println(str);
//        }else {
//            System.out.println("no match");
//        }
//        String str = "*";
//        String param = "^\\d{1,}$";//考虑三种情况 -6,-5/6,11/-2
//        if(str.matches(param)) {
//            System.out.println(str);
//        }else {
//            System.out.println("no match");
//        }
        CalculateExp ce = new CalculateExp();
//        Create create = new Create(100,10,4);
//        List<String> list = create.createList();
//        for (String str : list){
//            System.out.print(str);
//            String [] endStr = ce.midToEnd(ce.transStr(str));//后缀表达式
//            String temp = (String)ce.calculate(endStr);//得到答案
//            System.out.println("=" + temp);
//            for (String tmp : endStr){
//                System.out.print(tmp);
//            }
//            System.out.println();
//        }
        String test = "6-8÷(2/6-3/7)";
        String [] endStr = ce.midToEnd(ce.transStr(test));//后缀表达式
        for (String tmp : endStr){
                System.out.print(tmp);
        }
        System.out.println();
        String temp = (String)ce.fractionCalculate(endStr);//得到答案
        System.out.println(test + "=" + temp);
        //1+2÷(2-2)
        //6-8÷(2/6-3/7)=90
    }
}
