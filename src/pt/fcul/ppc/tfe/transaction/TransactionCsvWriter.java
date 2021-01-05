package pt.fcul.ppc.tfe.transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionCsvWriter {
    private FileWriter writer;

    public TransactionCsvWriter() {
    }

    public void write(ArrayList<Transaction> transactions, String filePath) {
        try {
            writer = new FileWriter(filePath);
            writeLine(Transaction.getHeader());

            for (Transaction transaction : transactions) {
                writeLine(transaction.toCsv());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLine(String line) {
        try {
            writer.write(line);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
