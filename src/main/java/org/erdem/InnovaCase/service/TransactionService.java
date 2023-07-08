package org.erdem.InnovaCase.service;

import org.erdem.InnovaCase.model.Transaction;
import org.erdem.InnovaCase.model.User;

import java.util.List;

public interface TransactionService {


    Transaction saveTransaction(Transaction transaction);

    Transaction findById(String id);

}
