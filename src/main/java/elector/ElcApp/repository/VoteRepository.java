package elector.ElcApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elector.ElcApp.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    /**
     * Kiểm tra xem một người dùng đã bình chọn cho một cuộc bình chọn cụ thể hay chưa.
     * @param userId ID của người dùng.
     * @param pollId ID của cuộc bình chọn.
     * @return true nếu đã bình chọn, ngược lại là false.
     */
    boolean existsByUserIdAndPollId(Integer userId, Integer pollId);

    /**
     * Đếm số lượt bình chọn cho một lựa chọn cụ thể.
     * @param optionId ID của lựa chọn.
     * @return Số lượt bình chọn.
     */
    long countByOptionId(Integer optionId);
}