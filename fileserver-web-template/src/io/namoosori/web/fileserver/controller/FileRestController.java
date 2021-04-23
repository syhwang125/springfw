package io.namoosori.web.fileserver.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import io.namoosori.web.fileserver.service.FileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = {"/fileserver-web/rest"})
@RequiredArgsConstructor
public class FileRestController {
    //
    private final FileService fileService;

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file") List<MultipartFile> uploadFiles) throws IOException {
        //
        // TODO 
    	// 1. save file to server using FileService 
        // 2. redirect to main page
    	for(int i =0; i < uploadFiles.size(); i++) {	
    		MultipartFile mpf = uploadFiles.get(i);
    		File file = new File(mpf.getOriginalFilename());
    		mpf.transferTo(file);
    		fileService.upload(file);
    	}
    	return new ModelAndView("/views/index.jsp");
    }

    @GetMapping(value = {"/download"}, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> download(@RequestParam("fileName") String fileName) {
        //
    	// TODO send file to client using FileService
    	
    	byte[] contents = fileService.download(fileName);
    	ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(contents,HttpStatus.OK);
    	return responseEntity;
    }
    

    @GetMapping("/remove")
    public void remove(@RequestParam("fileName") String fileName) {
        //
        fileService.delete(fileName);
    }
    
    
}
