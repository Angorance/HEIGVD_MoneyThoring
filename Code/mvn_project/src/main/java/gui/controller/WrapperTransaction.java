package gui.controller;

import bll.logic.IOTransactionLogic;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class WrapperTransaction extends RecursiveTreeObject<WrapperTransaction> {
    IOTransactionLogic transaction;

    public WrapperTransaction(IOTransactionLogic transaction){
        this.transaction = transaction;
    }

    public IOTransactionLogic getTransaction() {
        return transaction;
    }
}
