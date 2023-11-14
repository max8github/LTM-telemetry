
name := "customers"

organization := "reactivebbq"

version := "0.1-SNAPSHOT"

scalaVersion := "2.13.4"

lazy val akkaHttpVersion = "10.5.1"
lazy val akkaVersion    = "2.8.2"
lazy val akkaManagementVersion =  "1.4.1"
lazy val leveldbVersion = "1.8"
lazy val logbackVersion = "1.2.3"
lazy val scalaTestVersion = "3.2.2"
val gatlingBundleName = "gatling-charts-highcharts-bundle"
val gatlingVersion = "3.9.5"

enablePlugins(GatlingPlugin)

fork := true
ThisBuild / parallelExecution := false

scalacOptions ++= Seq("-deprecation", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster"         % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools"   % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding"% akkaVersion,
  "com.typesafe.akka" %% "akka-persistence"     % akkaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime"   % scalapb.compiler.Version.scalapbVersion % "protobuf",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % leveldbVersion,
  "com.lightbend.akka.management" %% "akka-management" % akkaManagementVersion,
  "com.lightbend.akka.management" %% "akka-management-cluster-http" % akkaManagementVersion,
  "com.typesafe.akka" %% "akka-slf4j"           % akkaVersion,
  "ch.qos.logback"    % "logback-classic"       % logbackVersion,
  "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
  "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
  "org.scalatest"     %% "scalatest"            % scalaTestVersion % Test,
  "org.mockito" % "mockito-all" % "1.10.19" % "it,test",
  "io.gatling" % "gatling-test-framework" % gatlingVersion % Test,
  "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test,
  "com.github.pjfanning" %% "scala-faker" % "0.5.3" % Test,
  "io.spray" %% "spray-json" % "1.3.6" % Test,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion % Test,


  Cinnamon.library.cinnamonCHMetrics,
  Cinnamon.library.cinnamonAkka,
  Cinnamon.library.cinnamonAkkaHttp,
  Cinnamon.library.cinnamonJvmMetricsProducer,
  Cinnamon.library.cinnamonPrometheus,
  Cinnamon.library.cinnamonPrometheusHttpServer
)

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value
)

enablePlugins(JavaAppPackaging)
enablePlugins(Cinnamon)

run / cinnamon := true
test / cinnamon := true
