package com.george.chat;

import junit.framework.TestCase;

public class HttpTest extends TestCase
{
    public void testHttpThread()
    {
        HttpThread.httpRequest("����Ц��");
        assertTrue(1 == 1);
    }

}
