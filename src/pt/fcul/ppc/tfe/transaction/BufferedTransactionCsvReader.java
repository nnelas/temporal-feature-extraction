package pt.fcul.ppc.tfe.transaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTransactionCsvReader {
    private static final String SPLIT_BY = ",";
    private final TransactionMapper transactionMapper;
    private final BufferedReader csvBufferedReader;

    public BufferedTransactionCsvReader(TransactionMapper transactionMapper, String filePath) {
        this.transactionMapper = transactionMapper;
        this.csvBufferedReader = loadFile(filePath);
    }

    private BufferedReader loadFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            return new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Transaction getNextTransaction() {
        if (csvBufferedReader == null) {
            System.out.println("Can't get next Transaction without BufferedRead");
            return null;
        }

        try {
            String line = csvBufferedReader.readLine();
            if (line == null) {
                return null;
            }

            String[] rawTransaction = line.split(SPLIT_BY);
            return transactionMapper.map(rawTransaction);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
