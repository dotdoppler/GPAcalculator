//
///**
// * Created by doppler on 2016/5/6.
// */
//public class TestProcessor {
//
//    private LoginSimulator loginSimulator;
//    private TextProcessor processor;
//    private List[] lists;
//    private String text;
//    private Logger logger = Logger.getLogger(TestProcessor.class);
//
//    @Before
//    public void init(){
//       loginSimulator = new LoginSimulator();
//       text = loginSimulator.loginAndGetText(null);
//       processor  = new TextProcessor(text);
//       lists = processor.process();
//   }
//    @Test
//    public void testGetSemesters(){
//
//        Assert.assertEquals(lists[0].size(),66);
//    }
//    @Test
//    public void testGetCourseName(){
//
//        Assert.assertEquals(lists[1].size(),66);
//
//    }
//    @Test
//    public void testGetTeachers(){
//
//        Assert.assertEquals(lists[2].size(),66);
//
//    }
//    @Test
//    public void testGetGrade(){
//
//        Assert.assertEquals(lists[3].size(),66);
//
//    }
//
//    @Test
//    public void testGetCredits(){
//        Assert.assertEquals(lists[4].size(),66);
//    }
//
//    @Test
//    public void testGetCode(){
//       Assert.assertEquals(lists[5].size(),66);
//   }
//}
