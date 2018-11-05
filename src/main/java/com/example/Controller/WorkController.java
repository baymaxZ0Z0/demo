package com.example.Controller;

import com.example.util.FileUtil;
import com.example.Services.Searcher;
import com.example.util.Struct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/demo")
public class WorkController {


    @GetMapping("/uploadfile")
     public String upload() {
        return "index.html";
    }

    Struct[] str;
    //    @PostMapping("/file")
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ResponseBody
    Struct[] uploadImg(@RequestParam("file") MultipartFile file,
                       HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        System.out.println("filename = "+fileName);

        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            System.out.println("路径地址"+filePath+fileName);
            Searcher search = new Searcher();
            str = search.hello(filePath+fileName);


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println(str);
        return str;
    }

}
