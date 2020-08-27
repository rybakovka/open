package mapping;

import java.util.List;

public class Page {

    String page;

    public List<User> data;

    public class User {
        public String id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;
    }
}
