package merger

import common._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.SparkFiles
import org.apache.spark.HashPartitioner

import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.hadoop.mapred.TextOutputFormat
import org.apache.hadoop.io.Text
import org.apache.hadoop.io.NullWritable

class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat[Any, Any] {
  override def generateActualKey(key: Any, value: Any): Any = 
    NullWritable.get()

  override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String = {
    //val (y, mdh) = key.asInstanceOf[Long].toString.splitAt(4) //.asInstanceOf[String]
    //val (ym, dh) = (y + "/" + mdh).splitAt(7)
    //val (ymd, h) = (ym + "/" + dh).splitAt(10)
    //ymd + "/" + h
    key.asInstanceOf[String]
  }
}

object S3FileMerger {
   
  def main(args: Array[String]) {
    
    parser.parse(args, Config()) match {
      case Some(config) =>
        val sc = new SparkContext(new SparkConf().setAppName("S3 File Merger").set("spark.hadoop.validateOutputSpecs", "false"))
        val logs = sc.textFile(config.cfpaths)
                     .repartition(config.num)
                     .filter(!_.startsWith("#"))
                     .map(classification)
                     .partitionBy(new HashPartitioner(24))
                     .saveAsHadoopFile(config.outpath, classOf[Text], classOf[Text], classOf[RDDMultipleTextOutputFormat], classOf[org.apache.hadoop.io.compress.GzipCodec])
      case None =>
        // arguments are bad, error message will have been displayed
    }
  }
}
