package pt.fcul.ppc.tfe.transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionCsvReader {
    private static final String SPLIT_BY = ",";
    private final TransactionMapper transactionMapper;

    public TransactionCsvReader(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    public ArrayList<Transaction> read(String filePath) {
        String line = "";
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] rawTransaction = line.split(SPLIT_BY);
                Transaction transaction = transactionMapper.map(rawTransaction);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
