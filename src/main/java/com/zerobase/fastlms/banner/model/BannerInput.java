package com.zerobase.fastlms.banner.model;

import lombok.Data;

@Data
public class BannerInput {

    long id;

    String name;
    String forwardTo; //링크 주소
    boolean useYn; // 표시 여부
    String targetStatus; //클릭시 타겟 정보
    int sortValue; //정렬 우선순위
    
    //삭제를 위한
    String idList;
    
    
    //ADD
    String filename;
    String urlFilename;

}

