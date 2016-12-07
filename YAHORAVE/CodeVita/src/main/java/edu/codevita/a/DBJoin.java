package edu.codevita.a;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 */
public class DBJoin {

    private static int numberColumnIdFileOne = 3;
    private static int numberColumnIdFileTwo = 1;

    public static void main(String[] args) throws IOException {
        DBJoin dBJoin = new DBJoin();
        Scanner entradaEscaner = new Scanner(System.in);
        String pathFileOne = entradaEscaner.nextLine();
        String pathFileTwo = entradaEscaner.nextLine();

        List<String[]> recordsFileOne = dBJoin.getRecords(pathFileOne);
        List<String[]> recordsFileTwo = dBJoin.getRecords(pathFileTwo);
        List<String[]> resultJoin = dBJoin.joinRecords(recordsFileOne, recordsFileTwo, 2, 0);

        Collections.sort(resultJoin, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });
        
        for (String[] record : resultJoin) {
            for (String data : record) {
                System.out.print(data + ", ");
            }
            System.out.println("");
        }
    }

    private List<String[]> getRecords(String path) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        List<String[]> records = new ArrayList<>();
        String record = "";
        while ((record = br.readLine()) != null) {
            records.add(record.split(","));
        }
        return records;
    }

    private List<String[]> joinRecords(List<String[]> recordsFileOne, List<String[]> recordsFileTwo, int joinColumnFileOne, int joinColumnFileTwo) {
        List<String[]> recordsJoin = new ArrayList<>();
        for (String[] dataFileOne : recordsFileOne) {
            String[] dataFileTwo = getDataFileTwo(recordsFileTwo, dataFileOne[joinColumnFileOne], joinColumnFileTwo);
            if (dataFileTwo != null) {
                recordsJoin.add(new String[]{dataFileTwo[0], dataFileTwo[1], dataFileOne[1], dataFileOne[5], dataFileOne[3], dataFileOne[4], dataFileOne[6]});
            }
        }
        return recordsJoin;
    }

    private String[] getDataFileTwo(List<String[]> recordsFileTwo, String id, int joinColumnFileTwo) {
        for (String[] record : recordsFileTwo) {
            if (id.equals(record[joinColumnFileTwo])) {
                return record;
            }
        }
        return null;
    }

    private void orderRecords(List<String[]> records, int columnId) {
        for (String[] record : records) {
        }
    }
}
