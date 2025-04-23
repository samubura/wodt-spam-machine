package it.wldt

import io.github.webbasedwodt.adapter.WoDTDigitalAdapter
import io.github.webbasedwodt.adapter.WoDTDigitalAdapterConfiguration
import io.github.webbasedwodt.model.dtd.DTVersion
import it.wldt.core.engine.DigitalTwin
import it.wldt.core.engine.DigitalTwinEngine
import it.wldt.core.model.ShadowingFunction
import java.net.URI

fun main() {

    val engine = DigitalTwinEngine()

    for ( i in 0..10) {
        engine.addDigitalTwin(createDigitalTwin(i))
    }

    engine.startAll()
}

private fun createDigitalTwin(i: Int): DigitalTwin {
    val shadowing: ShadowingFunction = BasicShadowing()
    val dt = DigitalTwin("dt$i", shadowing)


    dt.addPhysicalAdapter(SpamPhysicalAdapter("spam-adapter", 1000L, 0L))

    val port = 3000 + i
    val config = WoDTDigitalAdapterConfiguration(
        URI.create("http://localhost:$port"),
        DTVersion(1,0,0),
        BasicTemperatureSemantics(),
        port,
        "temp-sensor-$i",
        setOf(URI.create("http://localhost:4567")) // TODO this has to match with the platform
    )

    dt.addDigitalAdapter(WoDTDigitalAdapter("wodt-adapter", config))
    //dt.addDigitalAdapter(ConsoleDigitalAdapter("console-adapter"))
    return dt
}




