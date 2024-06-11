package com.dw.lms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="lms_events")
public class Lms_events {
    @Id
    @Column(name = "lms_events_seq")
    private Long lmsEventSeq;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    @Column(name = "lms_events_title", length = 100)
    private String lmsEventsTitle;

    @Column(name = "lms_events_content", length = 255)
    private String lmsEventsContent;

    @Column(name = "lms_events_start_date")
    private LocalDate lmsEventsStartDate;

    @Column(name = "lms_events_end_date")
    private LocalDate lmsEventEndDate;

    @ManyToOne
    @JoinColumn(name = "lms_events_writer")
    private User user;

    @Column(name = "lms_events_writing_date")
    private LocalDate lmsEventsWritingDate;

    @Column(name = "lms_events_view_count")
    private Long lmsEventViewCount;

    @Column(name = "sys_date", updatable = false)
    private LocalDateTime sysDate;

    @Column(name = "upd_date")
    private LocalDateTime updDate;


}