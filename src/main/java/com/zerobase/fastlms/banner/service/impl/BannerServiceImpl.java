package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.entity.Course;
import com.zerobase.fastlms.course.entity.TakeCourse;
import com.zerobase.fastlms.course.mapper.CourseMapper;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import com.zerobase.fastlms.course.repository.CourseRepository;
import com.zerobase.fastlms.course.repository.TakeCourseRepository;
import com.zerobase.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;
    
    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .name(parameter.getName())
                .forwardTo(parameter.getForwardTo())
                .useYn(parameter.isUseYn())
                .targetStatus(parameter.getTargetStatus())
                .sortValue(parameter.getSortValue())
                .regDt(LocalDateTime.now())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .build();

        //
        bannerRepository.save(banner);
        
        return true;
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());

        if(!optionalBanner.isPresent()){
            return false;
        }

        Banner banner = optionalBanner.get();

        banner.setName(parameter.getName());
        banner.setForwardTo(parameter.getForwardTo());
        banner.setUseYn(parameter.isUseYn());
        banner.setTargetStatus(parameter.getTargetStatus());
        banner.setSortValue(parameter.getSortValue());
        banner.setUdtDt(LocalDateTime.now());
        banner.setFilename(parameter.getFilename());
        banner.setUrlFilename(parameter.getUrlFilename());

        bannerRepository.save(banner);
        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {

        System.out.println("---------------------------------------------");
        System.out.println(idList);
        if( idList != null && idList.length() > 0){
            String[] ids = idList.split(",");

            for(String x : ids){
                long id = 0L;

                try{
                    id = Long.parseLong(x);
                }
                catch(Exception e){

                }

                if(id > 0){
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> indexBannerList() {

       return BannerDto.of(bannerRepository.findByUseYnOrderBySortValue(true));
    }

}


























