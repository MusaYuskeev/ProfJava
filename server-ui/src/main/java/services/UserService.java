package services;

import dao.User;
import dao.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableJpaRepositories("dao")
@EntityScan(basePackageClasses = dao.User.class)
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    public User getUser(Integer id) {
        return userRepository.findOne(id);
    }

    public User updateUser(Integer id, User request) {
        User fromDb = getUser(id);
        fromDb.setName(request.getName());
        fromDb.setFullName(request.getFullName());
        return userRepository.save(fromDb);
    }
}

