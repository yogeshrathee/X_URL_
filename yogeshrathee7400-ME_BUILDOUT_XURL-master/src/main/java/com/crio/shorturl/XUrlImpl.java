package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;

public class XUrlImpl implements XUrl {

    private Map<String, String> longToShortUrlMap = new HashMap<>();
    private Map<String, String> shortToLongUrlMap = new HashMap<>();
    private Map<String, Integer> longUrlHitCount = new HashMap<>();

    @Override
    public String registerNewUrl(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)) {
            return longToShortUrlMap.get(longUrl);
        }
        
        String shortUrl = generateShortUrl();
        longToShortUrlMap.put(longUrl, shortUrl);
        shortToLongUrlMap.put(shortUrl, longUrl);
        longUrlHitCount.put(longUrl, 0);
        return shortUrl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if (shortToLongUrlMap.containsKey(shortUrl)) {
            return null; // Short URL already exists
        }
        
        longToShortUrlMap.put(longUrl, shortUrl);
        shortToLongUrlMap.put(shortUrl, longUrl);
        longUrlHitCount.put(longUrl, 0);
        return shortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        if (shortToLongUrlMap.containsKey(shortUrl)) {
            String longUrl = shortToLongUrlMap.get(shortUrl);
            longUrlHitCount.put(longUrl, longUrlHitCount.get(longUrl) + 1);
            return longUrl;
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        return longUrlHitCount.getOrDefault(longUrl, 0);
    }

    @Override
    public String delete(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)) {
            String shortUrl = longToShortUrlMap.get(longUrl);
            longToShortUrlMap.remove(longUrl);
            shortToLongUrlMap.remove(shortUrl);
            return shortUrl;
        }
        return null;
    }

    private String generateShortUrl() {
        // Generate a 9-character alphanumeric string for the short URL
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl = new StringBuilder("http://short.url/");
        for (int i = 0; i < 9; i++) {
            int index = (int) (Math.random() * characters.length());
            shortUrl.append(characters.charAt(index));
        }
        return shortUrl.toString();
    }
}