package com.rpa.wawinterpreter;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.rpa.wawinterpreter.inputs.ExampleInputs;
import com.rpa.wawinterpreter.waw.MappedWaw;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AutomationRunner {

    @Before
    public void before() {
        Log.d("Test", "before");
    }

    @Test
    public void test() {
        String wawInput = ExampleInputs.SIMPLE_WAW;
        MappedWaw waw = new MappedWaw(wawInput);
    }

    @After
    public void after() {
        Log.d("Test", "after");
    }
}
