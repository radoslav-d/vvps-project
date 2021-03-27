package org.tusofia.vvps.utils;

import org.junit.Assert;
import org.junit.Test;

public class CheckUtilsTest {

    @Test
    public void assertNotNull_Should_ThrowException_OnNull() {
        Object testObj = null;
        Assert.assertThrows(IllegalArgumentException.class, () -> CheckUtils.assertNotNull(testObj, "Test"));
    }

    @Test
    public void assertNotNull_Should_Not_ThrowException_OnNonNull() {
        Object testObj = new Object();
        CheckUtils.assertNotNull(testObj, "Test");
    }
}
