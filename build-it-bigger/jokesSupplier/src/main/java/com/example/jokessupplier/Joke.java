package com.example.jokessupplier;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class Joke {

    private List<String> jokes = asList(
            "As long as there are tests, there will be prayer in schools.",
            "What did one ocean say to the other ocean? Nothing, they just waved.",
            "A day without sunshine is like, night.",
            "Born free, taxed to death.",
            "A bank is a place that will lend you money, if you can prove that you don’t need it.",
            "What’s the difference between a new husband and a new dog? After a year, the dog is still excited to see you.",
            "Love may be blind, but marriage is a real eye-opener.",
            "When everything’s coming your way, you’re in the wrong lane.",
            "Whenever I find the key to success, someone changes the lock.",
            "I just let my mind wander, and it didn’t come back.");

    public String getJoke() {
        int joke_id = new Random().nextInt(jokes.size());
        return jokes.get(joke_id);
    }
}
