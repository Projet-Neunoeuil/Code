package fr.unilim.iut.aqualala

import fr.unilim.iut.aqualala.modele.Temperature
import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureTest {
    @Test
    fun temperatureDepasseLeMaximum28DegresCelcius() {
        var temperature: Temperature = Temperature(30.0,"2020-01-12T15:57")
        assertEquals("La température dépasse le maximum de 28 degrés",
            !temperature.estDansLaLimite, false)
    }

    @Test
    fun temperatureDepasseLeMinimum22DegresCelcius() {
        var temperature: Temperature = Temperature(0.0,"2020-01-12T15:57")
        assertEquals("La température dépasse le minnimum de 22 degrés",
            !temperature.estDansLaLimite, false)
    }

    @Test
    fun temperatureNeDepassePassePasLesLimitesDesDegres() {
        var temperature: Temperature = Temperature(25.0,"2020-01-12T15:57")
        assertEquals("La température ne dépasse pas les limites",
            temperature.estDansLaLimite, true)
    }
}