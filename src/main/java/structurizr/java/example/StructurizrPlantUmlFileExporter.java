package structurizr.java.example;

import com.structurizr.Workspace;
import com.structurizr.export.plantuml.StructurizrPlantUMLExporter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * This class enhances {@link com.structurizr.export.plantuml.StructurizrPlantUMLExporter}
 * by adding {@link structurizr.java.example.StructurizrPlantUmlFileExporter#exportToFile(Workspace, String)}.
 * This method takes an output path as param and writes the given workspace to .puml files.
 * The code is largely taken from @see <a href="https://github.com/structurizr/cli/blob/master/src/main/java/com/structurizr/cli/export/ExportCommand.java">structurizr-cli's run method</a>
 */
public class StructurizrPlantUmlFileExporter extends StructurizrPlantUMLExporter {

    public void exportToFile(Workspace workspace, String outputPath) throws Exception {
        var diagrams = super.export(workspace);
        var workspaceId = workspace.getId();

        for (var diagram : diagrams) {
            var file = new File(outputPath, String.format("%s-%s.%s", prefix(workspaceId), diagram.getKey(), diagram.getFileExtension()));
            writeToFile(file, diagram.getDefinition());

            if (diagram.getLegend() != null) {
                file = new File(outputPath, String.format("%s-%s-key.%s", prefix(workspaceId), diagram.getKey(), diagram.getFileExtension()));
                writeToFile(file, diagram.getLegend().getDefinition());
            }

            if (!diagram.getFrames().isEmpty()) {
                int index = 1;
                for (var frame : diagram.getFrames()) {
                    file = new File(outputPath, String.format("%s-%s-%s.%s", prefix(workspaceId), diagram.getKey(), index, diagram.getFileExtension()));
                    writeToFile(file, frame.getDefinition());
                    index++;
                }
            }
        }
    }

    private String prefix(long workspaceId) {
        if (workspaceId > 0) {
            return "structurizr-" + workspaceId;
        } else {
            return "structurizr";
        }
    }

    private void writeToFile(File file, String content) throws Exception {
        var writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
        writer.write(content);
        writer.flush();
        writer.close();
    }

}
