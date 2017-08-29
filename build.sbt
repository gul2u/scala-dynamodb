name := "scala-dynamodb"

version := "0.0.1"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-dynamodb" % "1.10.62",
  "io.atlassian.aws-scala" %% "aws-scala-dynamodb"  % "6.0.0"
)


