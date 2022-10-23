package com.rizvi.mockito.MockitoDemoExample.services.springdatajpa;

import com.rizvi.mockito.MockitoDemoExample.model.Speciality;
import com.rizvi.mockito.MockitoDemoExample.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock(lenient = true)
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
    void testDeleteById() {
        //given = none

        // when
        service.deleteById(1L);
        // then
        then(specialtyRepository).should().deleteById(1L);
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
        then(specialtyRepository).should(timeout(200)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }


    @Test
    void deleteById() {
        // Given = none
        //when
        service.deleteById(1l);
        service.deleteById(1l);
        //then
        then(specialtyRepository).should(timeout(1000).times(2)).deleteById(1L);

    }

    @Test
    void deleteByIdAtLeast(){
        // given = none

        // when
        service.deleteById(1l);
        service.deleteById(1l);
        // then
        then(specialtyRepository).should(timeout(1000).atLeastOnce()).deleteById(1l);

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
        then(specialtyRepository).should(timeout(500).atLeastOnce()).deleteById(1l);
        then(specialtyRepository).should(never()).deleteById(5L);

    }

    @Test
    void testDelete() {
        // given = none

        //when
        service.delete(new Speciality());
        //then
        then(specialtyRepository).should(timeout(100)).delete(any());
    }

    @Test
    void testDoThrow(){
      doThrow(new RuntimeException("Boom")).when(specialtyRepository).delete(any());

      assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

      verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

        assertThrows(RuntimeException.class, ()->service.findById(1L));

        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBDD() {

        willThrow(new RuntimeException("Boom")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, ()->specialtyRepository.delete(new Speciality()));
    }

    @Test
    void testSaveLambda() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);

        // need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

        //when
        Speciality returnedSpeciality = service.save(speciality);

        //then
        assertThat(returnedSpeciality.getId()).isEqualTo(1L);
    }


    @Test
    void testSaveLambdaNoMatch() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not a Match");

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);

        // need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);


        //when
        Speciality returnedSpeciality = service.save(speciality);

        //then
        assertNull(returnedSpeciality);

    }



}