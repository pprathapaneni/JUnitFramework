package com.qa.junit.testcases;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestResultLogger.class)
public class TestWatcher {
	
	 @Test
	    void givenFalseIsTrue_whenTestAbortedThenCaptureResult() {
	        Assumptions.assumeTrue(false);
	    }

	    @Disabled
	    @Test
	    void givenTrueIsTrue_whenTestDisabledThenCaptureResult() {
	        Assertions.assertTrue(true);
	    }

	    @Test
	    void givenTrueIsTrue_whenTestAbortedThenCaptureResult() {
	        Assumptions.assumeTrue(true);
	    }

	    @Disabled("This test is disabled")
	    @Test
	    void givenFailure_whenTestDisabledWithReason_ThenCaptureResult() {
	        fail("Not yet implemented");
	    }


}
