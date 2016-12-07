package edu.codevita.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author santi
 */
public class PivotTable {

    private static List<String> agregations =Arrays.asList(new String[]{"Sum","Average","Count","Invert"});

    public static void main(String[] args) {
        PivotTable pt = new PivotTable();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of tests: ");

        Integer testNumbers = pt.enterNumber();

        for (int i = 0; i < testNumbers; i++) {
            System.out.print("Agregation: ");
            String agregation = scanner.nextLine();
            while (!pt.isAgregation(agregation)) {
                System.out.print("Agregation: ");
                agregation = scanner.nextLine();
            }

            System.out.print("Column grouping: ");
            Integer columnGrouping = pt.enterNumber() - 1;
            System.out.print("Column agregation: ");
            Integer columnAgregation = pt.enterNumber() - 1;

            String data = scanner.nextLine();
            List<String[]> rows = new ArrayList<>();
            while (!"-1".equals(data)) {
                String[] row = data.split("\\s");
                if ("Sum".equals(agregation) || "Average".equals(agregation)) {
                    try {
                        Double.parseDouble(row[columnAgregation]);
                    } catch (Exception e) {
                        System.out.println("The agregation column must be a number");
                        continue;
                    }
                }
                rows.add(row);
                data = scanner.nextLine();
            }

            Map<String, String> results = new HashMap<>();
            Map<String, Integer> resultsCounter = new HashMap<>();
            Double sum;
            switch (agregation) {
                case "Sum":
                    for (String[] row : rows) {
                        String result = results.get(row[columnGrouping]);
                        if (result == null) {
                            results.put(row[columnGrouping], row[columnAgregation]);
                        } else {
                            sum = Double.parseDouble(row[columnAgregation]) + Double.parseDouble(result.toString());
                            results.put(row[columnGrouping], sum.toString());
                        }
                    }
                    System.out.println();
                    for (Map.Entry<String, String> entry : results.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    }
                    System.out.println(1);
                    break;
                case "Average":
                    for (String[] row : rows) {
                        String result = results.get(row[columnGrouping]);
                        Integer resultCounter = resultsCounter.get(row[columnGrouping]);
                        if (result == null) {
                            results.put(row[columnGrouping], row[columnAgregation]);
                            resultsCounter.put(row[columnGrouping], 1);
                        } else {
                            sum = Double.parseDouble(row[columnAgregation]) + Double.parseDouble(result.toString());
                            results.put(row[columnGrouping], sum.toString());
                            resultsCounter.put(row[columnGrouping], resultCounter + 1);
                        }
                    }
                    System.out.println();
                    for (Map.Entry<String, String> entry : results.entrySet()) {
                        Double average = Double.parseDouble(entry.getValue()) / resultsCounter.get(entry.getKey());
                        results.put(entry.getKey(), average.toString());
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    }
                    System.out.println();

                    break;
                case "Invert":

                    break;
                case "Count":
                    for (String[] row : rows) {
                        Integer resultCounter = resultsCounter.get(row[columnGrouping]);
                        if (resultCounter == null) {
                            resultsCounter.put(row[columnGrouping], 1);
                        } else {
                            resultsCounter.put(row[columnGrouping], resultCounter + 1);
                        }
                    }
                    System.out.println();
                    for (Map.Entry<String, Integer> entry : resultsCounter.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    }
                    System.out.println();
                    break;
            }

        }

    }

    private Integer enterNumber() {
        Scanner scanner = new Scanner(System.in);
        Integer number = -1;
        while (number == -1) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number < 1) {
                    number = -1;
                }
            } catch (Exception e) {
                System.out.println("Type a number higher than 0");
                number = -1;
            }
        }
        return number;
    }

    private boolean isAgregation(String agregation) {
        return agregations.contains(agregation);
    }
}
