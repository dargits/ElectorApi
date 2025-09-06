package elector.ElcApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "Options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PollID", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Poll poll;

    @Column(name = "OptionText", nullable = false)
    private String optionText;
}