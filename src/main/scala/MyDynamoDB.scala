import java.util.Date

import com.datastax.driver.core.Cluster

import scala.io.Source

class MyDynamoDB(IP: String) {

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
    * Insert data into Cassandra
    *
    * @param session_id
    * @param dt
    */
  def insertSessionId(session_id: String, dt: String): Unit = {

      println(s"Inserting $session_id $dt")
  }

  /**
    * Disconnect from DynamoDB
    *
    * @param str
    */
  def disconnect(str: String) {

    println("successfully closed")

  }

}
