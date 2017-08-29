import scala.io.Source
import org.apache.hadoop.io.Text
import org.apache.hadoop.dynamodb.DynamoDBItemWritable
import org.apache.hadoop.dynamodb.read.DynamoDBInputFormat
import org.apache.hadoop.dynamodb.write.DynamoDBOutputFormat
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.io.LongWritable
import org.apache.spark.sql.SparkSession


class MyDynamoDB(IP: String) {


  def test_dynamodb(): Unit = {

    val spark = SparkSession.builder
        .master("local")
        .appName("spark session dynamodb")
        .getOrCreate()

//    val df = spark.read.option("header","true").csv("src/main/resources/test.csv")
//    df.show()

    val jobConf = new JobConf(spark.sparkContext.hadoopConfiguration)
    jobConf.set("dynamodb.input.tableName", "ProductCatalog")
    jobConf.set("dynamodb.output.tableName", "ProductCatalog")

    jobConf.set("mapred.output.format.class", "org.apache.hadoop.dynamodb.write.DynamoDBOutputFormat")
    jobConf.set("mapred.input.format.class", "org.apache.hadoop.dynamodb.read.DynamoDBInputFormat")

    val products = spark.sparkContext.hadoopRDD(jobConf
                                              , classOf[DynamoDBInputFormat]
                                              , classOf[Text]
                                              , classOf[DynamoDBItemWritable])
    val total = products.count()

    println("-----------------------------------------------")
    println(s"Total number of products: $total")
    products.foreach(println)
    println("-----------------------------------------------")

  }

}
