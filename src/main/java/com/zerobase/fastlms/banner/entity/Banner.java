package com.zerobase.fastlms.banner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String forwardTo; //링크 주소
    boolean useYn; // 표시 여부
    String targetStatus; //클릭시 타겟 정보
    int sortValue; //정렬 우선순위
    
    LocalDateTime regDt;//등록일(추가날짜)
    LocalDateTime udtDt;//수정일(수정날짜)

    String filename;
    String urlFilename;
}
