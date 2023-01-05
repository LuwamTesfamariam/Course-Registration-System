package registration.registrationsystem.service;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.domain.Course;


import registration.registrationsystem.repository.CourseRepository;
import registration.registrationsystem.service.dto.CourseDto;

import registration.registrationsystem.service.impl.CourseService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;



    @RunWith(SpringRunner.class)
    public class CourseServiceTest {



        @InjectMocks
        private CourseService courseService;

        @Mock
        ListMapper<Course, CourseDto> listMapper;

        @Mock
        private CourseRepository courseRepository;

        @BeforeEach
        void setUp() {
            initMocks(this);
        }

        @Test
        public void testFindAll() {
            Course course = Course.builder().build();
            when(courseRepository.findAll()).thenReturn(List.of(course));
            when(listMapper.mapListToDto(List.of(course), new CourseDto())).thenReturn(List.of());
            List<CourseDto> result = courseService.findAll();
            assertEquals(0, result.size());
        }
        @Test
        public void testFindById() {
            Course course = Course.builder().build();
            CourseDto courseDto = CourseDto
                    .builder()
                    .id(1)
                    .code("Sanad")
                    .name("sanad@miu.edu")
                    .description("assistant professor")
                    .build();

            when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
            when(listMapper.mapClassToDto(course, new CourseDto())).thenReturn(courseDto);
            CourseDto result = courseService.findById(1L);
            assertEquals(courseDto, result);

        }
        @Test
        public void saveTest() {
            Course course = Course.builder().id(1L).build();
            CourseDto courseDto = CourseDto.builder().id(1L).build();
            when(listMapper.mapClassFromDto(courseDto, new Course())).thenReturn(course);
            courseService.save(courseDto);
//            verify(courseRepository, times(1)).save(course);
        }

        @Test
        public void updateTest() {
            Course course = Course
                    .builder()
                    .id(1)
                    .code("Lerman")
                    .name("lerman@miu.edu")
                    .description("professor")
                    .build();
            CourseDto courseDto = CourseDto.builder()
                    .id(1)
                    .code("Sanad")
                    .name("sanad@miu.edu")
                    .description("assistant professor")
                    .build();

            when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
            courseService.update(courseDto);
            verify(courseRepository, times(1)).save(course);
            assertEquals(courseDto.getName(), course.getName());
        }

        @Test
        public void deleteTest() {
            doNothing().when(courseRepository).deleteById(1L);
            courseService.delete(1L);
            verify(courseRepository, times(1)).deleteById(1L);
        }
    }
//        @Test
//        public void whenValidCourseIdThenCourseShouldBeFound() {
//            Long id =12L;
//            CourseDto found = courseService.findById(id);
//
//            assertThat(found.getId())
//                    .isEqualTo(id);
//        assertThat(found.getName())
//                .isEqualTo("group a");
//        assertThat(found.getCode())
//                .isEqualTo("d2");
//        assertThat(found.getDescription())
//                .isEqualTo("the best group");





