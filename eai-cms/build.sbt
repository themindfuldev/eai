name := "eai-cms"

version := "1.0.0"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "org.postgresql" % "postgresql" % "9.2-1002-jdbc4",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.8.Final",
  "br.com.casadocodigo.eai" % "eai-modelo" % "1.0.0"   
)     

resolvers += (
    "Local Maven Repository" at "file:///" + Path.userHome.absolutePath + "/.m2/repository"
)

play.Project.playJavaSettings