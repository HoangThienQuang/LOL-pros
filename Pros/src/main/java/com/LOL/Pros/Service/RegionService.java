package com.LOL.Pros.Service;

import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.dto.request.RegionRequest;
import com.LOL.Pros.dto.response.RegionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<RegionResponse> getAllRegion()
    {
        List<Region> regionResult = regionRepository.findAll();
        return regionResult.stream().map(region1 -> new RegionResponse(region1.getRegionName(),region1.getTeams(), region1.getDomesticTournaments())).collect(Collectors.toList());
    }

    public RegionResponse createRegion(RegionRequest request)
    {
        if (regionRepository.findByRegionName(request.getRegionName()).isPresent())
            throw new AppException(ResponseCode.REGION_EXISTED);
        Region region = Region.builder()
                .regionName(request.getRegionName())
                .build();
        regionRepository.save(region);
        return RegionResponse.builder()
                .regionName(region.getRegionName())
                .build();
    }

    //public RegionResponse addTeamToRegion()
}
