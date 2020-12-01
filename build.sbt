lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github.daniel0611",
      scalaVersion := "2.13.4"
    )),
    name := "advent-of-code-2020"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test
