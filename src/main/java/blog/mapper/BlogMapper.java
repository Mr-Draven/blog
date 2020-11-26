package blog.mapper;


import blog.bean.Options;
import blog.bean.TitleOptions;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface BlogMapper {

    ArrayList<Options> getClassify();

    ArrayList<Options> getSubtitle();

    ArrayList<Options> getSubtitleByClassifyId(@Param("parent")int parent);

    int insertBlogInfo(@Param("parent") int parent,@Param("title") String title);

    Integer getTitleId(@Param("title")String title);

    String[] getTitle(@Param("parent")int parent);


}
