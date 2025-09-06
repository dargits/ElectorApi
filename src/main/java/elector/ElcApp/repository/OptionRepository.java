package elector.ElcApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elector.ElcApp.model.Option;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
    
    /**
     * Tìm kiếm tất cả các lựa chọn thuộc một cuộc bình chọn cụ thể.
     * @param pollId ID của cuộc bình chọn.
     * @return Danh sách các đối tượng Option.
     */
    List<Option> findByPollId(Integer pollId);
}