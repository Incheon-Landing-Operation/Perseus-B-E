package com.example.perseus.domain.constellation.service;

import com.example.perseus.domain.constellation.entity.Constellation;
import com.example.perseus.domain.constellation.mapper.ConstellationMapper;
import com.example.perseus.domain.constellation.repository.ConstellationRepository;
import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.fact.service.FactService;
import com.example.perseus.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConstellationServiceTest {

    @Mock
    private ConstellationRepository constellationRepository;

    @Mock
    private FactService factService;

    @Mock
    private ConstellationMapper constellationMapper;

    @InjectMocks
    private ConstellationService constellationService;

    private LocalDateTime threshold;
    private User user;
    private Fact fact1;
    private Fact fact2;
    private Fact fact3;
    private Constellation constellation1;
    private Constellation constellation2;

    @BeforeEach
    void setUp() {
        threshold = LocalDateTime.now().minusDays(3);
        user = mock(User.class);

        // Create test facts
        fact1 = mock(Fact.class);
        fact2 = mock(Fact.class);
        fact3 = mock(Fact.class);

        when(fact1.getFactId()).thenReturn(1L);
        when(fact2.getFactId()).thenReturn(2L);
        when(fact3.getFactId()).thenReturn(3L);

        // Create test constellations
        constellation1 = mock(Constellation.class);
        constellation2 = mock(Constellation.class);
    }

    @Test
    @DisplayName("saveConstellation should filter out existing facts and save new constellations")
    void saveConstellation_ShouldFilterOutExistingFactsAndSaveNewConstellations() {
        // Given
        Set<Long> existingFactIds = new HashSet<>(Arrays.asList(1L));
        List<Fact> factsBeforeThreshold = Arrays.asList(fact1, fact2, fact3);

        when(constellationRepository.findAllFactIds()).thenReturn(existingFactIds);
        when(factService.findByCreatedAtBefore(any(LocalDateTime.class))).thenReturn(factsBeforeThreshold);
        when(constellationMapper.toConstellation(fact2)).thenReturn(constellation1);
        when(constellationMapper.toConstellation(fact3)).thenReturn(constellation2);

        // When
        constellationService.saveConstellation();

        // Then
        verify(constellationRepository).findAllFactIds();
        verify(factService).findByCreatedAtBefore(any(LocalDateTime.class));
        verify(constellationMapper, never()).toConstellation(fact1); // fact1 should be filtered out
        verify(constellationMapper).toConstellation(fact2);
        verify(constellationMapper).toConstellation(fact3);
        verify(constellationRepository).saveConstellationList(Arrays.asList(constellation1, constellation2));
    }

    @Test
    @DisplayName("saveConstellation should not save any constellations when all facts already exist")
    void saveConstellation_ShouldNotSaveAnyConstellations_WhenAllFactsAlreadyExist() {
        // Given
        Set<Long> existingFactIds = new HashSet<>(Arrays.asList(1L, 2L, 3L));
        List<Fact> factsBeforeThreshold = Arrays.asList(fact1, fact2, fact3);

        when(constellationRepository.findAllFactIds()).thenReturn(existingFactIds);
        when(factService.findByCreatedAtBefore(any(LocalDateTime.class))).thenReturn(factsBeforeThreshold);

        // When
        constellationService.saveConstellation();

        // Then
        verify(constellationRepository).findAllFactIds();
        verify(factService).findByCreatedAtBefore(any(LocalDateTime.class));
        verify(constellationMapper, never()).toConstellation(any(Fact.class));
        verify(constellationRepository).saveConstellationList(Collections.emptyList());
    }

    @Test
    @DisplayName("saveConstellation should save all constellations when no facts exist")
    void saveConstellation_ShouldSaveAllConstellations_WhenNoFactsExist() {
        // Given
        Set<Long> existingFactIds = new HashSet<>();
        List<Fact> factsBeforeThreshold = Arrays.asList(fact1, fact2, fact3);

        when(constellationRepository.findAllFactIds()).thenReturn(existingFactIds);
        when(factService.findByCreatedAtBefore(any(LocalDateTime.class))).thenReturn(factsBeforeThreshold);
        when(constellationMapper.toConstellation(fact1)).thenReturn(constellation1);
        when(constellationMapper.toConstellation(fact2)).thenReturn(constellation2);
        when(constellationMapper.toConstellation(fact3)).thenReturn(mock(Constellation.class));

        // When
        constellationService.saveConstellation();

        // Then
        verify(constellationRepository).findAllFactIds();
        verify(factService).findByCreatedAtBefore(any(LocalDateTime.class));
        verify(constellationMapper).toConstellation(fact1);
        verify(constellationMapper).toConstellation(fact2);
        verify(constellationMapper).toConstellation(fact3);
        verify(constellationRepository).saveConstellationList(anyList());
        verify(constellationRepository, times(1)).saveConstellationList(anyList());
    }
}
