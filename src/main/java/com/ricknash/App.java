package com.ricknash;

import com.ricknash.controller.WriterController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        WriterController wr = new WriterController();
        wr.saveWriter();
    }
}
