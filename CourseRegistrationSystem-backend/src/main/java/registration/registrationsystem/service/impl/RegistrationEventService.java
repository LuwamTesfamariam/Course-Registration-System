package registration.registrationsystem.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registration.registrationsystem.Integration.Sender;
import registration.registrationsystem.Util.RegistrationSystemUtility;
import registration.registrationsystem.domain.*;
import registration.registrationsystem.repository.*;
import registration.registrationsystem.service.IRegistrationEventService;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.service.dto.RegistrationEventDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationEventService implements IRegistrationEventService {

    @Autowired
    ListMapper<RegistrationEvent, RegistrationEventDto> listMapper;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Autowired
    RegistrationEventRepository registrationEventRepository;

    @Autowired
     RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    Sender sender;

    @Override
    public RegistrationEventDto getLatestRegistrationEvent() {
        RegistrationEvent latest = registrationEventRepository.findFirstByOrderByStartDateTimeDesc();
        RegistrationEventDto latestDto = listMapper.mapClassToDto(latest, new RegistrationEventDto());
        latestDto.setStatus(RegistrationSystemUtility.getEventStatus(latest));
        return latestDto;
    }

    public  void updateRequestProcessed(RegistrationRequest request) {
        registrationRequestRepository.save(request);
    }

//    @Override
//    public String processRegistrations(long id) {
//        RegistrationEvent registrationEvent = registrationEventRepository.findById(id).get();
//        String status = RegistrationSystemUtility.getEventStatus(registrationEvent);
//        if (status.equalsIgnoreCase("closed")) {
//            boolean registrationProcessed = false;
//            List<RegistrationGroup> groups = registrationEvent.getRegistrationGroups();
//            for (RegistrationGroup group : groups) {
//                System.out.println("groups" + group);
//                if(group != null){
//                    List<AcademicBlock> blocks = group.getAcademicBlocks();
//                    for (AcademicBlock block : blocks) {
//                        System.out.println("block" + blocks);
//                        if(block != null){
//                            List<CourseOffering> coursesOfferedInABlock = block.getCourseOfferings();
//                            for (Student student : group.getStudents()) {
//                                System.out.println("student" + student);
//                                if(student !=null){
//                                    Registration registration = RegistrationSystemUtility.convertToRegistration(student.getRegistrationRequests(), coursesOfferedInABlock);
//                                    System.out.println("registration" + registration);
//                                    if (registration != null) {
//                                        registrationRepository.save(registration);
//                                        CourseOffering courseOffering = registration.getCourseOffering();
//                                        int size = registrationRepository.findAll().size();
//                                        courseOffering.computeAvailableSeat(size);
//                                        courseOfferingRepository.updateAvailableSeats(courseOffering.getAvailableSeats());
//                                        registrationProcessed = true;
//                                    }
//                                }else{
//                                    System.out.println("student is null");
//                                }
//                            }
//                        }else {
//                            System.out.println("block is null");
//                        }
//                    }
//                }
//                else{
//                    System.out.println("group is null");
//                }
//            }
//            if (registrationProcessed) {
//                return "Registration processed successfully!";
//            }
//            return "Registration not processed!";
//        }
//        return "Event is not closed";
//    }

    @Override
    public String processRegistrations(long id) {
        RegistrationEvent registrationEvent = registrationEventRepository.findById(id).get();
        String status = RegistrationSystemUtility.getEventStatus(registrationEvent);
        if (status.equalsIgnoreCase("closed")) {
            boolean registrationProcessed = false;
            List<RegistrationGroup> groups = registrationEvent.getRegistrationGroups();
            for (RegistrationGroup group : groups) {
                System.out.println("groups" + group);
                    List<AcademicBlock> blocks = group.getAcademicBlocks();
                    for (AcademicBlock block : blocks) {
                        System.out.println("block" + blocks);
                            List<CourseOffering> coursesOfferedInABlock = block.getCourseOfferings();
                            for (Student student : group.getStudents()) {
                                System.out.println("student: " + student.getRegistrationRequests());
                                    Registration registration = RegistrationSystemUtility.convertToRegistration(student.getRegistrationRequests(), coursesOfferedInABlock);
                                    System.out.println("registration" + registration);
                                    if (registration != null) {
                                        registrationRepository.save(registration);
                                        CourseOffering courseOffering = registration.getCourseOffering();
                                        int size = registrationRepository.findAll().size();
                                        courseOffering.computeAvailableSeat(size);
                                        courseOfferingRepository.updateAvailableSeats(courseOffering.getAvailableSeats());
                                        registrationProcessed = true;
                                    }
                                }
                            }
            }
            if (registrationProcessed) {
                return "Registration processed successfully!";
            }
            return "Registration not processed!";
        }
        return "Event is not closed";
    }

    @Override
    public void save(RegistrationEventDto eventDto) {
        registrationEventRepository.save(listMapper.mapClassFromDto(eventDto, new RegistrationEvent()));
    }

    @Override
    public void sendEmail(long id) {
        Optional<RegistrationEvent> optional = registrationEventRepository.findById(id);
        if (optional.isPresent()) {
            RegistrationEvent registrationEvent = optional.get();
            List<RegistrationGroup> groups = registrationEvent.getRegistrationGroups();
            List<String> emails = new ArrayList<>();
            List<String> names = new ArrayList<>();
            groups.forEach(g -> g.getStudents().forEach(student -> {
                emails.add(student.getEmail());
                names.add(student.getName());
            }));
            System.out.println(names + " " + emails);
            EmailInfo emailMessage = new EmailInfo(emails, names, registrationEvent.getStartDateTime().toString(), registrationEvent.getEndDateTime().toString());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String eventString = objectMapper.writeValueAsString(emailMessage);
                sender.send("topicA", eventString);
                System.out.println("message sending");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            System.out.println("event not found");
    }

    @Override
    public void update(RegistrationEventDto eventDto) {
        RegistrationEvent eventToBeUpdated = registrationEventRepository.findById(eventDto.getId()).get();
        eventToBeUpdated.setEndDateTime(eventDto.getEndDateTime());
        eventToBeUpdated.setStartDateTime(eventDto.getStartDateTime());
        registrationEventRepository.save(eventToBeUpdated);
    }

    @Override
    public List<RegistrationEventDto> findAll() {
        List<RegistrationEvent> registrationEvents = registrationEventRepository.findAll();
        List<RegistrationEventDto> eventDtos = new ArrayList<>();
        for (RegistrationEvent registrationEvent : registrationEvents) {
            RegistrationEventDto dto = listMapper.mapClassToDto(registrationEvent, new RegistrationEventDto());
            dto.setStatus(RegistrationSystemUtility.getEventStatus(registrationEvent));
            eventDtos.add(dto);
        }
        return eventDtos;
    }

    @Override
    public RegistrationEventDto findById(long id) {
        return listMapper.mapClassToDto(registrationEventRepository.findById(id).get(), new RegistrationEventDto());
    }

    @Override
    public List<RegistrationEvent> getRegistrationEventByStudentId(long studentId) {
        List<RegistrationEvent> events = registrationEventRepository.getRegistrationEvenByStudentId(studentId);
        return events;
    }

}
