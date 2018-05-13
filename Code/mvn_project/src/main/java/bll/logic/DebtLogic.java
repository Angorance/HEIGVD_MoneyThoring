package bll.logic;

import bll.model.DebtModel;

import java.sql.Date;


/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class DebtLogic extends DebtModel {
	
	public DebtLogic() {}
	public DebtLogic(String description, int creditorID, int debitorID, double amount, Date limitDate) {}
}
