package org.erdem.InnovaCase.repository.impl;

import org.erdem.InnovaCase.model.Transaction;
import org.erdem.InnovaCase.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{

    private final MongoOperations mongoOperations;

    @Autowired
    public TransactionRepositoryImpl(MongoOperations mongoOperations) {
        //Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }


    @Override
    public Optional<Transaction> findById(String id) {
        Transaction d = this.mongoOperations.findOne(new Query(Criteria.where("_id").is(id)), Transaction.class);
        Optional<Transaction> transaction = Optional.ofNullable(d);
        return transaction;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        this.mongoOperations.save(transaction);
        return findById(transaction.getId()).get();
    }

}
