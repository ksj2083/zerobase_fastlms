package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BannerDto {


    Long id;

    String name;
    String forwardTo; //링크 주소
    Boolean useYn; // 표시 여부
    String targetStatus; //클릭시 타겟 정보
    int sortValue; //정렬 우선순위

    LocalDateTime regDt;//등록일(추가날짜)
    LocalDateTime udtDt;//수정일(수정날짜)

    String filename;
    String urlFilename;
    
    //추가컬럼
    long totalCount;
    long seq;
    
    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .forwardTo(banner.getForwardTo())
                .useYn(banner.isUseYn())
                .targetStatus(banner.getTargetStatus())
                .sortValue(banner.getSortValue())
                .regDt(banner.getRegDt())
                .udtDt(banner.getUdtDt())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .build();
    }
    
    public static List<BannerDto> of(List<Banner> banners) {
        
        if (banners == null) {
            return null;
        }
    
        List<BannerDto> bannerList = new ArrayList<>();
        for(Banner x : banners) {
            bannerList.add(BannerDto.of(x));
        }
        return bannerList;

    }
    
}

















