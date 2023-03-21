import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private Chainblock chainBlock;
    private List<Transaction> transactions;

    @Before
    public void prepare() {
        this.chainBlock = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        this.prepareTransactions();

    }
    private void prepareTransactions(){
        Transaction transaction = new TransactionImpl(0, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 11.20);
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Toshko", 10.00);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Sasho", "Pesho", 11.00);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Toshko", "Sasho", 12.20);
        Transaction transaction4 = new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "Sasho", "Pesho", 10.50);
        transactions.add(transaction);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
    }

    @Test
    public void testAddShouldAddTransaction() {
        chainBlock.add(transactions.get(0));
        assertEquals(1, chainBlock.getCount());
        chainBlock.add(transactions.get(1));
        assertEquals(2, chainBlock.getCount());
    }

    @Test
    public void testAddShouldNotAddDuplicateTransactions(){
        chainBlock.add(transactions.get(0));
        chainBlock.add(transactions.get(0));
        assertEquals(1, chainBlock.getCount());
    }
    @Test
    public void testContainsShouldReturnFalse(){
        chainBlock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainBlock.contains(transactions.get(1));
        assertFalse(chainBlockContainsTransaction);
    }
    @Test
    public void testContainsWithTransactionShouldReturnTrue(){
        chainBlock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainBlock.contains(transactions.get(0));
        assertTrue(chainBlockContainsTransaction);
    }
    @Test
    public void testContainsWithIdShouldReturnFalse(){
        chainBlock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainBlock.contains(transactions.get(1).getId());
        assertFalse(chainBlockContainsTransaction);
    }
    @Test
    public void testContainsWithIdShouldReturnTrue(){
        chainBlock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainBlock.contains(transactions.get(0).getId());
        assertTrue(chainBlockContainsTransaction);
    }

}