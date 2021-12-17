package com.mrd.learn.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author ducnh
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "answer")
public class Answer {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quiz_id")
    private Integer quizId;

    @Column(name = "content")
    private String content;

    @Column(name = "correct")
    private boolean correct;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modify_at")
    private Timestamp modifyAt;

    @PrePersist
    void preInsert() {
        if (this.getCreatedAt() == null) {
            this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        if (this.getModifyAt() == null) {
            this.setModifyAt(new Timestamp(System.currentTimeMillis()));
        }
    }
}
