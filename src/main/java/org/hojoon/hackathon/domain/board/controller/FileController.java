package org.hojoon.hackathon.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.board.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    public String fileUpload(@RequestPart MultipartFile file) {
        return fileService.fileUpload(file);
    }


}
