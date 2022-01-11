package com.example.marcdeguzman_comp304sec002_lab2;

public class Helper {
    public static class Validate {
        // check if we have a blank input
        public static boolean isBlank(String field) {
            String tempField = field.trim();
            return tempField.isEmpty();
        }
    }
}
