package no.fintlabs.operator;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import lombok.extern.slf4j.Slf4j;
import no.fintlabs.FlaisKubernetesDependentResource;
import no.fintlabs.FlaisWorkflow;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NamespaceDependentResource extends FlaisKubernetesDependentResource<Namespace, FintorgeratorCrd, FintorgeratorSpec> {

    public NamespaceDependentResource(FlaisWorkflow<FintorgeratorCrd, FintorgeratorSpec> workflow, KubernetesClient kubernetesClient) {
        super(Namespace.class, workflow, kubernetesClient);
    }

    @Override
    protected Namespace desired(FintorgeratorCrd primary, Context<FintorgeratorCrd> context) {
        return new NamespaceBuilder()
                .withNewMetadata()
                .withName(primary.getMetadata().getName())
                .withNamespace("")
                .addToLabels("goldilocks.fairwinds.com/enabled", "true")
                .endMetadata()
                .build();
    }
}
