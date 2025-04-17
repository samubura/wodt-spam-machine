package it.wldt

import it.wldt.adapter.physical.PhysicalAdapter
import it.wldt.adapter.physical.PhysicalAssetDescription
import it.wldt.adapter.physical.PhysicalAssetProperty
import it.wldt.adapter.physical.event.PhysicalAssetActionWldtEvent
import it.wldt.adapter.physical.event.PhysicalAssetPropertyWldtEvent
import kotlinx.coroutines.*

class SpamPhysicalAdapter(id: String?, val delay: Long = DELAY_TIME, val coldStart: Long = COLD_START) : PhysicalAdapter(id) {

    companion object {
        const val TEMPERATURE_PROPERTY = "temperature";
        private const val DELAY_TIME = 1000L // 1 second
        private const val COLD_START = 10*1000L // 10 seconds
    }

    override fun onAdapterStart() {
        CoroutineScope(Dispatchers.Default).launch {
            val pad = PhysicalAssetDescription(
                listOf(),
                listOf(PhysicalAssetProperty(TEMPERATURE_PROPERTY, 0.0)),
                listOf()
            )
            delay(coldStart)
            notifyPhysicalAdapterBound(pad)
            launch {
                generateUpdates()
            }
        }
    }

    private suspend fun generateUpdates() {
        while (true) {
            delay(delay)
            val physicalAssetProperty =
                PhysicalAssetPropertyWldtEvent(TEMPERATURE_PROPERTY, computeNewTemperature())
            publishPhysicalAssetPropertyWldtEvent(physicalAssetProperty);
        }
    }

    private fun computeNewTemperature(): Number {
        return (Math.random() * 100)
    }

    override fun onAdapterStop() {

    }

    override fun onIncomingPhysicalAction(actionEvent: PhysicalAssetActionWldtEvent<*>?) {
        //no actions
    }

}