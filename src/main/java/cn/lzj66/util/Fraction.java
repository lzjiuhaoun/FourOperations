package cn.lzj66.util;

public class Fraction {
    private int numerator;      // 分子
    private int denominator;    // 分母
    public Fraction() {
        this(0, 1);
    }
    public Fraction(int numerator){
        this(numerator,1);
    }
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("分母不能为 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        shrink();
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    // 分子分母同除以最大公约数
    private Fraction shrink() {
        int maxCommonDivisor = getMaxCommonDivisor(this.denominator, this.numerator);
        this.numerator /= maxCommonDivisor;
        this.denominator /= maxCommonDivisor;
        return this;
    }

    // 辗转相除法求最大公约数
    private int getMaxCommonDivisor(int a, int b) {
        if(b == 0){//如果是辗转相除法出现b等于0的情况直接返回1
            return 1;
        }
        int mod = a % b;
        if (mod == 0) {
            return b;
        } else {
            return getMaxCommonDivisor(b, mod);
        }
    }

    // 分数加法
    public Fraction add(Fraction that) {
        return new Fraction(this.numerator * that.denominator + this.denominator * that.numerator,
                this.denominator * that.denominator);
    }

    // 分数减法
    public Fraction minus(Fraction that) {
        return new Fraction(this.numerator * that.denominator - this.denominator * that.numerator,
                this.denominator * that.denominator);
    }

    // 分数乘法
    public Fraction multiply(Fraction that) {
        return new Fraction(this.numerator * that.numerator,
                this.denominator * that.denominator);
    }

    // 分数除法
    public Fraction devide(Fraction that) {
        return new Fraction(this.numerator * that.denominator,
                this.denominator * that.numerator);
    }

    public double doubleValue() {
        return (double) numerator / denominator;
    }

    @Override
    public String toString() {
        int numerator = this.numerator;
        int denominator = this.denominator;
        if(numerator % denominator == 0){//整数
            return Integer.toString(numerator / denominator);
        }else if(numerator / denominator >= 1){//假分数
            int num = numerator / denominator;
            int left = numerator % denominator;
            return String.format("%d'%d/%d", num,left,denominator);
        }else{//真分数
            return String.format("%d/%d", numerator,denominator);
        }
    }
}
