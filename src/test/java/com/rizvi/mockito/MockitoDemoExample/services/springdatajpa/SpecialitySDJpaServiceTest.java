package com.rizvi.mockito.MockitoDemoExample.services.springdatajpa;

import com.rizvi.mockito.MockitoDemoExample.model.Speciality;
import com.rizvi.mockito.MockitoDemoExample.repositories.SpecialtyRepository;
import com.rizvi.mockito.MockitoDemoExample.services.SpecialtyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject(){
        // Given
        Speciality speciality = new Speciality();
        // when
        service.delete(speciality);
        // then
        then(specialtyRepository).should().delete(any(Speciality.class));

    }

    @Test
    void findByIdTest() {
         // Given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
         // whem
        Speciality foundSpeciality = service.findById(1L);
        // then
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }


    @Test
    void deleteById() {
        // Given = none
        //when
        service.deleteById(1l);
        service.deleteById(1l);
        //then
        then(specialtyRepository).should(times(2)).deleteById(1L);

    }

    @Test
    void deleteByIdAtLeast(){
        // given = none

        // when
        service.deleteById(1l);
        service.deleteById(1l);
        // then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);

    }

    @Test
    void deleteByIdAtMost(){
        //given = none

        // when
        service.deleteById(1l);
        service.deleteById(1l);

        //then
        then(specialtyRepository).should(atMost(5)).deleteById(1l);

    }
    @Test
    void deleteByIdAtNever(){
        // given = none

        // when
        service.deleteById(1l);
        service.deleteById(1l);

        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);
        then(specialtyRepository).should(never()).deleteById(5L);

    }

    @Test
    void testDelete() {
        // given = none

        //when
        service.delete(new Speciality());
        //then
        then(specialtyRepository).should().delete(any());
    }
}