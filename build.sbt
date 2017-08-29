name := "scala-dynamodb"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    "com.sparkjava" % "spark-core" % "2.6.0"
  , "org.apache.spark" % "spark-sql_2.11" % "2.2.0" % "provided"
  , "com.amazonaws" % "aws-java-sdk-dynamodb" % "1.10.62"
  , "io.atlassian.aws-scala" %% "aws-scala-dynamodb"  % "6.0.0"

)


