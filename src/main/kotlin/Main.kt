package it.wldt

import io.github.webbasedwodt.adapter.WoDTDigitalAdapter
import io.github.webbasedwodt.adapter.WoDTDigitalAdapterConfiguration
import it.wldt.core.engine.DigitalTwin
import it.wldt.core.engine.DigitalTwinEngine
import it.wldt.core.model.ShadowingFunction

fun main() {

    val engine = DigitalTwinEngine()

    for ( i in 0..10) {
        engine.addDigitalTwin(createDigitalTwin("dt-$i"))
    }

    engine.startAll()
}

private fun createDigitalTwin(id: String?): DigitalTwin {
    val shadowing: ShadowingFunction = BasicShadowing()
    val dt = DigitalTwin(id, shadowing)

    dt.addPhysicalAdapter(SpamPhysicalAdapter("spam-adapter", 1000L))

    val config: WoDTDigitalAdapterConfiguration? = null
    //dt.addDigitalAdapter(WoDTDigitalAdapter("wodt-adapter", config))
    dt.addDigitalAdapter(ConsoleDigitalAdapter("console-adapter"))
    return dt
}



