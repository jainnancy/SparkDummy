name := "SparkDummy"

version := "0.1"

scalaVersion := "2.11.12"

val sparkVersion = "2.4.3"

val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % Test
)

libraryDependencies ++= sparkDependencies ++ testDependencies
