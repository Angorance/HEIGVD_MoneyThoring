/******* Cron job budget *******/
SELECT cron.schedule('0 0 * * *', $$UPDATE moneythoring.Budget 
									SET startingDate = NOW() 
									SET endingDate = NOW() + gap
									WHERE isRecurrent = true
									AND endingDate < NOW()$$);

/******* Cron job recurrence *******/
SELECT cron.schedule('0 0 * * *', $$$$);

/******* Trigger category (OK) *******/
CREATE OR REPLACE FUNCTION moneythoring.set_uncategorized_category() RETURNS TRIGGER AS $before_category_deleted$
    BEGIN
        UPDATE iOTransaction
		SET Category_id = 1 /* id de la catégorie non-catégorisé */
		WHERE Category_id = OLD.id;
    END
$before_category_deleted$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS before_category_deleted ON moneythoring.category;
CREATE TRIGGER before_category_deleted
    BEFORE DELETE ON moneythoring.category
    FOR EACH ROW
    EXECUTE PROCEDURE moneythoring.set_uncategorized_category();

/******* Trigger default category (OK) *******/
CREATE OR REPLACE FUNCTION moneythoring.create_default_category() RETURNS TRIGGER AS $after_client_created$
    BEGIN
    	INSERT INTO moneythoring.category (name, colour, isdefault, client_id) VALUES ('non-catégorisé', 'GREY', true, NEW.id);
    	RETURN NULL;
    END
$after_client_created$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS after_client_created ON moneythoring.client;
CREATE TRIGGER after_client_created
    AFTER INSERT ON moneythoring.client
    FOR EACH ROW
    EXECUTE PROCEDURE moneythoring.create_default_category();