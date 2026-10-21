name := "smart-agri-ingestion"
version := "1.0"
scalaVersion := "2.12.18"

val sparkVersion = "3.5.3"

libraryDependencies ++= Seq(
  "org.apache.spark"            %% "spark-sql"             % sparkVersion % "provided",
  "org.apache.spark"            %% "spark-mllib"           % sparkVersion % "provided",
  "org.apache.spark"            %% "spark-sql-kafka-0-10"  % sparkVersion,
  "org.locationtech.geotrellis" %% "geotrellis-raster"    % "3.7.1",
  "com.github.jai-imageio"      %  "jai-imageio-jpeg2000" % "1.4.0",
  "edu.ucar"                    %  "cdm"                  % "4.5.5"      % "provided",
  "commons-io"                  %  "commons-io"           % "2.15.1",
  "org.apache.kafka"            %  "kafka-clients"        % "3.8.0",
  "org.scalatest"                %% "scalatest"            % "3.2.19"     % Test
)

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "services", _*) => MergeStrategy.concat
  case PathList("META-INF", _*)             => MergeStrategy.discard
  case "reference.conf"                     => MergeStrategy.concat
  case _                                    => MergeStrategy.first
}
