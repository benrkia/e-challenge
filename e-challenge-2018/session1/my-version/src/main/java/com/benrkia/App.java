package com.benrkia;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CloudInfrastructure cloud = new CloudInfrastructure();
        cloud.createMachine("machine1", "Linux", "50gb", "8gb");
        cloud.createMachine("machine2", "Windows", "20gb", "4gb");



    }
}
