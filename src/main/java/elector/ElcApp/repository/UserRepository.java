package elector.ElcApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elector.ElcApp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    /**
     * Tìm kiếm một người dùng dựa trên username.
     * Spring Data JPA sẽ tự động tạo câu lệnh SQL tương ứng.
     * SELECT * FROM Users WHERE Username = ?
     * @param username Tên đăng nhập của người dùng.
     * @return Đối tượng User hoặc null nếu không tìm thấy.
     */
    User findByUsername(String username);
}