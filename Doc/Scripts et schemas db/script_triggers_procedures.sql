/******* Cron job budget *******/
SELECT cron.schedule('0 0 * * *', $$UPDATE moneythoring.Budget 
									SET startingDate = NOW() 
									SET endingDate = NOW() + gap
									WHERE isRecurrent = true
									AND endingDate < NOW()$$);

/******* Cron job recurrence *******/
SELECT cron.schedule('0 0 * * *', $$$$);

/******* Trigger category *******/
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