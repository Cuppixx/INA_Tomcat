package org.ina.p1;

import java.util.Scanner;

/**
 * Main class for the RSS Feed Reader.
 */
public class Main {
    /**
     * The main method for the RSS Feed Reader.
     * It prompts the user to enter a URL, finds the RSS feed for the URL,
     * and interprets the feed.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("RRS-System");

        // Create a new RSSFeed object
        RSSFeed rssFeed = new RSSFeed();

        // Create a new Scanner object for reading input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a valid URL (with RSS Feed):");
        // Read the URL from the user
        String url = scanner.nextLine();

        // Find the RSS feed for the URL
        String feedUrl = rssFeed.feedFinder(url);

        // Interpret the RSS feed
        rssFeed.feedInterpreter(feedUrl, true, true, true);
    }
}
