package no.fintlabs.operator;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.KubernetesDependent;
import lombok.extern.slf4j.Slf4j;
import no.fintlabs.FlaisKubernetesDependentResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KubernetesDependent(labelSelector = "app.kubernetes.io/managed-by=flaiserator")
public class NamespaceDependentResource extends FlaisKubernetesDependentResource<Namespace, FintorgeratorCrd, FintorgeratorSpec> {

    public NamespaceDependentResource(FintorgeratorWorkflow workflow, KubernetesClient kubernetesClient) {
        super(Namespace.class, workflow, kubernetesClient);
    }

    @Override
    protected Namespace desired(FintorgeratorCrd primary, Context<FintorgeratorCrd> context) {
        return new NamespaceBuilder()
                .withNewMetadata()
                .withName("rogfk-no")
                .withNamespace(primary.getMetadata().getNamespace())
                .addToLabels("goldilocks.fairwinds.com/enabled:", "true")
                .endMetadata()
                .build();
    }
}
