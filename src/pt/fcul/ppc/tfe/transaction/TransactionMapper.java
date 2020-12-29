package pt.fcul.ppc.tfe.transaction;

public class TransactionMapper {
    public TransactionMapper() {
    }

    public Transaction map(String[] rawTransaction) {
        return new Transaction(Integer.parseInt(rawTransaction[0]),
                Integer.parseInt(rawTransaction[1]), Integer.parseInt(rawTransaction[2]),
                Integer.parseInt(rawTransaction[3]), Integer.parseInt(rawTransaction[4]),
                Integer.parseInt(rawTransaction[5]), Integer.parseInt(rawTransaction[6]),
                Integer.parseInt(rawTransaction[7]), Integer.parseInt(rawTransaction[8]),
                Integer.parseInt(rawTransaction[9]));
    }
}
