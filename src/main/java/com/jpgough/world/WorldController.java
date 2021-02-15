package com.jpgough.world;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class WorldController {

    private List<String> words = new ArrayList<>(70_000);
    private Random randomGenerator = new Random();

    public WorldController() {
        try (InputStream in = getClass().getResourceAsStream("/wordlist.txt")) {
            words = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/message")
    public Response getMessage() {
        return new Response("World");
    }

    @GetMapping("/message/random")
    public Response getRandomMessage() {
        return new Response(words.get(randomGenerator.nextInt(words.size())));
    }

}
