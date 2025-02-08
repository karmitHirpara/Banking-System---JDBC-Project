package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.BankingApplication.*;

public class operations {
    public static void createAccount(PreparedStatement statement) throws SQLException {
        System.out.print("enter name: ");
        String name = sc.next();

        if(!name.matches("[a-zA-z]+")){
            System.out.println("Please enter valid name...");
            return;
        }

        System.out.print("enter balance: ");
        double balance = sc.nextInt();

        String sql = "insert into bankingApk(acName, acBal) values (?, ?);";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setDouble(2, balance);
        int addData = statement.executeUpdate();
        if(addData>0){
            System.out.println("-----------sucess-----------");
        }else{
            System.out.println("------------invalid---------");
        }
    }

    public static void checkBalance(PreparedStatement statement) throws SQLException{
        System.out.print("enter no: ");
        int no = sc.nextInt();
        String sql = "select acBal,acName from bankingApk where acNo = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, no);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            System.out.println("Name: " + resultSet.getString("acName"));
            System.out.println("Balance: " + resultSet.getString("acBal"));
        }else {
            System.out.println("----------------------Not Found!------------------------");
        }
    }

    public static void diposite(PreparedStatement statement) throws SQLException{
        System.out.print("enter no: ");
        int no = sc.nextInt();
        System.out.print("enter balance: ");
        double addbalance = sc.nextInt();

        String sql = "select acBal from bankingApk where acNo = ?";

        statement = connection.prepareStatement(sql);
        statement.setInt(1, no);
        ResultSet resultSet = statement.executeQuery();
        if(addbalance<=0){
            System.out.println("----------please enter valid amount!-------");
        }
        else if(resultSet.next()){
            double currantbalance = resultSet.getDouble("acBal") + addbalance;
            sql = "update bankingApk set acBal=? where acNo=?";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, currantbalance);
            statement.setInt(2, no);
            int check= statement.executeUpdate();
            if(check>0) {
                System.out.println("-------------------------------Sucessfully Update!---------------------------------");
            }else{
                System.out.println("-------------------------------Filed...!-------------------------------");
            }
        }else {
            System.out.println("-------Account_No_Not_Valid----------------");
        }

    }

    public static void withdraw(PreparedStatement statement) throws SQLException{
        System.out.print("enter account no: ");
        int no = sc.nextInt();
        System.out.print("enter amount no: ");
        int getAmount = sc.nextInt();;
        if(getAmount<=0){
            System.out.println("-------------enter_valid_input--------------");
            return;
        }
        String sql = "select acBal from bankingApk where acNo = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, no);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            int prvAmount = resultSet.getInt("acBal");
            if(prvAmount<getAmount){
                System.out.println("not_sufficient_balance_in_your_aacount!");
            }else {
                int newBalance = prvAmount - getAmount;
                int update = statement.executeUpdate("update bankingApk set acBal = " + newBalance + " where acNo = " + no);
                if (update > 0) System.out.println("-----sucessfully_withdrew--------");
                else System.out.println("-----------something_went_wrong!----------");
            }
        }else {
            System.out.println("-----------Id_Not_Found!------------");
        }
    }

    public static void deleteAccount(PreparedStatement statement) throws SQLException{
        System.out.print("enter account no: ");
        int no = sc.nextInt();

        if(!checkAccountExists(preparedStatement, no)){
            System.out.println("please enter valid account number");
            return;
        }
        String sql = "delete from bankingApk where acNo = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, no);

        System.out.println("Are you sure this aacount wiil be delete if have you have balance so please withdewal it otherwise lost your money if you enter 2");
        System.out.print("enter 1 for agree: ");
        int deleteChoice = sc.nextInt();
        if(deleteChoice == 1){
            int success = statement.executeUpdate();
            if(success>0){
                System.out.println("Your account deleted");
            }else {
                System.out.println("invalid");
            }
        }
    }
    public static boolean checkAccountExists(PreparedStatement statement, int no) throws SQLException{
        String sql = "select count(*) from bankingApk where acNo = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, no);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            int res = resultSet.getInt("count(*)");
            if(res == 1) return true;
        }
        return false;
    }

    public static void details() {
        System.out.println("enter 1 for create new account: ");
        System.out.println("enter 2 for check balance: ");
        System.out.println("enter 3 for deposite: ");
        System.out.println("enter 4 for withdraw: ");
        System.out.println("enter 5 for delete account: ");
        System.out.println("enter 6 for watch demo account: ");
        System.out.println("enter 7 for exit: ");
    }
}
