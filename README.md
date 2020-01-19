# SpreadSheet2Java

This is a proof of concept to generate a Java code from a spreadsheet / Excel file. 

There are a few instances where data is written in a spreadsheet for easier organization, sharing (using Google Doc) or for non-tech people. In cases where this data needs to be directly translated into code, for some game achievement, data definitions, type references, names, ... it is quite tedious to manually retype this into the code.

This a really simple test that tries to automate this. From a table, it generates a Java enum class, where each row represents an enum value and each row is a field/value in the enum. It packages it into a Jar and uploads it to the local Maven repository.

There are a few benefits of these solutions:
- The code is automatically generated. There is no need to manually retype it to the code
- The data in the spreadsheet is versioned using the Maven versioning system. When someone makes a change in for example in Google Drive, the change is not propagated to the code and is har to trach when it was changed, if everything was changed, what version of the data definition is the product using. If multiple parts relay on the same data are they in sync or not.  
- The code can be simply accessed through the Maven repository and the version change/upgrade comes with it.


#### Example of data
| ID_A   | ID_B   |
|--------|--------|
| id_a_1 | id_b_1 |
| id_a_2 | id_b_2 |
| id_a_3 | id_b_3 |
| id_a_4 | id_b_4 |
| id_a_5 | id_b_5 |
| id_a_6 | id_b_6 |
| id_a_7 | id_b_7 |
| id_a_8 | id_b_8 |


#### Example of Java class


```Java
public enum Data {

    id_a_1("id_a_1", "id_b_1"),
    id_a_2("id_a_2", "id_b_2"),
    id_a_3("id_a_3", "id_b_3"),
    id_a_4("id_a_4", "id_b_4"),
    id_a_5("id_a_5", "id_b_5"),
    id_a_6("id_a_6", "id_b_6"),
    id_a_7("id_a_7", "id_b_7"),
    id_a_8("id_a_8", "id_b_8");

    Data(String ID_A, String ID_B) {
        this.ID_A = ID_A;
        this.ID_B = ID_B;
    }

    private final String ID_A;

    private final String ID_B;
}
```