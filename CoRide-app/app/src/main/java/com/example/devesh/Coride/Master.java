package com.example.devesh.Coride;
import java.util.*;
import java.lang.String;
/**
 * Created by prabhakar on 10/5/16.
 */
public class Master {

    public static String url="http://172.16.81.242:8080/Coride/rest/connector/";
    public static String source="";
    public static String destination="";
    public static String mobile="";
    public static String mobile1="";
    public static String s1="";
    public static String d1="";
    public static String a="";
    public static String b="";
    public static String distance="";
    public static String amount="";


    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        Master.flag = flag;
    }

    public static int flag=0;
}
