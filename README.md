Cassandra Scala Insert  
======================


## Spark
```
import com.datastax.spark.connector.cql.CassandraConnector
import collection.JavaConversions._

val cc = CassandraConnector(sc.getConf)
val select = s"SELECT * FROM cctest.users where userid=?"
val ids = sc.parallelize(1 to 10)
ids.flatMap(id =>
      cc.withSessionDo(session =>
        session.execute(select, id.toInt: java.lang.Integer).iterator.toList.map(row =>
          (row.getInt("userid"), row.getString("username"))))).collect
```
