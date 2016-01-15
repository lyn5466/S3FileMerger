import AssemblyKeys._

assemblySettings

name := "S3FileMerger"
version := "2.0"
scalaVersion := "2.10.4"
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.0" % "provided"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.0.0"
libraryDependencies += "com.github.scopt" %% "scopt" % "3.3.0"


mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case x => old(x)
  }
}
