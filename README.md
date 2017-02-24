# Description
This program is made to help to control your costs on food, especially it helps in:
- Counting how much you spend for food;
- Showing your everyday purchases;
- Possibility to filter by date;
- Counting the sum of expenses on every category;
- Sorting categories by the sum of expenses;
- Possiblity to add new categories.

### File format

For correct work you need next files:
- drink-category.json;
- food-category.json;
- purchase.json.

Files `drink-category.json`, `food-category.json`, `purchase.json` must be empty.
On the first launch the program shows next sentence: `"You have incorrect structure of file! Please check documentation!"`
It means that your file is empty, but this message will desapear after adding new categories in the file.

### Start programm

To launch the program you need to find the folder with `pom.xml` file.
With the help of command line execute next command: 
```sh
$ mvn clean install
```
After that you need to find folder `"targer"`.
In this folder you must open the command line and execute next commands:

```sh
$ java -jar food-manager-1.0-jar-with-dependencies.jar C:/Users/Viacheslav_Belymenko/Desktop/foodmanager/file
```
C:/Users/Viacheslav_Belymenko/Desktop/foodmanager/file - application path to folder with `drink-category.json`, `food-category.json`, `purchase.json` files.
