scala-manifest-info
===================

Small utility for getting the manifest of the jar that a class belongs to in a scala friendly form.

We use this at iseek for getting the manifest info from the assembled jar of our unfiltered app. This is really useful because the sbt-release plugin tags the version of the code into the manifest which gives us a nice automatic way of presenting the version of the code from our API. 

Use it like so: 

```
lazy val manifest = com.benkolera.ManifestInfo.forClass( this.getClass )      
lazy val version = manifest.get("Implementation-Version").getOrElse("NONE")
```
