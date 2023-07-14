package Mykikker.kikkers.monsters.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JpaUserRepository jpa;

    public Player createUser(Player newUser) {
        Player newPlayer = jpa.save(newUser);
        return newPlayer;
    }

    public List<Player> getUsers() {
        return Streamable.of(jpa.findAll()).toList();
    }
}
