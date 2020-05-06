package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTorpedoStorePrimary;
  private TorpedoStore mockTorpedoStoreSecondary;

  @BeforeEach
  public void init(){
    mockTorpedoStorePrimary = mock(TorpedoStore.class);
    mockTorpedoStoreSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTorpedoStorePrimary,mockTorpedoStoreSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTorpedoStorePrimary.isEmpty()).thenReturn(true);
    when(mockTorpedoStoreSecondary.isEmpty()).thenReturn(false);
    when(mockTorpedoStoreSecondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTorpedoStorePrimary, times(0)).fire(1);
    verify(mockTorpedoStoreSecondary, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTorpedoStorePrimary.isEmpty()).thenReturn(false);
    when(mockTorpedoStoreSecondary.isEmpty()).thenReturn(false);
    when(mockTorpedoStorePrimary.fire(1)).thenReturn(true);
    when(mockTorpedoStoreSecondary.fire(1)).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTorpedoStorePrimary, times(1)).fire(1);
    verify(mockTorpedoStoreSecondary, times(1)).fire(1);
    assertEquals(true, result);
  }

}
