package no.fintlabs.operator;

import io.javaoperatorsdk.operator.api.reconciler.ControllerConfiguration;
import io.javaoperatorsdk.operator.api.reconciler.dependent.Deleter;
import io.javaoperatorsdk.operator.api.reconciler.dependent.DependentResource;
import lombok.extern.slf4j.Slf4j;
import no.fintlabs.FlaisReconiler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ControllerConfiguration(
        generationAwareEventProcessing = false
)
public class FintorgeratorReconciler extends FlaisReconiler<FintorgeratorCrd, FintorgeratorSpec> {

    public FintorgeratorReconciler(FintorgeratorWorkflow workflow,
                                   List<? extends DependentResource<?,FintorgeratorCrd>> eventSourceProviders,
                                   List<? extends Deleter<FintorgeratorCrd>> deleters) {
        super(workflow, eventSourceProviders, deleters);
    }
}
