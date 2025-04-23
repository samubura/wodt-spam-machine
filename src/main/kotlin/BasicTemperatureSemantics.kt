package it.wldt

import io.github.webbasedwodt.model.ontology.DigitalTwinSemantics
import io.github.webbasedwodt.model.ontology.rdf.*
import it.wldt.core.state.DigitalTwinStateAction
import it.wldt.core.state.DigitalTwinStateProperty
import it.wldt.core.state.DigitalTwinStateRelationship
import it.wldt.core.state.DigitalTwinStateRelationshipInstance
import java.net.URI
import java.util.*


class BasicTemperatureSemantics : DigitalTwinSemantics {

    override fun getDigitalTwinTypes(): MutableList<RdfClass> {
        return mutableListOf(
            RdfClass(
                URI.create("http://example.org/ontology#TemperatureSensor"),
            )
        )
    }

    override fun getDomainTag(property: DigitalTwinStateProperty<*>?): Optional<RdfUriResource> {
        return if (property?.key == SpamPhysicalAdapter.TEMPERATURE_PROPERTY) {
            Optional.of(
                RdfUriResource(
                    URI.create("http://example.org/ontology#hasTemperature")
                )
            )
        } else {
            Optional.empty()
        }
    }

    override fun getDomainTag(relationship: DigitalTwinStateRelationship<*>?): Optional<RdfUriResource> {
        return Optional.empty();
    }

    override fun getDomainTag(action: DigitalTwinStateAction?): Optional<RdfUriResource> {
        return Optional.empty();
    }

    override fun mapData(property: DigitalTwinStateProperty<*>?): Optional<MutableList<RdfUnSubjectedTriple>> {
        return if (property?.key == SpamPhysicalAdapter.TEMPERATURE_PROPERTY) {
            Optional.of(
                mutableListOf(
                    RdfUnSubjectedTriple(
                        RdfProperty(
                            URI.create("http://example.org/ontology#hasTemperature")
                        ),
                        RdfLiteral(property.value as Number),
                    )
                )
            )
        } else {
            Optional.empty()
        }
    }

    override fun mapData(relationship: DigitalTwinStateRelationshipInstance<*>?): Optional<MutableList<RdfUnSubjectedTriple>> {
        return Optional.empty();
    }

}
