package com.dw.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LectureStatusCountDto {
    private String LectureStatusId;
    private String LectureStatusName;
    private Long LectureStatusCount;
    private Long SortSeq;
}
