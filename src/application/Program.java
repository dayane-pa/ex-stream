package application;

import entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        String file = "/home/dayane/Documents/Ex.Stream";
        System.out.println("Enter full file path: " + file);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            List<Product> productList = new ArrayList<>();

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                productList.add(new Product(split[0],Double.parseDouble(split[1])));
                line = bufferedReader.readLine();
            }

            double priceValue = productList.stream()
                    .map(p -> p.getPrice())
                    .reduce(0.0, (x, y) -> x + y) / productList.size();

            System.out.printf("Average price: " + "%.2f", priceValue);
            System.out.println();

            Comparator<String> comparator = (name1, name2) -> name1.toUpperCase().compareTo(name2.toUpperCase());

            List<String> nameList = productList.stream()
                    .filter(p -> p.getPrice() < priceValue)
                    .map(p -> p.getName())
                    .sorted(comparator.reversed())
                    .collect(Collectors.toList());

            nameList.forEach(System.out::println);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        scanner.close();
    }
}
