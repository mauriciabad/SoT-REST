package fontys.sot.rest.service.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersCollection {

    // Singleton
    private static final UsersCollection instance = new UsersCollection();
    public static UsersCollection getInstance(){ return instance; }

    // Initialize with some data
    private UsersCollection(){
        this.add(new User("Ash"));
        this.add(new User("Misty"));
        this.add(new User("Brock"));
        this.add(new User("Maya"));
        this.add(new User("Aura"));
        this.add(new User("Gary"));
        this.add(new User("Oak"));
    }

    // Data structure containing users
    private Map<Integer, User> users = new HashMap<>();
    private int idCount = 0;

    // Methods
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }
    public int total() {
        return users.size();
    }
    public User get(int user_number) {
        return users.get(user_number);
    }
    public boolean exists(int user_number){
        return users.containsKey(user_number);
    }
    public void remove(int user_number){
        users.remove(user_number);
    }
    public int add(User user) {
        user.setUser_id(getNewId());
        users.putIfAbsent(user.getUser_id(), user);
        return user.getUser_id();
    }
    public User update(User user) {
        return users.replace(user.getUser_id(), user);
    }
    public int getNewId() { return ++idCount; }
}
