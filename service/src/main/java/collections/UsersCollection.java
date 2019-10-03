package collections;

import model.User;

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
    public User get(int userId) {
        return users.get(userId);
    }
    public boolean exists(int userId){
        return users.containsKey(userId);
    }
    public void remove(int userId){
        users.remove(userId);
    }
    public int add(User user) {
        user.setUserId(getNewId());
        users.putIfAbsent(user.getUserId(), user);
        return user.getUserId();
    }
    public User update(User user) {
        return users.replace(user.getUserId(), user);
    }
    public int getNewId() { return ++idCount; }
}
