package org.tusofia.vvps.utils;

import org.junit.Assert;
import org.junit.Test;

public class UserIdParserTest {

    @Test
    public void deriveUserIdFromEventDescription_Should_Return_Valid_Id() {
        String id = UserIdParser.deriveUserIdFromEventDescription("The user with id '8429' viewed the course with id '130'.");
        Assert.assertEquals("8429", id);
    }

    @Test
    public void deriveUserIdFromEventDescription_Should_AssertNotNull_OnNullId() {
        Assert.assertThrows(IllegalArgumentException.class, () -> UserIdParser.deriveUserIdFromEventDescription(null));
    }

    @Test
    public void deriveUserIdFromEventDescription_Should_Throw_Exception_On_NotFound_Group() {
        Assert.assertThrows(IllegalArgumentException.class, () -> UserIdParser.deriveUserIdFromEventDescription("fsfvrsververve"));
    }

    @Test
    public void deriveUserIdFromEventDescription_Should_Throw_Exception_On_Non_NumericId() {
        Assert.assertThrows(IllegalArgumentException.class,
                            () -> UserIdParser.deriveUserIdFromEventDescription("The user with id 'abc' viewed the course with id '130'."));
    }
}
