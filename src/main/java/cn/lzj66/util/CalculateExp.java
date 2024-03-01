package cn.lzj66.util;

import java.util.*;

public class CalculateExp
{
    private HashMap sign = new HashMap();
    /* 将运算符的优先级放入到缓存处理 */
    public CalculateExp() {
        this.sign.put(")", "3");
        this.sign.put("/", "3");
        this.sign.put("*", "2");
        this.sign.put("÷", "2");
        this.sign.put("+", "1");
        this.sign.put("-", "1");
        this.sign.put("(", "0");
    }
    /**
     * @param str String 输入的表达式
     * @return List 解析后的字符串元素
     * 对输入的字符串进行解析
     * 转换为需要处理的数据
     * 例如:3+12+25*(20-20/4)+10
     * 转换后的结果为:
     * List 元素为 ret = {3,+,12,+,25,*,(,20,-,20,-,20,/,4,),+,10}
     */
    public List transStr(String str) {
        List strList = new ArrayList();
        /* 获取提出数据的符号串 */
        String tmp = str.replaceAll("\\d*", "");
        /* 记录当前的运算符 */
        String curLet = null;
        /* 记录tmp字符串中第一个运算符的位置 */
        int loc = 0;
        /* 符号串长度 */
        int len = tmp.length();
        for (int i = 0; i < len; i++) {
            curLet = tmp.substring(i, i + 1);
            loc = str.indexOf(curLet);
           //如果当前处理字符为( 或者 )
            if (!"".equals(str.substring(0, loc).trim())) {
                strList.add(str.substring(0, loc).trim());
            }
            strList.add(str.substring(loc, loc + 1));
            str = str.substring(loc + 1);
        }
        if (0 < str.length()) {
            strList.add(str.trim());
        }
        return strList;
    }

    /**
     * 将表达式从中缀表达式转换为后缀表达式(波兰式)
     * @param midList List 解析后的表达式的列表
     * @return String[] 转换后的表达式字符串数组
     */
    public String[] midToEnd(List midList) {
        Stack embl = new Stack();
        Stack result = new Stack();

        Iterator it = midList.iterator();
        String curStr = null;
        while (it.hasNext()) {
            curStr = (String) it.next();
            if(sign.containsKey(curStr)) {//确认是否式字符串
                if (0 == embl.size() || "(".equals(curStr)) {//如果符号栈为空 或者符号为(
                    embl.push(curStr);
                } else {
                    if(")".equals(curStr)) {//如果符号为) 符号栈需要出栈,直到匹配一个(为止
                        while(!"(".equals(embl.peek())) {
                            if(0 >= embl.size()) {
                                return null;
                            }
                            result.push(embl.pop());
                        }
                        embl.pop();
                    } else {
                        int p1 = Integer.parseInt((String) sign.get(curStr));
                        int p2 = Integer.parseInt((String) sign.get(embl.peek()));
                        /* 如果当前字符的优先级大于栈顶符号的优先级 */
                        if (p1 > p2){
                            embl.push(curStr);
                        } else {
                            while ( (p1 <= p2 || embl.size() > 0) && p2 !=0 ) {
                                result.push(embl.pop());
                                if(0 == embl.size()) {
                                    break;
                                }
                                p2 = Integer.parseInt((String) sign.get(embl.peek()));
                            }
                            embl.push(curStr);
                        }
                    }
                }
            } else {
                result.push(curStr);
            }
        }
        while (0 < embl.size())
        {
            result.push(embl.pop());
        }

        int len = result.size();
        String[] ret = new String[len];
        for (int i = 0; i < len; i++)
        {
            ret[len - i - 1] = (String) result.pop();
        }

        return ret;
    }

