package com.binance.api.client.domain.account;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class NewOrderResponseTest {

  private NewOrderResponse response;
  private Trade someTrade;

  @Before
  public void setUp() {
    response = new NewOrderResponse();

    someTrade = new Trade();
    someTrade.setId(123L);
  }

  @Test
  public void shouldDefaultToNoFills() {
    assertThat(response.getFills().size(), is(0));
  }

  @Test
  public void shouldClearAnyFillsIfSetToZero() {
    // Given:
    response.setFills(trades(someTrade));

    // When:
    response.setFills(null);

    // Then:
    assertThat(response.getFills().size(), is(0));
  }

  @Test
  public void shouldHandleToStringWithNullFills() {
    assertThat(response.toString(), containsString(", fills=[]"));
  }

  @Test
  public void shouldHandleToStringWithNoFills() {
    // Given:
    response.setFills(Collections.emptyList());

    // Then:
    assertThat(response.toString(), containsString(", fills=[]"));
  }

  @Test
  public void shouldHandleToStringWithFills() {
    // Given:
    response.setFills(trades(someTrade));

    // Then:
    assertThat(response.toString(), containsString(", fills=[Trade[id=123,"));
  }

  private static List<Trade> trades(final Trade... trades) {
    return Arrays.asList(trades);
  }
}