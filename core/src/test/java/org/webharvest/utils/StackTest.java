/*
 Copyright (c) 2006-2007, Vladimir Nikic
 All rights reserved.

 Redistribution and use of this software in source and binary forms,
 with or without modification, are permitted provided that the following
 conditions are met:

 * Redistributions of source code must retain the above
   copyright notice, this list of conditions and the
   following disclaimer.

 * Redistributions in binary form must reproduce the above
   copyright notice, this list of conditions and the
   following disclaimer in the documentation and/or other
   materials provided with the distribution.

 * The name of Web-Harvest may not be used to endorse or promote
   products derived from this software without specific prior
   written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.

 You can contact Vladimir Nikic by sending e-mail to
 nikic_vladimir@yahoo.com. Please include the word "Web-Harvest" in the
 subject line.
 */

package org.webharvest.utils;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: awajda
 * Date: Sep 21, 2010
 * Time: 1:04:17 AM
 */
public class StackTest {

    Stack<String> stack = new Stack<String>();

    @Test
    public void testPush() throws Exception {
        stack.push("a");
        stack.push("b");
        stack.push("b"); // duplicate should be allowed
        stack.push("c");

        assertArrayEquals(new String[]{"a", "b", "b", "c"}, stack.getList().toArray());
    }

    @Test
    public void testReplaceTop() throws Exception {
        stack.push("a");
        stack.push("b");
        stack.replaceTop("c");

        assertArrayEquals(new String[]{"a", "c"}, stack.getList().toArray());
        stack.replaceTop("d");

        stack.pop();
        stack.replaceTop("e");
        assertArrayEquals(new String[]{"e"}, stack.getList().toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReplaceTop_emptyStack() throws Exception {
        stack.replaceTop("a");
    }

    @Test
    public void testPop() throws Exception {
        stack.push("a");
        stack.push("b");
        stack.push("c");

        stack.pop();
        assertArrayEquals(new String[]{"a", "b"}, stack.getList().toArray());
        stack.pop();
        assertArrayEquals(new String[]{"a"}, stack.getList().toArray());
        stack.pop();
        assertArrayEquals(new String[]{}, stack.getList().toArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPop_emptyStack() throws Exception {
        stack.pop();
    }

    @Test
    public void testPeek() throws Exception {
        stack.push("a");
        assertEquals("a", stack.peek());

        stack.push("b");
        assertEquals("b", stack.peek());

        stack.push("c");
        assertEquals("c", stack.peek());
        assertEquals("c", stack.peek()); // repeatable operation

        stack.pop();
        assertEquals("b", stack.peek());

        stack.pop();
        assertEquals("a", stack.peek());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeek_emptyStack() throws Exception {
        stack.peek();
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(stack.isEmpty());
        stack.push("a");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, stack.size());
        stack.push("a");
        assertEquals(1, stack.size());
        stack.push("b");
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
    }
}