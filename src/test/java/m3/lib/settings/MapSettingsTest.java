package m3.lib.settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapSettingsTest {

    @Test
    void getFirstPointId() {
        // given
        var mapId = 10L;

        // when
        var firstPointId = MapSettings.getFirstPointId(mapId);

        // then
        Assertions.assertEquals(163L, firstPointId);
    }

    @Test
    void getLastPointId() {
        // given
        var mapId = 10L;

        // when
        var lastPointId = MapSettings.getLastPointId(mapId);

        // then
        Assertions.assertEquals(180L, lastPointId);
    }
}