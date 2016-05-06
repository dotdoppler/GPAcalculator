import doppler.LoginSimulator;
import doppler.TextProcessor;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by doppler on 2016/5/6.
 */
public class TestProcessor {

    private LoginSimulator loginSimulator;
    private TextProcessor processor;
    private List[] lists;
    private String text;
    private Logger logger = Logger.getLogger(TestProcessor.class);

    @Before
    public void init(){
       loginSimulator = new LoginSimulator();
       text = loginSimulator.loginAndGetText(null);
       processor  = new TextProcessor(text);
       lists = processor.process();
   }
    @Test
    public void testGetSemesters(){

        Assert.assertEquals(lists[0].size(),62);
    }
    @Test
    public void testGetCourseName(){

        Assert.assertEquals(lists[1].size(),62);

    }
    @Test
    public void testGetTeachers(){

        Assert.assertEquals(lists[2].size(),62);

    }
    @Test
    public void testGetGrade(){

        Assert.assertEquals(lists[3].size(),62);

    }

    @Test
    public void testGetCredits(){
        Assert.assertEquals(lists[4].size(),62);
    }
    @Test
    public void testPrint(){
                for (Object o :lists[3])
           logger.info(o.toString().length());
    }
    @Test
    public  void testStr(){
        System.out.println(lists[3].get(0).toString().equals("中"));


    }
}
