import java.util.Date

import com.datastax.driver.core.Cluster

import scala.io.Source

class MyDynamoDB(IP: String) {

  val clusterBuilder = Cluster.builder()
  clusterBuilder.addContactPoint(IP)
  clusterBuilder.withPort(9042)
  //optional clusterBuilder.withCredentials("admin", "password")
  val cluster = clusterBuilder.build()
  val session = cluster.connect("events")
  println(session.getCluster().getClusterName + " connection successful\n")

  val page = 1
  var cnt = 0

  /**
    * Insert data from a flat file
    *
    * @param filename
    */
  def flatFileInserts(filename: String): Unit = {

    println(s"Reading Filename = $filename")

    //val buffer = io.Source.fromFile("data/links.dat")

    for (line <- Source.fromFile(filename).getLines) {

      val col = line.split("\\,").map(_.trim)
      //println(s"${col(0)}|${col(1)}")

      if (col.length == 2) {

        if ((col(0).length() > 2) && (col(1).length() > 2)) {
          insertSessionId(col(0), col(1))
        } else {
          println(s"Column is too short = $line")
        }
      }
    }
  }

  /**
    * Get Session DateTime Stamp
    *
    * @param session_id
    * @return
    */
  def getSessionId(session_id: String): Date = {
    val query = String.format("SELECT session_id, dt FROM events.session WHERE session_id = '%s';", session_id)
    val row = session.execute(query).one()
    //      println(row)
    val timestamp: Date = row.getTimestamp("dt")
    timestamp
  }

  /**
    * Insert data into Cassandra
    *
    * @param session_id
    * @param dt
    */
  def insertSessionId(session_id: String, dt: String): Unit = {
    val SQL = String.format("INSERT INTO events.session (session_id, dt) VALUES('%s','%s');", session_id, dt)
    if ((cnt % page) == 0) println(cnt + " " + SQL)
    cnt += 1
    session.execute(SQL)
  }

  /**
    * Disconnect from Cassandra
    *
    * @param str
    */
  def disconnect(str: String) {
    cluster.close()
    session.close()
    println("\n" + str + " successfully closed")
  }

}
