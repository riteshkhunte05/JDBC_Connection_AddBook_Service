package com.bridgelabz.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AddressBookService {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        boolean exit = false;
        while (!exit) {
            System.out.println(" Press\n 1 ->  Retrieve data\n 2 -> Update Address,city,state,zip  by srNo\n " +
                    "3 -> Retrieve data for particular date\n" +
                    "4 -> Retrieve Count of Contacts for City or State\n" +
                    "5 -> Add new Contacts to AddressBook\n" +
                    "6 -> exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    retrieveData();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    retrieveDataForParticularDate();
                    break;
                case 4:
                    retrieveCountByCityOrState();
                case 5:
                    addNewContact();
                    break;
                case 6:
                    exit = true;
            }
        }
    }

    private static void retrieveData() {
        AddressBookMain addressBookRepo = new AddressBookMain();
        List<ContactsData> employeeInfoList = addressBookRepo.retrieveData();
        for (ContactsData employee : employeeInfoList
        ) {
            System.out.println(employee + "\n");
        }
    }

    private static void update() {
        AddressBookMain addressBookRepo = new AddressBookMain();
        System.out.println("Enter the address,city,state, zip and Serial Number  to Update");
        addressBookRepo.updateCityByZip(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt());
    }
    private static void retrieveDataForParticularDate() {
        AddressBookMain addressBookRepo = new AddressBookMain();
        System.out.println("Enter the Date of Joining (YYYY-MM-DD");
        System.out.println("Enter year , month and Day ex: 2020 02 03");
        List<ContactsData> employeeInfoList = addressBookRepo.findAllForParticularDate(LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        for (ContactsData employee : employeeInfoList
        ) {
            System.out.println(employee + "\n");
        }
    }
    private static void retrieveCountByCityOrState() {
        AddressBookMain addressBookRepo = new AddressBookMain();
        System.out.println("Enter 1 -> Contacts count by City\n" +
                "2 -> Contacts count by State");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Enter city Name");
                int cityContactsCount = addressBookRepo.countByCiy(scanner.next());
                System.out.println("Number of Contacts is Given city= " + cityContactsCount);
                break;
            case 2:
                System.out.println("Enter state name");
                int stateContactsCount=  addressBookRepo.countByState(scanner.next());
                System.out.println("Number of Contacts is Given state= " + stateContactsCount);
                break;
        }


    }
    private static void addNewContact() throws SQLException {
    	ContactsData add = new ContactsData();
        System.out.println("Enter First Name:");
        add.setFirstName(scanner.next());
        System.out.println("Enter Last name:");
        add.setLastName(scanner.next());
        System.out.println("Enter address");
        add.setAddress(scanner.next());
        System.out.println("Enter city");
        add.setCity(scanner.next());
        System.out.println("Enter state");
        add.setState(scanner.next());
        System.out.println("Enter Zip");
        add.setZip(scanner.nextInt());
        System.out.println("Enter PhoneNumber");
        add.setPhoneNumber(scanner.next());
        System.out.println("Enter Email");
        add.setEmailId(scanner.next());
        System.out.println("Enter Addressbook name");
        add.setBookName(scanner.next());
        System.out.println("Enter contact type");
        add.setContactType(scanner.next());
        add.setDateAdded(LocalDate.now());
        AddressBookMain.insertData(add);

    }
}