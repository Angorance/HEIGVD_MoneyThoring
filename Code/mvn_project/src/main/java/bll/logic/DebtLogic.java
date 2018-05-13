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
	public DebtLogic(String nom, int creatorID, int otherUserID, boolean isIncome, double amount, Date limitDate) {}
}
