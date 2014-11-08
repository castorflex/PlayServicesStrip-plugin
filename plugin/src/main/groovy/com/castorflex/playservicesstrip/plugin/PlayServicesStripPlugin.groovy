package com.castorflex.playservicesstrip.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ModuleVersionIdentifier
import org.gradle.api.artifacts.result.ResolutionResult
import org.gradle.api.tasks.bundling.Jar

import static com.castorflex.playservicesstrip.plugin.Utils.toCamelCase

class PlayServicesStripPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def hasApp = project.plugins.withType(AppPlugin)
        if (!hasApp) {
            throw new IllegalStateException("'android' plugin required.")
        }

        final def log = project.logger

        project.extensions.create("playservices", PlayServicesStripExtension)

        project.afterEvaluate { ->
            PlayServicesStripExtension extension = project.playservices
            if (extension == null) {
                throw new IllegalArgumentException("No playservices configurations found for plugin playservicesstrip")
            }
            
            // skip work if the plugin isn't enabled.
            if (!extension.shouldStrip) {
                return;
            }
            
            extension.checkConfig()

            Configuration runtimeConfiguration = project.configurations.getByName('compile')
            ResolutionResult resolution = runtimeConfiguration.incoming.resolutionResult
            // Forces resolve of configuration
            ModuleVersionIdentifier module = resolution.getAllComponents().find {
                it.moduleVersion.name.equals("play-services")
            }.moduleVersion
            String prepareTaskName = "prepare${toCamelCase("${module.group} ${module.name} ${module.version}")}Library"
            Task prepareTask = project.tasks.findByName(prepareTaskName)
            File playServiceRootFolder = prepareTask.explodedDir
            // Add the stripping to the existing task that extracts the AAR containing the original classes.jar
            prepareTask.doLast {
                // First create a copy of the GMS classes.jar
                project.copy {
                    from(project.file(new File(playServiceRootFolder, "classes.jar")))
                    into(project.file(playServiceRootFolder))
                    rename { fileName ->
                        fileName = "classes_orig.jar"
                    }
                }
                // Then create a new .jar file containing everything from the first one except the stripped packages
                project.tasks.create(name: "stripPlayServices" + module.version, type: Jar) {
                    destinationDir = playServiceRootFolder
                    archiveName = "classes.jar"
                    from(project.zipTree(new File(playServiceRootFolder, "classes_orig.jar"))) {
                        extension.includes.each { s -> PlayServicesPackages.PACKAGES[s].packageNames.each { p -> include p } }
                        extension.excludes.each { s -> PlayServicesPackages.PACKAGES[s].packageNames.each { p -> exclude p } }
                    }
                }.execute()
                project.delete project.file(new File(playServiceRootFolder, "classes_orig.jar"))
            }
        }
    }
}