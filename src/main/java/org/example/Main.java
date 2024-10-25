package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {

            JSONObject data = loadJSON("input.json");


            JSONArray testCases = data.getJSONArray("testcases");
            for (int i = 0; i < testCases.length(); i++) {
                System.out.println("Processing Test Case " + (i + 1) + ":");

                JSONObject testCase = testCases.getJSONObject(i);


                int n = testCase.getJSONObject("keys").getInt("n");
                int k = testCase.getJSONObject("keys").getInt("k");


                Map<Integer, BigInteger> roots = new HashMap<>();
                for (String key : testCase.keySet()) {
                    if (!key.equals("keys")) {
                        int x = Integer.parseInt(key);
                        int base = testCase.getJSONObject(key).getInt("base");
                        String value = testCase.getJSONObject(key).getString("value");
                        BigInteger y = new BigInteger(value, base);
                        roots.put(x, y);
                    }
                }


                BigInteger secret = calculateConstantTerm(roots, k);
                System.out.println("The constant term (secret) for Test Case " + (i + 1) + " is: " + secret);
                System.out.println("-----------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static JSONObject loadJSON(String fileName) {
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("File " + fileName + " not found in resources.");
            }
            return new JSONObject(new JSONTokener(inputStream));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON file", e);
        }
    }

    public static BigInteger calculateConstantTerm(Map<Integer, BigInteger> roots, int k) {
        BigInteger result = BigInteger.ZERO;

        for (Map.Entry<Integer, BigInteger> entry : roots.entrySet()) {
            int xi = entry.getKey();
            BigInteger yi = entry.getValue();


            BigInteger li = BigInteger.ONE;
            for (Map.Entry<Integer, BigInteger> other : roots.entrySet()) {
                int xj = other.getKey();
                if (xi != xj) {

                    BigInteger xjBig = BigInteger.valueOf(xj);
                    BigInteger xiBig = BigInteger.valueOf(xi);


                    li = li.multiply(xjBig.negate())
                            .divide(xiBig.subtract(BigInteger.valueOf(xj)));
                }
            }


            result = result.add(yi.multiply(li));
        }

        return result.mod(BigInteger.TWO.pow(256)); e
    }

}
