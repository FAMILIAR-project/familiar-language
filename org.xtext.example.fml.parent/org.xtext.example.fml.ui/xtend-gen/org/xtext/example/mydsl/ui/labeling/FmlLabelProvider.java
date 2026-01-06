package org.xtext.example.mydsl.ui.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

public class FmlLabelProvider extends DefaultEObjectLabelProvider {
    @Inject
    public FmlLabelProvider(AdapterFactoryLabelProvider delegate) {
        super(delegate);
    }
}
