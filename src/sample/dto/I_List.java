package sample.dto;

/* Interface for a group of objects
 */
/**
 *
 * @author Hoa Doan
 */
public interface I_List {
    // add new element( input from scanner) to I_List

    void add();
    // Input the code wanna remove

    void remove();
    // input the code want to update, after that update other information--> use set method

    void update();

    // show detail of each element of List
    void output();

    void checkExistedUser();

    void searchUser();

    void save(String fName);

    void load(String fName);

    User findUserByName(String userName);

    User login(String username, String password);
}
