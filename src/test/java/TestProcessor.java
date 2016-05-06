import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import doppler.*;
import java.util.List;
/**
 * Created by doppler on 2016/5/6.
 */
public class TestProcessor {
    LoginSimulator loginSimulator;
    TextProcessor processor;
    List[] lists;
    String text;
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
    public void testGetGradePoint(){
        Assert.assertEquals(lists[5].size(),62);
    }
}
