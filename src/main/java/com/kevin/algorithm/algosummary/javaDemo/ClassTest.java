package com.kevin.algorithm.algosummary.javaDemo;

/**
 * @ClassName: ClassTest
 * @Description:
 * @Author: Kevin
 * @Date: 2018/10/24 10:35
 */
public class ClassTest {

    class A{
        public A() {
            System.out.println("A");
        }
    }
    class B extends A{
        public B() {
            //super();
            System.out.println("B");
            //super();//编译报错
        }
    }
    public void testFunction(){
        B b= new B();
        System.out.println("testTry, result="+testTry());
        System.out.println("testTry2, result="+testTry2());
        System.out.println("testTry3, result="+testTry3());


    }
    public int testTry(){
        int a = 0;
        try{
            int b = 1/0;
            return a;
        }catch (Exception e){
            a = 1;
            return a;
        }finally {
            a = 2;
            return a;
        }
        //return a; //编译报错
    }
    public StringBuilder testTry2(){
        StringBuilder sb = new StringBuilder("0");
        try{
            //int b = 1/0;
            return sb;
        }catch (Exception e){
            sb = new StringBuilder("1");
            return sb;
        }finally {
            sb = new StringBuilder("2");
            //return sb;
        }
        //return a; //编译报错
    }
    public StringBuilder testTry3(){
        StringBuilder sb = new StringBuilder("0");
        try{
            //int b = 1/0;
            return sb;
        }catch (Exception e){
            sb = sb.append("1");
            return sb;
        }finally {
            sb = sb.append("2");
            //return sb;
        }
        //return a; //编译报错
    }

    public static void main(String[] args) {
        ClassTest test = new ClassTest();
        test.testFunction();
    }
}
