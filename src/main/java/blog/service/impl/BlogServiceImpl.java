package blog.service.impl;

import blog.bean.Options;
import blog.bean.TitleOptions;
import blog.mapper.BlogMapper;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;


    public ArrayList<Options> getClassify() {
        return blogMapper.getClassify();
    }

    public ArrayList<Options> getSubtitle() {
        return blogMapper.getSubtitle();
    }

    public ArrayList<Options> getSubtitleByClassify(String classify) {
        Integer titleId = blogMapper.getTitleId(classify);
        if (titleId==null)return null;
        return blogMapper.getSubtitleByClassifyId(titleId);
    }

    public boolean insertBlogInfo(String classify, String subtitle, String title){
        int key = 0;
        if (blogMapper.getTitleId(classify)==null){
            blogMapper.insertBlogInfo(0,classify);
            key = blogMapper.getTitleId(classify);
        }else {
            key = blogMapper.getTitleId(classify);
        }
        if (key<=0)return false;

        if(blogMapper.getTitleId(subtitle)==null){
            blogMapper.insertBlogInfo(key,subtitle);
            key = blogMapper.getTitleId(subtitle);
        }else {
            key = blogMapper.getTitleId(subtitle);
        }
        if (key<=0)return false;
        key = blogMapper.insertBlogInfo(key,title);
        if (key<=0)return false;
        return true;
    }

    public ArrayList<TitleOptions> getTitleOptions(String classify) {
        int classifyId = blogMapper.getTitleId(classify);
        String[] subtitle = blogMapper.getTitle(classifyId);
        ArrayList<TitleOptions> list = new ArrayList<TitleOptions>();
        int subId = 0;
        for (String sub:subtitle){
            TitleOptions titleOptions = new TitleOptions();
            titleOptions.setTitle(sub);
            subId = blogMapper.getTitleId(sub);
            titleOptions.setSubtitle(blogMapper.getTitle(subId));
            list.add(titleOptions);
        }
        return list;
    }
}
