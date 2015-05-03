package com.george.chat;

import junit.framework.TestCase;

public class HttpTest extends TestCase
{
    public void testHttpThread()
    {
        HttpThread.httpRequest("½²¸öÐ¦»°");
        assertTrue(1 == 1);
    }

}
