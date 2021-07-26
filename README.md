# Customer Success Balancing

This project has the goal to balance available customer success with your clients according to heir level.

## Installation

On linux environment, it is necessary open a terminal and download maven. 
Execute the following commands to install maven:

```bash
sudo apt update
sudo apt install maven
```
After the installation finishes execute ```mvn -version ``` to check maven version installed on your machine.

It is also required java 11 to work.

## Usage

To import the data from customer success, clients and the off duty customer, there is a file in the folder ```src/main/resources/CustomerSuccess.txt``` where the information is kept.

To add data we must first need to understand how the file structure works:

The values on each line are separated by semicolons and the first value on each line describes what kind of information will come next. There are only 3 possible values for the first element in the line: one, two or tree. The number one describes a Customer Success data, number two describes a Client data and the number tree describes a off duty Customer. The following shows an example of this:

```
1;1;50
1;2;60
2;1;30
3;2
```
The data above is showing 4 lines of a file example. The first is a Customer line (first value = 1). The second value of the line is the customer id (id = 1) and the third value is the customer level (level = 5). The second line is again a customer.

The third line is a Client line (first value = 2). The second value in the line is the client id (id = 1) and the third value is the client level (level = 30).

The fourth line in the file is a off duty customer line (first value = 3). The second value in the line is the customer id (id = 2). The customer with id = 2 is off duty, which means he is not available to work with clients.

## Run
Enter the project folder and execute ```mvn install``` to compile and build the project.

To run simply execute ```java -jar target/customer-success-balancing-0.0.1-SNAPSHOT.jar``` on the console.