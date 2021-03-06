package com.github.oxo42.stateless4j;


import org.junit.Test;

import com.github.oxo42.stateless4j.triggers.TriggerWithParameters1;
import com.github.oxo42.stateless4j.triggers.TriggerWithParameters2;

import static org.junit.Assert.*;


public class TriggerWithParametersTests {
    @Test
    public void DescribesUnderlyingTrigger() {
        TriggerWithParameters1<String, State, Trigger> twp = new TriggerWithParameters1<String, State, Trigger>(Trigger.X, String.class);
        assertEquals(Trigger.X, twp.getTrigger());
    }

    @Test
    public void ParametersOfCorrectTypeAreAccepted() {
        TriggerWithParameters1<String, State, Trigger> twp = new TriggerWithParameters1<String, State, Trigger>(Trigger.X, String.class);
        twp.validateParameters(new Object[]{"arg"});
    }

    @Test
    public void ParametersArePolymorphic() {
        TriggerWithParameters1<String, State, Trigger> twp = new TriggerWithParameters1<String, State, Trigger>(Trigger.X, String.class);
        twp.validateParameters(new Object[]{"arg"});
    }

    @Test
    public void IncompatibleParametersAreNotValid() {
        try {
            TriggerWithParameters1<String, State, Trigger> twp = new TriggerWithParameters1<String, State, Trigger>(Trigger.X, String.class);
            twp.validateParameters(new Object[]{123});
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void TooFewParametersDetected() {
        try {
            TriggerWithParameters2<String, String, State, Trigger> twp = new TriggerWithParameters2<String, String, State, Trigger>(Trigger.X, String.class, String.class);
            twp.validateParameters(new Object[]{"a"});
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void TooManyParametersDetected() {
        try {
            TriggerWithParameters2<String, String, State, Trigger> twp = new TriggerWithParameters2<String, String, State, Trigger>(Trigger.X, String.class, String.class);
            twp.validateParameters(new Object[]{"a", "b", "c"});
            fail();
        } catch (Exception e) {
        }
    }
}
