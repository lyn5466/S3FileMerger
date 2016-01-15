package object common {

  case class Config(
    cfpaths: String = "", 
    bydate: Boolean = false,
    outpath: String = "",
    num: Int = 20 ) 

  val parser = new scopt.OptionParser[Config]("spark") {
    head("spark", "3.x")

    opt[Int]('n', "num") action { (x, c) =>
      c.copy(num = x) } validate { x =>
      if (x > 0) success else failure("Option --num must be >0") } text("num is an integer > 0 for coalesce")
    
    opt[String]('o', "outpath") required() valueName("<path>") action { (x, c) =>
      c.copy(outpath = x) } text("outpath is required")

    opt[String]('c', "cfpaths") valueName("<path1>,<path2>...") action { (x,c) =>
      c.copy(cfpaths = x) } text("cfpaths to process")
   
    opt[Unit]("bydate") action { (_, c) =>
      c.copy(bydate = true) } text("bydate is a flag, which change aggregation rule from hour to date")

    help("help") text("prints this usage text")

    checkConfig { c =>
      if (c.cfpaths.isEmpty || c.outpath.isEmpty) failure("Must give month, cfpaths and outpath!") else success}
    // || c.month == 0
  }

}