    /**
     * 解析后缀表达式,返回对应的运算结果
     * @param endStr String[] 转换后的后缀表达式
     * @return Object 返回运算结果 如果表达式有误直接打印"Input Error"
     */
    public Object calculate(String[] endStr)
    {
        int len = endStr.length;
        Stack calc = new Stack();
        int p1,p2;
        for (int i = 0; i < len; i++) {
            //运算符处理
            String key = endStr[i];
            if("/".equals(key) || "÷".equals(key)){//如果是出现分数符号，或者是除号就进行分数运算
                return fractionCalculate(endStr);
            }
            if (sign.containsKey(key)) {
                try {
                    p2 = Integer.parseInt((String) calc.pop());
                    p1 =Integer.parseInt((String) calc.pop());
                    calc.push(String.valueOf(simpleCalc(p1, p2,key)));
                } catch(NumberFormatException ex) {
                    ex.printStackTrace();
                    return "Input Error";
                } catch(Exception ex) {
                    ex.printStackTrace();
                    return "Input Error";
                }
            } else {//是数字的情况
                calc.push(key);
            }
        }
        if (1 == calc.size()) {//只剩下最后的运算结果了
            return calc.pop();
        } else {
            return "Input Error";
        }
    }

    /**
     * 实现底层的运算函数
     * @param p1 int 数字1
     * @param p2 int  数字2
     * @param operate String 运算符 +-/*
     * @return double
     */
    public int simpleCalc(int p1, int p2, String operate)
    {
        switch(operate.charAt(0)) {
            case '+':
                return p1 + p2;
            case '-':
                return p1 - p2;
            case '*':
                return p1 * p2;
            case '÷':
                return p1 / p2;
            default:
                return p1;
        }
    }

