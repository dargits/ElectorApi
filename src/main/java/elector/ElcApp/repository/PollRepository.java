package elector.ElcApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elector.ElcApp.model.Poll;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {

    /**
     * Tìm kiếm tất cả các cuộc bình chọn đang hoạt động (isActive = true).
     * @return Danh sách các đối tượng Poll.
     */
    List<Poll> findByIsActive(Integer isActive);
}
