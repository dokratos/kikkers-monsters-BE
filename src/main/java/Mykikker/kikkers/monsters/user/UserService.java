package Mykikker.kikkers.monsters.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository repo;
    public UserService(@Autowired UserRepository repo) {
        this.repo = repo;
    }

    public Player createUser(PlayerDTO player) {
        Player newUser = new Player(player.name(), player.score());
        return repo.createUser(newUser);
    }

    public List<Player> getUsers() {
        return repo.getUsers();
    }
}
