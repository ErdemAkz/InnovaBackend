package org.erdem.InnovaCase.repository;

import org.erdem.InnovaCase.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {




    public Transaction saveTransaction(Transaction transaction);

    public Optional<Transaction> findById(String id);



}