    /**
     * 实现分数计算
     * @param endStr
     * @return
     */
    public String fractionCalculate(String[] endStr){
        int length = endStr.length;
        Stack stack = new Stack();//栈是先进后出
        double p1,p2;
        int num1,num2;
        Object temp,temp2;
        Fraction fraction;
        for (int i = 0;i<length;i++){
            String key = endStr[i];
            if (sign.containsKey(key)) {
                try {
                    switch (key){
                        case "+":
                            temp = stack.pop();
                            temp2 = stack.pop();
                            //是temp2 + - * / temp1
                            if(temp instanceof Fraction){//如果是分数
                                if(temp2 instanceof Fraction){
                                    stack.push(((Fraction) temp2).add((Fraction)temp));
                                }else{
                                    num1 = Integer.parseInt((String)temp2);
                                    stack.push(new Fraction(num1).add((Fraction) temp));
                                }
                            } else{
                                if(temp2 instanceof Fraction){
                                    num1 = Integer.parseInt((String)temp);
                                    stack.push(((Fraction) temp2).add(new Fraction(num1)));
                                } else{
                                    num1 = Integer.parseInt((String)temp);
                                    num2 = Integer.parseInt((String)temp2);
                                    stack.push(new Fraction(num2).add(new Fraction(num1)));
                                }
                            }
                            break;
                        case "-":
                            temp = stack.pop();
                            temp2 = stack.pop();
                            //是temp2 + - * / temp1
                            if(temp instanceof Fraction){//如果是分数
                                if(temp2 instanceof Fraction){
                                    stack.push(((Fraction) temp2).minus((Fraction)temp));
                                }else{
                                    num1 = Integer.parseInt((String)temp2);
                                    fraction = new Fraction(num1);
                                    stack.push(fraction.minus((Fraction) temp));
                                }
                            } else{
                                if(temp2 instanceof Fraction){
                                    num1 = Integer.parseInt((String)temp);
                                    stack.push(((Fraction) temp2).minus(new Fraction(num1)));
                                } else{
                                    num1 = Integer.parseInt((String)temp);
                                    num2 = Integer.parseInt((String)temp2);
                                    stack.push(new Fraction(num2).minus(new Fraction(num1)));
                                }
                            }
                            break;
                        case "*":
                            temp = stack.pop();
                            temp2 = stack.pop();
                            //是temp2 + - * / temp1
                            if(temp instanceof Fraction){//如果是分数
                                if(temp2 instanceof Fraction){
                                    stack.push(((Fraction) temp2).multiply((Fraction)temp));
                                }else{
                                    num1 = Integer.parseInt((String)temp2);
                                    stack.push(new Fraction(num1).multiply((Fraction) temp));
                                }
                            } else{
                                if(temp2 instanceof Fraction){
                                    num1 = Integer.parseInt((String)temp);
                                    stack.push(((Fraction) temp2).multiply(new Fraction(num1)));
                                } else{
                                    num1 = Integer.parseInt((String)temp);
                                    num2 = Integer.parseInt((String)temp2);
                                    stack.push(new Fraction(num2).multiply(new Fraction(num1)));
                                }
                            }
                            break;
                        case "÷":
                            temp = stack.pop();
                            temp2 = stack.pop();
                            //是temp2 + - * / temp1
                            if(temp instanceof Fraction){//如果是分数
                                if(((Fraction) temp).getNumerator() == 0){//去除分母为0的情况
                                    return "-1";
                                }
                                if(temp2 instanceof Fraction){
                                    stack.push(((Fraction) temp2).devide((Fraction)temp));
                                }else{
                                    num1 = Integer.parseInt((String)temp2);
                                    stack.push(new Fraction(num1).devide((Fraction) temp));
                                }
                            } else{
                                if(temp2 instanceof Fraction){
                                    num1 = Integer.parseInt((String)temp);
                                    if(num1 == 0){//避免分母为0抛出的异常
                                        return "-1";
                                    }
                                    stack.push(((Fraction) temp2).devide(new Fraction(num1)));
                                } else{
                                    num1 = Integer.parseInt((String)temp);
                                    num2 = Integer.parseInt((String)temp2);
                                    if(num1 == 0){//避免分母为0抛出的异常
                                        return "-1";
                                    }
                                    stack.push(new Fraction(num2).devide(new Fraction(num1)));
                                }
                            }
                            break;
                        case "/":
                            num1 = Integer.parseInt((String) stack.pop());
                            num2 = Integer.parseInt((String) stack.pop());
                            if(num1 == 0){//去掉分母为0的情况
                                return "-1";
                            }
                            fraction = new Fraction(num2,num1);
                            stack.push(fraction);
                            break;
                        default:
                            throw new Exception("不存在的运算符");
                    }
                } catch(NumberFormatException ex) {
                    ex.printStackTrace();
                    return "Input Error";
                } catch(Exception ex) {
                    ex.printStackTrace();
                    return "Input Error";
                }
            } else {//是数字的情况
                stack.push(key);
            }
        }
        return stack.pop().toString();
    }
    /**
     * 格式转化，如1.00就是1
     * @param str String
     * @return String
     */
    private String doubleFormat(String str){
        String param = "^[0-9]+[.]+[0]{2}$";
        if(str.matches(param)){
           return Integer.toString((int)Double.parseDouble(str));
        }else{
            return str;
        }
    }
    public Map<String,List<String>> build(int total,int maxNumber,int difficult){
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        List<String> question = new ArrayList<String>();
        List<String> answer = new ArrayList<String>();
//        List<String> end = new ArrayList<String>();
        Create create = new Create(total, maxNumber, difficult);
        CalculateExp ce = new CalculateExp();
        while (question.size() != total) {
            String str = create.createQuestion();
            String [] endStr = ce.midToEnd(ce.transStr(str));//后缀表达式
            String temp = (String)ce.fractionCalculate(endStr);//得到答案
            String param = "^(\\-\\d+)|(\\-\\d+\\/\\d+)|(\\d+\\/\\-\\d+)$";//考虑三种情况 -6,-5/6,11/-2
            if(temp.matches(param)){
                continue;
            }else{
                question.add(str);
                answer.add(temp);
            }
        }
        map.put("question",question);
        map.put("answer",answer);
        return map;
    }
    public boolean create(int total,int maxNumber,int difficlut){
        List<String> question = new ArrayList<String>();
        List<String> answer = new ArrayList<String>();
//        List<String> end = new ArrayList<String>();
        Create create = new Create(total, maxNumber, difficlut);
        CalculateExp ce = new CalculateExp();
        while (question.size() != total) {
            String str = create.createQuestion();
            String [] endStr = ce.midToEnd(ce.transStr(str));//后缀表达式
            String temp = (String)ce.fractionCalculate(endStr);//得到答案
            String param = "^(\\-\\d+)|(\\-\\d+\\/\\d+)|(\\d+\\/\\-\\d+)$";//考虑三种情况 -6,-5/6,11/-2
            if(temp.matches(param)){
                continue;
            }else{
                question.add(str);
                answer.add(temp);
//                end.add(endStr.toString());
            }
        }
//        for (int i = 0;i<question.size();i++){
//            for (int j = 0; j<i;j++){
//
//            }
//        }
        //将题目和答案写到文件中
        if(Output.out("text.txt","javaee/operate",question,answer)){
            return  true;
        }else {
            return false;
        }

    }

