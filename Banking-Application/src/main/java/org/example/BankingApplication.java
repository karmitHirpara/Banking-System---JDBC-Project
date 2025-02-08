package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class BankingApplication {

    private final static String url = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
    private final static String user = "USER";
    private final static String password = "PASSWORD";
    public static Scanner sc = new Scanner(System.in);

    public static Connection connection;
    public static PreparedStatement preparedStatement;

    public static void main(String[] args) {

           try{
               connection = DriverManager.getConnection(url,user,password);
               int choice = 0;
               while (true) {
                   operations.details();
                   choice = sc.nextInt();
                   System.out.println("--------------------------------------------------------------------------");
                   switch (choice) {
                        case 1: //create account
                        {
                            operations.createAccount(preparedStatement);
                            break;
                        }
                        case 2://check bal
                        {
                            operations.checkBalance(preparedStatement);
                            break;
                        }
                        case 3://deposite money
                        {
                            operations.diposite(preparedStatement);
                            break;
                        }
                        case 4://withdraw money
                        {
                            operations.withdraw(preparedStatement);
                            break;
                        }
                       case 5://delete
                       {
                           operations.deleteAccount(preparedStatement);
                           break;
                       }
                        case 6://watch demo account
                        {
                            System.out.println("enter type: account_type");
                            System.out.println("enter name: account_name");
                            System.out.print("enter balance: add_money");
                            break;
                        }
                        default: //exit
                        {
                            System.out.println("*******************************************************Thanks_For_visite_Banking_Apk*******************************************************");
                            return;
                        }
                    }
                   System.out.println("-----------------------------------End---------------------------------------");

                }
           }catch (Exception e){
               System.out.println("Error:: " + e.getMessage());
           }
    }
}
