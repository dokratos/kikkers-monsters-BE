package Mykikker.kikkers.monsters.user;

import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<Player, Long> {
}
