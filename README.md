# Internet-shop
# Table of Contents
* [Purpose of the project](#purpose)
* [Structure of the project](#structure)
* [For developers](#for-developers)
* [Authors of the project](#authors)

<a name="purpose"></a>
# Purpose
This project is an implementation of a simple internet shop written in Java.
***
This project has a basic functionality of a typical online store. 
The following actions are available for all users:
* view the index page of the website
* create a user account
* login 
* logout 

Actions available for administrators of the shop:
* add new products to the database
* delete products from the database
* see all registered users 
* delete users from the database
* see all users' order history

Actions available for users only:
* view a list of all products
* add products to their shopping cart
* create orders 

<a name="stucture"></a>
# Structure
* Java 13
* Maven 4.0.0
* javax.servlet.-api 3.0.1
* jstl 1.2
* log4j 1.2.17
* maven-checkstyle-plugin
* mysql-connector-java 8.0.20

<a name="for-developers"></a>
# For developers
Clone this project into some folder on your computer.
Open the project in your IDE.

Configure Tomcat Server.

Add SDK 13.0.1 to the project structure.

Change your log file path in src/main/resources/log4j2.xml on line 9.

In order to create a schema and its tables required for this project, you can use the 
init_db.sql file, which is located in the 'resources' folder.

Set your own MySQL username and password in the ConnectionUtil class, which is
located in the 'util' package.

Make sure MySQL server is running when launching the application.

If you want to add test data to the database, you can click on
the 'Inject test data' menu item. 

By default, there are two user roles: USER and ADMIN.

<a name="authors"></a>
# Authors
[Anastasiia Kosiak](https://github.com/AnastasiiaKosiak)

 