    /**
     * 新建一个树节点p，值为当前元素的值，如果取到的元素是操作数，
     * 直接把p入栈s，如果是运算符，从栈中弹出2个节点，把第一个弹出的节点作为p的右子树，第二个弹出的节点作为p的左子树，
     * 然后把p入栈。
     * 当遍历完逆波兰式时，树的根节点就保存在栈里了
     * @param endStr
     * @return
     */
    public Node endToTree(String [] endStr) {
        Node node;
        Stack stack = new Stack();
        Node p1, p2;
        for (String key : endStr) {
            node = new Node(key);
            if (sign.containsKey(key)) {
                try {
                    if("*".equals(key) || "+".equals(key)){
                        p2 = (Node) stack.pop();
                        p1 = (Node) stack.pop();
                        String value1 = p1.value;
                        String value2 = p2.value;
                        if(value1.matches("^\\d{1,}$") && value2.matches("^\\d{1,}$")){//都为数字，值大的为左子树
                            if(Integer.parseInt(p2.value) > Integer.parseInt(p1.value)){//p2的值比p1的大
                              node.rightChild = p1;
                              node.leftChild = p2;
                            }else{
                                node.rightChild = p2;
                                node.leftChild = p1;
                            }
                        }else if(value1.matches("^\\d{1,}$") && !value2.matches("^\\d{1,}$")){//一个数组，一个运算符，运算符为左子树
                            node.leftChild = p2;
                            node.rightChild = p1;
                        }else if(!value1.matches("^\\d{1,}$") && value2.matches("^\\d{1,}$")){//一个数组，一个运算符，运算符为左子树
                            node.leftChild = p1;
                            node.rightChild = p2;
                        } else{//两个都是操作符
                            int first = Integer.parseInt((String) sign.get(p1.value));
                            int second = Integer.parseInt((String) sign.get(p2.value));
                            if(first > second){//p1运算符的优先级高于p1,则p1作为左子树
                                node.leftChild = p1;
                                node.rightChild = p2;
                            }else if(first == second){//运算符相等的情况要比较，各自左子树的值
                                if(p1.leftChild.value.compareTo(p2.leftChild.value)>= 0){//左子树值大的作为左子树
                                    node.leftChild = p1;
                                    node.rightChild = p2;
                                }else{
                                    node.leftChild = p2;
                                    node.rightChild = p1;
                                }
                            }else{
                                node.leftChild = p2;
                                node.rightChild = p1;
                            }
                        }
                        stack.push(node);
                    }else{
                        p2 = (Node) stack.pop();
                        p1 = (Node) stack.pop();
                        node.rightChild = p2;
                        node.leftChild = p1;
                        stack.push(node);
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {//是数字的情况
                stack.push(node);
            }
        }
        if (1 == stack.size()) {//只剩下最后的运算结果了
            return (Node) stack.pop();
        }
        return null;

    }
    /**
     * 主控函数
     */
    public static void main(String[] args)
    {
        Create create = new Create(10,10,3);
        List<String> list = create.createList();
        List<String> answer =new ArrayList<String>();
        CalculateExp ce = new CalculateExp();
        String str []  = {"(4+1)*(2+3)","(3+2)*(4+1)"};
        for (String string : str){
            String [] temp = ce.midToEnd(ce.transStr(string));
            for (String tr: temp) {
                System.out.print(tr);
            }
            System.out.println();
            Node node = ce.endToTree(temp);
            List<String> ls = node.postOrderByStack(node);
            String str1 = String.join("",ls.toArray(new String[ls.size()]));
//        node.postOrderTraverse(node);
            System.out.println(str1);
            System.out.println(string + "=" + ce.calculate(temp) );
        }

//        for (String item :list){
//            String [] temp = ce.midToEnd(ce.transStr(item));
//            System.out.println(item + "=" + ce.calculate(temp) );
//            answer.add((String) ce.calculate(temp));
//        }
       // Output.out("text.txt","javaee/operate",list,answer);

    }
}