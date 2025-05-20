package de.berlin.gd.kantine.domain.ereignis;

import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EreignisServiceUnitTest {

  @Autowired
  private EreignisService ereignisService;

  @MockBean
  private KantinenEreignis kantinenEreignis;

  @Test
  void shouldReceiveEvent() {
    try {
      ereignisService.empfangeEreignis(kantinenEreignis);
    } catch (Exception ex) {

    }

    verify(kantinenEreignis, times(2)).getEreignisTyp();

  }
}