package bibhas.com.retrieve_json;

public class Contact {

    private String name;
    private String age;
    private String mobile;
    private String home;
    private String home_no;

    public Contact(String name, String age, String mobile, String home, String home_no) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.home = home;
        this.home_no = home_no;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHome() {
        return home;
    }

    public String getHome_no() {
        return home_no;
    }
}
