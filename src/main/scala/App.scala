/**
  * App Class for querying Cassandra
  */
object App {

  var IP = "127.0.0.1"
  var filename = ""

  /**
    * Main Method of App Class
    * @param args
    */
  def main(args: Array[String]) {

    get_command_line_parameters(args)
    get_session_id("4e73c248-b421-43e7-a736-fa9d370e8c7c")

    //    batch_file_test(IP, filename)

  }

  /**
    * Get session id
    * @param session_id
    */
  private def get_session_id(session_id: String) = {

    try {

      val cassandra = new Sessionization(IP)
      val dt1 = cassandra.getSessionId(session_id)
      val dt2 = dt1.getTime()

      println(s"DateTime String = $dt1")
      println(s"DateTime Long   = $dt2")

      cassandra.disconnect("cassandra.disconnect")

    } catch {
      case e: Exception => println("exception caught: " + e);
    }

  }


  /**
    * Get command line parameters
    * @param args
    */
  private def get_command_line_parameters(args: Array[String]) = {

    if (args.size > 1) {
      println("args: " + args(0))
      IP = args(0).toString
      filename = args(1).toString

    }

  }

  /**
    * Testing Batch File
    * @param filename
    */
  private def batch_file_test(filename: String) = {

    try {

      val cassandra = new Sessionization(IP)
      cassandra.flatFileInserts(filename)
      cassandra.disconnect("cassandra.disconnect")

    } catch {
      case e: Exception => println("exception caught: " + e);
    }

  }

}
