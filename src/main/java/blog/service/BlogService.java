package blog.service;

import blog.bean.Options;
import blog.bean.TitleOptions;

import java.util.ArrayList;

public interface BlogService {

    ArrayList<Options> getClassify();

    ArrayList<Options> getSubtitle();


    ArrayList<Options> getSubtitleByClassify(String classify);


    boolean insertBlogInfo(String classify,String subtitle,String title);

    ArrayList<TitleOptions> getTitleOptions(String classify);

}
