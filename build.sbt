name := "ElasticSearchWithJson"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.elasticsearch" % "elasticsearch" % "2.2.1",
  "org.elasticsearch.plugin" % "delete-by-query" % "2.2.0",
  "org.scalatest"  %%  "scalatest"  %   "2.2.2" % "test"
)
    