package zil.meetup.authms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zil.meetup.authms.entity.User;

public interface UserDAO extends MongoRepository<User, Integer> {
}
