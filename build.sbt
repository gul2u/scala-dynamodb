name := "dynamodb"

version := "0.0.1"

scalaVersion := "2.11.8"

val cassandraDriver = "3.3.0"

libraryDependencies ++= Seq(
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.3.0",
  "com.amazonaws" % "aws-java-sdk-dynamodb" % "1.10.62",
  "io.atlassian.aws-scala" %% "aws-scala-dynamodb"  % "6.0.0"

)


/*
 * References:
 * https://mvnrepository.com/artifact/com.datastax.cassandra/cassandra-driver-core
 */
