import com.github.nscala_time.time.Imports._
import common._

package object merger {

  def classification(s: String): (String, String) = {
    val fulldate = s.split("\t").head
    val year = fulldate.split("-").head
    val month = fulldate.split("-")(1)
    val date = fulldate.split("-").last
    val hour = s.split("\t")(1).split(":").head

    val key = year + "/" + month + "/" + date + "/" + hour
    //(year + month + date + hour).toLong
    /*
    if (bydate) {
      //year + "/" + month + "/" + date
      (year + month + date).toLong
    } else {
      //year + "/" + month + "/" + date + "/" + hour
      (year + month + date + hour).toLong
    }
    */
    (key, s)
  } 

  /*    
  def ValueToString(s: List[Any]): String = {
    s.map(e =>
      e match {
        case e:LocalTime => e.toString("HH:mm:ss")
        case e:Int => e
        }).mkString("\031")
  }

  def TimeAndCount(a: List[Any], b: List[Any]): List[Any] = {
    (a, b, (0 until a.length)).zipped.map((i, j, x) =>
      (i, j, x) match {
        //ealier time
        case (i:LocalTime, j:LocalTime, 0) => if (i.isBefore(j)) i else j
        //later time
        case (i:LocalTime, j:LocalTime, 1) => if (i.isAfter(j)) i else j
        //sum counts
        case (i:Int, j:Int, x:Int) => i+j
        })
  }

  def ParseElement(s: String, query: String): String = {
    if (query == "badformat") {
      if (s == "????") "badformat"
      else ""
    } else if (query == "dummy") {
      if (s == "????") "dummy"
      else ""
    } else if (query == "nokey") {
      if (s == "????") "nokey"
      else ""
    } else {
      val target = s + "="
      val sidx = query.indexOf(target)
      if (sidx > -1) {
        val eidx = query.indexOf("&", sidx)
        if (eidx > -1) query.substring(sidx + target.length, eidx) 
        else query.substring(sidx + target.length)
      } else ""
    }
  }
  
  def ParseAllFields(s: Array[String], par:(IpLookups, Boolean)): List[(String, List[Any])] = { 
    val ipLookups = par._1
    val date = s(0)
    val ip = s(4)
    val lookupResult = ipLookups.performLookups(ip)
    val city = lookupResult._1.map(_.city).getOrElse(None).getOrElse("")
    val country = lookupResult._1.map(_.countryName).getOrElse("")

    //concate key and value
    val key = period :: city :: Nil
    val value = time :: time :: Nil
    val keyd = project :: Nil
    val valued = pdata :: Nil
    
    //(key.mkString("\031"), value)
    List((key.mkString("\031"), value), (keyd.mkString("\031"), valued))
  }
  */
}