/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.xtext.example.mydsl.FmlGenerator", storages = { @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE), @Storage(id = "dir", file = (StoragePathMacros.PROJECT_CONFIG_DIR + "/FmlGeneratorConfig.xml"), scheme = StorageScheme.DIRECTORY_BASED) })
@SuppressWarnings("all")
public class FmlFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState> {
}