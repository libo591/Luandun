package com.boboeye.luandun;

import com.robotium.solo.Solo;

/**
 * Created by libo_591 on 15/9/15.
 */
public class MainActivityTest extends android.test.ActivityInstrumentationTestCase2 {
    private static final String pkg = "com.boboeye.luandun";
    private static final String ac_class = "com.boboeye.luandun.MainActivity";

    private Solo solo;
    private static Class clz;
    static{
        try {
            clz = Class.forName(ac_class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public MainActivityTest(){
        super(pkg,clz);
    }

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(),getActivity());
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_some(){
        assertTrue(true);
    }

}
