package com.example.filesample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class SampleController {

    @GetMapping("/sample")
    public ModelAndView sample(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/home");

        List<String> resultList = new ArrayList<>();
        resultList.add("aaa");
        resultList.add("bbb");
        resultList.add("ccc");
        resultList.add("ddd");
        resultList.add("eee");
        resultList.add("fff");
        mav.addObject("resultList", resultList);

        return mav;
    }

    /*@PostMapping("/sampleFile")
    public void sampleFile(@RequestParam MultipartFile[] uploadFile){
        System.out.println("sampleFile >>>>>");

        for(MultipartFile file : uploadFile){
            String oriName = file.getOriginalFilename();
            String name = file.getName();
            long size = file.getSize();

            System.out.println("oriName = " + oriName + " name = " + name + " size = " + size);
        }
    }*/

    @PostMapping("/sampleFile")
    public ModelAndView sampleFile(MultipartHttpServletRequest request){
        System.out.println("sampleFile >>>>>");

        Map<String, String[]> map = request.getParameterMap();

        for (String key : map.keySet()) {
            System.out.println("key = " + key);
            String[] values = map.get(key);
            for (String value : values) {
                System.out.println("value = " + value);
            }

        }
        /*map.forEach((key, value) -> {
            System.out.println("key = " + key);
            System.out.println("value = " + value);
        });*/

        System.out.println("==========================================");

        List<MultipartFile> uploadFile = request.getFiles("uploadFile");
        for (MultipartFile multipartFile : uploadFile) {
            System.out.println("multipartFile.oriName = " + multipartFile.getOriginalFilename()
                    + " multipartFile.name = " + multipartFile.getName()
                    + " multipartFile.size = " + multipartFile.getSize());
        }

        System.out.println("==========================================");

        Map<String, MultipartFile> fileMap = request.getFileMap();
        for (String key : fileMap.keySet()) {
            System.out.println("key = " + key);
            System.out.println("fileMap = " + fileMap.get(key));
            System.out.println("fileMap.oriName = " + fileMap.get(key).getOriginalFilename()
                    + " fileMap.name = " + fileMap.get(key).getName()
                    + " fileMap.size = " + fileMap.get(key).getSize());
        }
        /*
        for(MultipartFile file : uploadFile){
            String oriName = file.getOriginalFilename();
            String name = file.getName();
            long size = file.getSize();

            System.out.println("oriName = " + oriName + " name = " + name + " size = " + size);
        }

         */
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("data", "ok");

        return mav;
    }
}
