package hkmu.gp.online_course.service;

import hkmu.gp.online_course.dto.UserDto;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;   // 注入 PasswordEncoder

    // 注册方法（保持不变）
    public void register(UserDto dto, String role) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRole(role);
        userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    // 方法1：通过实体更新（用于用户自己更新个人信息）
    public void updateUser(User user) {
        userRepo.save(user);
    }

    // 方法2：通过 ID 和 DTO 更新（用于管理员编辑用户）
    public void updateUser(Long id, UserDto dto) {
        User user = userRepo.findById(id).orElseThrow();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}