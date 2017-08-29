/**
  * App Class for querying Cassandra
  */
object App {

  var IP = "127.0.0.1"
  var filename = ""

  /**
    * Main Method of App Class
    *
    * @param args
    */
  def main(args: Array[String]) {

    get_command_line_parameters(args)
    val dynamo = new MyDynamoDB(IP)
    dynamo.test_dynamodb()

  }

  /**
    * Get command line parameters
    *
    * @param args
    */
  private def get_command_line_parameters(args: Array[String]) = {

    if (args.size > 1) {
      println("args: " + args(0))
      IP = args(0).toString
      filename = args(1).toString

    }

  }


}
