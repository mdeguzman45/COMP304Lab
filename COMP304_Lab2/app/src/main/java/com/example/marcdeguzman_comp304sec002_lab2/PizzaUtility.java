package com.example.marcdeguzman_comp304sec002_lab2;

public class PizzaUtility {
    public static class PizzaIDGenerator
    {
        private static int nextId;

        public static int GetNext()
        {
            return nextId++;
        }
    }
}
