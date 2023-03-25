import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    private void fillChainBlockWithTransactions() {
        transactions.forEach(t -> chainBlock.add(t));
    }

    private void prepareTransactions(){
        Transaction transaction = new TransactionImpl(0, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 11.20);
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Toshko", 10.00);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.UNAUTHORIZED, "Sasho", "Pesho", 11.00);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.FAILED, "Toshko", "Sasho", 12.20);
        Transaction transaction4 = new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "Sasho", "Pesho", 10.50);
        Transaction transaction6 = new TransactionImpl(6, TransactionStatus.SUCCESSFUL, "Toshko", "Sasho", 9.00);
        Transaction transaction5 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 14.00);
        transactions.add(transaction);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);
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

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusShouldThrowForMissingTransaction() {
        chainBlock.changeTransactionStatus(100, TransactionStatus.FAILED);
    }

    @Test
    public void testChangeTransactionStatusShouldChangeStatus() {
        chainBlock.add(transactions.get(0));
        chainBlock.changeTransactionStatus(transactions.get(0).getId(), TransactionStatus.FAILED);
        TransactionStatus newTransactionStatus = chainBlock.getById(transactions.get(0).getId()).getStatus();
        assertEquals(TransactionStatus.FAILED, newTransactionStatus);
    }
    @Test
    public void testGetByIdShouldReturnTransaction(){
        chainBlock.add(transactions.get(0));
        Transaction actual = chainBlock.getById(transactions.get(0).getId());
        assertEquals(transactions.get(0), actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetById_ShouldThrowExceptionForMissingTransaction(){
        chainBlock.add(transactions.get(0));
        chainBlock.add(transactions.get(1));
        chainBlock.add(transactions.get(2));
        chainBlock.getById(200);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionById_ShouldThrowForMissingTransaction(){
        fillChainBlockWithTransactions();
        chainBlock.removeTransactionById(200);
    }
    @Test
    public void testRemoveTransactionById_ShouldRemove(){
        fillChainBlockWithTransactions();
        chainBlock.removeTransactionById(1);
        assertFalse(chainBlock.contains(1));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatus_ShouldThrowIfNoSuchTransactions(){
        fillChainBlockWithTransactions();
        chainBlock.getByTransactionStatus(TransactionStatus.ABORTED);
    }
    @Test
    public void testGetByTransactionStatus_ShouldReturnTransactions(){
        fillChainBlockWithTransactions();
        List<Transaction> expectedTransactions = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()).toList();
        Iterable<Transaction> actualTransactions =
                chainBlock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatus_ShouldThrowIfNoTransactions() {
        fillChainBlockWithTransactions();
        chainBlock.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllSendersWithTransactionStatus_ShouldReturnSortedNames(){
        fillChainBlockWithTransactions();
        List<String> expectedTransactionSenders = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
        Iterable<String> actualTransactionSenders = chainBlock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expectedTransactionSenders, actualTransactionSenders);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatus_ShouldThrowIfNoTransactions() {
        fillChainBlockWithTransactions();
        chainBlock.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllReceiversWithTransactionStatus_ShouldReturnSortedNames(){
        fillChainBlockWithTransactions();
        List<String> expectedTransactionReceivers = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getTo)
                .collect(Collectors.toList());
        Iterable<String> actualTransactionReceivers = chainBlock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expectedTransactionReceivers, actualTransactionReceivers);

    }

    @Test
    public void testGetAllInAmountRange_ShouldReturnTransactions(){
        fillChainBlockWithTransactions();
        Iterable<Transaction> expectedTransactions = transactions.stream()
                .filter(t -> t.getAmount() < 12 && t.getAmount() > 10)
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = chainBlock.getAllInAmountRange(10, 12);

        assertEquals(expectedTransactions, actualTransactions);

    }
    @Test
    public void testGetAllInAmountRange_ShouldReturnEmptyCollectionIfNoSuch(){
        fillChainBlockWithTransactions();
        Iterable<Transaction> expectedTransactions = transactions.stream()
                .filter(t -> t.getAmount() < 1100 && t.getAmount() > 1000)
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = chainBlock.getAllInAmountRange(1100, 1000);

        assertEquals(expectedTransactions, actualTransactions);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRange_ShouldThrowIfNoSuchReceiver(){
        fillChainBlockWithTransactions();

        chainBlock.getByReceiverAndAmountRange("Ivan", 10, 12);


    }

    @Test
    public void testGetByReceiverAndAmountRange_ShouldReturnTransactionsInRangeByReceiverSortedByAmount(){
        fillChainBlockWithTransactions();
       List<Transaction> expectedTransactions = transactions.stream()
               .filter(t -> t.getAmount() < 13 && t.getAmount() > 10)
               .filter(t -> t.getTo().equals("Sasho"))
               .sorted(Comparator.comparing(Transaction::getAmount).reversed()).toList();

        Iterable<Transaction> actualTransactions = chainBlock.getByReceiverAndAmountRange("Sasho", 10, 13);
        assertEquals(expectedTransactions, actualTransactions);

    }
    @Test
    public void testGetAllOrderedByAmountDescendingThenById_ShouldReturnTransactions() {
        fillChainBlockWithTransactions();
        List<Transaction> expectedTransactions = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId)).collect(Collectors.toList());
        Iterable<Transaction> actualTransactions = chainBlock.getAllOrderedByAmountDescendingThenById();
        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrdersByAmountThenById_ShouldThrowNoSuchTransaction(){
        fillChainBlockWithTransactions();

        chainBlock.getByReceiverOrderedByAmountThenById("Ivan");
    }
    @Test
    public void testGetByReceiverOrdersByAmountThenById_ShouldReturnTransactions(){
        fillChainBlockWithTransactions();

       Iterable<Transaction> expected = transactions.stream()
                .filter(t -> t.getTo().equals("Sasho"))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId)).collect(Collectors.toList());
       Iterable<Transaction> actual = chainBlock.getByReceiverOrderedByAmountThenById("Sasho");

       assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrdersByAmountDescending_ShouldThrowForNoSuchTransactions(){
        fillChainBlockWithTransactions();

        chainBlock.getBySenderOrderedByAmountDescending("Ivan");
    }

    @Test
    public void testGetBySenderOrdersByAmountDescending_ShouldReturnSortedTransactions(){
        fillChainBlockWithTransactions();
        Iterable<Transaction> expected = transactions.stream()
                .filter(t -> t.getFrom().equals("Pesho"))
                        .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                                .collect(Collectors.toList());

        Iterable<Transaction> actual = chainBlock.getBySenderOrderedByAmountDescending("Pesho");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmount() {
        fillChainBlockWithTransactions();

        Iterable<Transaction> expected = transactions
                .stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL) &&
                t.getAmount() < 11)
                .collect(Collectors.toList());

        Iterable<Transaction> actual = chainBlock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 11);
        assertEquals(expected, actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescending_ShouldThrowForMissingTrans(){
        fillChainBlockWithTransactions();
        chainBlock.getBySenderAndMinimumAmountDescending("Ivan", 1000);

    }
    @Test
    public void testGetBySenderAndMinimumAmountDescending_ShouldReturnSortedAndFilteredTransactions(){
        fillChainBlockWithTransactions();
        Iterable<Transaction> expected = transactions.stream()
                        .filter(t -> t.getFrom().equals("Pesho") &&
                                t.getAmount() > 10)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                        .collect(Collectors.toList());
        Iterable<Transaction> actual = chainBlock.getBySenderAndMinimumAmountDescending("Pesho", 10);

        assertEquals(expected, actual);
    }
}