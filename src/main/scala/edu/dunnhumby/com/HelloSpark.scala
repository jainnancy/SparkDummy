package edu.dunnhumby.com

import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object HelloSpark extends Serializable {

  @transient lazy val logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {

    if(args.length == 0) {
      logger.error("Usage: HelloSpark filename")
      System.exit(1)
    }

    val sparkConf = new SparkConf()
    sparkConf.set("spark.app.name", "Hello Spark")
    sparkConf.set("spark.master", "local[3]")
    val spark = SparkSession.builder()
      .config(sparkConf)
      .getOrCreate()

    val surveyDF = loadSurveyDF(spark, args(0))

    val df = surveyDF.where("Age >= 25")
      .select("Name", "Age", "Gender")
    println("\n\n\n Transformed data for age > 25 \n")
    df.show()

    val df1 = df.select("Gender").distinct()
    println("\n\n\n Transformed data for Distinct gender")
    df1.show()
    df1.write.format("com.databricks.spark.csv").save("file:///home/TESUKPERT/nancy/myFile.csv")

    println("Finished Hello Spark")
    spark.stop()
  }

  def loadSurveyDF(spark: SparkSession, dataFile: String) : DataFrame = {
    spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(dataFile)
  }
}
