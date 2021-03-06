organization  := "com.github.chhsiao90"

version       := "0.1"
scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-http" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-json" % "1.3.2",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.slick" %% "slick" % "3.0.3",
    "com.h2database" % "h2" % "1.3.170",
    "org.slf4j" % "slf4j-nop" % "1.6.4"
  )
}

Revolver.settings


fork in run := true