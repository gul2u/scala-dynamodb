import scala.io.Source
//import org.apache.hadoop.io.Text
//import org.apache.hadoop.dynamodb.DynamoDBItemWritable
//import org.apache.hadoop.dynamodb.read.DynamoDBInputFormat
//import org.apache.hadoop.dynamodb.write.DynamoDBOutputFormat
//import org.apache.hadoop.mapred.JobConf
//import org.apache.hadoop.io.LongWritable


class MyDynamoDB(IP: String) {


  def initialize_dynamodb(): Unit = {

//    var jobConf = new JobConf(sc.hadoopConfiguration)
//    jobConf.set("dynamodb.input.tableName", "test")
//    jobConf.set("dynamodb.output.tableName", "test")
//
//    jobConf.set("mapred.output.format.class", "org.apache.hadoop.dynamodb.write.DynamoDBOutputFormat")
//    jobConf.set("mapred.input.format.class", "org.apache.hadoop.dynamodb.read.DynamoDBInputFormat")
//
//    var names = sc.hadoopRDD(jobConf, classOf[DynamoDBInputFormat], classOf[Text], classOf[DynamoDBItemWritable])
//    names.count()

  }

  /**
    * Insert data from a flat file
    *
    * @param filename
    */
  def flatFileInserts(filename: String): Unit = {

    println(s"Reading data from flat file: $filename")

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
    * Insert data into DynamoDB
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
