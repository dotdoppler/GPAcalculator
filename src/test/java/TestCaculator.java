import doppler.LoginSimulator;
import doppler.TextProcessor;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by doppler on 2016/5/6.
 */
public class TestCaculator {

    private LoginSimulator loginSimulator;
    private TextProcessor processor;
    private List[] lists;
    private String text;
    private Logger logger = Logger.getLogger(TestCaculator.class);

    @Before
    public void init(){
        loginSimulator = new LoginSimulator();
        text = loginSimulator.loginAndGetText(null);
        processor  = new TextProcessor(text);
        lists = processor.process();
    }
    @Test
    public void testCaculator(){
        int[] grades = new int[lists[3].size()];
        float[] credits = new float[lists[4].size()];
        for (int i = 0 ,j = 0;i < lists[3].size() & j < lists[4].size();i++,j++){
            String grade_str = lists[3].get(i).toString();
            int grade_int = 0;
            if (grade_str.equals("优"))
                grade_int = 95;
            else if (grade_str.equals("良"))
                grade_int = 85;
            else if (grade_str.equals("中"))
                grade_int = 75;
            else if (grade_str.equals("及格"))
                grade_int = 65;
            else if (grade_str.equals("不及格") || grade_str.equals(""))
                grade_int = 0;
            else {
                grade_int = Integer.parseInt(grade_str);
            }
            grades[i] = grade_int;

            credits[j] = Float.parseFloat(lists[4].get(j).toString());
            }
        int GPA = 0;
        float creditSum = 0;
        float gradePointSum = 0;
        for (int i = 0 ; i < grades.length; i++){
            if (grades[i] >= 60)
                creditSum += credits[i];
        }
        for (int k = 0;k < credits.length; k ++){
            if (grades[k] >= 60)
                gradePointSum += credits[k] * ((grades[k] - 50) / 10.0);
        }
        System.out.println("  GPA   :" + gradePointSum / creditSum);
        System.out.println("已修学分 ：" + creditSum);
    }

}
