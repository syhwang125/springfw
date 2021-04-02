package io.namoosori.web.fileserver.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.namoosori.web.fileserver.service.FileService;
import lombok.RequiredArgsConstructor;

@RequestMapping(value = { "/file"})
@Controller
@RequiredArgsConstructor
public class FileController {
	//
    private final FileService fileService;

    @GetMapping("/")
    public ModelAndView main() {
        // TODO Implements method
    	ModelAndView modelAndView = new ModelAndView("/views/main.jsp");
    	modelAndView.addObject("fileName", fileService.listFiles());
        return modelAndView;
    }
    
    @GetMapping("/find")
    public ModelAndView findNameLike(@RequestParam String nameLike) {
        // TODO Implements method
        List<String> fileList = fileService.listFilesNameLike(nameLike);
		return new ModelAndView("/views/main.jsp", "fileNames", fileList);
    }
}
