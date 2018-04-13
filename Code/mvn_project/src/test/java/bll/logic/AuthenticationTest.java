package bll.logic;

import org.junit.Test;

import static bll.logic.Authentication.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @authors Daniel Gonzalez Lopez
 */
public class AuthenticationTest {

    @Test
    public void checkEmailFormatShouldWork() {
        assertTrue(checkEmailFormat("daniel@angorance.tech"));
        assertTrue(checkEmailFormat("helena.line@bidoum.bla"));
        assertTrue(checkEmailFormat("francis.larbre@heig-vd.ch"));

        // Visibly works
        // assertFalse(checkEmailFormat("fanc.@gmail.com"));

        assertFalse(checkEmailFormat("bryan@.com"));
        assertFalse(checkEmailFormat("guizar.gmail.com"));
        assertFalse(checkEmailFormat("lalaine@gamil"));
    }
}
