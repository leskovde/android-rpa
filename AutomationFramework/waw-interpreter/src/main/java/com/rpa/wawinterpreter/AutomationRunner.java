package com.rpa.wawinterpreter;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.rpa.wawinterpreter.inputs.ExampleInputs;
import com.rpa.wawinterpreter.waw.MappedWaw;
import com.rpa.wawinterpreter.waw.Variable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;
import java.util.Set;

/**
 * The launcher for the WAW interpreter.
 * <p>
 * This class structured as a test is used create a parametrized automation run.
 */
@RunWith(AndroidJUnit4.class)
public class AutomationRunner {

    @Before
    public void before() {
        Log.println(Log.INFO,"AutomationRunner", "Launching the WAW interpreter");
    }

    @Test
    public void runWawFile() {
        String wawInput = ExampleInputs.SIMPLE_WAW;
        MappedWaw waw = new MappedWaw(wawInput);
        waw.run();
    }

    @After
    public void after() {
        Log.println(Log.INFO,"AutomationRunner", "WAW interpreter finished");

        Set< Map.Entry<String, Variable>> variables = Variable.getAll();
        if (variables.isEmpty()) {
            return;
        }

        Log.println(Log.INFO,"AutomationRunner", "Variables:");
        for (Map.Entry<String, Variable> variable : variables) {
            Log.println(Log.INFO,"AutomationRunner", variable.getKey() + " = " + variable.getValue());
        }
    }
}
