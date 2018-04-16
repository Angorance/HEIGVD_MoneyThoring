package dal.utilisation.orm;

import dal.dalexception.DALException;
import dal.orm.DerbyORM;
import dal.orm.IORM;
import dal.orm.PgORM;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PgORMTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getClientRepository() {

    }

    @Test
    void getBankaccountRepository() {
    }

    @Test
    void openSession() throws DALException {
        IORM orm = new PgORM();
        IORM ormderby = new DerbyORM();

    }

    @Test
    void beginTransaction() {
    }

    @Test
    void rollback() {
    }

    @Test
    void commit() {
    }

}