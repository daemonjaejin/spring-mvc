package com.springapp.mvc;

import java.util.StringTokenizer;

/**
 * Created by jin on 15. 10. 29..
 */
public class ForTest {
    public static void main(String args[]){

        String [] a = {"안녕하세요", "방가워요"};

        for(int i=0, len = a.length; i < len; i++){
            System.out.println(i + "번째 테스트");
        }

        String ret = System.getProperty("user.dir");
        System.out.println("ret : " + ret);

        StringTokenizer st = new StringTokenizer("안녕하세요 # 방가워요 & 누구세요 @ 어허 참말로 ￢ 왜 이러세요% 정말루 $", "#" + "&" + "@" + "￢" + "%" + "$", true);

        while( st.hasMoreTokens() ) {
            String token = st.nextToken();
            System.out.println("token : " + token);
        }

        long abc = 0;

        System.out.println(abc);

        abc |= 10;

        System.out.println(abc);

        abc |= 10;

        System.out.println(abc);

    }
}
