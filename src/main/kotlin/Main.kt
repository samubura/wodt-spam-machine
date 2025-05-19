package it.wldt


import io.github.webbasedwodt.adapter.WoDTDigitalAdapter
import io.github.webbasedwodt.adapter.WoDTDigitalAdapterConfiguration
import io.github.webbasedwodt.model.dtd.DTVersion
import it.wldt.core.engine.DigitalTwin
import it.wldt.core.engine.DigitalTwinEngine
import it.wldt.core.model.ShadowingFunction
import java.net.URI
import kotlin.random.Random


fun main(args: Array<String>) {

    val dtNumber = args.getOrNull(0)?.toIntOrNull() ?: 10
    val engine = DigitalTwinEngine()

    for ( i in 1..dtNumber) {
        engine.addDigitalTwin(createDigitalTwin(i))
    }

    engine.startAll()
}

private fun createDigitalTwin(i: Int): DigitalTwin {
    val shadowing: ShadowingFunction = BasicShadowing()
    val dt = DigitalTwin("dt$i", shadowing)



    val rand = Random(1234)

    dt.addPhysicalAdapter(SpamPhysicalAdapter("spam-adapter", 1000L, rand.nextLong(0, 1000L)))

    val port = 3000 + i
    val config = WoDTDigitalAdapterConfiguration(
        URI.create("http://localhost:$port"),
        DTVersion(1,0,0),
        BasicTemperatureSemantics(),
        port,
        "temp-sensor-$i",
        setOf(URI.create("http://localhost:4567")) // this has to match with the platform
    )

    dt.addDigitalAdapter(WoDTDigitalAdapter("wodt-adapter-$i", config))
    //dt.addDigitalAdapter(ConsoleDigitalAdapter("console-adapter"))
    return dt
}




