package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Entity.Tournament;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.Repository.TournamentRepository;
import com.LOL.Pros.dto.request.RegionRequest;
import com.LOL.Pros.dto.request.Update.RegionUpdateRequest;
import com.LOL.Pros.dto.response.RegionResponse;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegion()
    {
        return regionRepository.findAll();
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
}
