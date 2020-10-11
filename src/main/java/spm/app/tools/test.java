package spm.app.tools;

public class test {
    public static void main(String[] args) {
        String s  = "新建文本文档 (2).txt";
        //.号应该用 \\. 代替
        Long ss = (long)s.split("").length;
        Long aa = (long)s.replace(" ","").split("").length;
        System.out.println(aa);
    }
}
