# Structurizr for Java with PlantUML file export example

This repo contains a class `StructurizrPlantUmlFileExporter.java` that enhances `StructurizrPlantUMLExporter.java`
by adding the possibility to write diagrams out to PlantUML files via the method `exportToFile(Workspace workspace, String outputPath)`.

Additionally, it showcases in `App.java` an example of how to create a **Structurizr Workspace** in Java
and write it to PlantUML files via the enhancement mentioned above.

**Note**: This project was initialized via `gradle init --type java-application` and has been adapted from there on. 