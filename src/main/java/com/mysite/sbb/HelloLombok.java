package com.mysite.sbb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

    private String hello;
    private int lombok;

    public HelloLombok(String hello, int lombok) {
        this.hello = hello;
        this.lombok = lombok;
    }


    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("Hello Lombok!",100);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}
