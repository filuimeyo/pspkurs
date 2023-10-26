# pspkurs

create materialized view subject_teacher_count as
SELECT id as "subject_id" ,  COALESCE(count,0)  AS count FROM subject
LEFT JOIN (SELECT subject_id, COUNT(teacher_id)  count FROM teacher_subject GROUP BY subject_id)
 as derivedTable on derivedTable.subject_id = subject.id;

CREATE UNIQUE INDEX unique_for_view ON subject_teacher_count (subject_id, count);

CREATE TRIGGER teacher_count
after delete or insert
ON subject
FOR EACH statement
EXECUTE PROCEDURE refresh_mat_view();


CREATE TRIGGER teacher_count2
    after delete or insert
    ON teacher_subject
    FOR EACH statement
EXECUTE PROCEDURE refresh_mat_view();



--drop function refresh_mat_view cascade;



CREATE OR REPLACE FUNCTION refresh_mat_view()
    RETURNS TRIGGER LANGUAGE plpgsql
AS $$
BEGIN
    Refresh Materialized View concurrently subject_teacher_count;
    return null;
END $$;


