package edu;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        if (a>b){
            System.out.println(">");
        }
        else if (a<b){
            System.out.println("<");
        }
        else {
            System.out.println("=");
        }

    }
}
//goodsifno select/insert/ avg(,) sum*(), count()/groupby having maker중심(제조사 = 엘지삼성대우 등을 몇개 인서트하고 하기 ) record 출력
//avg(price) >= 300000 등 (위 그룹바이랑 연계)having에는 집계conut만