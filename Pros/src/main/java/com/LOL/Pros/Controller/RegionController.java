package com.LOL.Pros.Controller;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Service.RegionService;
import com.LOL.Pros.dto.request.RegionRequest;
import com.LOL.Pros.dto.request.Update.RegionUpdateRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.RegionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    ApiResponse<RegionResponse> createRegion(@RequestBody RegionRequest request)
    {
        return ApiResponse.<RegionResponse>builder()
                .code(100)
                .message("Create Region success")
                .data(regionService.createEmptyRegion(request))
                .build();
    }

    @GetMapping("/getAll")
    ApiResponse<Object> getAllRegion()
    {
        List<Region> result = regionService.getAllRegion();
        return ApiResponse.builder()
                .code(100)
                .message("Get all region success")
                .data(result)
                .build();
    }

    @PutMapping("/update")
    ApiResponse<RegionResponse> updateRegion(@RequestBody RegionUpdateRequest request)
    {
        return ApiResponse.<RegionResponse>builder()
                .code(100)
                .message("Update region success")
                .data(regionService.updateRegion(request))
                .build();
    }
}
