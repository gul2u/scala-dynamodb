--- Create a Namespace ---
CREATE KEYSPACE IF NOT EXISTS events WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

--- Create a Table ---
USE events;
DROP TABLE session;

CREATE TABLE IF NOT EXISTS session (
  session_id text,
  dt timestamp,
  PRIMARY KEY (session_id)
) WITH     COMMENT     = 'sessionization'
       AND COMPACTION  = { 'class' :  'LeveledCompactionStrategy' }
       AND COMPRESSION = { 'sstable_compression' : '' };

DESCRIBE events;


