package gui.controller;

import java.awt.event.ActionEvent;

public interface IController{
    void formReturn(Object result);
    void deleteItem(Object toDelete);
    void modifyItem(Object toUpdated);
}
