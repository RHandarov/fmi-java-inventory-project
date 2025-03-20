package fmi.java.inventory_project.model;

public class ClubMember {
    private static int nextId = 0;

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public ClubMember(String firstName,
                      String lastName,
                      String email) {
        id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
