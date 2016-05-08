package doppler.service.ServiceImpl;

import doppler.constants.GlobalConstants;
import doppler.service.ProcessService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by doppler on 2016/5/8.
 */
@ComponentScan
@Service
public class ProcessServiceImpl implements ProcessService {
    private List[] lists = new ArrayList[7];
    private Logger logger = Logger.getLogger(ProcessServiceImpl.class);

    @Override
    public List[] process(String content) {

        if (content == null) {
            logger.error("no content");
            return null;
        }
        else {
            ArrayList<String> tags = getTagsofLi(content);

            lists[0] = getSemesters(tags);
            lists[1] = getCourseName(tags);
            lists[2] = getTeachers(tags);
            lists[3] = getGrade(cleanCredit(content));
            lists[4] = getCredits(tags);
            lists[5] = getCode(tags);
            logger.info("Process Done");
            return lists;
        }
    }
    /**
     * 匹配所有的<li></li>标签里的文本，并放入List中
     *
     * @return all contents between <li></li>
     */
    private ArrayList<String> getTagsofLi(String content){

        if (content != null) {
            ArrayList<String> items = new ArrayList<String>();

            Pattern pattern = Pattern.compile(GlobalConstants.REGEX_0);
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
        logger.info("parse Semesters");
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHSEM,1);
    }

    /**
     * 匹配每个<li>标签中的课程名称,放入list并返回
     *
     */
    private ArrayList<String> getCourseName(List<String> tags){
        logger.info("parse CourseName");
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHNAME,1);
    }
    /**
     * 匹配老师，放入list并返回
     *
     */
    private ArrayList<String> getTeachers(List<String> tags){
        logger.info("parse teacher");
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHTEACHER,1);
    }

    /**
     * 匹配成绩，放入list并返回
     */
    private ArrayList<String> getGrade(List<String> tags){
        logger.info("parse Grade");
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHGRADE,1);
    }

    /**
     * 匹配成绩类型，放入list并返回
     */
    @Deprecated
    private ArrayList<String> getGradeType(List<String> tags){
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHGRADETYPE,1);
    }
    /**
     * 匹配学分，放入list并返回
     */
    private ArrayList<String> getCredits(List<String> tags){
        logger.info("parse Credits");
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHCREDITS,1);
    }
    /**
     * 匹配绩点，放入list并返回
     */
    @Deprecated
    private ArrayList<String> getGradePoint(List<String> tags){
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHGRADEPOINT,1);
    }
    /**
     * 匹配课程代码，放入list并返回
     */
    private ArrayList<String> getCode(List<String> tags){
        logger.info("parse CourseCode");
        return match(tags,new ArrayList<String>(), GlobalConstants.REGEX_MATCHCODE,1);
    }

    /**
     *替换匹配到的学分
     */
    private ArrayList<String> cleanCredit(String content){
        logger.info("replace credit");
        if (content != null) {
            ArrayList<String> items = new ArrayList<String>();
            ArrayList<String> itemsNoCredits = new ArrayList<String>();
            Pattern pattern = Pattern.compile(GlobalConstants.REGEX_0);
            Matcher matcher = pattern.matcher(content);
            boolean isFind = matcher.find();
            while (isFind) {
                items.add(matcher.group(0));
                isFind = matcher.find();
            }

            String newStr;
            for (String s : items){
                newStr = s.replaceAll(GlobalConstants.REGEX_MATCHCREDITS,"");
                itemsNoCredits.add(newStr);
            }
            return itemsNoCredits;
        }


        return null;
    }

    private ArrayList<String> match(List<String> src,ArrayList<String> des,String regex,int group){
        logger.info("match Regular Expressions");
        if (src != null) {
            for (String s : src) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s);
                boolean isFind = matcher.find();

                while (isFind) {
                    des.add(matcher.group(group) != null ? matcher.group(group) : "");
                    isFind = matcher.find();
                }
            }
            return des;
        }
        return null;
    }
}
