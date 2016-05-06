package doppler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by doppler on 2016/5/5.
 */
public class TextProcessor {
    private String content;
    private List[] lists;
    public TextProcessor(String rowContent){
        lists = new  ArrayList[7];
        this.content = rowContent;
    }

    /**
     * 调用方法，进行匹配，返回一个list数组
     *
     */
    public List[] process(){
        if (content == null)
            return null;
        else {
            ArrayList<String> tags = getTagsofLi(content);
            lists[0] = getSemesters(tags);
            lists[1] = getCourseName(tags);
            lists[2] = getTeachers(tags);
            lists[3] = getGrade(tags);
            lists[4] = getCredits(tags);
            lists[5] = getGradePoint(tags);

            return lists;
        }
    }

    /**
     * 匹配所有的<li></li>标签里的文本，并放入List中
     *
     * @return all contents between <li></li>
     */
    private ArrayList<String> getTagsofLi(String content){
        content = this.content;
        if (content != null) {
            ArrayList<String> items = new ArrayList<String>();

            Pattern pattern = Pattern.compile(Constants.REGEX_0);
            Matcher matcher = pattern.matcher(content);
            boolean isFind = matcher.find();
            while (isFind){
                items.add(matcher.group(0));
                isFind = matcher.find();
            }
            return items;
        }
        return null;
    }
    /**匹配每个<li>标签中的学期,放入list并返回
     *
     */
    private ArrayList<String> getSemesters(List<String> tags){
        return match(tags,new ArrayList<String>(),Constants.REGEX_MATCHSEM,1);
    }

    /**
     * 匹配每个<li>标签中的课程名称,放入list并返回
     *
     */
    private ArrayList<String> getCourseName(List<String> tags){
       return match(tags,new ArrayList<String>(),Constants.REGEX_MATCHNAME,1);
    }
    /**
     * 匹配老师，放入list并返回
     *
     */
    private ArrayList<String> getTeachers(List<String> tags){
        return match(tags,new ArrayList<String>(),Constants.REGEX_MATCHTEACHER,1);
    }

    /**
     * 匹配成绩，放入list并返回
     */
    private ArrayList<String> getGrade(List<String> tags){
        return match(tags,new ArrayList<String>(),Constants.REGEX_MATCHGRADE,1);
    }

    /**
     * 匹配成绩类型，放入list并返回
     */
    @Deprecated
    private ArrayList<String> getGradeType(List<String> tags){
        return match(tags,new ArrayList<String>(),Constants.REGEX_MATCHGRADETYPE,1);
    }
    /**
     * 匹配学分，放入list并返回
     */
    private ArrayList<String> getCredits(List<String> tags){
        return match(tags,new ArrayList<String>(),Constants.REGEX_MATCHCREDITS,1);
    }
    /**
     * 匹配绩点，放入list并返回
     */
    private ArrayList<String> getGradePoint(List<String> tags){
        return match(tags,new ArrayList<String>(),Constants.REGEX_MATCHGRADEPOINT,1);
    }


    private ArrayList<String> match(List<String> src,ArrayList<String> des,String regex,int group){
        for (String s : src){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);
            boolean isFind = matcher.find();

            while (isFind){
                des.add(matcher.group(group) == null ? matcher.group(group) : "" );
                isFind = matcher.find();
            }
        }
        return des;
    }


}
