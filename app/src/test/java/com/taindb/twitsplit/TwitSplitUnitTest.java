package com.taindb.twitsplit;

import com.taindb.twitsplit.utils.StringUtils;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C) 2017, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public class TwitSplitUnitTest {
    private static final int CHARACTER_LIMIT = 50;

    /**
     * message< 50
     * Expected: "Android application, Java!"
     */
    @Test
    public void testMessageLengthLessThanCharacterLimit() throws Exception {
        List<String> expected = new ArrayList<>();
        String message = "Android application, Java!";
        String ex1 = "Android application, Java!";
        expected.add(ex1);
        assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));

    }

    /**
     * message == 50
     * Expected: "Android application, Java111111111111111111111111!"
     */
    @Test
    public void testMessageLengthEqualCharacterLimit() throws Exception {
        List<String> expected = new ArrayList<>();
        String message = "Android application, Java111111111111111111111111!";
        String ex1 = "Android application, Java111111111111111111111111!";
        expected.add(ex1);
        assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
    }

    /**
     * Expected:"1/2 I can't believe Tweeter now supports chunking" "2/2 my messages, so I don't have to do it myself."
     */
    @Test
    public void testTheExampleInAssigment() {
        List<String> expected = new ArrayList<>();
        String message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.";
        String ex1 = "1/2 I can't believe Tweeter now supports chunking";
        String ex2 = "2/2 my messages, so I don't have to do it myself.";
        expected.add(ex1);
        expected.add(ex2);
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected: Empty list
     */
    @Test
    public void testAnyMessageLengthExcessThanLimit() {
        List<String> expected = new ArrayList<>();
        String message = "Nguyen Duy Ba Tai, hhaskdjaslkjdlaskjdklasjdlkasjldkjaslkdjsalkjdlkasjdlkasjdlkjsalkdjlksajdlkas skldjlaksdjlkasjdlkasjdskaljdklasjdklasjdljasdjlsajdklasjdlsajdl";
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected: Empty list
     */
    @Test
    public void testSomeWordsIsExcessMaximumCase() {
        List<String> expected = new ArrayList<>();
        String message = "I can't believe Tweeter abcdefghijilmnopqrstuvwxyz1234567890!@#$%^&1111111";
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected: Empty list
     */
    @Test
    public void testPartialSecondLengthAt50() {
        List<String> expected = new ArrayList<>();
        String message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. skldjlaksdjlkasjdlkasjdskaljdklasjdklasjdljasdjlsajdklasjdlsajdl , so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.";
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected: ["1/3 I can't believe Tweeter now supports", "2/3 AndroidJavaAndroidJavaAndroidJ my messages, so", "3/3 I don't have to do it myself."]
     */
    @Test
    public void testCharacterIndexAt50() {
        List<String> expected = new ArrayList<>();
        String message = "I can't believe Tweeter now supports AndroidJavaAndroidJavaAndroidJ my messages, so I don't have to do it myself.";
        String ex1 = "1/3 I can't believe Tweeter now supports";
        String ex2 = "2/3 AndroidJavaAndroidJavaAndroidJ my messages, so";
        String ex3 = "3/3 I don't have to do it myself.";
        expected.add(ex1);
        expected.add(ex2);
        expected.add(ex3);
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected: ["1/5 I can't believe Tweeter now supports",
     * "2/5 chunkingchunking my messages, so I don't have",
     * "3/5 to do it myself. I can't believe Tweeter now",
     * "4/5 supports chunkingchunking my messages, so I",
     * "5/5 don't have to do it myself."]
     */
    @Test
    public void testTotalPartIsGreaterThanEstimate() {
        List<String> expected = new ArrayList<>();
        String message = "I can't believe Tweeter now supports chunkingchunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunkingchunking my messages, so I don't have to do it myself.";
        String ex1 = "1/5 I can't believe Tweeter now supports";
        String ex2 = "2/5 chunkingchunking my messages, so I don't have";
        String ex3 = "3/5 to do it myself. I can't believe Tweeter now";
        String ex4 = "4/5 supports chunkingchunking my messages, so I";
        String ex5 = "5/5 don't have to do it myself.";
        expected.add(ex1);
        expected.add(ex2);
        expected.add(ex3);
        expected.add(ex4);
        expected.add(ex5);
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected: [  "1/11 I can't believe Tweeter now supports chunking",
     * "2/11 my messages, so I don't have to do it myself.",
     * "3/11 I can't believe Tweeter now supports chunking",
     * "4/11 my messages, so I don't have to do it myself.",
     * "5/11 I can't believe Tweeter now supports chunking",
     * "6/11 my messages, so I don't have to do it myself.",
     * "7/11 I can't believe Tweeter now supports chunking",
     * "8/11 my messages, so I don't have to do it myself.",
     * "9/11 I can't believe Tweeter now supports chunking",
     * "10/11 my messages, so I don't have to do it",
     * "11/11 myself."]
     */
    @Test
    public void testOverThanTenParts() {
        List<String> expected = new ArrayList<>();
        String message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.";
        String ex1 = "1/11 I can't believe Tweeter now supports chunking";
        String ex2 = "2/11 my messages, so I don't have to do it myself.";
        String ex3 = "3/11 I can't believe Tweeter now supports chunking";
        String ex4 = "4/11 my messages, so I don't have to do it myself.";
        String ex5 = "5/11 I can't believe Tweeter now supports chunking";
        String ex6 = "6/11 my messages, so I don't have to do it myself.";
        String ex7 = "7/11 I can't believe Tweeter now supports chunking";
        String ex8 = "8/11 my messages, so I don't have to do it myself.";
        String ex9 = "9/11 I can't believe Tweeter now supports chunking";
        String ex10 = "10/11 my messages, so I don't have to do it";
        String ex11 = "11/11 myself.";

        expected.add(ex1);
        expected.add(ex2);
        expected.add(ex3);
        expected.add(ex4);
        expected.add(ex5);
        expected.add(ex6);
        expected.add(ex7);
        expected.add(ex8);
        expected.add(ex9);
        expected.add(ex10);
        expected.add(ex11);
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /***
     * Expected: "My name is Tai, hello everybody
     */
    @Test
    public void testWithMultipleWhitespaceAndNewLine() {
        List<String> expected = new ArrayList<>();
        String message = "My                    name         is \n\n Tai\n\n, hello everybody     ";
        String ex = "My name is Tai, hello everybody";
        expected.add(ex);
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }


    /***
     * Expected: ["1/3 I can't believe Tweeter now supports chunking", "2/3 my messages, I can't believe Tweeter now", "3/3 supports chunking my messages"]
     */
    @Test
    public void testWithMultipleWhitespaceAndNewLine2() {
        List<String> expected = new ArrayList<>();
        String message = "I can't     believe     Tweeter     now     supports     chunking     my     messages\n, I can't     believe     \n\nTweeter     now     supports     chunking     my     messages\n";
        String ex1 = "1/3 I can't believe Tweeter now supports chunking";
        String ex2 = "2/3 my messages, I can't believe Tweeter now";
        String ex3 = "3/3 supports chunking my messages";
        expected.add(ex1);
        expected.add(ex2);
        expected.add(ex3);
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Expected: Empty list
     */
    @Test
    public void testEdgeCases() {
        List<String> expected = new ArrayList<>();
        String message = "abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^& abcdefghijilmnopqrstuvwxyz1234567890!@#$%^&22";
        try {
            assertEqualsList(expected, StringUtils.splitMessage(message, CHARACTER_LIMIT));
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
        }
    }

    private void assertEqualsList(List<String> expected, List<String>  actual) {
        assertEquals(expected.size(),  actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i),  actual.get(i));

        }
    }

    @After
    public void tearDown() {

    }

}
