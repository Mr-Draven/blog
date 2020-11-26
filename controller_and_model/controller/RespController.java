package blog.controller;

import blog.bean.TitleOptions;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class RespController {
    @Autowired
    BlogService blogService;

    @GetMapping("/api/dashboard")
    public Object getDashboard(){
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("status","ok");
        dataMap.put("options",blogService.getClassify());
        return dataMap;
    }


    /*---------------------------------博客界面的-------------------------------------------------*/
    @RequestMapping("/api/getTitleOptions")
    public Object getBlogContent(@RequestParam("classify")String classify) throws IOException {
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("titleOptions",blogService.getTitleOptions(classify));
        return dataMap;
    }

    private String doSomething(String title) throws IOException {
        File file = new File(title+".md");
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        String line = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine())!=null){
            stringBuffer.append(line+"\n");
        }
        bufferedReader.close();
        return stringBuffer.toString();
    }

    @GetMapping("/api/fetchArticle")
    public Object fetchArticle(@RequestParam("title")String title) throws IOException {
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("status","ok");
        dataMap.put("article",doSomething(title));
        return dataMap;
    }

    @RequestMapping("/api/storeContent")
    public String storeContent(@RequestParam("title")String title,
                               @RequestParam("content")String content) throws IOException {
        File file = new File(title+".md");
        BufferedWriter  bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bufferedWriter.write(content);
        bufferedWriter.close();
        return "ok";
    }

    /*---------------------------------下面是添加界面的数据-------------------------------------------------------*/
    @PostMapping("/api/addArticle")
    public String addArticle(@RequestParam("content")String content,
                             @RequestParam("classify")String classify,
                             @RequestParam("subtitle")String subTitle,
                             @RequestParam("title")String title) throws IOException {


        if (!blogService.insertBlogInfo(classify,subTitle,title))return "error";

        File file = new File(title + ".md");
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedWriter  bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bufferedWriter.write(content);
        bufferedWriter.close();
        return "ok";
    }

    @GetMapping("/api/getAddPageInitInfo")
    public Object getAddPageInitInfo(){
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("status","ok");
        dataMap.put("classify",blogService.getClassify());
        return dataMap;
    }

    @GetMapping("/api/getAddPageSubtitleInfo")
    public Object getAddPageSubtitle(@RequestParam("classify")String classify){
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("status","ok");
        dataMap.put("subtitle",blogService.getSubtitleByClassify(classify));
        return dataMap;
    }


}
