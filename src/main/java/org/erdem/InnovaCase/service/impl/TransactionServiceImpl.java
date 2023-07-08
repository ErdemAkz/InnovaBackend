package org.erdem.InnovaCase.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.erdem.InnovaCase.config.AppProperty;
import org.erdem.InnovaCase.exception.UserNotFoundException;
import org.erdem.InnovaCase.model.Transaction;
import org.erdem.InnovaCase.model.User;
import org.erdem.InnovaCase.repository.TransactionRepository;
import org.erdem.InnovaCase.repository.UserRepository;
import org.erdem.InnovaCase.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private static final Log log = LogFactory.getLog(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final AppProperty appProperty;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AppProperty appProperty) {
        super();
        this.transactionRepository = transactionRepository;
        this.appProperty = appProperty;
    }

    @Override
    public Transaction findById(String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        }else
            throw new UserNotFoundException(id);
    }


    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.saveTransaction(transaction);
    }
}
