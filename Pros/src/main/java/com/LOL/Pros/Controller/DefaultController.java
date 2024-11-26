package com.LOL.Pros.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class DefaultController {
    @GetMapping("/")
    @ResponseBody
    public byte[] index() throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources/templates/index.html"));
    }
}
